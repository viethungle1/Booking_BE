package org.example.minitest1.service;

import org.example.minitest1.model.User;

import java.util.List;

public interface IUserService extends GeneralService<User>{
    List<User> findAll();
}
