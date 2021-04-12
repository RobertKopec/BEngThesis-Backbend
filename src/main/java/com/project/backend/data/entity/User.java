package com.project.backend.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String password;
    private String userName;
    private String telNumber;
    private String email;
    private String city;
    private String role;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<TradeAdvert> tradeAdverts;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "user")
    private List<Favourite> favourites;

    public User(String password, String userName, String telNumber, String email, String city, String role,
                List<TradeAdvert> tradeAdverts) {
        this.password = password;
        this.userName = userName;
        this.telNumber = telNumber;
        this.email = email;
        this.city = city;
        this.role = role;
        this.tradeAdverts = tradeAdverts;
    }

    public User() {
    }
}
