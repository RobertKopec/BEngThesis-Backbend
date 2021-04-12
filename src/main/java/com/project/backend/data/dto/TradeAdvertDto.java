package com.project.backend.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TradeAdvertDto {
    private long tradeAdvertId;

    private String title;
    private String description;
    private double price;
    private String category;
    private String telNumber;
    private String city;
    private String state;
    private String picture;
    private boolean personal;
    private boolean shipment;
    private String tags;
    private String userName;

    private UserDto user;

    public TradeAdvertDto(long tradeAdvertId, String title, String description, double price,
                          String category, String telNumber, String city, String state, String picture, boolean personal,
                          boolean shipment, String tags, String userName, UserDto user) {
        this.tradeAdvertId = tradeAdvertId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.telNumber = telNumber;
        this.city = city;
        this.state = state;
        this.picture = picture;
        this.personal = personal;
        this.shipment = shipment;
        this.tags = tags;
        this.userName = userName;
        this.user = user;
    }

    public TradeAdvertDto() {
    }
}
