package com.example.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.bo.CategoryBO;
import com.example.bo.PetBO;
import com.example.bo.PetStatus;

@Repository
public interface PetRepository extends IJpaRepository<PetBO, Long> {

  List<PetBO> findByName(String name);

  List<PetBO> findByCategory(CategoryBO category);

  List<PetBO> findByStatus(PetStatus status);

}
