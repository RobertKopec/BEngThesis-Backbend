package com.project.backend.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long favouriteId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne
    @JoinColumn(name="tradeAdvertId")
    private TradeAdvert tradeAdvert;

    public Favourite() {
    }

    public Favourite(User user, TradeAdvert tradeAdvert) {
        this.user = user;
        this.tradeAdvert = tradeAdvert;
    }
}
