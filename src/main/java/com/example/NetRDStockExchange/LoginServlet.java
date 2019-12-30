package com.example.NetRDStockExchange;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    UserRepository userRepository;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // get request parameters for userID and password
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");

        List<UserEntity> userEntities = userRepository.findAll();

        System.out.println("userEntities: " + userEntities);

        for (UserEntity userEntity : userEntities) {
            if (userEntity.getUsername().equals(username) && userEntity.getPassword().equals(pwd) && userEntity.getUserType() == 0) {
                Cookie loginCookie = new Cookie("username", username);
                //setting cookie to expiry in 30 mins
                loginCookie.setMaxAge(30 * 60);
                response.addCookie(loginCookie);
                response.sendRedirect("/adminHome.html");
                return;
            } else if (userEntity.getUsername().equals(username) && userEntity.getPassword().equals(pwd) && userEntity.getUserType() == 1) {
                Cookie loginCookie = new Cookie("username", username);
                loginCookie.setMaxAge(30 * 60);
                response.addCookie(loginCookie);
                response.sendRedirect("/userHome.html");
                return;
            } else if (userEntity.getUsername().equals(username) && userEntity.getPassword().equals(pwd) && userEntity.getUserType() == 2) {
                response.sendRedirect("/bannedUser.html");
                return;
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Either username or password is wrong.</font>");
                rd.include(request, response);
            }
        }
    }
}