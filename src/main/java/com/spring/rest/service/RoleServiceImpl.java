package com.spring.rest.service;

import com.spring.rest.models.Role;
import com.spring.rest.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Collection<Role> getRoleList() {
        return roleRepository.findAll();
    }

    public Role getRoleById(long id) {
        return roleRepository.getById(id);
    }

}
