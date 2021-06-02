## Structure

```bash
├── raw.csv
├── preprocessed.csv
├── filtered.csv
├── NE
│   ├── train.csv
│   └── test.csv
└── classification
    ├── train.csv
    └── test.csv
```

## Details

* `raw` is a raw data from scraping results. Columns consists of: query, report, institute, category
*  `preprocessed` is a preprocessed data, the values of report are casefolded and normalized from slangwords dictionary. We also add label for text classification.
*  `filtered` The unnecessary report data will be deleted. Then, we split the data (80:20) for `classification` and `NE`
*  `NE` is data for Named Entity Recognition. All sentences will be tokenized. We use BILOU notation for tagging the train data, there will be no tag for test data.
*  `classification` is data for report classification. We use report and label columns, the train data still have label.
