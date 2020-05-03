package com.mahproject.spring;

import com.mahproject.spring.model.UserModel;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Base64;

public class CookieHandler {
    private static final String userCookie = "user";

    public static void addSession(HttpServletResponse response, UserModel user) {
        String userString = toString(user);
        response.addCookie(new Cookie(userCookie, userString));
        System.out.println(userString);
    }

    public static UserModel getUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(userCookie)) {
                    System.out.println(cookie.getValue());
                    return ((UserModel) fromString(cookie.getValue()));
                }
            }
        }

        return null;
    }

    private static String toString(Serializable o) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static Object fromString(String s) {
        byte [] data = Base64.getDecoder().decode( s );
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(data));
            Object o  = ois.readObject();
            ois.close();
            System.out.println("returning from string\n");
            return o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
