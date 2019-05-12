package com.restapi.controller;

import java.util.List;

import javax.validation.Valid;

import com.restapi.entity.ProgressTime;
import com.restapi.service.ProgressTimeService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 進行可能時間操作のコントローラ
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/time")
public class ProgressTimesController {

    @Autowired
    private ProgressTimeService timeService;

    /**
     * 進行可能時間検索
     *
     * @param id ユーザ番号
     * @return 進行可能時間
     */
    @GetMapping("{userNo}")
    public List<ProgressTime> getTimeByUserNo(@PathVariable("userNo") long userNo) {
        return timeService.getProgressTimeByUserNo(userNo);
    }

    /**
     * 進行可能時間登録
     *
     * @param timeBody リクエストボディ
     * @return 更新後の進行可能時間
     */
    @PutMapping
    public ResponseEntity<ProgressTime> createTime(@RequestBody @Validated ProgressTime time) {
        ProgressTime createdTime = timeService.createProgressTime(time);
        if (createdTime != null)
            return new ResponseEntity<ProgressTime>(createdTime, HttpStatus.CONFLICT);
        return new ResponseEntity<ProgressTime>(HttpStatus.CREATED); 
    }

    /**
     * 進行可能時間修正
     *
     * @param timeBody リクエストボディ
     * @return 更新後の進行可能時間
     */
    @PatchMapping("{id}")
    public ResponseEntity<ProgressTime> patchTime(@PathVariable("id") long id, @RequestBody @Valid ProgressTime time) {
        ProgressTime updatedTime = timeService.patchProgressTime(id, time); 
        if(updatedTime != null)
            return new ResponseEntity<ProgressTime>(updatedTime, HttpStatus.OK);
        return new ResponseEntity<ProgressTime>(HttpStatus.NOT_FOUND);
    }

    /**
     * 進行可能時間削除
     *
     * @param id 進行可能時間ID
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
        if(timeService.deleteProgressTime(id)) return new ResponseEntity<Void>(HttpStatus.OK);
        else return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}
