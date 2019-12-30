package com.example.NetRDStockExchange;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet("/ownedStocksServlet")
public class OwnedStocksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    OwnedStocksRepository ownedStocksRepository;

    @Autowired
    UserRepository userRepository;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // get request parameters for userID and password

        BufferedReader bufferedReader = request.getReader();
        String requestParam = bufferedReader.readLine();

        String username = requestParam.substring(requestParam.indexOf(':')+2, requestParam.lastIndexOf('"'));

        List<OwnedStocksEntity> ownedStocksEntities = ownedStocksRepository.findAll();
        UserEntity currentUser = userRepository.findByUsername(username);
        String cookieValue = "";

        if (!ownedStocksEntities.isEmpty()) {
            for (OwnedStocksEntity ownedStocksEntity : ownedStocksEntities) {
                if (currentUser.getId().equals(ownedStocksEntity.getUserEntity().getId())) {
//                    cookieValue += ownedStocksEntity.getStockEntity().toString();
                    cookieValue += ownedStocksEntity.toString();
                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
                    PrintWriter out = response.getWriter();
                    out.println("<font color=red>You do not own any stocks of this kind.</font>");
                    rd.include(request, response);
                }
            }

            Cookie stockCookie = new Cookie("allOwnedStocks", cookieValue);
            stockCookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(stockCookie);
            response.sendRedirect("/userHome.html");
        } else {
            response.sendRedirect("/userHome.html");
        }
    }
}
