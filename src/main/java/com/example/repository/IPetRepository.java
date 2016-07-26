package com.example.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.model.Category;
import com.example.model.Pet;
import com.example.model.PetStatusType;

@Repository
public interface IPetRepository extends IJpaRepository<Pet, Long> {

  List<Pet> findByName(String name);

  List<Pet> findByCategory(Category category);

  List<Pet> findByStatus(PetStatusType status);

}
