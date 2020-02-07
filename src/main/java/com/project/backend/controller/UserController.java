package com.project.backend.controller;

import com.project.backend.data.dto.UserDto;
import com.project.backend.data.entity.TradeAdvert;
import com.project.backend.data.entity.User;
import com.project.backend.data.repository.TradeAdvertRepository;
import com.project.backend.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TradeAdvertRepository tradeAdvertRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public void addNewUser(@RequestBody UserDto requestUserDto) {
        List<TradeAdvert> tradeAdverts = this.tradeAdvertRepository.getAdvertsByUserId(requestUserDto.getUserID());
        User user = new User(requestUserDto.getLogin(), requestUserDto.getPassword(), requestUserDto.getUserName(),
                requestUserDto.getTelNumber(), requestUserDto.getEmail(), requestUserDto.getAddress(), tradeAdverts);
        userRepository.save(user);
    }

    @GetMapping("/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{login}")
    public @ResponseBody
    User getUser(HttpServletResponse response, @PathVariable String login) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        return userRepository.findUserByLogin(login);
    }
}
