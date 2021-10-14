package com.homeproject.blog.backend.classes;



public class Comment {
    private Post post;
    private String text;
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Comment(Post post, String text, Author author, Long id) {
        this.post = post;
        this.text = text;
        this.id = id;
        this.author = author;
    }
}
