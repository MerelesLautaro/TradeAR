package com.lautadev.tradear.repository;

import com.lautadev.tradear.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInventoryRepository extends JpaRepository<Inventory,Long> {
}
