package com.lautadev.tradear.repository;

import com.lautadev.tradear.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemRepository extends JpaRepository<Item,Long> {

    @Query("SELECT i FROM Item i WHERE i.inventory.userSec.id != :userId OR i.inventory.userSec IS NULL")
    public List<Item> findItemsNotBelongingToUser(@Param("userId") Long userId);

    @Query("SELECT i FROM Item i WHERE i.inventory.userSec.id = :userId")
    List<Item> findItemsBelongingToUser(@Param("userId") Long userId);
}
