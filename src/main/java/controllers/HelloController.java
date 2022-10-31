package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.ShopService;

@RestController
public class HelloController {
    private final ShopService shopService;

    @Autowired
    public HelloController(ShopService shopService) {
        this.shopService = shopService;
    }


    @PostMapping("/test")
    public String test(){
        return "test1";
    }
}
