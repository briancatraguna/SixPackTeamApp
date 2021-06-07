## Named Entity Recognition

### Introduction
Here, we extract entities from user report/input.

### Entity
`PERSON`, `LOCATION`, `WEAPON`, `DESCRIPTION`

### Notation
BIO tagging.

### Embeddings
We fine-tuned pretrained embeddings from [TF Hub](https://tfhub.dev/google/nnlm-id-dim50-with-normalization/2) with our datasets.

### Layers

### Train data

Train data is [here](https://github.com/annisann/SixPackTeamApp/blob/ner-patch/data/NE/train.csv)

### Test data
You can get add two kind of input:
1. A dataframe. The test data is [here](https://github.com/annisann/SixPackTeamApp/blob/ner-patch/data/NE/test.csv)
2. Your own string. Pretend that you're in trouble and see the magic happens! ✨ 

### Results
