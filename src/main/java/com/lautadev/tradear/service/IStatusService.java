package com.lautadev.tradear.service;

import com.lautadev.tradear.model.Status;

import java.util.List;
import java.util.Optional;

public interface IStatusService {
    public Status saveStatus(Status status);
    public List<Status> getStatuses();
    public Optional<Status> findStatus(Long id);
    public void deleteStatus(Long id);
    public Status editStatus(Long id, Status status);
}
