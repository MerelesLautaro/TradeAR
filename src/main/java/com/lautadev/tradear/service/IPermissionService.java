package com.lautadev.tradear.service;

import com.lautadev.tradear.model.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {
    public void savePermission(Permission permission);
    public List<Permission> getPermission();
    public Optional<Permission> findPermission(Long id);
    public void deletePermission(Long id);
    public Permission editPermission(Permission permission);
}
