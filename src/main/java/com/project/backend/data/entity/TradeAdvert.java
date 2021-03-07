package com.project.backend.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class TradeAdvert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tradeAdvertId;

    private String title;
    @Column( length = 1000000 )
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "tradeAdvert")
    private List<Favourite> favourites;

    public TradeAdvert(String title, String description, double price, Date createDate, String category, String telNumber, String city, String state, String picture, boolean personal, boolean shipment,String tags, User user) {
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

    public TradeAdvert() {
    }
}
