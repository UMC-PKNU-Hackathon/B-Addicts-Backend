package com.example.hackathon.repository;

import com.example.hackathon.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitle(String title);

    @Query("select distinct b from Board b " +
            "left join fetch b.comments " +
            "join fetch b.user " +
            "where b.boardId = :boardId")
    Board findByIdWithComments(@Param("boardId")Long boardId);

    @Query("select b from Board b " +
            "join fetch b.user")
    List<Board> selectAll();
}
