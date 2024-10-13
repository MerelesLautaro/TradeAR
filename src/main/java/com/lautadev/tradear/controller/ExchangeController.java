package com.lautadev.tradear.controller;

import com.lautadev.tradear.dto.ExchangeDTO;
import com.lautadev.tradear.model.Exchange;
import com.lautadev.tradear.service.IExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exchange")
@PreAuthorize("permitAll()")
public class ExchangeController {

    @Autowired
    private IExchangeService exchangeService;

    @PostMapping("/save")
    public ResponseEntity<String> saveExchange(@RequestBody Exchange exchange){
        exchangeService.saveExchange(exchange);
        return ResponseEntity.ok("Exchange saved successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<List<ExchangeDTO>> getExchanges(){
        return ResponseEntity.ok(exchangeService.getExchanges());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ExchangeDTO> findExchange(@PathVariable Long id){
        Optional<ExchangeDTO> exchange = exchangeService.findExchange(id);
        return exchange.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExchange(@PathVariable Long id){
        exchangeService.deleteExchange(id);
        return ResponseEntity.ok("Exchange deleted");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<Exchange> editExchange(@PathVariable Long id,@RequestBody Exchange exchange){
        return ResponseEntity.ok(exchangeService.editExchange(id,exchange));
    }


}
