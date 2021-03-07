package com.project.backend.controller;

import com.project.backend.data.dto.UserDto;
import com.project.backend.data.entity.Favourite;
import com.project.backend.data.entity.TradeAdvert;
import com.project.backend.data.entity.User;
import com.project.backend.data.repository.FavouriteRepository;
import com.project.backend.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FavouriteRepository favouriteRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public @ResponseBody
    void addNewUser(HttpServletResponse response, @RequestBody UserDto requestUserDto) {
        List<TradeAdvert> tradeAdverts = new ArrayList<>();
        User newUser = new User(requestUserDto.getLogin(), requestUserDto.getPassword(), requestUserDto.getUserName(),
                requestUserDto.getTelNumber(), requestUserDto.getEmail(), requestUserDto.getCity(), tradeAdverts);
        User user = userRepository.findUserByLogin(requestUserDto.getLogin());
        if (user != null)
            response.setStatus(403);
        else {
            response.setStatus(200);
            userRepository.save(newUser);
        }
    }

    @GetMapping("/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public @ResponseBody
    User getUser(HttpServletResponse response, @RequestBody UserDto requestUserDto) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        User user = userRepository.findUserByLogin(requestUserDto.getLogin());
        if (user != null){
            if(requestUserDto.getPassword().equals(user.getPassword()))
                response.setStatus(200);
            else
                response.setStatus(403);
        } else {
            response.setStatus(400);
        }
        return user;
    }
}
