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
    public ResponseEntity<ExchangeDTO> saveExchange(@RequestBody ExchangeDTO exchangeDTO){
        return ResponseEntity.ok(exchangeService.saveExchange(exchangeDTO));
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

    @GetMapping("/get/my-exchanges")
    public ResponseEntity<List<ExchangeDTO>> myExchanges(@RequestParam String email){
        return ResponseEntity.ok(exchangeService.findExchangesByUserEmail(email));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExchange(@PathVariable Long id){
        exchangeService.deleteExchange(id);
        return ResponseEntity.ok("Exchange deleted");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<ExchangeDTO> editExchange(@PathVariable Long id,@RequestBody Exchange exchange){
        return ResponseEntity.ok(exchangeService.editExchange(id,exchange));
    }

}
