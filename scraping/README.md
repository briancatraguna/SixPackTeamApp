This code is to scrape user's reports through LAPOR! website (https://lapor.go.id)

#  WORKFLOW
1. Input the query (e.g 'kebakaran', 'copet') and set max page to scrape
2. Create DataFrame of user's reports on the website.

# GUIDE
1. If you haven't, create an account.
2. Open developer tools on your browser, or press F12.
3. Go to the Network tab.
4. Log in to your account.
5. Select the latest POST request.
6. See the `Form data` on the Request tab.
7. Replace the `_session_key`, `token`, `login`, and `password` with yours.
8. All set!

NOTE:
- If it doesn't work: copy the cURL of the POST request and convert it through https://curl.trillworks.com/, then replace the code.
- You must redoing it again if you turn off your laptop, or closing the tab (maybe).
