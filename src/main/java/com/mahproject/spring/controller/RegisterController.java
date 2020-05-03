package com.mahproject.spring.controller;

import com.mahproject.spring.model.UserRepository;
import com.mahproject.spring.model.ProfileModel;
import com.mahproject.spring.model.ProfileRepository;
import com.mahproject.spring.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String get(ModelMap model) {
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(@RequestParam("username") String username,
                       @RequestParam("password") String password,
                       @RequestParam("repeat_password") String repeatPassword,
                       @RequestParam("email") String email,
                       @RequestParam("first_name") String firstName,
                       @RequestParam("lastname") String lastname,
                       HttpServletResponse response) {

        if (username == null || password == null || repeatPassword == null || email == null || firstName == null || lastname == null) {
            return "register";
        }

        UserModel user = userRepository.findByUsername(username);

        if (user != null) {
            return "register";
        }

        if (!password.equals(repeatPassword)) {
            return "register";
        }

        user = userRepository.save(new UserModel(username, password));
        profileRepository.save(new ProfileModel(user, firstName, lastname, email));

        return "login";
    }
}
