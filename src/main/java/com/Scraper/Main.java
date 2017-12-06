package com.Scraper;

import com.Scraper.scraper.Scraper;
import com.Scraper.dao.GenericDAO;
import com.Scraper.entity.Links;
import com.Scraper.entity.Media;
import com.Scraper.entity.WebPage;
import com.Scraper.entity.Word;
import com.Scraper.model.AllTablesData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class Main {

    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    // input URL
    private static final String INPUT_URL = "https://www.ebay.com";



    public static void main(String[] args) {

        LOGGER.info("START - " + new Date());
        Scraper scraper = new Scraper();

        //scrape all tables data
        AllTablesData allTablesData = scraper.scrapeResultsPage(INPUT_URL);

        String domain = allTablesData.getWebPage().getInputUrl();

        //check if input url exists in the table - if -1 doesnt exist
        boolean exist = GenericDAO.getInstance().isExist(domain);
        WebPage webPage = allTablesData.getWebPage();
        if (exist) {
            LOGGER.info("web page entity already exists in the table: " + domain);
        } else {
            //save input entity into a table if not exist input url
            GenericDAO.getInstance().save(webPage);
            LOGGER.info("New web_page entity: " + domain);
        }

        //insert link data into link table
        List<Links> links = allTablesData.getLinks();
        for (Links link : links) {
            LOGGER.info("New link entity: " + link.getTitle());
            GenericDAO.getInstance().save(link);
        }
        //insert word data into word table
        List<Word> words = allTablesData.getWords();
        for (Word word : words) {
            LOGGER.info("New word entity: " + word.getWord());
            GenericDAO.getInstance().save(word);
        }
        //insert media data
        List<Media> medias = allTablesData.getMedias();
        for (Media media : medias) {
            LOGGER.info("New media entity: " + media.getTitle());
            GenericDAO.getInstance().save(media);
        }
        LOGGER.info("END - " + new Date());


    }

}


