package com.example.repository;

import com.example.bo.CategoryBO;

public interface CategoryRepository extends IJpaRepository<CategoryBO, Long> {

  CategoryBO findByName(String name);

  void deleteByName(String name);

}
