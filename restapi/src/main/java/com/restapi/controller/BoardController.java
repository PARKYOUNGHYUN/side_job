package com.restapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.restapi.entity.Board;
import com.restapi.repository.BoardRepository;
import com.restapi.service.BoardService;

import lombok.NonNull;
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
@RequestMapping(path = "/api/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @NonNull
    private final BoardRepository boardRepository;

    /**
     * 全体掲示板検索
     *
     * @return 掲示板
     */
    @GetMapping
    public List<Board> getBoards() {
        return boardService.getBoards();
    }

    /**
     * 掲示板検索
     *
     * @param id 掲示板ID
     * @return 掲示板
     */
    @GetMapping("{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable("id") Long id) {
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
    @PutMapping
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
    @PatchMapping("{id}")
    public ResponseEntity<Board> patchBoard(@PathVariable("id") Long id, @RequestBody @Valid Board board) {
        Board updateBoard = boardService.patchBoard(id, board); 
        if(updateBoard != null)
            return new ResponseEntity<Board>(updateBoard, HttpStatus.OK);
        return new ResponseEntity<Board>(HttpStatus.NOT_FOUND);
    }

    /**
     * 掲示板削除
     *
     * @param id 削除したい掲示板ID
     */
    // @DeleteMapping("/boards/{id}")
    // public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
    //     Board deleteBoard = boardRepository.findById(id).orElseThrow(() -> new NotFoundException(id + " is not found."));

    //     boardRepository.delete(deleteBoard);
    //     return ResponseEntity.ok().build();
    // }
}
