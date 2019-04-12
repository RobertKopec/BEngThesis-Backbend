package com.project.backend.controller;

import com.project.backend.data.entity.User;
import com.project.backend.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/api/user" )
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public void addNewUser (@RequestBody User requestUser){
        User user = new User(requestUser.getLogin(),requestUser.getPassword(),requestUser.getUserName(),requestUser.getTelNumber(),requestUser.getEmail(),requestUser.getAddress());
        userRepository.save(user);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{login}")
    public @ResponseBody User getUser(HttpServletResponse response, @PathVariable String login){
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        return userRepository.findUserByLogin(login);
    }
}
