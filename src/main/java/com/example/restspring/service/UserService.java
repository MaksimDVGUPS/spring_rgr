package com.example.restspring.service;

import com.example.restspring.entity.UserEntity;
import com.example.restspring.exception.UserAlreadyExistException;
import com.example.restspring.exception.UserNotFoundException;
import com.example.restspring.model.User;
import com.example.restspring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("Пользователь с таким именем существует");
        }
        return userRepo.save(user);
    }

    public User login(String login, String password) throws UserNotFoundException {
        UserEntity user = userRepo.findByUsername(login);

        if (user == null) {
            throw new UserNotFoundException("Некорректный логин и/или пароль");
        }

        if (!user.getPassword().equals(password)) {
            throw new UserNotFoundException("Некорректный логин и/или пароль");
        }

        return User.toModel(user);
    }
}
