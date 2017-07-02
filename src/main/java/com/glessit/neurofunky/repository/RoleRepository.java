package com.glessit.neurofunky.repository;

import com.glessit.neurofunky.entity.Role;
import com.glessit.neurofunky.entity.type.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    Set<Role> findByType(RoleType simpleUser);
}
