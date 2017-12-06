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
@Table(name = "media")
public class Media {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "input_url")
    private String inputUrl;
    private String title;
    private String media;
    @Column(name = "media_type")
    private String mediaType;
    private Date createdAt;

    public Media(String inputUrl, String title, String media, String mediaType) {
        this.inputUrl = inputUrl;
        this.title = title;
        this.media = media;
        this.mediaType = mediaType;
        this.createdAt = new Date();
    }
}
