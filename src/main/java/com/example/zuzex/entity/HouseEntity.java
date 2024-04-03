package com.example.zuzex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "houses")
public class HouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "house")
    private List<UserEntity> residents;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity owner;

    public void addResident(UserEntity user) {
        if (residents == null) {
            residents = new ArrayList<>();
        }
        residents.add(user);
        setResidents(residents);
    }


}
