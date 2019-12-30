package com.example.NetRDStockExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@SessionAttributes({"username","ownedStocks"})
@RequestMapping("/users")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    StockRepository stockRepository;

    @GetMapping("/allUsers")
    public List<UserEntity> getAllUsers() {
        logger.info("All users requested.");
        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public UserEntity findUserByUsername(@RequestParam("username") String username) {
        return userRepository.findByUsername(username);
    }

    @RequestMapping(value = "/loadNewUserPage", method = RequestMethod.GET)
    public String loadNewUserPage() {
        return "/newUserForm.html";
    }

    @DeleteMapping("/delete")
    public boolean deleteUserById(@RequestParam int deleteUserId) {
        userRepository.deleteUserById(deleteUserId);
        return true;
    }

    @RequestMapping(value = "/purchaseStock", method = RequestMethod.POST)
    public ModelAndView purchaseStock(@RequestParam("stockId") String stockId, @RequestParam("stockAmount") int stockAmount, @RequestParam("username") String username, RedirectAttributes redirAttr) {
        UserEntity currentUser = getUserEntity(username);

        //Add owned stock to current user owned stock list
        if (currentUser.getOwnedStocks().isEmpty() || currentUser.getOwnedStocks().stream().allMatch(x -> x.getStockEntity().getId() != Integer.parseInt(stockId))) {
            OwnedStocksEntity ownedStocksEntity = new OwnedStocksEntity();
            Optional<StockEntity> stockEntity = stockRepository.findById(Integer.parseInt(stockId));
            ownedStocksEntity.setUserEntity(currentUser);
            ownedStocksEntity.setStockAmount(stockAmount);
            if (stockEntity.isPresent()) {
                ownedStocksEntity.setStockEntity(stockEntity.get());
            }
            currentUser.getOwnedStocks().add(ownedStocksEntity);
        } else {
            //Update already owned stock
            currentUser.getOwnedStocks().stream().filter(x -> x.getStockEntity().getId() == Integer.parseInt(stockId)).forEach(x -> {
                x.setStockAmount(x.getStockAmount() + stockAmount);
            });
        }
        userRepository.save(currentUser);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/userHome.html");
        redirAttr.addFlashAttribute("message", "Redirecting back to your Home Page!");
        return modelAndView;
    }

    @RequestMapping(value = "/sellStock", method = RequestMethod.POST)
    public ModelAndView sellStock(@RequestParam("stockId") String stockId, @RequestParam("stockAmount") int stockAmount, @RequestParam("username") String username, RedirectAttributes redirAttr) throws Exception {
        UserEntity currentUser = getUserEntity(username);

        // stock varsa
        if (currentUser.getOwnedStocks().isEmpty() || currentUser.getOwnedStocks().stream().allMatch(x -> x.getStockEntity().getId() != Integer.parseInt(stockId))) {

        } else {
            currentUser.getOwnedStocks().stream().filter(x -> x.getStockEntity().getId() == Integer.parseInt(stockId) && x.getStockAmount() >= stockAmount).forEach(x -> {
                x.setStockAmount(x.getStockAmount() - stockAmount);
            });
        }
        System.out.println(currentUser.getOwnedStocks());
        userRepository.save(currentUser);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/userHome.html");
        redirAttr.addFlashAttribute("message", "Redirecting back to your Home Page!");
        return modelAndView;
    }

    private UserEntity getUserEntity(@RequestParam("username") String username) {
        return userRepository.findByUsername (username);
    }

    @RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
    public ModelAndView addNewUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, @RequestParam("userType") int userType, RedirectAttributes redirAttr) {
        UserEntity newUserEntity = new UserEntity();

        newUserEntity.setUsername(username);
        newUserEntity.setPassword(password);
        newUserEntity.seteMail(email);
        newUserEntity.setUserType(userType);

        userRepository.save(newUserEntity);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/adminHome.html");
        redirAttr.addFlashAttribute("message", "Redirecting back to Admin Home Page!");
        return modelAndView;
    }
}
