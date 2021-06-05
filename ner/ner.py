import numpy as np
import pandas as pd
import os
import glob
import json
import tensorflow as tf
import tensorflow_hub as hub
from contextlib import redirect_stdout
from tensorflow import keras
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.utils import plot_model

class Data:
    def __init__(self, trainData, testData):
        self.trainData = pd.read_csv(trainData)[:21200].append(pd.read_csv(trainData)[26485:32445])
        self.testData = pd.read_csv(testData)[:2000]
        self.token = self.trainData.token
        self.label = self.trainData.tag
        self.tagList = list(self.label.unique())
        self.X = self.getX()
        self.Y = self.getY()

    def getX(self):
        self.X = list(self.token)
        return self.X

    def getY(self):
        tag2idx = {tag: index for index, tag in enumerate(self.tagList)}
        self.Y = [tag2idx[l] for l in self.label]
        return self.Y

    def getInfo(self):
        self.nTags = len(self.tagList)
        self.lenX = len(self.X)
        self.lenY = len(self.Y)
        return 'Total label: {}\nLength of X: {}\nLength of y: {}'.format(self.nTags,
                                                                          self.lenX,
                                                                          self.lenY)

class Model:
    def __init__(self, X, Y):
        self.MODEL_PATH = 'model/'
        self.embedding = "https://tfhub.dev/google/nnlm-id-dim50-with-normalization/2"
        self.optimizer ="rmsprop"
        self.loss = "sparse_categorical_crossentropy"
        self.metrics = ["accuracy"]
        self.model = self.layer()
        self.X = X
        self.Y = Y

        self.has_trained = False

    def layer(self):
        self.output = len(d.tagList)
        self.model = Sequential([hub.KerasLayer(self.embedding,
                                                input_shape=[],
                                                dtype=tf.string,
                                                trainable=True),
                                Dense(128, activation='relu'),
                                # Dense(64, activation='relu'),
                                Dense(self.output, activation='softmax')
                                ])
        return self.model

    def train(self, epochs, verbose):
        self.has_trained = True
        self.epochs = epochs
        self.verbose = verbose
        self.model.compile(optimizer=self.optimizer, loss=self.loss, metrics=self.metrics)
        return self.model.fit(self.X, self.Y, self.epochs, self.verbose)

    def save(self):
        def getLastIndex(self):
            folder = [f for f in glob.iglob(self.MODEL_PATH+'run_*')]
            return len(folder)

        self.processID = getLastIndex(self)+1
        self.workingDir = self.MODEL_PATH+'run_'+str(self.processID)
        os.makedirs(self.workingDir)

        with open(os.path.join(self.workingDir, 'model_config.json'), 'w') as fw : 
            json.dump(self.layer().get_config(), fw)

        with open(os.path.join(self.workingDir, 'architecture.txt'),'w+') as f:
            with redirect_stdout(f):
                self.model.summary()

        plot_model(self.model, to_file=os.path.join(self.workingDir, 'model.png'), show_shapes=True, show_layer_names=True)
        self.model.save_weights(os.path.join(self.workingDir, 'model.h5'))
    
    def load(self, processID):
        self.path = self.MODEL_PATH +'run_'+str(self.processID)
        with open(os.path.join(self.path, 'model_config.json'), 'r') as json_file:
            json_file = json.load(json_file)
        self.model = Sequential.from_config((json_file))
        self.model.load_weights(os.path.join(self.path, 'model_weight.h5'))
        return self.model

    def test(self):
        pass


if __name__ == '__main__': 
    load = Data('train.csv', 'test.csv')
    x, y = load.X, load.Y
    model = Model(x, y)
