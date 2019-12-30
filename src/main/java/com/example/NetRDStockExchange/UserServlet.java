package com.example.NetRDStockExchange;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Autowired
    private UserRepository userRepository;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        // get request parameters for userID and password
//        request.getParameter("username");
        String username = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                username = cookie.getValue();
            }
        }
        List<UserEntity> userEntities = userRepository.findAll();
        String cookieValue = "";
        for (UserEntity userEntity : userEntities) {
            cookieValue += userEntity.toString();
        }
        Cookie userCookie = new Cookie("allUsers", cookieValue);
        userCookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(userCookie);
        response.sendRedirect("/newUserForm.html");
    }
}
