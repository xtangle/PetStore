package com.example.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.NamedObjectDTO;
import com.example.service.CategoryService;

@RestController
@JsonRequestMapping(value = "api/category")
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;

	@JsonRequestMapping(method = RequestMethod.GET)
	@Cacheable("api.category")
	public List<NamedObjectDTO> getAllCategories() {
		return categoryService.getAllCategories();
	}

}
