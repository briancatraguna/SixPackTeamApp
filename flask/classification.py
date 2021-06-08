import numpy as np
import tensorflow_hub as hub
from flask import Flask, request, jsonify
from tensorflow.keras.models import load_model

app = Flask(__name__)

model = load_model('classification.h5', custom_objects={'KerasLayer':hub.KerasLayer})

def classify(sentence):
  '''
    Args:
      Input : string; raw sentence.
      Output: string; category
              list of categories:
              crime | medical | fire | natural disaster | traffice accident
  '''

  # get predictions number
  predictions = model.predict([sentence])[0]

  # get classification based on the index
  classification = np.where(predictions == np.max(sentence))[0][0]
  switcher = {0: "Crime",
              1: "Medical",
              2: "Fire",
              3: "Natural Disaster",
              4: "Traffic Accident"
              }

  result = switcher.get(classification, "Nothing")
  return {'label': result}

@app.route("/")
def classification():
    inputString = request.args.get('input')
    classOutput = classify(inputString)
    return jsonify(classOutput)


if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=int(os.environ.get("PORT", 8080)))
