package com.example.zuzex.service.impl;


import com.example.zuzex.entity.HouseEntity;
import com.example.zuzex.entity.UserEntity;
import com.example.zuzex.exception.UserAlreadyExistException;
import com.example.zuzex.exception.UserIsNotFoundException;
import com.example.zuzex.exception.UserIsTheOwnerHouseException;
import com.example.zuzex.model.HouseModel;
import com.example.zuzex.model.UserModel;
import com.example.zuzex.repository.HouseRepo;
import com.example.zuzex.repository.UserRepo;
import com.example.zuzex.security.jwt.JwtTokenProvider;
import com.example.zuzex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final HouseRepo houseRepo;


    JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, HouseRepo houseRepo, JwtTokenProvider jwtTokenProvider) {
        this.userRepo = userRepo;
        this.houseRepo = houseRepo;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String createUser(UserEntity user) throws UserAlreadyExistException {

        if (userRepo.findByName(user.getName()) != null) {
            throw new UserAlreadyExistException("Такой пользователь уже существует");
        }

        String token = jwtTokenProvider.createToken(user.getName());

        user.setToken(token);

        userRepo.save(user);
        return token;
    }

    public UserEntity findByUserName(String name) {
        return userRepo.findByName(name);
    }

    public UserModel readUser(Long id) throws UserIsNotFoundException {
        UserEntity user = userRepo.findById(id).get();

        if (!userRepo.findById(id).isPresent()) {
            throw new UserIsNotFoundException("Такого пользователя не существует");
        }

        return UserModel.toModel(user);
    }

    public UserEntity updateUser(UserEntity user) throws UserIsNotFoundException {
        if (!userRepo.findById(user.getId()).isPresent()) {
            throw new UserIsNotFoundException("Такого пользователя не существует");
        }
        UserEntity userEntity = userRepo.findById(user.getId()).get();
        userEntity.setName(user.getName());
        userEntity.setAge(user.getAge());
        return userRepo.save(userEntity);
    }

    public void deleteUser(Long id) throws UserIsNotFoundException {
        if (!userRepo.findById(id).isPresent()) {
            throw new UserIsNotFoundException("Такого пользователя не существует");
        }
        userRepo.deleteById(id);
    }

    public HouseModel buyHouse(Long ownerId, HouseEntity house) throws Exception {
        try {
            UserEntity user = userRepo.findById(ownerId).get();
            if (!userRepo.findById(house.getId()).isPresent() || !houseRepo.findById(house.getId()).isPresent()) {
                throw new UserIsNotFoundException("Такого пользователя/дома не существует");
            }
            house.setOwner(user);
            return HouseModel.toModel(houseRepo.save(house));
        } catch (Exception e) {
            throw new Exception("Exception buyHouse:\n" + e.getMessage());
        }
    }

    public HouseModel addResident(Long userId, Long houseId) throws Exception {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserEntity user = userRepo.findById(userId).get();
            HouseEntity house = houseRepo.findById(houseId).get();
            if (user.equals(house.getOwner())) {
                throw new UserIsTheOwnerHouseException("Пользователь является хозяином дома");
            }

            if (!userRepo.findById(house.getId()).isPresent() || !houseRepo.findById(house.getId()).isPresent()) {
                throw new UserIsNotFoundException("Такого пользователя/дома не существует");
            }

            if (house.getResidents().stream().anyMatch(UserEntity -> UserEntity.equals(user))) {
                throw new UserAlreadyExistException("Данный пользователь уже живет в этом доме");
            }

            house.getResidents().add(user);
            return HouseModel.toModel(houseRepo.save(house));


        } catch (Exception e) {
            throw new Exception("Exception addResident:\n" + e.getMessage());
        }
    }

    public HouseModel delResident(Long userId, Long houseId) throws Exception {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserEntity user = userRepo.findById(userId).get();
            HouseEntity house = houseRepo.findById(houseId).get();

            if (!userRepo.findById(house.getId()).isPresent() || !houseRepo.findById(house.getId()).isPresent()) {
                throw new UserIsNotFoundException("Такого пользователя/дома не существует");
            }

            if (house.getResidents().stream().anyMatch(resident -> resident.equals(user))) {
                house.getResidents().remove(user);

            } else {
                throw new UserIsNotFoundException("Данный пользователь не живет в этом доме");
            }


            return HouseModel.toModel(houseRepo.save(house));
        } catch (Exception e) {
            throw new Exception("Exception delResident:\n" + e.getMessage());
        }
    }


}
