package com.lautadev.tradear.service;

import com.lautadev.tradear.dto.UserSecDTO;
import com.lautadev.tradear.model.UserSec;

import java.util.List;
import java.util.Optional;

public interface IUserSecService {
    public UserSec saveUser(UserSec userSec);
    public List<UserSec> getUsers();
    public Optional<UserSecDTO> findUser(Long id);
    public void deleteUser(Long id);
    public UserSec editUser(Long id, UserSec userSec);
    public Optional<UserSecDTO> findByEmail(String email);
}
