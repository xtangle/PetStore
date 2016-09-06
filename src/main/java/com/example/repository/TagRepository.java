package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.bo.TagBO;

@Repository
public interface TagRepository extends IJpaRepository<TagBO, Long> {

  TagBO findByName(String name);

  void deleteByName(String name);

}
