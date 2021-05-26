import re
import json
import pandas as pd

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
    # remove \n and remove space
    words = sentence.strip('\n').split()
    
    with open('slangwords.json', 'r') as f:
        file = json.load(f)
        slang = {value:key for value, key in file.items()}
    
    normal = [slang.get(word, word) for word in sentence.split()]
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
  
def to_DF1(df):
    '''
    Args:
      Input : raw dataframe, results of lapor_scraping
              query | report | institute | category
      Output: dataframe, preprocessed string on `report` column
    '''
    reports = df.report.tolist()
    preprocessed = [preprocess(report) for report in reports]
    df['report'] = preprocessed
    df.to_csv('data/df1.csv', index=False)
    return df
  
def to_DF2(df):
    '''
    Args:
      Input : DF1, preprocessed dataframe.
              query | report | institute | category
      Output: tokenized report from DF1.
              index | report
                --- | ---
              1     | sentence1_token1
              1     | sentence1_token2
              2     | sentence2_token1
    '''
    df2 = df.copy()
    df2.drop(['query', 'institute', 'category', 'label'], axis=1, inplace=True)
    df2.insert(0, 'report_num', pd.factorize(df['report'])[0]+1)
    df2.report = [r.split() for r in df.report]
    df2 = df2.explode('report')
    df2.to_csv('data/df2.csv', index=False)
    return df2
  
def main():
    df = pd.read_csv('data/lapor_scraping_results.csv')
    df1 = to_DF1(df)
    to_DF2(df1)
    
main()
