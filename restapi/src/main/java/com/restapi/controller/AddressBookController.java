package com.restapi.controller;

import java.util.List;

import javax.validation.Valid;

import com.restapi.entity.AddressBook;
import com.restapi.service.AddressBookService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 住所録操作のコントローラ
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/addressBook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * 住所録検索
     *
     * @param id 掲示板番号
     * @return 住所録
     */
    @GetMapping("{boardNo}")
    public List<AddressBook> getAddressBook(@PathVariable("boardNo") long boardNo) {
        return addressBookService.getAddressBookByBoardNo(boardNo);
    }

    /**
     * 住所録登録
     *
     * @param addressBookBody リクエストボディ
     * @return 更新後の住所録
     */
    @PutMapping
    public ResponseEntity<AddressBook> createAddressBook(@RequestBody @Validated AddressBook addressBook) {
        AddressBook createdAddressBook = addressBookService.createAddressBook(addressBook);
        if (createdAddressBook != null)
            return new ResponseEntity<AddressBook>(createdAddressBook, HttpStatus.CONFLICT);
        return new ResponseEntity<AddressBook>(HttpStatus.CREATED); 
    }

    /**
     * 住所録修正
     *
     * @param addressBookBody リクエストボディ
     * @return 更新後の住所録
     */
    @PatchMapping("{id}")
    public ResponseEntity<AddressBook> patchAddressBook(@PathVariable("id") long id, @RequestBody @Valid AddressBook addressBook) {
        AddressBook updatedAddressBook = addressBookService.patchAddressBook(id, addressBook); 
        if(updatedAddressBook != null)
            return new ResponseEntity<AddressBook>(updatedAddressBook, HttpStatus.OK);
        return new ResponseEntity<AddressBook>(HttpStatus.NOT_FOUND);
    }

    /**
     * 住所録削除
     *
     * @param id 住所録ID
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
        if(addressBookService.deleteAddressBook(id)) return new ResponseEntity<Void>(HttpStatus.OK);
        else return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}
