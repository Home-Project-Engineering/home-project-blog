package com.homeproject.blog.backend.classes;

public class Author {
    private String name;
    private int id;

    public Author getId() {
        return Long.valueOf(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
