package com.example.zuzex.controller;

import com.example.zuzex.entity.HouseEntity;
import com.example.zuzex.exception.HouseAlreadyExistException;
import com.example.zuzex.exception.HouseIsNotFoundException;
import com.example.zuzex.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/house")
public class HouseController {
    @Autowired
    HouseService houseSevice;

    @PostMapping("/create")
    private ResponseEntity createHouse(@RequestBody HouseEntity house) {
        try {
            houseSevice.createHouse(house);
            return ResponseEntity.ok().body("House created");
        } catch (HouseAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/read")
    private ResponseEntity readHouse(@RequestParam Long houseId) {
        try {
            return ResponseEntity.ok().body(houseSevice.getHouse(houseId));
        } catch (HouseIsNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    private ResponseEntity updateHouse(@RequestBody HouseEntity house) {
        try {
            houseSevice.updateHouse(house);
            return ResponseEntity.ok().body("House updated");
        } catch (HouseIsNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    private ResponseEntity deleteHouse(@RequestParam Long houseId) {
        try {
            houseSevice.deleteHouse(houseId);
            return ResponseEntity.ok().body("House deleted");
        } catch (HouseIsNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
