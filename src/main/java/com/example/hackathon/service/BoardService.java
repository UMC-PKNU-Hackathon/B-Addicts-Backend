package com.example.hackathon.service;

import com.example.hackathon.entity.Board;
import com.example.hackathon.entity.User;
import com.example.hackathon.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    public void createBoard(User user, String title, String content){

        Board board = Board.builder()
                .user(user)
                .title(title)
                .content(content)
                .view(0)
                .createdAt(LocalDateTime.now())
                .build();

        boardRepository.save(board);
    }

    public List<Board> getAllBoards() {

        return boardRepository.findAll();
    }

    public Optional<Board> getBoardById(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        optionalBoard.ifPresent(board -> {
            board.setView(board.getView() + 1);
            boardRepository.save(board);
        });
        return boardRepository.findById(id);
    }

    public void updateBoard(Long id, String title, String content) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            board.setTitle(title);
            board.setContent(content);
            boardRepository.save(board);
        }
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    //
}
