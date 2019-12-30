package com.example.NetRDStockExchange;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
@RequestMapping("/adminHome")
public class AdminPageController {

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String getAdminHomePage(ModelMap modelMap) {
        modelMap.put("username", "admin");
        return "redirect:/adminHome.html";
    }
}
