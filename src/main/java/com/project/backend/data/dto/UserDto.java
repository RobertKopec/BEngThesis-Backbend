package com.project.backend.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private long userID;
    private String city;
    private String email;
    private String login;
    private String password;
    private String telNumber;
    private String userName;
    private List<TradeAdvertDto> tradeAdverts;

    public UserDto(long userID, String city, String email, String login, String password, String telNumber, String userName) {
        this.userID = userID;
        this.city = city;
        this.email = email;
        this.login = login;
        this.password = password;
        this.telNumber = telNumber;
        this.userName = userName;
    }

    public UserDto() {
    }
}
