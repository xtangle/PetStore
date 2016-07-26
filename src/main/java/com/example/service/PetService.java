package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Category;
import com.example.model.Pet;
import com.example.model.Tag;
import com.example.repository.ICategoryRepository;
import com.example.repository.IPetRepository;
import com.example.repository.ITagRepository;

@Service
@Transactional
public class PetService {

  @Autowired
  private IPetRepository petRepository;

  @Autowired
  private ITagRepository tagRepository;

  @Autowired
  private ICategoryRepository categoryRepository;

  public PetService() {
    super();
  }

  public Pet findPet(long petId) {
    return petRepository.findOne(petId);
  }

  public Pet addPet(Pet pet) {

    if (pet == null || pet.getName() == null) {
      return null;
    }

    Category category = pet.getCategory();
    if (category != null) {
      Category storedCategory = categoryRepository.findByName(category.getName());
      if (storedCategory == null) {
        return null;
      }
      category.setId(storedCategory.getId());
      category.setPets(storedCategory.getPets());
      category.getPets().add(pet);
    }

    for (Tag tag : pet.getTags()) {
      Tag storedTag = tagRepository.findByName(tag.getName());
      if (storedTag != null) {
        tag.setId(storedTag.getId());
        tag.setPets(storedTag.getPets());
      }
      tag.getPets().add(pet);
      tagRepository.save(tag);
    }

    Pet returnedPet = petRepository.save(pet);
    return returnedPet;
  }

  public boolean deletePet(Pet pet) {
    long petId = pet.getId();
    if (!petRepository.exists(petId)) {
      return false;
    }
    petRepository.delete(pet);
    return true;
  }

}
