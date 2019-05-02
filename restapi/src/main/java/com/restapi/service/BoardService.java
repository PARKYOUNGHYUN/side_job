package com.restapi.service;

import java.util.List;
import java.util.Optional;

import com.restapi.entity.Board;
import com.restapi.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public Board createBoard(Board board) {
        try {
            return boardRepository.save(board);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> getBoard(Long id){
        return boardRepository.findById(id);
    }

    // public Board updateBoard(Long id, Board board){
    //     final Optional<Board> fetchedBoard = boardRepository.findById(id);
    //     if(fetchedBoard.isPresent()){
    //         board.setId(id);
    //         return boardRepository.save(board);
    //     }
    //     else{
    //         return null;
    //     }
    // }
    
    public Board patchBoard(Long id, Board board){
        final Optional<Board> fetchedBoard = boardRepository.findById(id);
        if(fetchedBoard.isPresent()){
            if(board.getTitle() != null) fetchedBoard.get().setTitle(board.getTitle());
            if(board.getContent() != null) fetchedBoard.get().setContent(board.getContent());
            if(board.getEntryStartAt() != null) fetchedBoard.get().setEntryStartAt(board.getEntryStartAt());
            if(board.getEntryEndAt() != null) fetchedBoard.get().setEntryEndAt(board.getEntryEndAt());
            if(board.getPostType() > 0) fetchedBoard.get().setPostType(board.getPostType());
            if(board.getCapacity() >= 0) fetchedBoard.get().setCapacity(board.getCapacity());
            if(board.getStartAt() != null) fetchedBoard.get().setStartAt(board.getStartAt());
            if(board.getEndAt() != null) fetchedBoard.get().setEndAt(board.getEndAt());
            if(board.getPrice() != null) fetchedBoard.get().setPrice(board.getPrice());
            if(board.isDelFlg()) fetchedBoard.get().setDelFlg(board.isDelFlg());
            
            return boardRepository.save(fetchedBoard.get());
        }
        else return null;
    }
}