package com.eigenbaumarkt.spring.restclientexamples.controllers;

import com.eigenbaumarkt.spring.restclientexamples.services.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ServerWebExchange;

@Slf4j
@Controller
public class UserController {

    private ApiService apiService;

    public UserController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }

    @PostMapping("/users")
    public String formPost(Model model, ServerWebExchange serverWebExchange) {

        /*
        no need anymore: now we're getting reactive!
        Spring is smart enough to know that we are going to bind a reactive type


        MultiValueMap<String, String> map = serverWebExchange.getFormData().block();

        Integer limit = Integer.valueOf(map.get("limit").get(0));

        log.debug("Received Limit value: " + limit);

        if(limit == null || limit == 0){
            log.debug("Setting limit to default of 2");
            limit = 2;
        }

        model.addAttribute("users", apiService.getUsers(limit));

         */

        model.addAttribute("users",
                apiService
                        .getUsers(serverWebExchange
                                .getFormData()
                                .map(data -> new Integer(data.getFirst("limit")))
                        ));

        return "userlist";
    }
}
