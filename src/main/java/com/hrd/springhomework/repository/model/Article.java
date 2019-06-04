package com.hrd.springhomework.repository.model;

public class Article {
    private static int ids = 0;
    private int id;
    private String title;
    private String author;
    private String description;
    private String image;

    public Article(String title, String author, String description, String image) {
        this.id = ++ids;
        System.out.println("paramet" + id);
        this.title = title;
        this.author = author;
        this.description = description;
        this.image = image;
    }

    public Article() {
        this.id = ++ids;
        System.out.println("default " + id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
}
