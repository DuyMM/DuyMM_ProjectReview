package com.example.maimanhduy.rbook.model;

/**
 * Created by MaiManhDuy on 1/10/2017.
 */

public class BookInFireBase {
    public String TitleBook;
    public String AuthorName;
    public String LinkImage;
    public String LinkBook;
    public String Id;

    public BookInFireBase() {
    }

    public BookInFireBase(String titleBook, String authorName, String linkImage, String linkBook, String id) {
        TitleBook = titleBook;
        AuthorName = authorName;
        LinkImage = linkImage;
        LinkBook = linkBook;
        Id = id;
    }

    public String getTitleBook() {
        return TitleBook;
    }

    public void setTitleBook(String titleBook) {
        TitleBook = titleBook;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public String getLinkImage() {
        return LinkImage;
    }

    public void setLinkImage(String linkImage) {
        LinkImage = linkImage;
    }

    public String getLinkBook() {
        return LinkBook;
    }

    public void setLinkBook(String linkBook) {
        LinkBook = linkBook;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}

