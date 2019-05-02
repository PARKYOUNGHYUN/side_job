package com.restapi.repository;

import java.util.List;

import com.restapi.entity.FavoriteTag;
import com.restapi.entity.FavoriteTagId;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPAを利用するためのインタフェース
 */
public interface FavoriteTagRepository extends JpaRepository<FavoriteTag, FavoriteTagId> {
    List<FavoriteTag> findByFtIdUserNo(Long userNo);
}