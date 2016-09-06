package com.example.repository;

import com.example.bo.TagBO;

public interface TagRepository extends IJpaRepository<TagBO, Long> {

  TagBO findByName(String name);

  void deleteByName(String name);

}
