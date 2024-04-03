package com.example.zuzex.repository;

import com.example.zuzex.entity.HouseEntity;
import org.springframework.data.repository.CrudRepository;

public interface HouseRepo extends CrudRepository<HouseEntity,Long> {
    HouseEntity findByAddress(String address);
}
