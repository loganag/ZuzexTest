package com.example.zuzex.model;

import com.example.zuzex.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class UserModel {

    private String Name;
    private String Age;

    private List<String> livingPlace;
    private List<HouseModel> houseEntity;

    public UserModel() {
    }

    public static UserModel toModel(UserEntity user) {
        UserModel userModel = new UserModel();
        userModel.setAge(user.getAge());
        userModel.setName(user.getName());
        return userModel;
    }
}
