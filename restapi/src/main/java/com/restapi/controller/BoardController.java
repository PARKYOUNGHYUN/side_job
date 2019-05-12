package com.restapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.restapi.entity.Board;
import com.restapi.service.BoardService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 掲示板操作のコントローラ
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class BoardController {

    @Autowired
    private BoardService boardService;

    /**
     * 全体掲示板検索
     *
     * @return 掲示板
     */
    @GetMapping("/boards")
    public List<Board> getBoards(@RequestParam(required = false) int postType, @RequestParam(required = false) int limit) {
        return boardService.getBoards(postType, limit);
    }

    /**
     * 掲示板検索
     *
     * @param id 掲示板ID
     * @return 掲示板
     */
    @GetMapping("/board/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable("id") long id) {
        Optional<Board> board = boardService.getBoard(id); 
        if(board.isPresent())
            return new ResponseEntity<Board>(board.get(), HttpStatus.OK);
        return new ResponseEntity<Board>(HttpStatus.NOT_FOUND);
    }

    /**
     * 掲示板登録
     *
     * @param boardBody リクエストボディ
     * @return 更新後の掲示板
     */
    @PutMapping("/board")
    public ResponseEntity<Board> createBoard(@RequestBody @Validated Board board) {
        Board createdBoard = boardService.createBoard(board);
        if (createdBoard != null)
            return new ResponseEntity<Board>(createdBoard, HttpStatus.CONFLICT);
        return new ResponseEntity<Board>(HttpStatus.CREATED); 
    }

    /**
     * 掲示板修正
     *
     * @param boardBody リクエストボディ
     * @return 更新後の掲示板
     */
    @PatchMapping("/board/{id}")
    public ResponseEntity<Board> patchBoard(@PathVariable("id") long id, @RequestBody @Valid Board board) {
        Board updatedBoard = boardService.patchBoard(id, board); 
        if(updatedBoard != null)
            return new ResponseEntity<Board>(updatedBoard, HttpStatus.OK);
        return new ResponseEntity<Board>(HttpStatus.NOT_FOUND);
    }
}
