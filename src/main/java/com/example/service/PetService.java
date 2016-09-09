package com.example.service;

import java.util.List;

import com.example.dto.PetDTO;

public interface PetService {

	PetDTO getPet(long petId);
	PetDTO addPet(PetDTO petDTO);
	boolean deletePet(long petId);
	List<String> getPetCategories();
	List<String> getPetStatuses();

}
