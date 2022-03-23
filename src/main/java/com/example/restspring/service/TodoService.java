package com.example.restspring.service;

import com.example.restspring.entity.BoardEntity;
import com.example.restspring.entity.TodoEntity;
import com.example.restspring.exception.BoardNotFoundException;
import com.example.restspring.model.Todo;
import com.example.restspring.repository.BoardRepo;
import com.example.restspring.repository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private BoardRepo boardRepo;

    public Todo createTodo(TodoEntity todo, Long boardId) throws BoardNotFoundException {
        Optional<BoardEntity> board = boardRepo.findById(boardId);

        if (board.isEmpty()) {
            throw new BoardNotFoundException("Доска не найдена");
        }

        todo.setBoard(board.get());
        todo.setCompleted(false);
        return Todo.toModel(todoRepo.save(todo));
    }

    public Todo complete(Long id) {
        TodoEntity todo = todoRepo.findById(id).get();
        todo.setCompleted(!todo.getCompleted());
        return Todo.toModel(todoRepo.save(todo));
    }
}
