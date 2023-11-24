package com.example.hackathon.service;

import com.example.hackathon.dto.request.SaveCommentDto;
import com.example.hackathon.entity.Board;
import com.example.hackathon.entity.Comment;
import com.example.hackathon.entity.User;
import com.example.hackathon.repository.BoardRepository;
import com.example.hackathon.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public void saveComment(User user, SaveCommentDto saveCommentDto){

        Board board = boardRepository.findById(saveCommentDto.getBoardId()).orElseThrow();
        board.addCommentCount();

        Comment comment = Comment.builder()
                .user(user)
                .board(boardRepository.getReferenceById(saveCommentDto.getBoardId()))
                .content(saveCommentDto.getContent())
                .build();

        commentRepository.save(comment);
    }
}
