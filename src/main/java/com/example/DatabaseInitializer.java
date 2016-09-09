package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.bo.CategoryBO;
import com.example.bo.PetStatus;
import com.example.dto.PetDTO;
import com.example.repository.CategoryRepository;
import com.example.service.PetService;

@SpringBootApplication
public class DatabaseInitializer implements ApplicationRunner {

	@Autowired
	private PetService petService;
	@Autowired
	private CategoryRepository categoryRepository;

	private List<CategoryBO> categories = new ArrayList<>();

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		// Populate the database with some initial entities
		populateCategories();
		populatePets();
	}

	private void populateCategories() {
		categories.add(new CategoryBO("Dog"));
		categories.add(new CategoryBO("Cat"));
		categories.add(new CategoryBO("Rabbit"));
		categories.add(new CategoryBO("Snake"));
		categories.forEach(category -> categoryRepository.save(category));
	}

	private void populatePets() {
		PetDTO pet;

		pet = new PetDTO();
		pet.setName("Woofie");
		pet.setCategory(categories.get(0).getName());
		pet.setTags(Arrays.asList("Domestic", "Aggressive"));
		pet.setPhotoURLs(Arrays.asList(
			"http://trendingpost.net/wp-content/uploads/2015/09/10-Small-aggressive-dog-breeds-that-can-be-dangerous1.jpg",
			"http://platpets.com/wp-content/uploads/2015/12/Tosa-Inu1.jpg"
		));
		pet.setStatus(PetStatus.AVAILABLE.getName());
		petService.addPet(pet);

		pet = new PetDTO();
		pet.setName("Snowball");
		pet.setCategory(categories.get(1).getName());
		pet.setTags(Arrays.asList("Domestic", "Tame", "White", "Cute"));
		pet.setPhotoURLs(Arrays.asList(
			"http://d39kbiy71leyho.cloudfront.net/wp-content/uploads/2016/05/09170020/cats-politics-TN.jpg"
		));
		pet.setStatus(PetStatus.SOLD.getName());
		petService.addPet(pet);

		pet = new PetDTO();
		pet.setName("Bunbun");
		pet.setCategory(categories.get(2).getName());
		pet.setTags(Arrays.asList("Rescued", "White", "Cute", "Fluffy"));
		pet.setPhotoURLs(Arrays.asList(
			"http://maxcdn.thedesigninspiration.com/wp-content/uploads/2014/07/Cute-Rabbits-026.jpg",
			"http://images4.fanpop.com/image/photos/19000000/Cute-little-white-bunny-bunny-rabbits-19034359-500-333.jpg",
			"https://s-media-cache-ak0.pinimg.com/236x/f3/65/35/f365358f04b8c48df65c2630d4982a5c.jpg"
		));
		pet.setStatus(PetStatus.PENDING.getName());
		petService.addPet(pet);
	}

}
