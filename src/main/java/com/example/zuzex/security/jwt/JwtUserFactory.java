package com.example.zuzex.security.jwt;

import com.example.zuzex.entity.UserEntity;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(UserEntity user) {
        return new JwtUser(
                user.getId(),
                user.getName(),
                user.getPassword()
        );
    }


}
