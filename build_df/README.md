<!-- PREPROCESSING -->
<details open="open">
  <summary>
    <a href="#preprocessing-scraping-results">Preprocessing</a>
  </summary>
  <ol>
    <li>
      <a href="#steps">Steps</a>
    </li>
    <li>
      <a href="#run!">Run!</a>
    </li>
  </ol>
</details>

<!--  DATA PROCESSING -->
# Processing Scraping Results

This code is to convert raw dataset to NER and classification train and test data. 😺

## Steps
From the scraping results, `build_df.py` will preprocessing the results, which do casefolding and tokenizing, the create two different files, `train.csv` and `test.csv` on each `classification` and `ner` folder as shown below.
<p align="center">
  <img src="https://user-images.githubusercontent.com/73707695/119653116-265a1b80-be51-11eb-8f91-8fa1231f288b.jpg" width="480">
</p>

The columns of the second dataframe will be  `index` (nth report) and `report` which consists tokens, then we will tag each token.

## Run!
```
cd SixPackTeamApp/build_df
```
```
python build_df.py
```
