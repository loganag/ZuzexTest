package com.example.zuzex.controller;

import com.example.zuzex.entity.HouseEntity;
import com.example.zuzex.entity.UserEntity;
import com.example.zuzex.exception.UserAlreadyExistException;
import com.example.zuzex.exception.UserIsNotFoundException;
import com.example.zuzex.service.HouseService;
import com.example.zuzex.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    HouseService houseService;

    @PostMapping("/create")
    private ResponseEntity createUser(@RequestBody UserEntity user) {
        try {
            return ResponseEntity.ok().body("User created " + userService.createUser(user));
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    private ResponseEntity getUser(@RequestParam Long idUser) {
        try {
            return ResponseEntity.ok().body(userService.readUser(idUser));
        } catch (UserIsNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    private ResponseEntity updateUser(@RequestBody UserEntity user) {
        try {
            userService.updateUser(user);
            return ResponseEntity.ok().body("User updated");
        } catch (UserIsNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    private ResponseEntity deleteUser(Long id_user) {
        try {
            userService.deleteUser(id_user);
            return ResponseEntity.ok().body("User deleted");
        } catch (UserIsNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/buyHouse")
    private ResponseEntity buyHouse(@RequestParam Long ownerId,
                                    @RequestBody HouseEntity house) {
        try {
            return ResponseEntity.ok().body(userService.buyHouse(ownerId, house));
        } catch (UserIsNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PutMapping("/addResident/{house_id}")
    private ResponseEntity addResident(@PathVariable Long houseId,
                                       @RequestParam Long userId) {
        try {
            return ResponseEntity.ok().body(userService.addResident(userId, houseId));
        } catch (UserIsNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/delResident/{house_id}")
    private ResponseEntity delResident(@PathVariable Long house_id,
                                       @RequestParam Long user_id) {
        try {
            return ResponseEntity.ok().body(userService.delResident(user_id, house_id));
        } catch (UserIsNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
