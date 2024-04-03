package com.example.zuzex.service;

import com.example.zuzex.entity.HouseEntity;
import com.example.zuzex.entity.UserEntity;
import com.example.zuzex.exception.UserAlreadyExistException;
import com.example.zuzex.exception.UserIsNotFoundException;
import com.example.zuzex.model.HouseModel;
import com.example.zuzex.model.UserModel;

public interface UserService {
    public String createUser(UserEntity user) throws UserAlreadyExistException;

    public UserEntity findByUserName(String name);

    public UserModel readUser(Long id) throws UserIsNotFoundException;

    public UserEntity updateUser(UserEntity user) throws UserIsNotFoundException;

    public void deleteUser(Long id) throws UserIsNotFoundException;

    public HouseModel buyHouse(Long owner_id, HouseEntity house) throws Exception;

    public HouseModel addResident(Long user_id, Long house_id) throws Exception;

    public HouseModel delResident(Long user_id, Long house_id) throws Exception;
}
