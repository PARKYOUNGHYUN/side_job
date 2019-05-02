package com.restapi.repository;

import java.util.List;

import com.restapi.entity.AddressBook;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPAを利用するためのインタフェース
 */
public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {
    List<AddressBook> findByBoardNoEquals(Long boardNo);
}
