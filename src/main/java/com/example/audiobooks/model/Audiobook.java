package com.example.audiobooks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

/**
 * Represents an audiobook entity with properties such as genre, author, title,
 * and release year.
 * This class is mapped to the "Book" table in the database.
 * <p>
 * Provides constructors for creating an Audiobook with default or specified
 * values,
 * as well as getter and setter methods for each property.
 * </p>
 *
 * <p>
 * Example usage:
 * 
 * <pre>
 * Audiobook book = new Audiobook("Fiction", "Author Name", "Book Title", "2023");
 * </pre>
 * </p>
 *
 * @author Noel Soto
 */
@Entity
@Table(name = "Book")
public class Audiobook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "genre")
    private String genre;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @Column(name = "release_year")
    private String releaseYear;

    /*
     * Default Constructor sets all fields to empty.
     */
    public Audiobook() {
        this.id = null;
        this.genre = "";
        this.author = "";
        this.title = "";
        this.releaseYear = "";
    }

    /**
     * Constructs an AudioBook object with the specified genre, author, title, and
     * release year.
     *
     * @param genre       The genre of the audiobook.
     * @param author      The author of the audiobook.
     * @param title       The title of the audiobook.
     * @param releaseYear The release year of the audiobook.
     */
    public Audiobook(Long id, String genre, String author, String title, String releaseYear) {
        this.id = id;
        this.genre = genre;
        this.author = author;
        this.title = title;
        this.releaseYear = releaseYear;

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseYear() {
        return this.releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "{" +
                " genre='" + getGenre() + "'" +
                ", author='" + getAuthor() + "'" +
                ", title='" + getTitle() + "'" +
                ", releaseYear='" + getReleaseYear() + "'" +
                "}";
    }

}
