package com.lautadev.tradear.repository;

import com.lautadev.tradear.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExchangeRepository extends JpaRepository<Exchange,Long> {

    @Query("SELECT e FROM Exchange e " +
            "WHERE e.issuingUser.email = :email OR e.receivingUser.email = :email")
    List<Exchange> findExchangesByUserEmail(@Param("email") String email);
}
