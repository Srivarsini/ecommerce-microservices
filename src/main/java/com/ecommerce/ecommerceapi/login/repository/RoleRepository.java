package com.ecommerce.ecommerceapi.login.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ecommerce.ecommerceapi.login.model.Role;
import com.ecommerce.ecommerceapi.login.model.ERole;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
