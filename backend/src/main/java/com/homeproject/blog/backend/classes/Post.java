package com.homeproject.blog.backend.classes;


public class Post {
    private String text;
    private Author author;
    private Long id;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post(String text, Author author, Long id) {
        this.text = text;
        this.author = author;
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return false;
    }

    @Override public int hashCode() {
        final int prime = 31;
        int result = 0;
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        //result = prime * result + id;
        return result;
        }
}