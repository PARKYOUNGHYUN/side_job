package com.restapi.repository;

import java.util.List;

import com.restapi.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPAを利用するためのインタフェース
 */
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByPostTypeIsAndDelFlgFalseOrderByUpdatedAt(int postType);
}
