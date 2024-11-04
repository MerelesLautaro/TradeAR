package com.lautadev.tradear.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date date;
    private String link;
    private int amount;
    @ManyToOne
    private Category category;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_inventory")
    private Inventory inventory;
}
