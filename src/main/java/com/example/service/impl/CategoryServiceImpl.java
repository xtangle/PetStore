package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.NamedObjectDTO;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;
import com.example.transform.NamedObjectTransformer;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	NamedObjectTransformer namedObjectTransformer;

	@Override
	public List<NamedObjectDTO> getAllCategories() {
		return namedObjectTransformer.toDTOList(categoryRepository.findAll());
	}
}
