package com.example.maimanhduy.rbook.model;

/**
 * Created by MaiManhDuy on 1/10/2017.
 */

public class BookInFireBase {
    private String TitleBook;
    private String AuthorName;
    private String LinkImage;
    private String LinkBook;
    private String Id;
    private String Views;
    private String BookCategory;

    public BookInFireBase() {
    }

    public BookInFireBase(String titleBook, String authorName, String linkImage, String linkBook, String id, String views, String bookCategory) {
        TitleBook = titleBook;
        AuthorName = authorName;
        LinkImage = linkImage;
        LinkBook = linkBook;
        Id = id;
        Views = views;
        BookCategory = bookCategory;
    }

    public String getBookCategory() {
        return BookCategory;
    }

    public void setBookCategory(String bookCategory) {
        BookCategory = bookCategory;
    }

    public String getViews() {
        return Views;
    }

    public void setViews(String views) {
        Views = views;
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

