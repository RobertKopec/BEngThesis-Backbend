package com.project.backend.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private long userId;
    private String city;
    private String email;
    private String password;
    private String telNumber;
    private String userName;
    private String role;
    private List<TradeAdvertDto> tradeAdverts;

    public UserDto(long userId, String city, String role, String email, String password, String telNumber, String userName) {
        this.userId = userId;
        this.city = city;
        this.email = email;
        this.password = password;
        this.telNumber = telNumber;
        this.userName = userName;
        this.role = role;
    }

    public UserDto() {
    }
}
