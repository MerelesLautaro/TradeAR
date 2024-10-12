package com.lautadev.tradear.service;

import com.lautadev.tradear.model.Status;
import com.lautadev.tradear.repository.IStatusRepository;
import com.lautadev.tradear.throwable.EntityNotFoundException;
import com.lautadev.tradear.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService implements IStatusService {
    @Autowired
    private IStatusRepository statusRepository;

    @Override
    public Status saveStatus(Status status) {
        if (status != null) {
            return statusRepository.save(status);
        }
        return null;
    }

    @Override
    public List<Status> getStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public Optional<Status> findStatus(Long id) {
        return statusRepository.findById(id);
    }

    @Override
    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }

    @Override
    public Status editStatus(Long id, Status status) {
        Status statusEdit = statusRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(status,statusEdit);

        return this.saveStatus(statusEdit);
    }
}
