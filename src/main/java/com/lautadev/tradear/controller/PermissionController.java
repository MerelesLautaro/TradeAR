package com.lautadev.tradear.controller;

import com.lautadev.tradear.model.Permission;
import com.lautadev.tradear.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @PostMapping("/save")
    public ResponseEntity<String> savePermission(@RequestBody Permission permission){
        permissionService.savePermission(permission);
        return ResponseEntity.ok("Permission saved successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<List<Permission>> getPermissions(){
        return ResponseEntity.ok(permissionService.getPermission());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Permission> findPermission(@PathVariable Long id){
        Optional<Permission> permission = permissionService.findPermission(id);
        return permission.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePermission(@PathVariable Long id){
        permissionService.deletePermission(id);
        return ResponseEntity.ok("Permission deleted");
    }

    @PatchMapping("/edit")
    public ResponseEntity<Permission> editPermission(@RequestBody Permission permission){
        return ResponseEntity.ok(permissionService.editPermission(permission));
    }




}
