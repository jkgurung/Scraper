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
@Table(name = "links")
public class Links {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "input_url")
    private String inputUrl;
    private String title;
    @Column(columnDefinition = "text")
    private String link;
    @Column(name = "link_type")
    private String linkType;
    private Date createdAt;

    public Links(String inputUrl, String title, String link, String linkType) {
        this.inputUrl = inputUrl;
        this.title = title;
        this.link = link;
        this.linkType = linkType;
        this.createdAt = new Date();
    }
}