package com.lautadev.tradear.service;

import com.lautadev.tradear.model.Account;
import com.lautadev.tradear.model.GoogleUserInfo;
import com.lautadev.tradear.model.Role;
import com.lautadev.tradear.repository.IAccountRepository;
import com.lautadev.tradear.throwable.EntityNotFoundException;
import com.lautadev.tradear.util.NullAwareBeanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional
public class AccountService implements IAccountService{

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IRoleService roleService;

    @Override
    public Account saveAccount(Account account) {
        Set<Role> roleList = new HashSet<>();

        account.setPassword(this.encriptPassword(account.getPassword()));

        for(Role role: account.getRoleList()){
            Role readRole = roleService.findRole(role.getId()).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));
            if(readRole!=null){
                roleList.add(readRole);
            }
        }

        if(!roleList.isEmpty()){
            account.setRoleList(roleList);
            accountRepository.save(account);
        }

        return null;
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findAccount(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account editAccount(Long id, Account account) {
        Account accountEdit = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(account,accountEdit);

        return this.saveAccount(accountEdit);
    }

    @Override
    public Account saveAccountOAuth(GoogleUserInfo googleUserInfo) {
        Account account = new Account();
        account.setUsername(googleUserInfo.getEmail());
        String randomPassword = RandomStringUtils.randomAlphanumeric(12);
        account.setPassword(randomPassword);
        account.setEnabled(true);
        account.setAccountNotLocked(true);
        account.setAccountNotExpired(true);
        account.setCredentialNotExpired(true);
        Set<Role> roleList = roleService.findRoleByName("USER");
        account.setRoleList(roleList);
        this.saveAccount(account);
        return account;
    }

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
