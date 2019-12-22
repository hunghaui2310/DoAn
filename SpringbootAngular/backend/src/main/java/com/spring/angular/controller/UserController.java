package com.spring.angular.controller;

import com.spring.angular.helper.ApiResponse;
import com.spring.angular.helper.Contains;
import com.spring.angular.model.User;
import com.spring.angular.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequestMapping("account")
public class UserController {

    @Autowired
    private UserService userService;

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    public ApiResponse createUser(@RequestBody User newUser){
        User user1 = userService.findByUserName(newUser.getUsername());
        if(user1 != null){
            logger.error("User Name Already exist " + newUser.getUsername());
            return ApiResponse.build(HttpServletResponse.SC_CONFLICT, false, Contains.CONFLICT, null);
        }else {
            newUser.setRole(Contains.USER);
            User user = userService.saveUser(newUser);
            return ApiResponse.build(HttpServletResponse.SC_OK, true, Contains.SUCCESS, user);
        }
    }

    @RequestMapping("/login")
    public Principal login(Principal principal){
        logger.info("USER LOGGED" + principal);
        return principal;
    }
}
