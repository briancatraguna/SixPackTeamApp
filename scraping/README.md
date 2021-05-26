<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>
    <a href="#website-scraping">Scraping</a>
  </summary>
  <ol>
    <li>
      <a href="#about">About</a>
      <ul>
        <li><a href="#input">Input</a></li>
      </ul>
      <ul>
         <li><a href="#output">Output</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
<!--     <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li> -->
  </ol>
</details>

<!-- PREPROCESSING -->
<details open="open">
  <summary>
    <a href="#preprocessing">Preprocessing</a>
  </summary>
  <ol>
    <li>
      <a href="#steps">Step</a>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#limitations">Limitations</a></li>
<!--     <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li> -->
  </ol>
</details>

# Website Scraping

##  About
This program is to scrape user's reports on LAPOR! website (https://lapor.go.id).
<br> Maximum reports per page on the website are 10.
<br> See limitations of this program <a href="#limitations">here.</a>
<br>Total datasets gathered are 1749 rows with queries below:
```
- kebakaran
- tawuran
- copet
- luka parah
- kdrt
- ambulans
- terjadi banjir
- pingsan
- pohon tumbang
- tabrak lari
- senjata tajam
- dirampok
- terjadi pelecehan
- kekerasan
- penyerangan
- pelemparan
- bencana alam
- korban kecelakaan
- kondisi korban
- pemerkosaan
- gigit ular
```
#### Input
`QUERY` -> the keyword you want to search, e.g 'kebakaran', 'kdrt'
<br> `PAGE_START` -> starting page
<br> `PAGE_END` -> ending page
<br>
<br> e.g `PAGE_START == 1` and `PAGE_END == 100`, thus you will scrape from page 1-100.

#### Output
The output of this program will be a csv file inside `data` folder (`data/{QUERY}.csv`) see example and explanation below.
query | report | institute | category
---|---|---|---
input query | reports from user/reporter | destination, to whom the user would like to filed the report | category of the report (the user decide)

## Getting Started

### Prerequisites
1. Install requirements
```
pip install -r requirements.txt
```
2. Have an account on https://lapor.go.id and `POST` request data, see <a href="#usage">Usage</a> for details.

### Installation
1. Setting up git credentials. Fill the inside brackets with yours.
```
git config --global user.name "{name}"
```
```
git config --global user.username "{username}"
```
```
git config --global user.email "{email}"
```
2. Clone the repository
```
git clone https://{username}:{password}@github.com/briancatraguna/SixPackTeamApp.git
```

## Usage
1. Go to the directory
```
cd SixPackTeamApp/scraping
```
2. Before running the program, do some steps below.
    1. If you haven't, create an account at https://lapor.go.id
    2. Open developer tools on your browser, or press `F12`.
    3. Go to the Network tab.
    4. Log in to your account.
    5. Select the latest POST request.
    6. See the `Form data` on the Request tab.
    7. Replace the `_session_key`, `token`, `login`, and `password` on  `get_html_source` with yours.
      <br> *If it doesn't work:*
      <br> copy the cURL of the POST request and convert it through https://curl.trillworks.com/, then replace the `cookies`, `headers`, `data` code.
    8. All set!
    
    **NOTE**
    *You must update the value if you turn off your laptop, closing the tab, or get disconnected.*
    
3. Finally, run the code on terminal/command prompt.
```sh
python lapor_scraping.py
```
**P.S**: there is also a notebook file for this! :)

## Limitations
- If you have run the program and have the csv file output, and you wish to re-run it again, DELETE the csv file first. Otherwise it will have duplicate rows.
- The program will keep running until `PAGE_START` even though the page is not found. Because of this, I find out the maximum page by myself.

<!--  PREPROCESSING -->
# Preprocessing

## Steps
From the scraping results, `preprocessing.py` will create two different files, `df1.csv` and `df2.csv` as shown below.
<p align="center">
  <img src="https://user-images.githubusercontent.com/73707695/119653116-265a1b80-be51-11eb-8f91-8fa1231f288b.jpg" width="480">
</p>
<br>The columns of the second dataframe will be  `index` (nth report) and `report` which consists tokens, then we will tag each token.
