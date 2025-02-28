package com.example.demo.persistence.entity;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "users" )

public class UserEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)


    private Long id;

    private String name;

    @Column(name = "last_name")
    private String lastName;
    private String email;
    private byte age;
}
