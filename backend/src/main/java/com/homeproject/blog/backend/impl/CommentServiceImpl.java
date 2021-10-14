package com.homeproject.blog.backend.impl;

import com.homeproject.blog.backend.services.CommentService;
import com.homeproject.blog.backend.classes.Comment;
import com.homeproject.blog.backend.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("com.homeproject.blog.backend.datalayer.repositories")
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    public Comment getCommentById(Integer id) {
        // SELECT * FROM Commnet where id - "id" LIMIT 1
        //findByIdAndAuthor
        // SELCT * FROM Comment where id = "id"  AND author_id = "" LIMIT 1

        ///findAllBy////
        /// SELECT * FROM COMMENT WHERE ...
        return commentRepository.findById(id);
    }
}
