package com.restapi.repository;

import com.restapi.entity.EntryProgress;
import com.restapi.entity.EntryProgressId;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPAを利用するためのインタフェース
 */
public interface EntryProgressRepository extends JpaRepository<EntryProgress, EntryProgressId> {
}