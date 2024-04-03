package com.example.zuzex.repository;

import com.example.zuzex.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface  UserRepo extends CrudRepository<UserEntity,Long> {
    UserEntity findByName(String name);
}
