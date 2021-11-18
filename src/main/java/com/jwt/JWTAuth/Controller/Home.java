package com.jwt.JWTAuth.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @RequestMapping("/welcome")
    public String welcome() {
        String text = "this is private page";
        text += " and this page is not allowed to unauthorized user";
        return text;
    }

    //68bfbc51-4552-4434-b099-6cf169776692
//form based login
    @RequestMapping("/getusers")
    public String getUser() {
        return "{\"name\":\"Sabyasachi\"}";
    }
}
