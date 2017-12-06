package com.Scraper.scraper;

import com.Scraper.entity.Links;
import com.Scraper.entity.Media;
import com.Scraper.entity.WebPage;
import com.Scraper.entity.Word;
import com.Scraper.model.AllTablesData;
import com.Scraper.utils.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Scraper {

    public static final Logger logger = LoggerFactory.getLogger(Scraper.class);


    public AllTablesData scrapeResultsPage(String INPUT_URL) {

        final List<String> resultList = new ArrayList<>();

        String words = "";
        try {
            //send request and retrieve html response
            Document doc = Jsoup.connect(INPUT_URL)
                            .ignoreContentType(true)
                            .userAgent(Constants.USER_AGENT)
                            .timeout(Constants.TIMEOUT)
                            .get();

            // Webpage's title
            String title = doc.title();
            System.out.println("Title : " + title);
            System.out.println();

            //Webpage data
            String websiteName = INPUT_URL;

            //get all the word list with freq
            List<Word> wordList = getWordList(doc, INPUT_URL);

            //get links
            List<Links> links = getLinkList(doc, INPUT_URL);

            //get links
            List<Media> mediaList = getMediaList(doc, INPUT_URL);

            logger.info("Scraped page {} ", INPUT_URL);

            return new AllTablesData(new WebPage(websiteName, websiteName), links, wordList, mediaList);
        } catch (Exception e) {
            logger.warn("Error while scraping page {} for term: {}", e);
        }

        return null;
    }


    /**
     * Finds words outside html elements and calculate freq. count
     *
     * @param doc
     * @return A list of words
     */
    private List<Word> getWordList(Document doc, String input_url) {
        List<Word> wordList = new ArrayList<>();
        Set<String> used = new HashSet<>();
        Map<String, Word> wordCountPairs = new HashMap<>();
        String texts = doc.body().text();
//        Stream<String> words = Arrays.asList(texts.split("\\s+|,\\s*|,\\.\\s*|,\\s*|!\\s*")).stream();
        String[] splited_string = texts.split("\\s+|,\\s*|,\\.\\s*|,\\s*|!\\s*,|\\|,|/");
        List<String> words = Arrays.asList(splited_string);
        for (String word : words) {
            Word temp = wordCountPairs.get(word);
            if (temp == null) {
                wordCountPairs.put(word, new Word(1L));
            } else {
                long freq = wordCountPairs.get(word).getFreq() + 1;
                wordCountPairs.put(word, new Word(freq));
            }
        }



        wordList.addAll(wordCountPairs.entrySet().stream()
                .map(wordFreq -> new Word(input_url, wordFreq.getKey(), wordFreq.getValue().getFreq())).collect(Collectors.toList()));
        return wordList;

    }

    /**
     * Selects all anchors(a elements) and checks if its the same as input url
     *
     * @param doc
     * @param domain
     * @return A list of RefUrls
     */
    private List<Links> getLinkList(Document doc, String domain) {

        List<Links> links = new ArrayList<>();
        Elements anchors = doc.select("a[href]");
        for (Element anchor : anchors) {
            String type = " a ";
            String title = anchor.text().trim();
            String href = anchor.attr("abs:href");
            if (href.contains(domain)) {
                type = "internal link";
            }
            links.add(new Links(domain, title, href, type));
        }
        return links;
    }


    /**
     * Extracts all the media lists.
     *
     * @param doc
     * @param domain
     * @return A list of media
     */
    private List<Media> getMediaList(Document doc, String domain) {

        List<Media> medias = new ArrayList<>();
        Elements mediaEls = doc.select("[src]");
        for (Element mediaEl : mediaEls) {
            String type = mediaEl.tagName();
            String media = mediaEl.attr("abs:src");
            String title = mediaEl.text().trim();
            medias.add(new Media(domain, title, media, type));
        }
        return medias;
    }




    private void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }


}

/*
String result = scraper.scrapeResultsPage(INPUT_URL);
        Stream<String> words = Arrays.asList(result.split("\\s+")).stream();


        Map<String, Long> counter = words.collect(Collectors.groupingBy(String::toString, Collectors.counting()));
//        print("\ncounter: (%d)", counter.size());

        counter.forEach((key, value) -> System.out.println(key + " : " + value));
 */