import requests
import pandas as pd
from tqdm import tqdm
from bs4 import BeautifulSoup

def get_url(query, page):
    """
    Generate URL from query and page number.
    """
    url = 'https://www.lapor.go.id/search?q={}&page={}'.format(query, page)
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
        response = requests.get(url, headers=headers, cookies=cookies, timeout=10)
        html_code = BeautifulSoup(response.text, 'html.parser')
        response = requests.post(url, headers=headers, cookies=cookies, data=data)
        return html_code
    except Exception as e:
        return 'ERROR'
  
def get_report(query, page_len):
    """
    Extract user's reports from the website based on query and page number.
    """
    urls = tqdm([get_url(query, page) for page in range(1, int(page_len)+1)], ncols=100)

    for url in urls:
        urls.set_description('Scraping...')
        html = get_html_source(url)
        
        if not 'ERROR':
            # check if there's <p class='readmore'> </p> in the HTML code. 
            pageExist = html.find('p', {'class':'readmore'})
            
            if pageExist:
                reports = [(report.text, unit.text) for report, unit in zip(html.find_all('p', {'class':'readmore'}), html.find_all('b'))]
                all_reports.extend(reports)

            elif pageExist==False:
                break

    return all_reports
  
def generate_dataframe(reports):
    """
    Generate a DataFrame from list.
    """
    columns = ['report', 'unit']
    return pd.DataFrame(reports, columns=columns)
  
def main():
    query = input()
    page_len = str(input())
    reports = get_report(query, page_len)
    df = generate_dataframe(reports)
    df.to_csv('{}.csv'.format(query), index=True)
    
main()
