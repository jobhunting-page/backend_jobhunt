from time import sleep
from selenium import webdriver
from io import BytesIO
from bs4 import BeautifulSoup
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
import json
import os

chrome_options = webdriver.ChromeOptions()
chrome_options.add_argument('--headless')               # headless
chrome_options.add_argument('--no-sandbox')
chrome_options.add_argument('--disable-dev-shm-usage')
chrome_options.add_argument('--disable-gpu')
chrome_options.add_argument('--window-size=1920x1080')

driver = webdriver.Chrome('chromedriver', chrome_options=chrome_options)
driver.implicitly_wait(3)
driver.get('https://www.codenary.co.kr/techblog/list')

parsing_data = {}
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
def processing(data):
    try:

        

        html = driver.page_source
        soup = BeautifulSoup(html, 'html.parser')
        companyheadline = soup.select('#techblog-body > div > div:nth-child({}) > div > div > div.mantine-1sp40g > div.mantine-Text-root.mantine-16559e4'.format(data))
        companyimg = soup.select('#techblog-body > div > div:nth-child({}) > div > img.mantine-14xayu2'.format(data))
        companyinfo= soup.select('#techblog-body > div > div:nth-child({}) > div > div > div.mantine-Text-root.mantine-tx69da'.format(data))
        companydate = soup.select('#techblog-body > div > div:nth-child({}) > div > div > div.mantine-1f3n6qv > div.mantine-Text-root.mantine-eg0xs6'.format(data))

        # print(companyheadline)
        # print(companyimg)
        # print(companydate)
        # print(companyinfo)
        # kimhae_button = driver.find_elements_by_xpath('//*[@id="techblog-body"]/div/div[1]/div[2]/div/div[2]')[0]
        # driver.execute_script("arguments[0].click();", kimhae_button)
        # companylink=soup.select('#techblog-body > div > div:nth-child(1)')
        # print(companylink)
        # companylink.click()
        
        for headline, img, info, date in zip(companyheadline, companyimg, companyinfo, companydate):
            
            preview = headline.get_text().strip()
            logo = img.get('src')
            info = info.get_text().strip()
            date = date.get_text().strip()
            
            print(preview)
            print(logo)
            print(info)
            print(date)

            parsing_data[data] = {
                "preview" : preview,
                "logo" : logo,
                "info" : info,
                "date" : date,
            }



    except Exception as e:
        # print(e)
        return None

# infolst1 = driver.find_elements(By.CSS_SELECTOR,'#container > div.stContainer > div.calContent > div.starNowMonth > table > tbody > tr:nth-child(1) > td.sunday > div > div > div > span.moreNum')
# infolst1 = infolst1 + driver.find_elements(By.CSS_SELECTOR,'#container > div.stContainer > div.calContent > div.starNowMonth > table > tbody > tr:nth-child(1) > td > div > div > div > span.moreNum')
# infolst1 = infolst1 + driver.find_elements(By.CSS_SELECTOR,'#container > div.stContainer > div.calContent > div.starNowMonth > table > tbody > tr:nth-child(1) > td.saturday > div > div > div > span.moreNum')
# infolst2 = driver.find_elements(By.CSS_SELECTOR,'#container > div.stContainer > div.calContent > div.starNowMonth > table > tbody > tr:nth-child(2) > td.sunday > div > div > div > span.moreNum')
# infolst2 = infolst2 + driver.find_elements(By.CSS_SELECTOR,'#container > div.stContainer > div.calContent > div.starNowMonth > table > tbody > tr:nth-child(2) > td > div > div > div > span.moreNum')
# infolst2 = infolst2 + driver.find_elements(By.CSS_SELECTOR,'#container > div.stContainer > div.calContent > div.starNowMonth > table > tbody > tr:nth-child(2) > td.saturday > div > div > div > span.moreNum')
# infolst3 = driver.find_elements(By.CSS_SELECTOR,'#container > div.stContainer > div.calContent > div.starNowMonth > table > tbody > tr:nth-child(3) > td.sunday > div > div > div > span.moreNum')
# infolst3 = infolst3 + driver.find_elements(By.CSS_SELECTOR,'#container > div.stContainer > div.calContent > div.starNowMonth > table > tbody > tr:nth-child(3) > td > div > div > div > span.moreNum')
# infolst3 = infolst3 + driver.find_elements(By.CSS_SELECTOR,'#container > div.stContainer > div.calContent > div.starNowMonth > table > tbody > tr:nth-child(3) > td.saturday > div > div > div > span.moreNum')
# infolst4 = driver.find_elements(By.CSS_SELECTOR,'#container > div.stContainer > div.calContent > div.starNowMonth > table > tbody > tr:nth-child(4) > td.sunday > div > div > div > span.moreNum')
# infolst4 = infolst4 + driver.find_elements(By.CSS_SELECTOR,'#container > div.stContainer > div.calContent > div.starNowMonth > table > tbody > tr:nth-child(4) > td > div > div > div > span.moreNum')
# infolst4 = infolst4 + driver.find_elements(By.CSS_SELECTOR,'#container > div.stContainer > div.calContent > div.starNowMonth > table > tbody > tr:nth-child(4) > td.saturday > div > div > div > span.moreNum')
# infolst5 = driver.find_elements(By.CSS_SELECTOR,'#container > div.stContainer > div.calContent > div.starNowMonth > table > tbody > tr:nth-child(5) > td.sunday > div > div > div > span.moreNum')
# infolst5 = infolst5 + driver.find_elements(By.CSS_SELECTOR,'#container > div.stContainer > div.calContent > div.starNowMonth > table > tbody > tr:nth-child(5) > td > div > div > div > span.moreNum')
# infolst5 = infolst5 + driver.find_elements(By.CSS_SELECTOR,'#container > div.stContainer > div.calContent > div.starNowMonth > table > tbody > tr:nth-child(5) > td.saturday > div > div > div > span.moreNum')



for data in range(1,11):
    if (processing(data) != None):
        pass
        # print(processing(data))
driver.quit()

print(parsing_data)
with open(os.path.join(BASE_DIR, 'news.json'), 'w+',encoding='utf-8') as json_file:
    json.dump(parsing_data, json_file, ensure_ascii = False, indent='\t')

print("완료!")
