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
 * Servlet implementation class StockServlet
 */
@WebServlet("/stockServlet")
public class StockServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Autowired
    private StockRepository stockRepository;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        List<StockEntity> stockEntities = stockRepository.findAll();
        String cookieValue = "";
        for (StockEntity stockEntity : stockEntities) {
            cookieValue += stockEntity.toString();
        }
        Cookie stockCookie = new Cookie("allStocks", cookieValue);
        stockCookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(stockCookie);
        response.sendRedirect("/newStockForm.html");
    }
}
