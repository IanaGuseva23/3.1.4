package com.spring.rest.service;

import com.spring.rest.models.Role;

import java.util.Collection;

public interface RoleService {
    Collection<Role> getRoleList();
    Role getRoleById(long id);
}
