package com.restapi.repository;

import com.restapi.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPAを利用するためのインタフェース
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
