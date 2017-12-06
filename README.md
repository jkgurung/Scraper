# Scraper
Java application that accepts a URL as input and scrapes the page referenced by the input URL. Filter outs HTML tags and calculates the frequency count of the remaining words. Finally stores the result into PostgreSQL.

---


#### Steps Performed

###### 1. Fetch a Webpage from given InputURL
###### 2. Data Extraction.
###### 3. Do the data analysis.
###### 4. Persist the result data to local PostgreSQL server.



###### 1. Fetch a Webpage from given InputURL
    I use jsoup a Java library to fetch the webpage referenced by the InputURL.

    ```
    //send request and retrieve html response
    Document doc = Jsoup.connect(INPUT_URL).ignoreContentType(true).userAgent(USER_AGENT).get();
    ```


###### 2. Data Extraction.

    In this step, I filter out all the HTML tags and grab the desired texts using jsoup library.
    Below are the types of data I stored in separate data structure.
    
    
  -   Words: All the words except HTML tags.
      
  -   Links: All the links that was present in this page. This includes external link such as "www.google.com" 
            and internal links such as "#/Contact".
  -   Media: This grouped all the media files such as video, audio, etc.




###### 3. Do the data analysis.

![Alt Text](/path/to/image.jpg "Title")



###### 4. Persist the result data to local PostgreSQL server.

    Use 