package com.example.hackathon.service;

import com.example.hackathon.entity.Board;
import com.example.hackathon.entity.Comment;
import com.example.hackathon.entity.User;
import com.example.hackathon.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void createComment(User user, Board board, String content){
        Comment comment = Comment.builder()
                .user(user)
                .board(board)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

        commentRepository.save(comment);
    }
}
