package com.example.zuzex.service;

import com.example.zuzex.entity.HouseEntity;
import com.example.zuzex.exception.HouseAlreadyExistException;
import com.example.zuzex.exception.HouseIsNotFoundException;
import com.example.zuzex.model.HouseModel;

public interface HouseService {

    public HouseEntity createHouse(HouseEntity house) throws HouseAlreadyExistException;

    public HouseModel getHouse(Long id) throws HouseIsNotFoundException;

    public HouseEntity updateHouse(HouseEntity house) throws HouseIsNotFoundException;

    public void deleteHouse(Long id) throws HouseIsNotFoundException;
}
