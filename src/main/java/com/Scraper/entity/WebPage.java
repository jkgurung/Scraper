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
@Table(name = "web_page")
public class WebPage {

    @Id
    @Column(name = "input_url")
    private String inputUrl;
    private String title;
    private Date createdAt;

    public WebPage(String inputUrl, String title) {
        this.inputUrl = inputUrl;
        this.title = title;
        this.createdAt = new Date();
    }
}
