package com.example.restspring.service;

import com.example.restspring.entity.BoardEntity;
import com.example.restspring.entity.UserEntity;
import com.example.restspring.exception.BoardNotFoundException;
import com.example.restspring.exception.UserNotFoundException;
import com.example.restspring.model.Board;
import com.example.restspring.repository.BoardRepo;
import com.example.restspring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardRepo boardRepo;
    @Autowired
    private UserRepo userRepo;

    public Board createBoard (BoardEntity board, Long userId) throws UserNotFoundException {
        Optional<UserEntity> user = userRepo.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException("Пользователь не найден");
        }

        board.setUser(user.get());
        board.setTodos(new ArrayList<>());

        return Board.toModel(boardRepo.save(board));
    }

    public Board updateBoard (Long tableId, String name) throws BoardNotFoundException {
        BoardEntity board = boardRepo.findById(tableId).get();

        if (board == null) {
            throw new BoardNotFoundException("Таблица не найдена");
        }

        board.setName(name);

        return Board.toModel(boardRepo.save(board));
    }

    public void deleteBoard (Long id) {
        try {
            boardRepo.deleteById(id);
        } catch (Exception e) {

        }
    }
}
