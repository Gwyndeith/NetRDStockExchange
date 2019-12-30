package com.example.NetRDStockExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class LoginService {

    Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login/{username}/{password}")
    public boolean findUserByUsername(@PathVariable String username, @PathVariable String password) {
        UserEntity tempUser = userRepository.findByUsername(username);

        if (tempUser != null) {
            return password.equals(tempUser.getPassword());
        } else {
            logger.error("User doesn't exist or the username / password entered was wrong.");
            return false;
        }
    }
}
