package com.project.backend.controller;

import com.project.backend.data.entity.Favourite;
import com.project.backend.data.repository.FavouriteRepository;
import com.project.backend.data.repository.TradeAdvertRepository;
import com.project.backend.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.project.backend.data.entity.TradeAdvert;
import com.project.backend.data.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(path = "/api/favourite")
public class FavouriteController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FavouriteRepository favouriteRepository;
    @Autowired
    private TradeAdvertRepository tradeAdvertRepository;
    private final String CLIENT_APP_DEV_URL = "http://localhost:4200";

    @CrossOrigin(origins = CLIENT_APP_DEV_URL)
    @GetMapping("/{userId}")
    public @ResponseBody
    List<Favourite> getFavouriteAdvertsByUserId(HttpServletResponse response, @PathVariable Long userId) {
        response.setHeader("Access-Control-Allow-Origin", CLIENT_APP_DEV_URL);
        User user = userRepository.findUserByUserId(userId);
        return favouriteRepository.findAllByUser(user);
    }

    @CrossOrigin(origins = CLIENT_APP_DEV_URL)
    @PostMapping("/add/{userId}/{advertId}")
    public @ResponseBody
    void addNewFavouriteTradeAdvert(HttpServletResponse response, @PathVariable("userId") Long userId, @PathVariable("advertId") Long advertId) {
        User user = userRepository.findUserByUserId(userId);
        TradeAdvert advert = tradeAdvertRepository.getAdvertsByTradeAdvertId(advertId);

        if(user != null && advert != null){
            if(this.favouriteRepository.findByUserAndTradeAdvert(user,advert) != null){
                response.setStatus(409);
            } else {
                response.setStatus(200);
                this.favouriteRepository.save(new Favourite(user, advert));
            }
        } else
            response.setStatus(400);
    }

    @Transactional
    @CrossOrigin(origins = CLIENT_APP_DEV_URL)
    @RequestMapping(value = "/remove/{userId}/{advertId}", method = RequestMethod.DELETE)
    public @ResponseBody
    void removeFavouriteTradeAdvert(HttpServletResponse response, @PathVariable("userId") Long userId, @PathVariable("advertId") Long advertId) {
        response.setHeader("Access-Control-Allow-Origin", CLIENT_APP_DEV_URL);
        User user = userRepository.findUserByUserId(userId);
        TradeAdvert advert = tradeAdvertRepository.getAdvertsByTradeAdvertId(advertId);

        if(user != null && advert != null){
            if(this.favouriteRepository.findByUserAndTradeAdvert(user,advert) != null){
                response.setStatus(200);
                this.favouriteRepository.deleteByUserAndTradeAdvert(user, advert);
            } else {
                response.setStatus(404);
            }
        } else
            response.setStatus(400);
    }
}
