package com.example.repository;

import com.example.model.Category;

public interface ICategoryRepository extends IJpaRepository<Category, Long> {

  Category findByName(String name);

  void deleteByName(String name);

}
