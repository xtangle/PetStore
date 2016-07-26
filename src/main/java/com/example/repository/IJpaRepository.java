package com.example.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.model.IStorable;

@NoRepositoryBean
public interface IJpaRepository<T extends IStorable, ID extends Serializable> extends JpaRepository<T, ID> {

  @Override
  <S extends T> S save(S entity);

  @Override
  T findOne(ID id);

  @Override
  List<T> findAll();

  @Override
  long count();

  @Override
  void delete(T entity);

  @Override
  void deleteAll();

  @Override
  boolean exists(ID id);

}
