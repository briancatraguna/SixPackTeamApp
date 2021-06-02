import pandas as pd
import numpy as np

def load_data(trainData, testData):
    # we haven't finished labelling sooo..
    t1 = pd.read_csv(trainData)[:21200]
    t2 = pd.read_csv(trainData)[26485:32445]

    train = t1.append(t2)
    test = pd.read_csv(testData)[:2000] # we use smaller data

    # return train, test

    token = train.token
    tag = train.tag

    # encode label
    tagList = list(train.tag.unique())
    tag2idx = {tag: index for index, tag in enumerate(tagList)}
    tagIndexed = [tag2idx[tag] for tag in train.tag]

    # list of token `['telah', 'terjadi', ...]` and list of tag `[1, 0, 2, 15, ...]`
    return list(token), tagIndexed


x, y = load_data('train_token.csv', 'test_token.csv')

import tensorflow as tf
from tensorflow import keras
from tensorflow.keras.models import Sequential
import tensorflow_hub as hub

embed = "https://tfhub.dev/google/nnlm-id-dim50-with-normalization/2"

model = tf.keras.Sequential()
model.add(hub.KerasLayer(embed, input_shape=[], dtype=tf.string, trainable=True))
model.add(tf.keras.layers.Dense(128, activation='relu'))
model.add(tf.keras.layers.Dense(64, activation='relu'))
model.add(tf.keras.layers.Dense(32, activation='relu'))
model.add(tf.keras.layers.Dense(nTag, activation='softmax'))

model.compile(optimizer="rmsprop", loss="sparse_categorical_crossentropy", metrics=["accuracy"])

history = model.fit(x, y, epochs=15, verbose=1)
model.summary()

tf.saved_model.save(model, 'savedmodel1')

def predict(testData, index):
    pred = model.predict(testData)[index] # output array
    label = np.argmax(pred) # output index label
    return testData[index], label


for i in range(len(test.token)):
    token, label = predict(test.token, i)
    print('{:20} {}'.format(token, label))
