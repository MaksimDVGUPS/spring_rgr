package com.example.restspring.controller;

import com.example.restspring.entity.BoardEntity;
import com.example.restspring.exception.BoardNotFoundException;
import com.example.restspring.exception.UserNotFoundException;
import com.example.restspring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tables")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping
    public ResponseEntity createBoard(@RequestBody BoardEntity table,
                                     @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(boardService.createBoard(table, userId));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("/{tableId}")
    public ResponseEntity updateBoard(@RequestBody BoardEntity board, @PathVariable Long tableId) {
        try {
            return ResponseEntity.ok(boardService.updateBoard(tableId, board.getName()));
        } catch (BoardNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{tableId}")
    public ResponseEntity deleteBoard(@PathVariable Long tableId) {
        try {
            boardService.deleteBoard(tableId);
            return ResponseEntity.ok("Доска успешно удалена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
