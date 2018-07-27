package com.flyer.chat;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //上线用户
    @PostMapping(value = "addUser")
    public HttpCode addUser(@RequestBody User user){
        User oldUser = userRepository.getUserByUdid(user.getUdid());
        if(oldUser!=null){
            user.setId(oldUser.getId());
        }
        userRepository.save(user);
        return new HttpCode<>(user);
    }

    @Transactional
    @RequestMapping(value = "deleteUser",method = RequestMethod.POST)
    public HttpCode deleteUser(@RequestBody User user){
        userRepository.deleteUserByUdid(user.getUdid());
        return new HttpCode();
    }

    @RequestMapping(value = "getUser",method = RequestMethod.POST)
    public HttpCode getUser(@RequestBody String udid){
        HttpCode<User> httpCode = new HttpCode<>();
        httpCode.setData(userRepository.getUserByUdid(udid));
        return httpCode;
    }

    @RequestMapping(value = "allUser",method = RequestMethod.GET)
    public HttpCode allUser(){
        HttpCode<List<User>> httpCode = new HttpCode<>();
        httpCode.setData(userRepository.findAll());
        return httpCode;
    }
}
