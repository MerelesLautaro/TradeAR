package com.lautadev.tradear.service;

import com.lautadev.tradear.model.Account;
import com.lautadev.tradear.model.GoogleUserInfo;
import com.lautadev.tradear.model.GoogleUserInfoAndroid;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    public Account saveAccount(Account account);
    public List<Account> getAccounts();
    public Optional<Account> findAccount(Long id);
    public void deleteAccount(Long id);
    public Account editAccount(Long id,Account account);
    public String encriptPassword(String password);
    public Account saveAccountOAuth(GoogleUserInfo googleUserInfo);
    public Account saveAccountOAuthFromAndroid(GoogleUserInfoAndroid googleUserInfoAndroid);
    public Optional<Account> findUserEntityByUsername(String username);
}
