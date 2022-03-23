package com.example.restspring.model;

import com.example.restspring.entity.BoardEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private Long id;
    private String name;
    private List<Todo> todos;

    public static Board toModel(BoardEntity entity) {
        Board model = new Board();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setTodos(entity.getTodos().stream().map(Todo::toModel).collect(Collectors.toList()));
        return model;
    }

    public Board() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}
