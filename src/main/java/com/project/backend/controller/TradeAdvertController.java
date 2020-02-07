package com.project.backend.controller;

import com.project.backend.data.dto.TradeAdvertDto;
import com.project.backend.data.entity.TradeAdvert;
import com.project.backend.data.entity.User;
import com.project.backend.data.repository.TradeAdvertRepository;
import com.project.backend.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public void addNewTradeAdvert(@RequestBody TradeAdvertDto requestTradeAdvertDto) {
        User user = this.userRepository.findUserByLogin(requestTradeAdvertDto.getUser().getLogin());
        TradeAdvert tradeAdvert = new TradeAdvert(requestTradeAdvertDto.getTitle(), requestTradeAdvertDto.getDescription(), requestTradeAdvertDto.getPrice(), requestTradeAdvertDto.getCreateDate(),
                requestTradeAdvertDto.getCategory(), requestTradeAdvertDto.getNumber(), requestTradeAdvertDto.getAddress(), requestTradeAdvertDto.getState(), requestTradeAdvertDto.getPicture(),
                requestTradeAdvertDto.isPersonal(), requestTradeAdvertDto.isShipment(), user);
        tradeAdvertRepository.save(tradeAdvert);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public @ResponseBody
    Iterable<TradeAdvert> getAllTradeAdverts() {
        return tradeAdvertRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{userID}")
    public @ResponseBody
    List<TradeAdvert> getAdvertByUserId(HttpServletResponse response, @PathVariable Long userID) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        return tradeAdvertRepository.getAdvertsByUserId(userID);
    }
}
