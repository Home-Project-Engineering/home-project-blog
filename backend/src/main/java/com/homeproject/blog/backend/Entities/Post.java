package com.homeproject.blog.backend.entities;

import java.util.ArrayList;
import java.util.Comparator;

public class Post {
    public static class PostIDComparator implements Comparator<Post> {
        private int sign = 1;

        public PostIDComparator(boolean ascending) {
            if (!ascending) {
                sign = -1;
            }
        }

        @Override
        public int compare(Post post1, Post post2) {
            if (post1.id > post2.id) {
                return sign;
            } else if (post1.id < post2.id) {
                return sign * -1;
            }
            return 0;
        }
    }

    public static class PostTitleComparator implements Comparator<Post> {
        private int sign = 1;

        public PostTitleComparator(boolean ascending) {
            if (!ascending) {
                sign = -1;
            }
        }

        @Override
        public int compare(Post post1, Post post2) {
            return post1.title.compareTo(post2.title) * sign;
        }
    }

    private Long id;
    private ArrayList<Tag> tags;
    private String createdOn;
    private Author author;
    private String text;
    private String title;
    private String previewAttachment;
    private String updatedOn;

    public Post(Long id, ArrayList<Tag> tags, String createdOn, Author author, String text, String title, String previewAttachment, String updatedOn) {
        this.id = id;
        this.tags = tags;
        this.createdOn = createdOn;
        this.author = author;
        this.text = text;
        this.title = title;
        this.previewAttachment = previewAttachment;
        this.updatedOn = updatedOn;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreviewAttachment() {
        return previewAttachment;
    }

    public void setPreviewAttachment(String previewAttachment) {
        this.previewAttachment = previewAttachment;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public boolean hasTag(Long tagId) {
        if (tags == null) {
            return false;
        }
        return tags.stream().anyMatch(tag -> tag.getId().equals(tagId));
    }

    public boolean hasTag(String tagName) {
        if (tags == null) {
            return false;
        }
        return tags.stream().anyMatch(tag -> tag.getName().equals(tagName));
    }
}
