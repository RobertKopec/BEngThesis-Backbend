package com.project.backend.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TradeAdvertDto {
    private long ID;

    private String title;
    private String description;
    private double price;
    private Date createDate;
    private String category;
    private String telNumber;
    private String city;
    private String state;
    private String picture;
    private boolean personal;
    private boolean shipment;
    private String tags;

    private UserDto user;

    public TradeAdvertDto(long ID, String title, String description, double price, Date createDate, String category, String telNumber, String city, String state, String picture, boolean personal, boolean shipment, String tags, UserDto user) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.category = category;
        this.telNumber = telNumber;
        this.city = city;
        this.state = state;
        this.picture = picture;
        this.personal = personal;
        this.shipment = shipment;
        this.tags = tags;
        this.user = user;
    }

    public TradeAdvertDto() {
    }
}
