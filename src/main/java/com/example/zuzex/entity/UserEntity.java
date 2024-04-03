package com.example.zuzex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private String age;
    @Column(name = "password")
    private String password;
    @Column(name = "Token")
    private String Token;

    @ManyToOne
    @JoinColumn(name = "houseId", referencedColumnName = "id")
    private HouseEntity house;

}
