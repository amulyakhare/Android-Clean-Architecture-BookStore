package com.amulyakhare.domain.model;

/**
 * @author amulya
 * @datetime 16 Nov 2014, 3:39 PM
 */
public class Book {

    private int id;
    private String title;
    private String author;
    private String downloadUrl;
    private String detail;

    public Book() {
        this.id = 1;
        this.title = "The Jungle Book";
        this.author = "Rudyard Kipling";
        this.downloadUrl = "http://www.google.com";
        this.detail = "The Jungle Book (1894) is a collection of stories by English author Rudyard Kipling. The stories were first published in magazines in 1893–94. The original publications contain illustrations, some by Rudyard's father, John Lockwood Kipling. Kipling was born in India and spent the first six years of his childhood there. After about ten years in England, he went back to India and worked there for about six-and-a-half years. These stories were written when Kipling lived in Vermont. There is evidence that it was written for his daughter Josephine, who died in 1899 aged six, after a rare first edition of the book with a poignant handwritten note by the author to his young daughter was discovered at the National Trust's Wimpole Hall in Cambridgeshire in 2010.";
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

    public String getDetail() {
        return detail;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
