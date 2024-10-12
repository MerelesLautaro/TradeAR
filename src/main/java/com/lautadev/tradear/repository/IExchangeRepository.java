package com.lautadev.tradear.repository;

import com.lautadev.tradear.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExchangeRepository extends JpaRepository<Exchange,Long> {
}
