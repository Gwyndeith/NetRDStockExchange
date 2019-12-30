package com.example.NetRDStockExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

@RestController
@SessionAttributes("stockName")
@RequestMapping("/stocks")
public class StockController {
    Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    StockRepository stockRepository;

    @GetMapping("/allStocks")
    public List<StockEntity> getAllStocks() {
        logger.info("All stocks requested.");
        return stockRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public StockEntity getStockByStockname(@RequestParam("stockName") String stockName) {
        return stockRepository.findStockEntityByStockName(stockName);
    }

    @RequestMapping(value = "/loadNewStockPage", method = RequestMethod.GET)
    public String loadNewStockPage() {
        return "/newStockForm.html";
    }

    @DeleteMapping("/delete")
    public boolean deleteStockById(@RequestParam int deleteId) {
        stockRepository.deleteStockById(deleteId);
        return true;
    }

    @RequestMapping(value = "/addNewStock", method = RequestMethod.POST)
    public ModelAndView addNewStock(@RequestParam("stockName") String stockName, @RequestParam("stockCode") String stockCode, @RequestParam("lastModifiedBy") String lastModifiedBy, RedirectAttributes redirAttr) {
        Random rnd = new Random();
        double randomPrice = new BigDecimal((15 + (200 - 15))* rnd.nextDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue();

        StockEntity newStockEntity = new StockEntity();

        newStockEntity.setStockName(stockName);
        newStockEntity.setStockCode(stockCode);
        newStockEntity.setStockPrice(randomPrice);
        newStockEntity.setLastModifiedBy(lastModifiedBy);

        stockRepository.save(newStockEntity);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/adminHome.html");
        redirAttr.addFlashAttribute("message", "Redirecting back to Admin Home Page!");
        return modelAndView;
    }
}