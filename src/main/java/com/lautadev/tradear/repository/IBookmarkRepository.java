package com.lautadev.tradear.repository;

import com.lautadev.tradear.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookmarkRepository extends JpaRepository<Bookmark,Long> {
    public Bookmark findByUserSec_Id(Long userId);
}
