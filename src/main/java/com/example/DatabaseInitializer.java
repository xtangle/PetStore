package com.example;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.model.Category;
import com.example.model.Pet;
import com.example.model.PetStatusType;
import com.example.model.Tag;
import com.example.repository.ICategoryRepository;
import com.example.repository.IPetRepository;
import com.example.repository.ITagRepository;

@SpringBootApplication
public class DatabaseInitializer implements ApplicationRunner {

  @Autowired
  IPetRepository petRepository;
  @Autowired
  ICategoryRepository categoryRepository;
  @Autowired
  ITagRepository tagRepository;

  @Override
  public void run(ApplicationArguments arg0) throws Exception {
    // Populate the database with some initial entities
    Category sampleCategory1 = new Category("Dog");
    Category sampleCategory2 = new Category("Cat");
    Category sampleCategory3 = new Category("Rabbit");

    Tag sampleTag1 = new Tag("Domestic");
    Tag sampleTag2 = new Tag("Rescued");
    Tag sampleTag3 = new Tag("Aggressive");
    Tag sampleTag4 = new Tag("Tame");
    Tag sampleTag5 = new Tag("White");
    Tag sampleTag6 = new Tag("Cute");

    Pet samplePet1 = new Pet(sampleCategory1, "Woofie", PetStatusType.AVAILABLE);
    Pet samplePet2 = new Pet(sampleCategory2, "Snowball", PetStatusType.SOLD);
    Pet samplePet3 = new Pet(sampleCategory3, "Bunbun", PetStatusType.PENDING);

    sampleCategory1.getPets().add(samplePet1);
    sampleCategory2.getPets().add(samplePet2);
    sampleCategory3.getPets().add(samplePet3);

    samplePet1.setTags(Arrays.asList(sampleTag1, sampleTag3));
    samplePet2.setTags(Arrays.asList(sampleTag1, sampleTag4, sampleTag5, sampleTag6));
    samplePet3.setTags(Arrays.asList(sampleTag2, sampleTag5, sampleTag6));

    sampleTag1.setPets(new HashSet<>(Arrays.asList(samplePet1, samplePet2)));
    sampleTag2.setPets(new HashSet<>(Arrays.asList(samplePet3)));
    sampleTag3.setPets(new HashSet<>(Arrays.asList(samplePet1)));
    sampleTag4.setPets(new HashSet<>(Arrays.asList(samplePet2)));
    sampleTag5.setPets(new HashSet<>(Arrays.asList(samplePet2, samplePet3)));
    sampleTag6.setPets(new HashSet<>(Arrays.asList(samplePet2, samplePet3)));

    samplePet1.setPhotoURLs(Arrays.asList(
        "http://trendingpost.net/wp-content/uploads/2015/09/10-Small-aggressive-dog-breeds-that-can-be-dangerous1.jpg",
        "http://platpets.com/wp-content/uploads/2015/12/Tosa-Inu1.jpg"));
    samplePet2.setPhotoURLs(Arrays.asList(
        "http://d39kbiy71leyho.cloudfront.net/wp-content/uploads/2016/05/09170020/cats-politics-TN.jpg"));
    samplePet3.setPhotoURLs(Arrays.asList(
        "http://maxcdn.thedesigninspiration.com/wp-content/uploads/2014/07/Cute-Rabbits-026.jpg",
        "http://images4.fanpop.com/image/photos/19000000/Cute-little-white-bunny-bunny-rabbits-19034359-500-333.jpg",
        "https://s-media-cache-ak0.pinimg.com/236x/f3/65/35/f365358f04b8c48df65c2630d4982a5c.jpg"));

    categoryRepository.save(sampleCategory1);
    categoryRepository.save(sampleCategory2);
    categoryRepository.save(sampleCategory3);

    tagRepository.save(sampleTag1);
    tagRepository.save(sampleTag2);
    tagRepository.save(sampleTag3);
    tagRepository.save(sampleTag4);
    tagRepository.save(sampleTag5);
    tagRepository.save(sampleTag6);

    petRepository.save(samplePet1);
    petRepository.save(samplePet2);
    petRepository.save(samplePet3);
  }

}
