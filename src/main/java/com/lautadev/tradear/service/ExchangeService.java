package com.lautadev.tradear.service;

import com.lautadev.tradear.dto.ExchangeDTO;
import com.lautadev.tradear.model.Chat;
import com.lautadev.tradear.model.Exchange;
import com.lautadev.tradear.model.Item;
import com.lautadev.tradear.model.Status;
import com.lautadev.tradear.repository.IExchangeRepository;
import com.lautadev.tradear.repository.IItemRepository;
import com.lautadev.tradear.repository.IStatusRepository;
import com.lautadev.tradear.repository.IUserSecRepository;
import com.lautadev.tradear.throwable.EntityNotFoundException;
import com.lautadev.tradear.util.NullAwareBeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeService implements IExchangeService {

    private final IExchangeRepository exchangeRepository;
    private final IItemRepository iItemRepository;
    private final IChatService chatService;
    private final IUserSecRepository userSecRepository;
    private final IStatusRepository statusRepository;

    public ExchangeService(IExchangeRepository exchangeRepository, IItemRepository iItemRepository,
                           IChatService chatService, IUserSecRepository userSecRepository, IStatusRepository statusRepository){
        this.exchangeRepository = exchangeRepository;
        this.iItemRepository = iItemRepository;
        this.chatService = chatService;
        this.userSecRepository = userSecRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    @Transactional
    public ExchangeDTO saveExchange(ExchangeDTO exchangeDTO) {
        Exchange exchange = Exchange.fromDTO(exchangeDTO, iItemRepository, userSecRepository);
        exchange.setDate(LocalDateTime.now());
        Exchange savedExchange = exchangeRepository.save(exchange);

        boolean isUpdated = false;

        if (savedExchange.getChat() == null) {
            Chat chat = new Chat();
            List<Item> items = exchange.getItemRequested();

            if (!items.isEmpty()) {
                chat.setName(items.get(0).toString());
            }

            chatService.saveChat(chat);
            savedExchange.setChat(chat);
            isUpdated = true;
        }

        if (savedExchange.getStatus() == null) {
            savedExchange.setStatus(statusRepository.findStatusByName("PENDIENTE"));
            isUpdated = true;
        }

        if (isUpdated) {
            exchangeRepository.save(savedExchange);
        }

        return ExchangeDTO.fromExchange(savedExchange);
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

        exchangeRepository.save(exchangeEdit);
        return ExchangeDTO.fromExchange(exchangeEdit);
    }

    @Override
    public List<ExchangeDTO> findExchangesByUserEmail(String email) {
        List<Exchange> exchanges = exchangeRepository.findExchangesByUserEmail(email);
        List<ExchangeDTO> exchangeDTOS = new ArrayList<>();

        for(Exchange exchange: exchanges){
            exchangeDTOS.add(ExchangeDTO.fromExchange(exchange));
        }

        return exchangeDTOS;
    }
}
