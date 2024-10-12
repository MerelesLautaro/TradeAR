package com.lautadev.tradear.repository;

import com.lautadev.tradear.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChatRepository  extends JpaRepository<Chat,Long> {
}
