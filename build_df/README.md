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
  <img src="https://github.com/annisann/SixPackTeamApp/blob/40e2e7250c37eb458b5286b5e039fa95bd440b94/assets/builddf.png">
</p>

The columns of the second dataframe will be  `index` (nth report) and `report` which consists tokens, then we will tag each token.

## Run!
```
cd SixPackTeamApp/build_df
```
```
python build_df.py
```
