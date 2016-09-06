package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.bo.CategoryBO;

@Repository
public interface CategoryRepository extends IJpaRepository<CategoryBO, Long> {

  CategoryBO findByName(String name);

  void deleteByName(String name);

}
