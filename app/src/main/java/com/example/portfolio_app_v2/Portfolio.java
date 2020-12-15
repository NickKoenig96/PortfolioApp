package com.example.portfolio_app_v2;

import java.io.Serializable;


public class Portfolio implements Serializable {
    private int number;
    private String title;
    private String description;
    private String image;
    private String link;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() { return link; }

    public void setLink(String link) { this.link = link; }
}
