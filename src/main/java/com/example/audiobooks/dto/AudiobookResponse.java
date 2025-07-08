package com.example.audiobooks.dto;

import com.example.audiobooks.model.Audiobook;

public class AudiobookResponse {

    private String author;
    private String genre;
    private String year;
    private String title;

    public AudiobookResponse(Audiobook audiobook) {
        this.author = audiobook.getAuthor();
        this.genre = audiobook.getGenre();
        this.year = audiobook.getReleaseYear();
        this.title = audiobook.getTitle();
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
