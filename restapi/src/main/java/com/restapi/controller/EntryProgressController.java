package com.restapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.restapi.entity.EntryProgress;
import com.restapi.entity.EntryProgressId;
import com.restapi.repository.EntryProgressRepository;
import com.restapi.service.EntryProgressService;

import lombok.NonNull;
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
    private EntryProgressService progresService;

    @NonNull
    private final EntryProgressRepository progresRepository;

    /**
     * 進行状態検索
     *
     * @param id 進行状態ID
     * @return 進行状態
     */
    @GetMapping
    public ResponseEntity<EntryProgress> getEntryProgressById(@RequestBody EntryProgressId epId) {
        Optional<EntryProgress> entryProgress = progresService.getEntryProgress(epId);
        if(entryProgress.isPresent())
            return new ResponseEntity<EntryProgress>(entryProgress.get(), HttpStatus.OK);
        return new ResponseEntity<EntryProgress>(HttpStatus.NOT_FOUND);
    }

    /**
     * 進行状態登録
     *
     * @param progresBody リクエストボディ
     * @return 更新後の進行状態
     */
    @PutMapping
    public ResponseEntity<EntryProgress> createEntryProgress(@RequestBody @Validated EntryProgress progress) {
        EntryProgress createdProgress = progresService.createProgress(progress);
        if (createdProgress != null)
            return new ResponseEntity<EntryProgress>(createdProgress, HttpStatus.CONFLICT);
        return new ResponseEntity<EntryProgress>(HttpStatus.CREATED); 
    }

    /**
     * 進行状態修正
     *
     * @param progresBody リクエストボディ
     * @return 更新後の進行状態
     */
    @PatchMapping
    public ResponseEntity<EntryProgress> patchEntryProgress(@RequestBody @Valid EntryProgress progress) {
        EntryProgress fetchedProgress = progresService.patchEntryProgress(progress); 
        if(fetchedProgress != null)
            return new ResponseEntity<EntryProgress>(fetchedProgress, HttpStatus.OK);
        return new ResponseEntity<EntryProgress>(HttpStatus.NOT_FOUND);
    }
}
