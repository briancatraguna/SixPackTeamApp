import requests
import pandas as pd
import os
from tqdm import tqdm
from bs4 import BeautifulSoup

def get_url(query, page):
    """
    Generate URL from query and page number.
    The input query will be split into token or list of word, e.g 'luka parah' will be ['luka', 'parah'] and 'kdrt' will be ['kdrt']
    For input query with more than 1 word, the url will be `..q={token1}+{token2}&..`, e.g `..q=luka+parah&..`
    """
    queryLength = len(query.split())
    if queryLength == 1:
        url = 'https://www.lapor.go.id/search?q={}&page={}'.format(query, page)
    else:
        query = query.split()
        param = '+'.join(query)
        url = 'https://www.lapor.go.id/search?q={}&page={}'.format(param, page)
    return url
  
def get_html_source(url):
    """
    Send request and log in to the website and return the HTML code.
    Replace value of the code below with yours.
    See README for more information.
    """
    cookies = {
        'lapor': 'eyJpdiI6Ing0MVlmM0RoU1lwSG9ldnA5S2VKS3c9PSIsInZhbHVlIjoiYVlEYUZJMHQyKzV3UnRUSHdiRjVOcGpjZ0YyeWk0WjJzazBmRlo1T1BSSjlGaXRnOFwveEdYRFRRMDREN3dqYUltMUw4cU9FSWRPc0E0MlFFa01SSEJ3PT0iLCJtYWMiOiJiNGJjODhhMzU1OGI0MmQ1NjA2ZGFlYjE3Njc0ZTVhZDEwMmFiYWY2NzJjNmIyZTZiMjkxYzRlNDI3NTNmOWNmIn0%3D',
    }

    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:88.0) Gecko/20100101 Firefox/88.0',
        'Accept': '*/*',
        'Accept-Language': 'en-US,en;q=0.5',
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
        'X-OCTOBER-REQUEST-HANDLER': 'laporAuth::onSignin',
        'X-OCTOBER-REQUEST-PARTIALS': 'captcha-login',
        'X-OCTOBER-REQUEST-FLASH': '1',
        'X-Requested-With': 'XMLHttpRequest',
        'Origin': 'https://www.lapor.go.id',
        'Connection': 'keep-alive',
        'Referer': 'https://www.lapor.go.id/',
        'Sec-GPC': '1',
    }

    data = {
      '_session_key': 'YOUR_SESSION_KEY',
      '_token': 'YOUR_TOKEN',
      'login': 'YOUR_EMAIL',
      'password': 'YOUR_PASSWORD'
    }

    try:
        response = requests.get(url, headers=headers, cookies=cookies, timeout=60) # if page need more than 60 secs to load, then abort and go to the next page
        html_code = BeautifulSoup(response.text, 'html.parser')
        response = requests.post(url, headers=headers, cookies=cookies, data=data)
        return html_code
    
    except Exception:
        print('  ERROR: Timeout')
  
def get_report(query, page_len):
    """
    Extract user's reports from the website based on query and page number.
    Here, we want to extract reports from user, institute destination, and category of the reports.
    In the HTML code, the reports element is in the <p> (paragraph) tag with class attribute `readmore`.
    As well as the institute element in the <b> (bold) tag and so on.
    """
    url = get_url(query, page_len)

    print('   + Getting HTML source . .')
    html = get_html_source(url)

    pageExist = html.find('p', {'class':'readmore'})
    if pageExist:
        print('   + Page found! Begin scraping . .')
        reports = [(report.text, institute.text, category.text) for report, institute, category in zip(html.find_all('p', {'class':'readmore'}), # report
                                                                                                       html.find_all('b'), # institute
                                                                                                       html.find_all('a', {'class':'text-muted'}) # category
                                                                                                       )
        ]
        return reports
    # for non existing page i.e no reports.
    else:
        print('  ERROR: Page not found!')
  
def generate_dataframe(query, reports):
    """
    Generate a DataFrame from list of tuples and input query.
    The reports output is [(report1, institute1, category1), (report2, institute2, category2), (report3, institute3, category3), ...].
    In that case, for each report1, report2, report3 will be added to dictionary, as well as institute1, institute2, institute3.
    Then, DataFrame will be created from the dictionary.
    """
    print('   + Generate reports . .')
    reportDict = {'query': query,
                  'report': [],
                  'institute': [],
                  'category': []}

    for report, institute, category in reports:
        reportDict['report'].append(report)
        reportDict['institute'].append(institute)
        reportDict['category'].append(category)
        
    return pd.DataFrame.from_dict(reportDict)

def writeFile(dataframe):
    """
    Save the DataFrame into a csv file.
    """
    print('   + Writing file . .')

    DATA_DIR = 'data/'
    RESULT_PATH = DATA_DIR + 'lapor_scraping_results.csv'

    # if result file exist in data dir
    if os.path.exists(RESULT_PATH):
        # append new data to the file
        with open(RESULT_PATH, 'a+') as file:
            dataframe.to_csv(file, header=False, index=False)

    # if dir doesn't exist
    elif os.path.exists(DATA_DIR) == False:
        # create dir
        os.mkdir(DATA_DIR)

    # if result file doesn't exist
    else:
        # write a new file
        with open(RESULT_PATH, 'w') as file:
            dataframe.to_csv(file, header=True, index=False)
  
def main():   
    global QUERY, PAGE_LEN

    print('Please insert 1) query, 2) starting page, and 3) max page.')
    QUERY = input()
    PAGE_START, PAGE_END = str(input()), str(input())

    print('\nBegin scraping {} page with keyword \'{}\'\n'.format(int(PAGE_END)-int(PAGE_START), QUERY))

    for _num in range(int(PAGE_START), int(PAGE_END)+1):
        print('\noooooooooo PAGE {} oooooooooo'.format(_num))
        try:
            report = get_report(QUERY, _num)
            df = generate_dataframe(QUERY, report)
        except Exception:
            print('  ERROR: No result.')
            continue # gimana caranya break kalo no result tapi continue kalo timeout??
        else:
            writeFile(df)
    
main()
