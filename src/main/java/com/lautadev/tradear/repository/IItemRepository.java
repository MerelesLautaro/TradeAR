package com.lautadev.tradear.repository;

import com.lautadev.tradear.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemRepository extends JpaRepository<Item,Long> {
}
