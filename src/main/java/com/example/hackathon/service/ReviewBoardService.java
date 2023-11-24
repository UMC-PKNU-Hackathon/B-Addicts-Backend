package com.example.hackathon.service;

import com.example.hackathon.dto.request.CreateReviewBoardDto;
import com.example.hackathon.dto.response.GetReviewBoardDto;
import com.example.hackathon.dto.response.GetReviewBoardList;
import com.example.hackathon.entity.ReviewBoard;
import com.example.hackathon.entity.User;
import com.example.hackathon.repository.ReviewBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewBoardService {

    private final ReviewBoardRepository reviewBoardRepository;

    public void create(User user, CreateReviewBoardDto createReviewBoardDto) {
        ReviewBoard reviewBoard = ReviewBoard.builder()
                .title(createReviewBoardDto.getTitle())
                .user(user)
                .before(createReviewBoardDto.getBefore())
                .after(createReviewBoardDto.getAfter())
                .startedAt(createReviewBoardDto.getStartedAt())
                .endedAt(createReviewBoardDto.getEndedAt())
                .keyword(createReviewBoardDto.getKeyword())
                .build();

        reviewBoardRepository.save(reviewBoard);
    }

    public GetReviewBoardDto getReviewBoard(Long id) {

        ReviewBoard board = reviewBoardRepository.findById(id).orElseThrow();

        return GetReviewBoardDto.builder()
                .nickname(board.getUser().getNickname())
                .title(board.getTitle())
                .before(board.getBefore())
                .after(board.getAfter())
                .startedAt(board.getStartedAt())
                .endedAt(board.getEndedAt())
                .good(board.getGood())
                .createdAt(board.getCreatedAt())
                .keyword(board.getKeyword())
                .build();

    }

    public List<GetReviewBoardList> getReviewBoardList() {
        List<GetReviewBoardList> list = new ArrayList<>();

        List<ReviewBoard> reviewBoards = reviewBoardRepository.findAll();

        System.out.println("SDFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFfff");
        System.out.println(reviewBoards.size());
        for(ReviewBoard board : reviewBoards) {
            GetReviewBoardList dto = GetReviewBoardList.builder()
                    .title(board.getTitle())
                    .nickname(board.getUser().getNickname())
                    .keyword(board.getKeyword())
                    .createdAt(board.getCreatedAt())
                    .build();
            list.add(dto);
        }

        return list;
    }
}
