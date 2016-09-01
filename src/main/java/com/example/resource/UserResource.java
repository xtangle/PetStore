package com.example.resource;

import com.example.model.Category;
import com.example.model.PetStatusType;
import com.example.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserResource {

  @Autowired
  ICategoryRepository categoryRepository;

  @RequestMapping(value = { "/", "index" })
  public String loadHomePage() {
    return "index";
  }

  @RequestMapping(value = { "addPet" })
  public String loadAddPetPage(Model model) {
    List<Category> categories = categoryRepository.findAll();
    List<PetStatusType> petStatusTypes = Arrays.asList(PetStatusType.values());
    model.addAttribute("categories", categories);
    model.addAttribute("petStatusTypes", petStatusTypes);
    return "add_pet";
  }

  @RequestMapping(value = { "findPet" })
  public String loadFindPetPage() {
    return "find_pet";
  }

}
