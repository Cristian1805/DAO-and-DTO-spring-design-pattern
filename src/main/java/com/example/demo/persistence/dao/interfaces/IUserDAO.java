package com.example.demo.persistence.dao.interfaces;

import com.example.demo.persistence.entity.UserEntity;
import org.apache.catalina.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {

    List<UserEntity> findAll();

    Optional findById(Long id);

    UserEntity createUser(UserEntity userEntity);

    void updateUser(UserEntity userEntity);

    void deleteUser(UserEntity userEntity);
}
