import numpy as np
import tensorflow_hub as hub
from flask import Flask, request, jsonify
from tensorflow.keras.models import load_model

app = Flask(__name__)

model = load_model('classification.h5', custom_objects={'KerasLayer':hub.KerasLayer})

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
    words = sentence.split()

    label = []
    for i in range(len(words)):
        prediction = model.predict(words)[i]
        labelIndex = np.argmax(prediction)
        label.append(labelDict.get(labelIndex))
    return list(zip(words, label))

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

@app.route("/")
def ner():
    inputString = request.args.get('input')
    predOutput = pred(inputString)
    entities = getEntity(predOutput)
    return jsonify(entities)


if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=int(os.environ.get("PORT", 8080)))
