package com.example.repository;

import com.example.bo.UserBO;

public interface UserRepository extends IJpaRepository<UserBO, Long> {

  UserBO findByUsername(String username);

  void deleteByUsername(String username);

}
