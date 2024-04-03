package com.example.zuzex.model;

import com.example.zuzex.entity.HouseEntity;
import com.example.zuzex.entity.UserEntity;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class HouseModel {
    private String adress;
    private Long owner_id;
    private List<String> residents;

    public static HouseModel toModel(HouseEntity house){
        HouseModel houseModel = new HouseModel();
            houseModel.setAdress(house.getAddress());

            if(house.getOwner() !=null) {
                houseModel.setOwner_id(house.getOwner().getId());
            }

            if(house.getResidents() != null) {
                houseModel.setResidents(house.getResidents().stream().map(UserEntity::getName).collect(Collectors.toList()));
            }
        return houseModel;
    }
    public HouseModel() {
    }
}
