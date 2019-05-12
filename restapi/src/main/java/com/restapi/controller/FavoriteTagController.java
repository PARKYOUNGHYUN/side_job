package com.restapi.controller;

import java.util.List;

import com.restapi.entity.FavoriteTag;
import com.restapi.entity.FavoriteTagId;
import com.restapi.service.FavoriteTagService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * お気に入りタグ操作のコントローラ
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/favoriteTag")
public class FavoriteTagController {

    @Autowired
    private FavoriteTagService favoriteTagService;

    /**
     * お気に入りタグ検索
     *
     * @param userNo ユーザID
     * @return お気に入りタグ
     */
    @GetMapping("{userNo}")
    public List<FavoriteTag> getFavoriteTags(@PathVariable("userNo") long userNo) {
        return favoriteTagService.getFavoriteTags(userNo);
    }

    /**
     * お気に入りタグ登録
     *
     * @param favoriteTagBody リクエストボディ
     * @return 更新後のお気に入りタグ
     */
    @PutMapping
    public ResponseEntity<FavoriteTag> createFavoriteTag(@RequestBody @Validated FavoriteTag favoriteTags) {
        FavoriteTag createdProgress = favoriteTagService.createProgress(favoriteTags);
        if (createdProgress != null)
            return new ResponseEntity<FavoriteTag>(createdProgress, HttpStatus.CONFLICT);
        return new ResponseEntity<FavoriteTag>(HttpStatus.CREATED); 
    }

    /**
     * お気に入りタグ削除
     *
     * @param id 住所録ID
     */
    @DeleteMapping
    public ResponseEntity<?> deleteFavoriteTag(@RequestBody FavoriteTagId ftId) {
        if(favoriteTagService.deleteFavoriteTag(ftId)) return new ResponseEntity<Void>(HttpStatus.OK);
        else return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}
