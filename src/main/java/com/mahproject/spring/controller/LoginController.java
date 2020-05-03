package com.mahproject.spring.controller;

import com.mahproject.spring.CookieHandler;
import com.mahproject.spring.model.UserModel;
import com.mahproject.spring.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public String getLogin(ModelMap model, HttpServletRequest request) {
        UserModel user = CookieHandler.getUser(request);

        if (user != null) {
            System.out.println("username" + user.getUsername());
        }

        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postLogin(@RequestParam("uname") String username,
                            @RequestParam("psw") String password,
                            HttpServletResponse response) {

        if (username != null && password != null) {
            UserModel user = repository.findByUsername(username);

            if (user != null && user.validatePassword(password)) {
                CookieHandler.addSession(response, user);
                return "welcome";
            }
        }
        System.out.println(username);
        System.out.println(password);
        return "login";
    }
}
