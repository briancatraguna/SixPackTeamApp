import numpy as np
import tensorflow_hub as hub
from flask import Flask, request
from flask_restful import Api
from tensorflow.keras.models import load_model

app = Flask(__name__)
api = Api(app)

model = load_model('model.h5', custom_objects={'KerasLayer':hub.KerasLayer})

# get input from args
@app.route('/')
def getInput():
    return request.args.get('input')

# do prediction
def pred(sentence):
    """
    Get predictions from model.
    Args:
      sentence: a sentence.
    Output:
      list of tuple, consists of input and label.
    """
    labelDict = {1: 'B-DESC',
                 2: 'B-LOCATION',
                 3: 'I-LOCATION',
                 4: 'I-DESC',
                 5: 'B-WEAPON',
                 6: 'B-PERSON',
                 7: 'I-PERSON',
                 8: 'I-WEAPON',
                 0: 'O'
                 }
    input = sentence.split()

    label = []
    for i in range(len(input)):
        prediction = model.predict(input)[i]
        labelIndex = np.argmax(prediction)
        label.append(labelDict.get(labelIndex))
    return input, label

def getEntity(predOutput):
    entities = {'PERSON': [],
                'LOCATION': [],
                'DESCRIPTION': [],
                'WEAPON': []
                }

    for pred in predOutput:
        if pred[1].endswith('PERSON'):
            entities['PERSON'].append(pred[0])
        elif pred[1].endswith('LOCATION'):
            entities['LOCATION'].append(pred[0])
        elif pred[1].endswith('DESCRIPTION'):
            entities['DESCRIPTION'].append(pred[0])
        elif pred[1].endswith('WEAPON'):
            entities['WEAPON'].append(pred[0])
    return entities

entities = getEntity(label)
sentence = getInput()
predOutput = pred(sentence)

if __name__ == '__main__':
    app.run(debug=True)
