This program is to scrape user's reports through LAPOR! website (https://lapor.go.id)

##  Input and output of this program
#### INPUT
`QUERY` -> the keyword you want to search, e.g 'kebakaran', 'kdrt'
<br> `PAGE_START` -> starting page
<br> `PAGE_END` -> ending page
<br> e.g `PAGE_START == 1` and `PAGE_END == 100`, you will scrape from page 1-100.
#### OUTPUT
The output of this program will be a csv file, see example below.
report | institute | category
---|---|---
reports from user/reporter | destination, to whom the user would like to filed the report | category of the report (the user decide)


## Input data for `get_html source`
1. If you haven't, create an account at https://lapor.go.id
2. Open developer tools on your browser, or press `F12`.
3. Go to the Network tab.
4. Log in to your account.
5. Select the latest POST request.
6. See the `Form data` on the Request tab.
7. Replace the `_session_key`, `token`, `login`, and `password` with yours.
   <br> If it doesn't work:
   <br> copy the cURL of the POST request and convert it through https://curl.trillworks.com/, then replace the code.
8. All set!

##### NOTE:
- You must update the value if you turn off your laptop, closing the tab, or get disconnected.
