package com.homeproject.blog.backend.classes;



public class Comment {
    private Post post;
    private String text;
    private int id;
    private Author author;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Comment(Post post, String text, Author author, int id) {
        this.post = post;
        this.text = text;
        this.id = id;
        this.author = author;
    }
}
