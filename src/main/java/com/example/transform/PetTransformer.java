package com.example.transform;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bo.CategoryBO;
import com.example.bo.PetBO;
import com.example.bo.PetStatus;
import com.example.dto.PetDTO;
import com.example.repository.CategoryRepository;
import com.example.repository.TagRepository;

@Component
public class PetTransformer extends BaseTransformer<PetBO, PetDTO> {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	TagRepository tagRepository;

	@Override
	public PetDTO toDTO(PetBO bo) {
		if (bo == null) {
			return null;
		}

		PetDTO dto = new PetDTO();

		dto.setId(bo.getId());
		dto.setName(bo.getName());
		dto.setPhotoURLs(bo.getPhotoURLs());

		CategoryBO category = bo.getCategory();
		dto.setCategory(category != null ? category.getName() : null);

		PetStatus status = bo.getStatus();
		dto.setStatus(status != null ? status.getName() : null);

		List<String> tagNames = bo.getTags().stream()
			.map(tagBO -> tagBO != null ? tagBO.getName() : null)
			.collect(Collectors.toList());
		dto.setTags(tagNames);

		return dto;
	}

	public PetBO toBO(PetDTO dto) {
		if (dto == null) {
			return null;
		}

		PetBO bo = new PetBO();
		bo.setId(dto.getId());
		bo.setName(dto.getName());
		bo.setPhotoURLs(dto.getPhotoURLs());
		bo.setCategory(categoryRepository.findByName(dto.getCategory()));
		bo.setStatus(PetStatus.getByName(dto.getStatus()));
		bo.setTags(dto.getTags().stream()
			.map(tagName -> tagRepository.findByName(tagName))
			.filter(Objects::nonNull)
			.collect(Collectors.toList()));

		return bo;
	}

}
