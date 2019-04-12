package com.project.backend.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class TradeAdvert {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="userID")
    private User user;

    public TradeAdvert(String title, String description, double price, Date createDate, String category, String number, String address, String state, String picture, boolean personal, boolean shipment, User user) {
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

    public TradeAdvert() {
    }

}
