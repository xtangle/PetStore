package com.example.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bo.PetBO;
import com.example.bo.PetStatus;
import com.example.bo.TagBO;
import com.example.dto.PetDTO;
import com.example.repository.CategoryRepository;
import com.example.repository.PetRepository;
import com.example.repository.TagRepository;
import com.example.service.PetService;
import com.example.transform.NamedObjectTransformer;
import com.example.transform.PetTransformer;

@Service
@Transactional
public class PetServiceImpl implements PetService {

	@Autowired
	private PetRepository petRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private PetTransformer petTransformer;

	@Autowired
	private NamedObjectTransformer namedObjectTransformer;

	@Override
	public PetDTO getPet(long petId) {
		return petTransformer.toDTO(petRepository.findOne(petId));
	}

	@Override
	public PetDTO addPet(PetDTO petDTO) {
		if (petDTO == null) {
			return null;
		}

		petDTO.getTags().forEach(tagName -> {
			if (tagRepository.findByName(tagName) == null) {
				TagBO tagBO = new TagBO();
				tagBO.setName(tagName);
				tagRepository.save(tagBO);
			}
		});

		PetBO petBO = petTransformer.toBO(petDTO);
		if (petBO.getCategory() == null) {
			return null;
		}

		petBO.getCategory().getPets().add(petBO);
		petBO.getTags().forEach(tagBO -> {
			tagBO.getPets().add(petBO);
		});

		return petTransformer.toDTO(petRepository.save(petBO));
	}

	@Override
	public boolean deletePet(long petId) {
		if (!petRepository.exists(petId)) {
			return false;
		}
		petRepository.delete(petId);
		return true;
	}

	@Override
	public List<String> getPetCategories() {
		return namedObjectTransformer.toDTOList(categoryRepository.findAll());
	}

	@Override
	public List<String> getPetStatuses() {
		return namedObjectTransformer.toDTOList(Arrays.asList(PetStatus.values()));
	}

}
