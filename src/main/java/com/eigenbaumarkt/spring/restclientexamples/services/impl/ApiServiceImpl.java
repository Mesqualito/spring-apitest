package com.eigenbaumarkt.spring.restclientexamples.services.impl;

import com.eigenbaumarkt.spring.apitest.domain.User;
import com.eigenbaumarkt.spring.apitest.domain.UserData;
import com.eigenbaumarkt.spring.restclientexamples.services.ApiService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    private final RestTemplate restTemplate;

    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getUsers(Integer limit) {

        UserData userData = restTemplate.getForObject("https://private-e426b4-hool.apiary-mock.com/hool/user?limit=" + limit, UserData.class);

        return userData.getData();
    }
}
