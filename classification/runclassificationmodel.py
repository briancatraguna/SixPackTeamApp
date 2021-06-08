import tensorflow as tf
import pandas as pd
import numpy as np

# get model path (ganti ini yak wkwk)
MODEL_PATH = '/content/content/classification_keras'

# Load Model
  model = tf.keras.models.load_model(MODEL_PATH)

def classification(report):
  '''
    Args:
      Input : string; raw sentence.
      Output: string; category
              list of categories:
              crime | medical | fire | natural disaster | traffice accident
  '''

  # get predictions number
  predictions = model.predict([report])[0]

  # get classification based on the index
  classification = np.where(predictions == np.max(predictions))[0][0]
  switcher = {0: "crime",
              1: "medical",
              2: "fire",
              3: "natural disaster",
              4: "traffic accident"
             }
  return switcher.get(classification, "nothing")

# Driver function
if __name__ == "__main__":
    argument = 'pohon tumbang di jalan dr.rum no 14 , perlu penanganan segera'
    category = classification(argument)
    print(category)
