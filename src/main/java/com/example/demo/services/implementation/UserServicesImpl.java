package com.example.demo.services.implementation;

import com.example.demo.persistence.dao.interfaces.IUserDAO;
import com.example.demo.persistence.entity.UserEntity;
import com.example.demo.presentation.dto.UserDTO;
import com.example.demo.services.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class UserServicesImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public List<UserDTO> findAll() {

        ModelMapper modelMapper = new ModelMapper();

        return this.userDAO.findAll().stream().map(userEntity -> modelMapper.map(userEntity, UserDTO.class)).toList();
    }

    @Override
    public UserDTO findById(Long id) {

        Optional userEntity = this.userDAO.findById(id);

        if (userEntity.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(userEntity.get(), UserDTO.class);
        } else {
            return new UserDTO();
        }
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
            this.userDAO.createUser(userEntity);
            return userDTO;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el usuario");
        }
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {


        Optional<UserEntity> userEntity = this.userDAO.findById(id);


        if (userEntity.isPresent()) {
            UserEntity currentUserEntity = userEntity.get();
            currentUserEntity.setName(userDTO.getName());
            currentUserEntity.setLastName(userDTO.getLastName());
            currentUserEntity.setEmail(userDTO.getEmail());
            currentUserEntity.setAge(userDTO.getAge());

            this.userDAO.updateUser(currentUserEntity);


            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(currentUserEntity, UserDTO.class);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }


    @Override
    public String deleteUser(Long id) {

        Optional<UserEntity> userEntity = this.userDAO.findById(id);

        if(userEntity.isPresent()){
            UserEntity currenUserEntity = userEntity.get();
            this.userDAO.deleteUser(currenUserEntity);
            return "Usuario eliminado";

        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }
}