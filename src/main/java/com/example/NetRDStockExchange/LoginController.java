package com.example.NetRDStockExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes({"username","users"})
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String showWelcomePage(Model model, @RequestParam String username, @RequestParam String password, HttpSession session){
        boolean isValidUser = loginService.findUserByUsername(username, password);

        if (!isValidUser) {
            return "redirect:/";
        }

        logger.info("User " + username + " has successfully logged in.");

        //Check to see if the user that just logged in has an admin role or not. Redirect accordingly.
        UserEntity currentUser = userRepository.findByUsername(username);

        System.out.println("username: " + currentUser.getUsername());

        model.addAttribute("username", currentUser.getUsername());
        session.setAttribute("username", currentUser.getUsername());

        List<UserEntity> allUsers = userRepository.findAll();

        model.addAttribute("users", allUsers);
        session.setAttribute("users", allUsers);
//        model.addAttribute("password", currentUser.getPassword());
//        model.put("password", currentUser.getPassword());
//        model.addAttribute("userType", currentUser.getUserType());
//        model.put("userType", currentUser.getUserType());

        //TODO Implement a for here to redirect blocked users back to the index screen

        if (currentUser.getUserType() == 0)
            return "redirect:/loginServlet/?username=" + currentUser.getUsername() + "&password=" + currentUser.getPassword();
        return "redirect:/home.html";
    }
}
