package com.sapo.edu.jwt.repository;


import com.sapo.edu.jwt.model.Role;
import com.sapo.edu.jwt.model.RoleName;

import java.util.Optional;


public interface RoleRepository  {
    Optional<Role> findByName(RoleName roleName);
}