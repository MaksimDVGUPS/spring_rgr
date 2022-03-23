package com.example.restspring.repository;

import com.example.restspring.entity.BoardEntity;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepo extends CrudRepository<BoardEntity, Long> {
}
