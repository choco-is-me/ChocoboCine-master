package com.example.cinemaapp.model;

import java.io.Serializable;
import java.util.Objects;

public class Film implements Serializable {

    private String title;
    private String genre;
    private String description;
    private String rating;
    private int imagePath;
    private boolean isInterested;

    public Film(String title, String genre, String description, String rating, int imagePath) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.rating = rating;
        this.imagePath = imagePath;
        isInterested = false;
    }
// get info of movies
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isFavorite() {
        return isInterested;
    }

    public void setFavorite(boolean favorite) {
        isInterested = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(title, film.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
