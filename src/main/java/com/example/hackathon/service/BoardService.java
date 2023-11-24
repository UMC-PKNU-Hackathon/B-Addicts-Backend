package com.example.hackathon.service;

import com.example.hackathon.dto.request.BoardRequestDto;
import com.example.hackathon.dto.response.GetBoardDto;
import com.example.hackathon.dto.response.GetBoardListDto;
import com.example.hackathon.dto.response.GetCommentDto;
import com.example.hackathon.dto.response.GetReviewBoardDto;
import com.example.hackathon.entity.Board;
import com.example.hackathon.entity.Comment;
import com.example.hackathon.entity.ReviewBoard;
import com.example.hackathon.entity.User;
import com.example.hackathon.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    public void createBoard(User user, BoardRequestDto dto){

        Board board = Board.builder()
                .user(user)
                .title(dto.getTitle())
                .content(dto.getContent())
                .keyword(dto.getKeyword())
                .build();

        boardRepository.save(board);
    }

    public List<GetBoardListDto> getAllBoards() {
        List <GetBoardListDto> list = new ArrayList<>();

        List<Board> boards = boardRepository.selectAll();

        for (Board board : boards) {
            GetBoardListDto dto = GetBoardListDto.builder()
                    .title(board.getTitle())
                    .view(board.getView())
                    .nickname(board.getUser().getNickname())
                    .commentCount(board.getCommentCount())
                    .boardId(board.getBoardId())
                    .keyword(board.getKeyword())
                    .createdAt(board.getCreatedAt())
                    .build();
            list.add(dto);
        }
        return list;
    }

    public GetBoardDto getBoard(Long id) {
        Board board = boardRepository.findByIdWithComments(id);

        List<Comment> commentList = board.getComments();

        List<GetCommentDto> commentDtos = new ArrayList<>();

        for(Comment comment : commentList) {
            GetCommentDto dto = GetCommentDto.builder()
                    .nickname(comment.getUser().getNickname())
                    .content(comment.getContent())
                    .createdAt(comment.getCreateAt())
                    .build();

            commentDtos.add(dto);
        }

        return GetBoardDto.builder()
                .title(board.getTitle())
                .nickname(board.getUser().getNickname())
                .view(board.getView())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .commentList(commentDtos)
                .build();

    }

    public GetBoardDto updateBoard(Long id, String title, String content) {
        Board board = boardRepository.findById(id).orElseThrow();

        return GetBoardDto.builder()
                .title(board.getTitle())
                .view(board.getView())
                .createdAt(board.getCreatedAt())
                .build();
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    //
}
