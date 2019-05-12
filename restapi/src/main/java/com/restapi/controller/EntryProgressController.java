package com.restapi.controller;

import java.util.List;
import java.util.Optional;

import com.restapi.entity.EntryProgress;
import com.restapi.service.EntryProgressService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 進行状態操作のコントローラ
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/progress")
public class EntryProgressController {

    @Autowired
    private EntryProgressService progressService;

    /**
     * 進行状態検索
     *
     * @param id 進行状態ID
     * @return 進行状態
     */
    @GetMapping
    public ResponseEntity<EntryProgress> getEntryProgressById(@RequestParam long boardNo,
            @RequestParam long entryUserNo) {
        Optional<EntryProgress> entryProgress = progressService.getEntryProgress(boardNo, entryUserNo);
        if (entryProgress.isPresent())
            return new ResponseEntity<EntryProgress>(entryProgress.get(), HttpStatus.OK);
        return new ResponseEntity<EntryProgress>(HttpStatus.OK);
    }

    /**
     * 進行状態登録
     *
     * @param progressBody リクエストボディ
     * @return 更新後の進行状態
     */
    @PutMapping
    public ResponseEntity<EntryProgress> createEntryProgress(@RequestBody @Validated EntryProgress progress) {
        EntryProgress createdProgress = progressService.createProgress(progress);
        if (createdProgress != null)
            return new ResponseEntity<EntryProgress>(createdProgress, HttpStatus.CREATED);
        return new ResponseEntity<EntryProgress>(HttpStatus.CONFLICT); 
    }
}