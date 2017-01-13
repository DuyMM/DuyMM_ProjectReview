package com.example.maimanhduy.rbook.model;

/**
 * Created by MaiManhDuy on 1/13/2017.
 */

public class FavoriteBook {
    private String id;
    private String date;
    private String category;

    public FavoriteBook() {
    }

    public FavoriteBook(String id, String date, String category) {
        this.id = id;
        this.date = date;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
