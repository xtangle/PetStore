package com.example.repository;

import com.example.model.User;

public interface IUserRepository extends IJpaRepository<User, Long> {

  User findByUsername(String username);

  void deleteByUsername(String username);

}
