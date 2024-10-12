package com.lautadev.tradear.service;

import com.lautadev.tradear.model.Exchange;
import com.lautadev.tradear.repository.IExchangeRepository;
import com.lautadev.tradear.throwable.EntityNotFoundException;
import com.lautadev.tradear.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExchangeService implements IExchangeService {
    @Autowired
    private IExchangeRepository exchangeRepository;

    @Override
    public Exchange saveExchange(Exchange exchange) {
        if(exchange != null) {
            return exchangeRepository.save(exchange);
        }

        return null;
    }

    @Override
    public List<Exchange> getExchanges() {
        return exchangeRepository.findAll();
    }

    @Override
    public Optional<Exchange> findExchange(Long id) {
        return exchangeRepository.findById(id);
    }

    @Override
    public void deleteExchange(Long id) {
        exchangeRepository.deleteById(id);
    }

    @Override
    public Exchange editExchange(Long id, Exchange exchange) {
        Exchange exchangeEdit = exchangeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(exchange,exchangeEdit);

        return this.saveExchange(exchangeEdit);
    }
}
