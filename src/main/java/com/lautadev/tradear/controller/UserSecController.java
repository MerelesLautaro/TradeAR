package com.lautadev.tradear.controller;

import com.lautadev.tradear.model.UserSec;
import com.lautadev.tradear.service.IUserSecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserSecController {

    @Autowired
    private IUserSecService userSecService;

    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody UserSec userSec){
        userSecService.saveUser(userSec);
        return ResponseEntity.ok("User saved successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserSec>> getUsers(){
        return ResponseEntity.ok(userSecService.getUsers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserSec> findUser(@PathVariable Long id){
        Optional<UserSec> userSec = userSecService.findUser(id);
        return userSec.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<UserSec> editUser(@PathVariable Long id, @RequestBody UserSec userSec){
        return ResponseEntity.ok(userSecService.editUser(id,userSec));
    }

}
