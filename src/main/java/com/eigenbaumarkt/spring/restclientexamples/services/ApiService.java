package com.eigenbaumarkt.spring.restclientexamples.services;

import com.eigenbaumarkt.spring.apitest.domain.User;

import java.util.List;

public interface ApiService {

    List<User> getUsers(Integer limit);

}
