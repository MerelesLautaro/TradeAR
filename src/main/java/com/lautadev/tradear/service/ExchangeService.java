package com.lautadev.tradear.service;

import com.lautadev.tradear.dto.ExchangeDTO;
import com.lautadev.tradear.model.Chat;
import com.lautadev.tradear.model.Exchange;
import com.lautadev.tradear.repository.IExchangeRepository;
import com.lautadev.tradear.repository.IItemRepository;
import com.lautadev.tradear.throwable.EntityNotFoundException;
import com.lautadev.tradear.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeService implements IExchangeService {
    @Autowired
    private IExchangeRepository exchangeRepository;

    @Autowired
    private IItemRepository iItemRepository;

    @Autowired
    private IChatService chatService;

    @Override
    @Transactional
    public ExchangeDTO saveExchange(Exchange exchange) {

        Exchange savedExchange =  exchangeRepository.save(exchange);

        if(savedExchange.getChat() == null){
            Chat chat = new Chat();
            chat.setName(exchange.getItemOffered().toString());
            chatService.saveChat(chat);

            savedExchange.setChat(chat);

            exchangeRepository.save(savedExchange);
        }

        return ExchangeDTO.fromExchange(exchange);
    }

    @Override
    public List<ExchangeDTO> getExchanges() {
        List<Exchange> exchanges = exchangeRepository.findAll();
        List<ExchangeDTO> exchangeDTOS = new ArrayList<>();

        for(Exchange exchange: exchanges){
            exchangeDTOS.add(ExchangeDTO.fromExchange(exchange));
        }

        return exchangeDTOS;

    }

    @Override
    public Optional<ExchangeDTO> findExchange(Long id) {
        Exchange exchange = exchangeRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Exchange Not Found"));
        return Optional.ofNullable(ExchangeDTO.fromExchange(exchange));
    }

    @Override
    public void deleteExchange(Long id) {
        exchangeRepository.deleteById(id);
    }

    @Override
    public ExchangeDTO editExchange(Long id, Exchange exchange) {
        Exchange exchangeEdit = exchangeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(exchange,exchangeEdit);

        return this.saveExchange(exchangeEdit);
    }
}
