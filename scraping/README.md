<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About</a>
      <ul>
        <li><a href="#input">Input</a></li>
      </ul>
      <ul>
         <li><a href="output">Output</a></li>
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

##  About
This program is to scrape user's reports on LAPOR! website (https://lapor.go.id).

#### Input
`QUERY` -> the keyword you want to search, e.g 'kebakaran', 'kdrt'
<br> `PAGE_START` -> starting page
<br> `PAGE_END` -> ending page
<br>
<br> e.g `PAGE_START == 1` and `PAGE_END == 100`, thus you will scrape from page 1-100.

#### Output
The output of this program will be a csv file inside `data` folder (`data/{QUERY}.csv`) see example and explanation below.
report | institute | category
---|---|---
reports from user/reporter | destination, to whom the user would like to filed the report | category of the report (the user decide)

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
      <br> copy the cURL of the POST request and convert it through https://curl.trillworks.com/, then replace the code.
    8. All set!

**NOTE**
*You must update the value if you turn off your laptop, closing the tab, or get disconnected.*

3. Finally, run the code on terminal/command prompt. `Notebook file will be added.`
```sh
python lapor_scraping.py
```
