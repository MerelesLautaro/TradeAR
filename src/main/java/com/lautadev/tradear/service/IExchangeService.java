package com.lautadev.tradear.service;

import com.lautadev.tradear.dto.ExchangeDTO;
import com.lautadev.tradear.model.Exchange;

import java.util.List;
import java.util.Optional;

public interface IExchangeService {
    public ExchangeDTO saveExchange(Exchange exchange);
    public List<ExchangeDTO> getExchanges();
    public Optional<ExchangeDTO> findExchange(Long id);
    public void deleteExchange(Long id);
    public ExchangeDTO editExchange(Long id, Exchange exchange);
}
