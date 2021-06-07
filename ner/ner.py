import glob
import json
import nltk
import os
import numpy as np
import pandas as pd
import tensorflow as tf
import tensorflow_hub as hub
from tensorflow import keras
from contextlib import redirect_stdout
from nltk.tokenize import word_tokenize
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.utils import plot_model

# download punctuation from nltk
nltk.download('punkt')

class Data:
    def __init__(self, trainData, testData):
        '''
        Initialize class attributes.
        Args: 
            trainData & testData: csv files.
        '''
        self.trainData = pd.read_csv(trainData)[:44527]
        self.testData = pd.read_csv(testData)[:2132]
        self.token = self.trainData.token
        self.tag = self.trainData.tag
        self.tagList = list(self.tag.unique())
        self.tagIndex = self.getTagIndex()
        self.X = self.getX()
        self.Y = self.getY()

    def getX(self):
        '''
        Return list of tokens of train data.
        '''
        self.X = list(self.token)
        return self.X

    def getY(self):
        '''
        Return list of indexed tag of train data
        '''
        self.tagIndex = {tag: index for index, tag in enumerate(self.tagList)}
        self.Y = [self.tagIndex[l] for l in self.tag]
        return self.Y
    
    def getTagIndex(self):
        '''
        Return a dictionary of tags and its indexes.
        '''
        self.tagIndex = {tag: index for index, tag in enumerate(self.tagList)}
        return self.tagIndex

    def getInfo(self):
        '''
        Return information about train dataset.
        '''
        self.nTags = len(self.tagList)
        self.lenX = len(self.X)
        self.lenY = len(self.Y)
        return 'Total label: {}\nLength of X: {}\nLength of y: {}'.format(self.nTags,
                                                                          self.lenX,
                                                                          self.lenY)

class Model:
    def __init__(self, tag):
        '''
        Initialize class.
        Args:
            tag: dictionary of the tag/label
        '''
        self.tag = tag  # tag dictionary

        self.MODEL_PATH = 'model/'
        self.embedding = "https://tfhub.dev/google/nnlm-id-dim50-with-normalization/2"
        self.optimizer ="rmsprop"
        self.loss = "sparse_categorical_crossentropy"
        self.metrics = ["accuracy"]
        
        self.has_trained = False

    def train(self, X, Y, epochs, verbose):
        '''
        A method to train the model.
        Args:
            X: list of tokens.
            Y: list of tags.
            epochs: number of epoch.
            verbose: number of verbose.
        '''
        self.has_trained = True

        self.X = X
        self.Y = Y
        self.nOutput = len(set(self.Y)) # buat train

        self.epochs = epochs
        self.verbose = verbose
        self.model = Sequential([hub.KerasLayer(self.embedding,
                                                input_shape=[],
                                                dtype=tf.string,
                                                trainable=True),
                                  Dense(128, activation='relu'),
                                  Dense(64, activation='relu'),
                                  Dense(self.nOutput, activation='softmax')
                                  ])
        self.model.compile(optimizer=self.optimizer, loss=self.loss, metrics=self.metrics)
        self.model.fit(self.X, self.Y, self.epochs, self.verbose)

    def save(self):
        '''
        Save the trained model into each folder.
        Contain its configuration, architecture summary, weights, and model plot.
        '''
        def getLastIndex(self):
            folder = [f for f in glob.iglob(self.MODEL_PATH+'run_*')]
            return len(folder)
        
        if self.has_trained == True:
            self.processID = getLastIndex(self)+1
            self.workingDir = self.MODEL_PATH+'run_'+str(self.processID)
            os.makedirs(self.workingDir)

            with open(os.path.join(self.workingDir, 'model_config.json'), 'w') as fw : 
                json.dump(self.model.get_config(), fw)

            with open(os.path.join(self.workingDir, 'architecture.txt'),'w+') as f:
                with redirect_stdout(f):
                    self.model.summary()

            plot_model(self.model, to_file=os.path.join(self.workingDir, 'model.png'), show_shapes=True, show_layer_names=True)
            self.model.save_weights(os.path.join(self.workingDir, 'model.h5'))
        else:
            print('Train model first.')
    
    def loadModel(self, processID):
        '''
        Load model from trained model folder.
        Args:
          processID: the number of process you want to load. see `model/run_*`
        '''
        self.path = self.MODEL_PATH +'run_'+str(processID)

        with open(os.path.join(self.path, 'model_config.json'), 'r') as json_file:
            json_file = json.load(json_file)
            
        self.model = Sequential.from_config(json_file, custom_objects={'KerasLayer':hub.KerasLayer})
        self.model.load_weights(os.path.join(self.path, 'model.h5'))
        print('Model loaded.')

    def test(self, data):
        '''
        Test the model with test set.
        Args:
            testData: csv file of test dataset.
            tag     : tag index.
        '''
        self.data = data
        
        if type(self.data) is pd.DataFrame:
            self.word = list(self.data.token)
        elif type(self.data) is str:
            self.word = word_tokenize(self.data)

        self.labelOutput = {index: tag for index, tag in enumerate(self.tag)}
        for i in range(len(self.word)):
            pred = self.model.predict(self.word)[i]
            labelIndex = np.argmax(pred)
            print('{:20} {}'.format(self.word[i], self.labelOutput.get(labelIndex)))

if __name__ == '__main__': 
    load = Data('train.csv', 'test.csv')
    x, y = load.X, load.Y
    testData = load.testData
    tag = load.getTagIndex()
    
    model = Model(tag)
    model.save()
    model.loadModel(1)
    model.test(testData)
