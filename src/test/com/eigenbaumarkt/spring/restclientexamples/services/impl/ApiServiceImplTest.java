package com.eigenbaumarkt.spring.restclientexamples.services.impl;

import com.eigenbaumarkt.spring.apitest.domain.User;
import com.eigenbaumarkt.spring.restclientexamples.services.ApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApiServiceImplTest {

    @Autowired
    ApiService apiService;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void testGetUsers() throws Exception {

        List<User> userList = apiService.getUsers(2);

        assertEquals(3, userList.size());

    }

}
