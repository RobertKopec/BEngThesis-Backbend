package com.project.backend.controller;

import com.project.backend.data.dto.TradeAdvertDto;
import com.project.backend.data.entity.TradeAdvert;
import com.project.backend.data.entity.User;
import com.project.backend.data.repository.TradeAdvertRepository;
import com.project.backend.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(path = "/api/tradeAdvert")
public class TradeAdvertController {

    @Autowired
    private TradeAdvertRepository tradeAdvertRepository;
    @Autowired
    private UserRepository userRepository;
    private final String CLIENT_APP_DEV_URL = "http://localhost:4200";

    @CrossOrigin(origins = CLIENT_APP_DEV_URL)
    @PostMapping("/add")
    public @ResponseBody
    void addNewTradeAdvert(HttpServletResponse response, @RequestBody TradeAdvertDto requestTradeAdvertDto) {
        User user = this.userRepository.findUserByEmail(requestTradeAdvertDto.getUser().getEmail());
        TradeAdvert tradeAdvert = new TradeAdvert(requestTradeAdvertDto.getTitle(), requestTradeAdvertDto.getDescription(),
                requestTradeAdvertDto.getPrice(), requestTradeAdvertDto.getCategory(),
                requestTradeAdvertDto.getTelNumber(), requestTradeAdvertDto.getCity(), requestTradeAdvertDto.getState(),
                requestTradeAdvertDto.getPicture(), requestTradeAdvertDto.isPersonal(), requestTradeAdvertDto.isShipment(),
                requestTradeAdvertDto.getTags(), requestTradeAdvertDto.getUserName(), user);

        if (user != null && tradeAdvert.getTitle() != null && tradeAdvert.getDescription() != null && tradeAdvert.getPrice() != 0
                && tradeAdvert.getCategory() != null && tradeAdvert.getTelNumber() != null
                && tradeAdvert.getCity() != null && tradeAdvert.getState() != null && tradeAdvert.getPicture() != null) {
            response.setStatus(201);
            tradeAdvertRepository.save(tradeAdvert);
        } else
            response.setStatus(400);

    }

    @CrossOrigin(origins = CLIENT_APP_DEV_URL)
    @GetMapping("/all")
    public @ResponseBody
    Iterable<TradeAdvert> getAllTradeAdverts() {
        return tradeAdvertRepository.findAll();
    }

    @CrossOrigin(origins = CLIENT_APP_DEV_URL)
    @GetMapping("/{userId}")
    public @ResponseBody
    List<TradeAdvert> getAdvertByUserId(HttpServletResponse response, @PathVariable Long userId) {
        response.setHeader("Access-Control-Allow-Origin", CLIENT_APP_DEV_URL);
        User user = userRepository.findUserByUserId(userId);
        return tradeAdvertRepository.getAdvertsByUser(user);
    }

    @CrossOrigin(origins = CLIENT_APP_DEV_URL)
    @PutMapping("/update")
    public @ResponseBody
    void updateAdvert(HttpServletResponse response, @RequestBody TradeAdvertDto requestAdvertDto) {
        response.setHeader("Access-Control-Allow-Origin", CLIENT_APP_DEV_URL);
        TradeAdvert advert = tradeAdvertRepository.getAdvertsByTradeAdvertId(requestAdvertDto.getTradeAdvertId());
        if(advert != null) {
            advert.setTitle(requestAdvertDto.getTitle());
            advert.setDescription(requestAdvertDto.getDescription());
            advert.setPrice(requestAdvertDto.getPrice());
            advert.setTelNumber(requestAdvertDto.getTelNumber());
            advert.setCategory(requestAdvertDto.getCategory());
            advert.setCity(requestAdvertDto.getCity());
            advert.setState(requestAdvertDto.getState());
            advert.setPicture(requestAdvertDto.getPicture());
            advert.setPersonal(requestAdvertDto.isPersonal());
            advert.setShipment(requestAdvertDto.isShipment());
            advert.setTags(requestAdvertDto.getTags());
            advert.setUserName(requestAdvertDto.getUserName());

            this.tradeAdvertRepository.save(advert);
            response.setStatus(200);
        } else {
            response.setStatus(404);
        }
    }

    @Transactional
    @CrossOrigin(origins = CLIENT_APP_DEV_URL)
    @RequestMapping(value = "/remove/{advertId}", method = RequestMethod.DELETE)
    public @ResponseBody
    void removeTradeAdvert(HttpServletResponse response, @PathVariable("advertId") Long advertId) {
        response.setHeader("Access-Control-Allow-Origin", CLIENT_APP_DEV_URL);
        TradeAdvert advert = tradeAdvertRepository.getAdvertsByTradeAdvertId(advertId);

        if(advert != null){
            response.setStatus(200);
            this.tradeAdvertRepository.deleteAdvertByTradeAdvertId(advertId);
        } else
            response.setStatus(404);
    }
}
