package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.entities.Author;
import com.homeproject.blog.backend.entities.Post;
import com.homeproject.blog.backend.entities.Tag;
import com.homeproject.blog.backend.exceptions.PostNotFoundException;
import com.homeproject.blog.backend.exceptions.TagNotFoundException;
import com.homeproject.blog.backend.supportclasses.CurrentDate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostServiceImpl implements PostService {

    private static final Map<Long, Post> posts = new HashMap<>();
    private static final AtomicLong index = new AtomicLong();
    private static Author author;

    private static Long getNextIndex (){
        return index.getAndIncrement();
    }

    static {
        author = new Author("Paul", "Pavlo", "Ponomarenko");
        populatePost(author, "Everything is good today.");
        populatePost(author, "Tomorrow will be better");
        populatePost(author, "Everything is perfect.");

    }

    private static void populatePost(Author author, String text) {
        Long nextIndex = getNextIndex();
        String date = CurrentDate.getDate();
        Post post = new Post(nextIndex, null, date, author, text, "", "", "");
        posts.put(nextIndex, post);
    }

    @Override
    public Post createPost(Post post) throws TagNotFoundException {
        Long nextIndex = getNextIndex();
        post.setId(nextIndex);
        post.setAuthor(author);
        String date = CurrentDate.getDate();
        post.setCreatedOn(date);
        post.setUpdatedOn(date);
        ArrayList<Tag> tags = post.getTags();
        TagServiceImpl.identifyTags(tags);
        posts.put(nextIndex, post);
        return post;
    }

    @Override
    public Post updatePost(Long id, Post changes) throws PostNotFoundException, TagNotFoundException {
        Post post = readPost(id);
        TagServiceImpl.identifyTags(changes.getTags());
        post.setTags(changes.getTags());
        post.setText(changes.getText());
        post.setTitle(changes.getTitle());
        post.setPreviewAttachment(changes.getPreviewAttachment());
        post.setUpdatedOn(CurrentDate.getDate());
        return post;
    }

    @Override
    public Post readPost(Long id) throws PostNotFoundException {
        Post post = posts.get(id);
        if (post == null) {
            throw new PostNotFoundException();
        }
        return post;
    }

    @Override
    public Collection<Post> getPosts() {
        return posts.values();
    }

    @Override
    public Collection<Post> sortPosts(Collection<Post> posts, Map<String, String> parameters) {
        Stream<Post> stream = posts.stream();
        if (parameters.containsKey("id")) {
            ArrayList<Post> list = new ArrayList<Post>();
            Long postId = Long.parseLong(parameters.get("id"));
            list.add(this.posts.get(postId));
            return list;
        }
        if (parameters.containsKey("tag_id")) {
            Long tag_id = Long.parseLong(parameters.get("tag_id"));
            stream = stream.filter(post -> post.hasTag(tag_id));
        }
        if (parameters.containsKey("tag_name")) {
            String tag_name = parameters.get("tag_name");
            stream = stream.filter(post -> post.hasTag(tag_name));
        }
        if (parameters.containsKey("author_name")) {
            String author_name = parameters.get("author_name");
            stream = stream.filter(post -> post.getAuthor().getName().equals(author_name));
        }
        String sort = parameters.getOrDefault("sort", "-id");
        if (sort.equals("-id")) {
            stream = stream.sorted(new Post.PostIDComparator(false));
        } else if (sort.equals("id")) {
            stream = stream.sorted(new Post.PostIDComparator(true));
        } else if (sort.equals("-title")) {
            stream = stream.sorted(new Post.PostTitleComparator(false));
        } else if (sort.equals("title")) {
            stream = stream.sorted(new Post.PostTitleComparator(true));
        }
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void deletePost(Long id) throws PostNotFoundException {
        readPost(id);
        posts.remove(id);
    }
}
