package com.example.NetRDStockExchange;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "users")
@SessionAttributes("username")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String eMail;
   // private String ownedStocks; //Make this a string of "StockID-stockAmountOwned" instead, because Map can't be put to Model.
    private int userType; // type 0 is admin, 1 is normal user, 2 is banned

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OwnedStocksEntity> ownedStocks;

    public UserEntity() {

    }

    public UserEntity(String username, String password, String eMail) {
        this.username = username;
        this.password = password;
        this.eMail = eMail;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public void setOwnedStocks(List<OwnedStocksEntity> ownedStocks) {
        this.ownedStocks = ownedStocks;
    }

    public Long getId() {
        return id;
    }

    public int getUserType() {
        return userType;
    }

    @ModelAttribute("username")
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<OwnedStocksEntity> getOwnedStocks() {
        return ownedStocks;
    }

    public String geteMail() {
        return eMail;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                "-username='" + username + '\'' +
                "-eMail='" + eMail + '\'' +
                "-userType=" + userType +
                "}_";
    }
}
