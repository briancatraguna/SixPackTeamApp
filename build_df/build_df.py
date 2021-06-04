import os
import re
import json
import nltk
import pandas as pd
from nltk.tokenize import word_tokenize
from sklearn.model_selection import train_test_split

BASE_PATH = 'data/'
TRAIN_PATH = BASE_PATH + 'train/'
TEST_PATH = BASE_PATH + 'test/'

if os.path.exists(TRAIN_PATH) or os.path.exists(TEST_PATH) == False:
    os.makedirs(TRAIN_PATH)
    os.makedirs(TEST_PATH)
    
def _casefold(sentence):
    '''
    Args:
      Input : raw sentence.
      Output: lower case sentence.
    '''
    return str(sentence).lower()
  
def _normalize(sentence):
    '''
    Turn sentence into its normal form, as long it's on the dictionary. :)
    Args:
      Input : lowercase sentence.
      Output: normalized sentence. 
    '''
    # replace duplicate chars, e.g okeee to oke
    sentence = re.sub(r'(\w)\1{2,}', r'\1\1', sentence)
    words = word_tokenize(sentence)

    with open('slangwords.json', 'r') as f:
        file = json.load(f)
        slang = {value:key for value, key in file.items()}

    normal = [slang.get(word, word) for word in words]
    return ' '.join(normal)
  
def preprocess(sentence):
    '''
    Preprocessing the sentence.
    Args:
      Input : raw sentence.
      Output: preprocessed sentence.
    '''
    sentence = _casefold(sentence)
    sentence = _normalize(sentence)
    return sentence
  
def preprocessingDF(df):
    '''
    Args:
      Input : raw dataframe, results of lapor_scraping
              query | report | institute | category | label
      Output: dataframe, preprocessed string on `report` column
    '''
    reports = df.report.tolist()
    preprocessed = [preprocess(report) for report in reports]
    df['report'] = preprocessed
    df.to_csv(os.path.join(BASE_PATH, 'preprocessed.csv'), index=False)
    return df
    
def splitData(preprocessedData):
    '''
    Split dataframe into train and test set.
    Args:
      Input : preprocessed data.
              query | report | institute | category | label
      Output: tuple; train and test dataframe
    '''
    x = preprocessedData.copy()
    x = preprocessedData.drop(['query', 'institute', 'category'], axis=1)
    y = x.pop('label')

    x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.2, random_state=42)
    train = x_train.join(y_train)
    test = x_test.join(y_test)

    return train, test

def classificationDF(df):
    '''
    Args:
      Input : splitted dataframe.
              report | label
      Output: classification dataframe.
              report | label
    '''
    train = df[0]
    test = df[1]

    train.to_csv(os.path.join(TRAIN_PATH, 'train.csv'), index=False)
    test.to_csv(os.path.join(TEST_PATH, 'test.csv'), index=False)
    
def tokenDF(df):
    '''
    Args:
      Input : splitted dataframe.
              query | report | institute | category | label
      Output: tokenized, for named entity recognition.
               report# | token
                   --- | ---
                 1     | sentence1_token1
                 1     | sentence1_token2
                 2     | sentence2_token1
    '''
    nltk.download('punkt')
    # TRAIN DATA
    train = df[0]
    train.insert(0, 'report#', pd.factorize(train.report)[0]+1) # add new column for report number
    train.drop('label', axis=1, inplace=True)
    train.rename(columns={'report':'token'}, inplace=True) # rename `report` to `token`
    train.token = [word_tokenize(word) for word in train.token]
    train = train.explode('token')
    train.to_csv(os.path.join(TRAIN_PATH, 'train_token.csv'), index=False)
    # TEST DATA
    test = df[1]
    test.insert(0, 'report#', pd.factorize(test.report)[0]+1) # add new column for report number
    test.drop('label', axis=1, inplace=True)
    test.rename(columns={'report':'token'}, inplace=True) # rename `report` to `token`
    test.token = [word_tokenize(word) for word in test.token]
    test = test.explode('token')
    test.to_csv(os.path.join(TEST_PATH, 'test_token.csv'), index=False)

def factorize(preprocessedData):
    '''
    Args:
      Change label from string to (integer)
      Input : preprocessed dataframe.
              query | report | institute | category | label (string)
      Output: factorized dataframe.
              query | report | institute | category | label (integer)
    '''
    preprocessedData.label = pd.factorize(preprocessedData.label)[0]
    
def main():
    df = pd.read_csv('filtered.csv')
    preprocessedDF = preprocessingDF(df)
    
    # Dataframe for NER
    # splitDF = splitData(preprocessedDF)
    # classificationDF(splitDF)
    # tokenDF(splitDF)
    
    # Dataframe for classification
    factorizedDF = factorize(preprocessedDF)
    splitDF = splitData(factorizedDF)
    classificationDF(splitDF)
    
main()
