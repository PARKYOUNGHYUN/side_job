package com.restapi.controller;

import java.util.Optional;

import com.restapi.entity.Hashtag;
import com.restapi.service.HashtagService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ハッシュタグ操作のコントローラ
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/hashtag")
public class HashtagController {

    @Autowired
    private HashtagService hashtagService;

    /**
     * ハッシュタグ検索
     *
     * @param id ハッシュタグID
     * @return ハッシュタグ
     */
    @GetMapping("{id}")
    public ResponseEntity<Hashtag> getHashtagById(@PathVariable("id") long id) {
        Optional<Hashtag> hashtag = hashtagService.getHashtag(id); 
        if(hashtag.isPresent())
            return new ResponseEntity<Hashtag>(hashtag.get(), HttpStatus.OK);
        return new ResponseEntity<Hashtag>(HttpStatus.NOT_FOUND);
    }

    /**
     * ハッシュタグ登録
     *
     * @param hashtagBody リクエストボディ
     * @return 登録後のハッシュタグ
     */
    @PutMapping
    public ResponseEntity<Hashtag> createHashtag(@RequestBody @Validated Hashtag hashtag) {
        Hashtag createdBoard = hashtagService.createHashtag(hashtag);
        if (createdBoard != null)
            return new ResponseEntity<Hashtag>(createdBoard, HttpStatus.CONFLICT);
        return new ResponseEntity<Hashtag>(HttpStatus.CREATED); 
    }
}