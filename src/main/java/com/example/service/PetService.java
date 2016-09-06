package com.example.service;

import java.util.List;

import com.example.dto.NamedObjectDTO;
import com.example.dto.PetDTO;

public interface PetService {

	PetDTO getPet(long petId);
	boolean addPet(PetDTO petDTO);
	boolean deletePet(long petId);
	List<NamedObjectDTO> getPetStatuses();

}
