package org.example.presentation;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.example.domain.model.User;
import org.example.application.service.UserService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/login")
    public User login(@RequestParam String username, @RequestParam String password){
        return userService.findUserByUsernameAndPassword(username,password);
    }

    @GetMapping("/findUserById")
    public User findUserById(@RequestParam Long id){
        return userService.findUserById(id);
    }

    @GetMapping("/findUserByUsername")
    public User findUserByUsername(@RequestParam String username){
        return userService.findUserByUsername(username);
    }

    @PostMapping("/register")
    public User register(@RequestParam String username, @RequestParam String password, @RequestParam String name,@RequestParam Integer sex){
        return userService.saveUser(username, password, name,sex);
    }

    @GetMapping("/backendA")
    @CircuitBreaker(name = "backendA",fallbackMethod = "fallback")
    public String findUser()  {
        if(true){
            throw new RuntimeException("An error occurred!");
        }
        return "User";
    }

    public String fallback(Throwable e){
        e.printStackTrace();
        return "哎呀，服务器繁忙中...";
    }
}
