package com.quantity.QuantityApp.repository;


import com.quantity.QuantityApp.entity.userEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<userEntity, Long> {
    Optional<userEntity> findByEmail(String email);


}
