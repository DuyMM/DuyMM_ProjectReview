package com.example.maimanhduy.rbook.model;

/**
 * Created by MaiManhDuy on 1/16/2017.
 */

public class BookOnSdCard {
    private String id;
    private String title;
    private String linkBook;
    private String linkImage;
    private String author;

    public BookOnSdCard() {
    }

    public BookOnSdCard(String id, String title, String linkBook, String linkImage, String author) {
        this.id = id;
        this.title = title;
        this.linkBook = linkBook;
        this.linkImage = linkImage;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLinkBook() {
        return linkBook;
    }

    public void setLinkBook(String linkBook) {
        this.linkBook = linkBook;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }
}
