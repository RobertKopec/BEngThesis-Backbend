package com.project.backend.controller;

import com.project.backend.data.entity.TradeAdvert;
import com.project.backend.data.repository.TradeAdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/tradeAdvert")
public class TradeAdvertController {

    @Autowired
    private TradeAdvertRepository tradeAdvertRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public void addNewTradeAdvert (@RequestBody TradeAdvert requestTradeAdvert){
        TradeAdvert tradeAdvert = new TradeAdvert(requestTradeAdvert.getTitle(), requestTradeAdvert.getDescription(), requestTradeAdvert.getPrice(), requestTradeAdvert.getCreateDate(),
                requestTradeAdvert.getCategory(), requestTradeAdvert.getNumber(), requestTradeAdvert.getAddress(), requestTradeAdvert.getState(), requestTradeAdvert.getPicture(),
                requestTradeAdvert.isPersonal(), requestTradeAdvert.isShipment(), requestTradeAdvert.getUser());
        tradeAdvertRepository.save(tradeAdvert);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public @ResponseBody Iterable<TradeAdvert> getAllTradeAdverts() {return tradeAdvertRepository.findAll();}
}
