package com.homeproject.blog.backend.business.models.DTO;

import java.util.Objects;

public class Tag {
    private String tag;
    private Long id;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag1 = (Tag) o;
        return Objects.equals(tag, tag1.tag) &&
                Objects.equals(id, tag1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, id);
    }
}
