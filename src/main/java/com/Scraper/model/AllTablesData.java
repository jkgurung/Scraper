package com.Scraper.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import com.Scraper.entity.Media;
import com.Scraper.entity.WebPage;
import com.Scraper.entity.Links;
import com.Scraper.entity.Word;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllTablesData {

    private WebPage webPage;
    private List<Links> links;
    private List<Word> words;
    private List<Media> medias;

}

