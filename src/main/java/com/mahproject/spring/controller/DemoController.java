package com.mahproject.spring.controller;

import java.util.HashMap;
import java.util.Map;

import com.mahproject.spring.model.UserModel;
import com.mahproject.spring.CookieHandler;
import com.mahproject.spring.model.ProfileModel;
import com.mahproject.spring.model.ProfileRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DemoController {

    @Autowired
    ProfileRepository profileRepository;

    @RequestMapping("/")
    public String welcome(Model model,
                          HttpServletRequest request) {
        UserModel user = CookieHandler.getUser(request);

        if (user == null) {
            return "login";
        }

        return "welcome";
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String apiGetUser(HttpServletRequest request) {
        UserModel user = CookieHandler.getUser(request);

        if (user != null) {
            ProfileModel profileModel = profileRepository.findByUserId(user.getId());

            Map<String, String> response = new HashMap<>();

            response.put("username", user.getUsername());
            response.put("first_name", profileModel.getFirstName());
            response.put("lastname", profileModel.getLastname());
            response.put("email", profileModel.getEmail());

            return new JSONObject(response).toString();
        }

        return "{}";
    }
}

