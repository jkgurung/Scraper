package com.Scraper.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "word")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "input_url")
    private String inputUrl;
    @Column(name = "word", columnDefinition = "text")
    private String word;
    private Long freq;
//    @Column(name = "word_type")
//    private String wordType;
    private Date createdAt;

    public Word(String inputUrl, String word, Long freq) {
        this.inputUrl = inputUrl;
        this.word = word;
        this.freq = freq;
//        this.wordType = wordType;
        this.createdAt = new Date();
    }

    public Word(Long freq) {
        this.freq = freq;
//        this.wordType = wordType;
    }
}

