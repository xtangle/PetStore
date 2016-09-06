package com.example.resource;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.NamedObjectDTO;
import com.example.dto.PetDTO;
import com.example.service.PetService;

@RestController
@JsonRequestMapping(value = "api/pet")
public class PetResource {

	@Autowired
	private PetService petService;

	@JsonRequestMapping(method = RequestMethod.POST)
	public void addPet(@RequestBody PetDTO petDTO, HttpServletResponse response) throws IOException {
		if (!petService.addPet(petDTO)) {
			response.sendError(Response.Status.METHOD_NOT_ALLOWED.getStatusCode(), "Invalid input");
		}
	}

	@JsonRequestMapping(value = "/{petIdString}", method = RequestMethod.GET)
	public PetDTO getPet(@PathVariable String petIdString, HttpServletResponse response) throws IOException {
		long petId;
		try {
			petId = Long.parseLong(petIdString);
		} catch (NumberFormatException e) {
			response.sendError(Response.Status.BAD_REQUEST.getStatusCode(), e.toString());
			return null;
		}
		PetDTO petDTO = petService.getPet(petId);
		if (petDTO == null) {
			response.sendError(Response.Status.NOT_FOUND.getStatusCode(), "Pet not found");
			return null;
		}
		return petDTO;
	}

	@RequestMapping(path = "/{petIdString}", method = RequestMethod.DELETE)
	public boolean deletePet(@PathVariable String petIdString, HttpServletResponse response) throws IOException {
		long petId;
		try {
			petId = Long.parseLong(petIdString);
		} catch (NumberFormatException e) {
			response.sendError(Response.Status.BAD_REQUEST.getStatusCode(), e.toString());
			return false;
		}
		if (petService.getPet(petId) == null) {
			response.sendError(Response.Status.NOT_FOUND.getStatusCode(), "Pet not found");
			return false;
		}
		return petService.deletePet(petId);
	}

	@RequestMapping(path = "/status", method = RequestMethod.GET)
	@Cacheable("api.pet.status")
	public List<NamedObjectDTO> getPetStatuses() {
		return petService.getPetStatuses();
	}

}
