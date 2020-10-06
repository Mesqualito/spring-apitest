package com.eigenbaumarkt.spring.restclientexamples.controllers;

import com.eigenbaumarkt.spring.restclientexamples.services.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ServerWebExchange;



@Slf4j
@Controller
public class UserController {

    private final ApiService apiService;

    public UserController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping({"", "/", "index", "index.htm", "index.html"})
    public String index() {
        return "index";
    }

    @PostMapping("/users")
    public String formPost(Model model, ServerWebExchange serverWebExchange) {

        MultiValueMap<String, String> formData = serverWebExchange.getFormData().block();

        Integer limit = Integer.valueOf(formData.get("limit").get(0));
        log.debug("Received limit-value: " + limit);

        if(limit == null || limit == 0) {
            log.debug("Setting limit to default of 2");
            limit = 2;
        }

        model.addAttribute("users", apiService.getUsers(limit));

        return "userlist";
    }
}
