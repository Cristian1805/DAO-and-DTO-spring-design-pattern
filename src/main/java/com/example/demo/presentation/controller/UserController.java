package com.example.demo.presentation.controller;


import com.example.demo.presentation.dto.UserDTO;
import com.example.demo.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private IUserService userService;

    //FindAll
    @GetMapping("/find")
    public ResponseEntity<List<UserDTO>> FindAll(){
        return new ResponseEntity<>(this.userService.findAll(), null, 200);
    }

    //FindById
    @GetMapping("/find/{id}")
    public ResponseEntity<UserDTO> FindById(@PathVariable Long id){
        return new ResponseEntity<>(this.userService.findById(id), null, 200);
    }

    //Creacion del usuario
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(this.userService.createUser(userDTO), null, 201);
    }

    //Update
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(this.userService.updateUser(id, userDTO), null, 201);
    }

    //Delete User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(null, null, 204);
    }
}
