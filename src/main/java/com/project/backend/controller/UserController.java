package com.project.backend.controller;

import com.project.backend.data.dto.UserDto;
import com.project.backend.data.entity.TradeAdvert;
import com.project.backend.data.entity.User;
import com.project.backend.data.repository.TradeAdvertRepository;
import com.project.backend.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    private TradeAdvertRepository tradeAdvertRepository;
    private final String CLIENT_APP_DEV_URL = "http://localhost:4200";

    @CrossOrigin(origins = CLIENT_APP_DEV_URL)
    @PostMapping("/add")
    public @ResponseBody
    void addNewUser(HttpServletResponse response, @RequestBody UserDto requestUserDto) {
        List<TradeAdvert> tradeAdverts = new ArrayList<>();
        User newUser = new User(requestUserDto.getPassword(), requestUserDto.getUserName(),
                requestUserDto.getTelNumber(), requestUserDto.getEmail(), requestUserDto.getCity(),
                requestUserDto.getRole(), tradeAdverts);
        User user = userRepository.findUserByEmail(requestUserDto.getEmail());
        if (user != null)
            response.setStatus(403);
        else {
            response.setStatus(200);
            userRepository.save(newUser);
        }
    }

    @CrossOrigin(origins = CLIENT_APP_DEV_URL)
    @GetMapping("/all")
    public @ResponseBody
    Iterable<User> getAllUsers(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", CLIENT_APP_DEV_URL);
        return userRepository.findAll();
    }

    @CrossOrigin(origins = CLIENT_APP_DEV_URL)
    @PostMapping("/login")
    public @ResponseBody
    User getUser(HttpServletResponse response, @RequestBody UserDto requestUserDto) {
        response.setHeader("Access-Control-Allow-Origin", CLIENT_APP_DEV_URL);
        User user = userRepository.findUserByEmail(requestUserDto.getEmail());
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

    @CrossOrigin(origins = CLIENT_APP_DEV_URL)
    @PutMapping("/update")
    public @ResponseBody
    void updateUser(HttpServletResponse response, @RequestBody UserDto requestUserDto) {
        response.setHeader("Access-Control-Allow-Origin", CLIENT_APP_DEV_URL);
        User user = userRepository.findUserByUserId(requestUserDto.getUserId());
        if(user != null) {
            user.setPassword(requestUserDto.getPassword());
            user.setUserName(requestUserDto.getUserName());
            user.setEmail(requestUserDto.getEmail());
            user.setCity(requestUserDto.getCity());
            user.setRole(requestUserDto.getRole());

            this.userRepository.save(user);
            response.setStatus(200);
        } else {
            response.setStatus(404);
        }
    }

    @Transactional
    @CrossOrigin(origins = CLIENT_APP_DEV_URL)
    @RequestMapping(value = "/remove/{userId}", method = RequestMethod.DELETE)
    public @ResponseBody
    void removeUser(HttpServletResponse response, @PathVariable("userId") Long userId) {
        response.setHeader("Access-Control-Allow-Origin", CLIENT_APP_DEV_URL);
        User user = userRepository.findUserByUserId(userId);
        List<TradeAdvert> adverts = tradeAdvertRepository.getAdvertsByUser(user);

        if(user != null){
            if(adverts != null)
                tradeAdvertRepository.deleteAll(adverts);
            this.userRepository.removeUserByUserId(userId);
            response.setStatus(200);
        } else
            response.setStatus(404);
    }
}
