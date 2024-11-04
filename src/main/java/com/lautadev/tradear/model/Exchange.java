package com.lautadev.tradear.model;

import com.lautadev.tradear.dto.ExchangeDTO;
import com.lautadev.tradear.dto.ItemDTO;
import com.lautadev.tradear.repository.IItemRepository;
import com.lautadev.tradear.repository.IUserSecRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exchanges")
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Item> itemOffered;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Item> itemRequested;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "issuing_user_id")
    private UserSec issuingUser;
    @ManyToOne
    @JoinColumn(name = "receiving_user_id")
    private UserSec receivingUser;
    @ManyToOne
    private Status status;
    @OneToOne
    private Chat chat;

    public static Exchange fromDTO(ExchangeDTO exchangeDTO, IItemRepository itemRepository, IUserSecRepository userSecRepository) {
        if (exchangeDTO == null) {
            return null;
        }

        Exchange exchange = new Exchange();

        List<Item> itemOffered = exchangeDTO.getItemOffered().stream()
                .map(ItemDTO::getId)
                .map(itemRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        List<Item> itemRequested = exchangeDTO.getItemRequested().stream()
                .map(ItemDTO::getId)
                .map(itemRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        exchange.setItemOffered(itemOffered);
        exchange.setItemRequested(itemRequested);

        Optional<UserSec> issuingUser = userSecRepository.findById(exchangeDTO.getIssuingUser().getId());
        Optional<UserSec> receivingUser = userSecRepository.findById(exchangeDTO.getReceivingUser().getId());

        issuingUser.ifPresent(exchange::setIssuingUser);
        receivingUser.ifPresent(exchange::setReceivingUser);

        return exchange;
    }
}
