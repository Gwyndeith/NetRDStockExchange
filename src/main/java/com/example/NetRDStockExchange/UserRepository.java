package com.example.NetRDStockExchange;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    //Get UserEntity by Username.
    UserEntity findByUsername(String username);

    //Delete user by UserID
    void deleteUserById(int Id);
}
