package com.project.backend.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TradeAdvertDto {
    private long ID;

    private String title;
    private String description;
    private double price;
    private Date createDate;
    private String category;
    private String number;
    private String address;
    private String state;
    private String picture;
    private boolean personal;
    private boolean shipment;

    private UserDto user;

    public TradeAdvertDto(long ID, String title, String description, double price, Date createDate, String category, String number, String address, String state, String picture, boolean personal, boolean shipment, UserDto user) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.category = category;
        this.number = number;
        this.address = address;
        this.state = state;
        this.picture = picture;
        this.personal = personal;
        this.shipment = shipment;
        this.user = user;
    }

    public TradeAdvertDto() {
    }
}
