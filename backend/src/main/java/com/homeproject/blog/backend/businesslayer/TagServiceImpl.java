package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.entities.Tag;
import com.homeproject.blog.backend.exceptions.TagNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TagServiceImpl implements TagService {
    private static final Map<Long, Tag> tags = new HashMap<>();
    private static final AtomicLong index = new AtomicLong();

    private static Long getNextIndex (){
        return index.getAndIncrement();
    }

    static {
        populateTag("Java8");
        populateTag("News");
        populateTag("Projects");
    }

    private static void populateTag(String name) {
        Long nextIndex = getNextIndex();
        tags.put(nextIndex, new Tag(nextIndex, name));
    }

    @Override
    public Tag readTag(Long id) throws TagNotFoundException {
        Tag tag = tags.get(id);
        if (tag == null) {
            throw new TagNotFoundException();
        }
        return tag;
    }

    @Override
    public Collection<Tag> getTags() {
        return tags.values();
    }

    @Override
    public void deleteTag(Long id) throws TagNotFoundException {
        readTag(id);
        tags.remove(id);
    }

    public static Long findTagByName(String name) {
        Set<Map.Entry<Long, Tag>> pairs = tags.entrySet();
        for (Map.Entry<Long, Tag> pair : pairs) {
            Tag current = pair.getValue();
            if (current.getName().equals(name)) {
                return current.getId();
            }
        }
        return -1L;
    }

    public static void identifyTags(ArrayList<Tag> tags) throws TagNotFoundException {
        if (tags == null) {
            return;
        }
        for (Tag tag : tags) {
            Long tagId = TagServiceImpl.findTagByName(tag.getName());
            if (tagId.equals(-1)) {
                throw new TagNotFoundException();
            }
            tag.setId(tagId);
        }
    }
}
