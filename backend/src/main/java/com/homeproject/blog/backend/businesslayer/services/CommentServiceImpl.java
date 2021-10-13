package com.homeproject.blog.backend.businesslayer.services;

import com.homeproject.blog.backend.businesslayer.dto.CommentDTO;
import com.homeproject.blog.backend.data.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    private static String currentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    public CommentDTO createComment(Long postId, CommentDTO comment) {
        return null;
    }

    @Override
    public CommentDTO updateComment(Long postId, Long id, CommentDTO comment) {
        return null;
    }

    @Override
    public CommentDTO readComment(Long postId, Long id) {
        return null;
    }

    @Override
    public Page<CommentDTO> getComments(Long postId) {
        return null;
    }

    @Override
    public void deleteComment(Long postId, Long id) {

    }
}
