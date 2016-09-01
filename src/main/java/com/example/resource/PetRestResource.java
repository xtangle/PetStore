package com.example.resource;

import com.example.model.Pet;
import com.example.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@RestController
public class PetRestResource {

  @Autowired
  private PetService petService;

  @RequestMapping(path = "/pet", method = RequestMethod.POST)
  @Consumes(MediaType.APPLICATION_JSON)
  public Pet createPet(@RequestBody Pet newPet, HttpServletResponse response) throws IOException {
    Pet returnedPet = petService.addPet(newPet);
    if (returnedPet == null) {
      // Error: invalid input
      response.sendError(Response.Status.METHOD_NOT_ALLOWED.getStatusCode(), "Invalid input");
      return null;
    }
    return returnedPet;
  }

  @RequestMapping(path = "/pet/{petIdString}", method = RequestMethod.GET)
  @Produces(MediaType.APPLICATION_JSON)
  public Pet getPet(@PathVariable String petIdString, HttpServletResponse response) throws IOException {
    long petId = 0;
    try {
      petId = Long.parseLong(petIdString);
    } catch (NumberFormatException e) {
      // Error: invalid id
      response.sendError(Response.Status.BAD_REQUEST.getStatusCode(), e.toString());
      return null;
    }
    Pet fetchedPet = petService.findPet(petId);
    if (fetchedPet == null) {
      // Error: pet not found
      response.sendError(Response.Status.NOT_FOUND.getStatusCode(), "Pet not found");
      return null;
    }
    return fetchedPet;
  }

  @RequestMapping(path = "/pet/{petIdString}", method = RequestMethod.DELETE)
  public Pet deletePet(@PathVariable String petIdString, HttpServletResponse response)
      throws IOException {
    long petId = 0;
    try {
      petId = Long.parseLong(petIdString);
    } catch (NumberFormatException e) {
      // Error: invalid id
      response.sendError(Response.Status.BAD_REQUEST.getStatusCode(), e.toString());
      return null;
    }
    Pet fetchedPet = petService.findPet(petId);
    if (fetchedPet == null) {
      // Error: pet not found
      response.sendError(Response.Status.NOT_FOUND.getStatusCode(), "Pet not found");
      return null;
    }
    petService.deletePet(fetchedPet);
    return null;
  }

}
