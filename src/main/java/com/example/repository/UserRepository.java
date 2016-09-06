package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.bo.UserBO;

@Repository
public interface UserRepository extends IJpaRepository<UserBO, Long> {

  UserBO findByUsername(String username);

  void deleteByUsername(String username);

}
