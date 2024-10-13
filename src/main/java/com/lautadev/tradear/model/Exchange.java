package com.lautadev.tradear.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exchanges")
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> itemOffered;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> itemRequested;
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
}
