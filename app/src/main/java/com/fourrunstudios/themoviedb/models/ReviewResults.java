package com.fourrunstudios.themoviedb.models;

public class ReviewResults {
    private String author;
    private String content;

    public ReviewResults(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

}
