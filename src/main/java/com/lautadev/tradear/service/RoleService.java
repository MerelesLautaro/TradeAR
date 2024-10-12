package com.lautadev.tradear.service;


import com.lautadev.tradear.model.Permission;
import com.lautadev.tradear.model.Role;
import com.lautadev.tradear.repository.IRoleRepository;
import com.lautadev.tradear.throwable.EntityNotFoundException;
import com.lautadev.tradear.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService implements IRoleService{
    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IPermissionService permissionService;

    @Override
    public Role saveRole(Role role) {
        Set<Permission> permissionsList = new HashSet<>();

        for(Permission permission: role.getPermissionSet() ){
            Permission readPermission = permissionService.findPermission(permission.getId()).orElse(null);
            if(readPermission!=null){
                permissionsList.add(readPermission);
            }
        }

        if(!permissionsList.isEmpty())
        {
            role.setPermissionSet(permissionsList);
            return roleRepository.save(role);
        }

        return role;
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findRole(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role editRole(Long id,Role role) {
        Role roleEdit = this.findRole(id).orElseThrow(() -> new EntityNotFoundException("Entity not found")) ;;

        NullAwareBeanUtils.copyNonNullProperties(role,roleEdit);

        return this.saveRole(roleEdit);
    }
}