package com.example.repository;

import com.example.model.Tag;

public interface ITagRepository extends IJpaRepository<Tag, Long> {

  Tag findByName(String name);

  void deleteByName(String name);

}
