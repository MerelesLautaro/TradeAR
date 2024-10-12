package com.lautadev.tradear.repository;

import com.lautadev.tradear.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageRepository  extends JpaRepository<Message,Long> {
}
