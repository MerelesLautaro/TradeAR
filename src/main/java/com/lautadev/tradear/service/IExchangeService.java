package com.lautadev.tradear.service;

import com.lautadev.tradear.dto.ExchangeDTO;
import com.lautadev.tradear.model.Exchange;

import java.util.List;
import java.util.Optional;

public interface IExchangeService {
    public Exchange saveExchange(Exchange exchange);
    public List<ExchangeDTO> getExchanges();
    public Optional<ExchangeDTO> findExchange(Long id);
    public void deleteExchange(Long id);
    public Exchange editExchange(Long id, Exchange exchange);
}
