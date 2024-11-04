package com.lautadev.tradear.service;

import com.lautadev.tradear.dto.UserSecDTO;
import com.lautadev.tradear.model.Inventory;
import com.lautadev.tradear.model.UserSec;
import com.lautadev.tradear.repository.IUserSecRepository;
import com.lautadev.tradear.throwable.EntityNotFoundException;
import com.lautadev.tradear.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserSecService implements IUserSecService{
    @Autowired
    private IUserSecRepository userSecRepository;

    @Autowired
    private IInventoryService iInventoryService;

    @Override
    @Transactional
    public UserSec saveUser(UserSec userSec) {

        UserSec savedUserSec = userSecRepository.save(userSec);

        if (savedUserSec.getInventory() == null) {
            Inventory inventory = new Inventory();
            inventory.setUserSec(savedUserSec);
            iInventoryService.saveInventory(inventory);

            savedUserSec.setInventory(inventory);

            return userSecRepository.save(savedUserSec);
        }

        return savedUserSec;
    }

    @Override
    public List<UserSec> getUsers() {
        return userSecRepository.findAll();
    }

    @Override
    public Optional<UserSecDTO> findUser(Long id) {
        Optional<UserSec> userSec = userSecRepository.findById(id);
        return userSec.map(UserSecDTO::fromUser);
    }

    @Override
    public Optional<UserSec> findUserEntity(Long id) {
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

    @Override
    public Optional<UserSecDTO> findByEmail(String email) {
        Optional<UserSec> userSec = userSecRepository.findByEmail(email);
        return userSec.map(UserSecDTO::fromUser);
    }
}
