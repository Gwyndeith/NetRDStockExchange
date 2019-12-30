package com.example.NetRDStockExchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes({"username","users"})
public class TestController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/listTest", method = RequestMethod.POST)
    public String getTestHTML(ModelMap modelMap, @RequestParam String username, HttpSession session) {

        //Check to see if the user that just logged in has an admin role or not. Redirect accordingly.
        UserEntity currentUser = userRepository.findByUsername(username);

        modelMap.put("username", currentUser.getUsername());
        session.setAttribute("username", currentUser.getUsername());

        List<UserEntity> allUsers = userRepository.findAll();

        modelMap.put("users", allUsers);
        session.setAttribute("users", allUsers);

        return "redirect:/listTest.html";
    }
}
