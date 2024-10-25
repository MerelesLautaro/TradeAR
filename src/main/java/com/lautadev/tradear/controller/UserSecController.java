package com.lautadev.tradear.controller;

import com.lautadev.tradear.dto.UserSecDTO;
import com.lautadev.tradear.model.UserSec;
import com.lautadev.tradear.service.IUserSecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@PreAuthorize("permitAll()")
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

    @GetMapping("/get/findByEmail")
    public ResponseEntity<UserSecDTO> findUserByEmail(@RequestParam String email){
        Optional<UserSecDTO> userSecDTO = userSecService.findByEmail(email);
        return userSecDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userSecService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<UserSec> editUser(@PathVariable Long id, @RequestBody UserSec userSec){
        return ResponseEntity.ok(userSecService.editUser(id,userSec));
    }

}
