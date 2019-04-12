package com.project.backend.data.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long userID;

    private String login;
    private String password;
    private String userName;
    private String telNumber;
    private String email;
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<TradeAdvert> tradeAdverts;

    public User(String login, String password, String userName, String telNumber, String email, String address) {
        this.login = login;
        this.password = password;
        this.userName = userName;
        this.telNumber = telNumber;
        this.email = email;
        this.address = address;
    }

    public User() {
    }



}

