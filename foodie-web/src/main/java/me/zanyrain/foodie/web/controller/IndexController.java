package me.zanyrain.foodie.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/foodie-shop/")
    public String shopIndex(){
        return "foodie-shop/index";
    }
    @GetMapping("/foodie-center/")
    public String uCenterIndex(){
        return "foodie-center/index";
    }
}
