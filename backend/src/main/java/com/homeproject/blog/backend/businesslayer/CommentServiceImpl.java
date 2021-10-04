package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.entities.Author;
import com.homeproject.blog.backend.entities.Comment;
import com.homeproject.blog.backend.exceptions.CommentNotFoundException;
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
public class CommentServiceImpl implements CommentService {

    private static final Map<Long, Comment> comments = new HashMap<>();
    private static final AtomicLong index = new AtomicLong();
    private static Author author;

    private static Long getNextIndex (){
        return index.getAndIncrement();
    }

    static {
        author = new Author("Paul", "Pavlo", "Ponomarenko");
    }

    @Override
    public Comment createComment(Comment comment) {
        Long id = getNextIndex();
        String date = CurrentDate.getDate();
        comment.setId(id);
        comment.setAuthor(author);
        comment.setCreatedOn(date);
        comment.setUpdatedOn(date);
        comments.put(id, comment);
        return comment;
    }

    @Override
    public Comment updateComment(Long id, Comment changes) throws CommentNotFoundException {
        Comment comment = readComment(id);
        comment.setText(changes.getText());
        comment.setUpdatedOn(CurrentDate.getDate());
        return comment;
    }

    @Override
    public Comment readComment(Long id) throws CommentNotFoundException {
        Comment comment = comments.get(id);
        if (comment == null) {
            throw new CommentNotFoundException();
        }
        return comment;
    }

    @Override
    public Collection<Comment> getComments() {
        return comments.values();
    }

    @Override
    public Collection<Comment> sortComments(Collection<Comment> comments, Map<String, String> parameters) {
        Stream<Comment> stream = comments.stream();
        if (parameters.containsKey("id")) {
            ArrayList<Comment> list = new ArrayList<>();
            Long commentId = Long.parseLong(parameters.get("id"));
            list.add(this.comments.get(commentId));
            return list;
        }
        if (parameters.containsKey("author_name")) {
            String author_name = parameters.get("author_name");
            stream = stream.filter(comment -> comment.getAuthor().getName().equals(author_name));
        }
        String sort = parameters.getOrDefault("sort", "-id");
        if (sort.equals("-id")) {
            stream = stream.sorted(new Comment.CommentIDComparator(false));
        } else if (sort.equals("id")) {
            stream = stream.sorted(new Comment.CommentIDComparator(true));
        }
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void deleteComment(Long id) throws CommentNotFoundException {
        readComment(id);
        comments.remove(id);
    }
}
