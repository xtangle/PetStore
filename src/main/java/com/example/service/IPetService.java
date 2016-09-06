package com.example.service;

import com.example.dto.PetDTO;

public interface IPetService {

	PetDTO getPet(long petId);
	boolean addPet(PetDTO petDTO);
	boolean deletePet(long petId);

}
