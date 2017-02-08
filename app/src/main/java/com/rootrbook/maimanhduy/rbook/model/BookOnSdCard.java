package com.rootrbook.maimanhduy.rbook.model;

/**
 * Created by MaiManhDuy on 1/16/2017.
 */

public class BookOnSdCard {
    private String id;
    private String title;
    private String linkBook;
    private String linkImage;
    private String category;
    public BookOnSdCard() {
    }

    public BookOnSdCard(String id, String title, String linkBook, String linkImage, String category) {
        this.id = id;
        this.title = title;
        this.linkBook = linkBook;
        this.linkImage = linkImage;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
