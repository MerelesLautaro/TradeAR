package com.lautadev.tradear.service;

import com.lautadev.tradear.model.UserSec;
import com.lautadev.tradear.repository.IUserSecRepository;
import com.lautadev.tradear.throwable.EntityNotFoundException;
import com.lautadev.tradear.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSecService implements IUserSecService{
    @Autowired
    private IUserSecRepository userSecRepository;

    @Override
    public UserSec saveUser(UserSec userSec) {
        if(userSec != null) {
            return userSecRepository.save(userSec);
        }
        return null;
    }

    @Override
    public List<UserSec> getUsers() {
        return userSecRepository.findAll();
    }

    @Override
    public Optional<UserSec> findUser(Long id) {
        return userSecRepository.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userSecRepository.deleteById(id);
    }

    @Override
    public UserSec editUser(Long id, UserSec userSec) {
        UserSec userSecEdit = userSecRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(userSec,userSecEdit);

        return this.saveUser(userSecEdit);

    }
}
