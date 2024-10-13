package com.lautadev.tradear.service;

import com.lautadev.tradear.dto.ExchangeDTO;
import com.lautadev.tradear.dto.ItemDTO;
import com.lautadev.tradear.model.Exchange;
import com.lautadev.tradear.repository.IExchangeRepository;
import com.lautadev.tradear.repository.IItemRepository;
import com.lautadev.tradear.throwable.EntityNotFoundException;
import com.lautadev.tradear.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExchangeService implements IExchangeService {
    @Autowired
    private IExchangeRepository exchangeRepository;

    @Autowired
    private IItemRepository iItemRepository;

    @Override
    public Exchange saveExchange(Exchange exchange) {
        if(exchange != null) {
            return exchangeRepository.save(exchange);
        }

        return null;
    }

    @Override
    public List<ExchangeDTO> getExchanges() {
        List<Exchange> exchanges = exchangeRepository.findAll();

        return exchanges.stream()
                .map(exchange -> {
                    List<ItemDTO> offeredItems = exchange.getItemOffered().stream()
                            .map(itemId -> iItemRepository.findById(itemId)
                                    .orElseThrow(() -> new EntityNotFoundException("Item Not Found")))
                            .map(ItemDTO::fromItem)
                            .collect(Collectors.toList());

                    List<ItemDTO> requestedItems = exchange.getItemRequested().stream()
                            .map(itemId -> iItemRepository.findById(itemId)
                                    .orElseThrow(() -> new EntityNotFoundException("Item Not Found")))
                            .map(ItemDTO::fromItem)
                            .collect(Collectors.toList());

                    return ExchangeDTO.fromExchange(exchange, offeredItems, requestedItems);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ExchangeDTO> findExchange(Long id) {
        Exchange exchange = exchangeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        List<ItemDTO> offeredItems = exchange.getItemOffered().stream()
                .map(itemId -> iItemRepository.findById(itemId)
                        .orElseThrow(() -> new EntityNotFoundException("Item Not Found")))
                .map(ItemDTO::fromItem)
                .collect(Collectors.toList());

        List<ItemDTO> requestedItems = exchange.getItemRequested().stream()
                .map(itemId -> iItemRepository.findById(itemId)
                        .orElseThrow(() -> new EntityNotFoundException("Item Not Found")))
                .map(ItemDTO::fromItem)
                .collect(Collectors.toList());

        return Optional.of(ExchangeDTO.fromExchange(exchange, offeredItems, requestedItems));
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
