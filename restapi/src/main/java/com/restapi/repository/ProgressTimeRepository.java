package com.restapi.repository;

import java.util.List;

import com.restapi.entity.ProgressTime;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPAを利用するためのインタフェース
 */
public interface ProgressTimeRepository extends JpaRepository<ProgressTime, Long> {
    List<ProgressTime> findByUserNoEquals(Long userNo);
}
