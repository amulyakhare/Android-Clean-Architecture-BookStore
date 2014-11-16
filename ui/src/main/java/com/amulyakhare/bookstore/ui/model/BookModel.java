package com.amulyakhare.bookstore.ui.model;

/**
 * @author amulya
 * @datetime 16 Nov 2014, 3:13 PM
 */
public class BookModel {

    private int id;

    private int icon;

    private String title;

    private String author;

    private String detail;

    private String downloadUrl;

    public BookModel(int id, int icon, String title, String author, String detail, String downloadUrl) {
        this.id = id;
        this.icon = icon;
        this.title = title;
        this.author = author;
        this.detail = detail;
        this.downloadUrl = downloadUrl;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDetail() {
        return detail;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public int getId() {
        return id;
    }
}
