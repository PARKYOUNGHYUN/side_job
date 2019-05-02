package com.restapi.repository;

import com.restapi.entity.Hashtag;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPAを利用するためのインタフェース
 */
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
}
