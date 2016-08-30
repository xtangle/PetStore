package com.example.service;

import static com.example.model.PetStatusType.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.model.Category;
import com.example.model.Pet;
import com.example.model.Tag;
import com.example.repository.ICategoryRepository;
import com.example.repository.IPetRepository;
import com.example.repository.ITagRepository;
import com.example.service.PetService;

@FixMethodOrder(MethodSorters.DEFAULT)
@RunWith(MockitoJUnitRunner.class)
public class PetServiceTest {

  @Mock
  IPetRepository mockedPetRepository;

  @Mock
  ITagRepository mockedTagRepository;

  @Mock
  ICategoryRepository mockedCategoryRepository;

  @Mock
  Tag mockedTag1, mockedTag2, mockedTag3;

  @InjectMocks
  PetService petService;

  static int testCount;
  static final String TEST_DIVIDER = new String(new char[100]).replace("\0", "-");

  @Rule
  public TestRule watcher = new TestWatcher() {
    @Override
    protected void starting(Description description) {
      System.out.println(TEST_DIVIDER);
      System.out.println("Starting test " + (++testCount) + ": " + description.getMethodName());
    }
  };

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    System.out.println(TEST_DIVIDER);
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void findPet_ReturnsNull_WhenPetRepositoryIsEmpty() {
    when(mockedPetRepository.findOne(any(long.class))).thenReturn(null);
    Pet fetchedPet = petService.findPet(1);
    System.out.println(fetchedPet);
    assertNull(fetchedPet);
  }

  @Test
  public void findPet_ReturnsNull_WhenPetRepositoryIsNotEmptyAndCouldNotFindPet() {
    when(mockedPetRepository.findOne(1L)).thenReturn(null);
    Pet fetchedPet = petService.findPet(1);
    System.out.println(fetchedPet);
    assertNull(fetchedPet);
  }

  @Test
  public void findPet_ReturnsCorrectPet_WhenPetRepositoryHasSpecifiedPet() {
    Pet pet1 = new Pet(new Category("dog"), "Woofy", AVAILABLE);
    Pet pet2 = new Pet(new Category("cat"), "Scratchy", PENDING);
    Pet pet3 = new Pet(new Category("rabbit"), "Bunbun", SOLD);
    pet1.setId(1);
    pet2.setId(2);
    pet3.setId(3);
    when(mockedPetRepository.findOne(1L)).thenReturn(pet1);
    when(mockedPetRepository.findOne(2L)).thenReturn(pet2);
    when(mockedPetRepository.findOne(3L)).thenReturn(pet3);
    Pet fetchedPet = petService.findPet(2);
    System.out.println(fetchedPet);
    assertEquals(pet2, fetchedPet);
  }

  @Test
  public void addPet_AddsPetToDatabase_WhenPetHasNoCategoryAndNoTagsAndDatabaseIsEmpty() {
    Pet newPet = new Pet(null, "newpet", AVAILABLE);
    Pet expectedReturnedPet = new Pet(null, "newpet", AVAILABLE);
    expectedReturnedPet.setId(1);

    when(mockedPetRepository.save(newPet)).thenReturn(expectedReturnedPet);
    when(mockedPetRepository.exists(any(long.class))).thenReturn(false);
    when(mockedTagRepository.exists(any(long.class))).thenReturn(false);
    when(mockedCategoryRepository.exists(any(long.class))).thenReturn(false);
    Pet returnedPet = petService.addPet(newPet);

    System.out.println(returnedPet);
    verify(mockedPetRepository, times(1)).save(newPet);
    verify(mockedTagRepository, times(0)).save(any(Tag.class));
    verify(mockedCategoryRepository, times(0)).save(any(Category.class));
    assertEquals(expectedReturnedPet, returnedPet);
  }

  @Test
  public void addPet_AddsPetAndTagsToDatabase_WhenPetHasTagsAndDatabaseIsEmpty() {
    Tag tag1 = new Tag("brown");
    Tag tag2 = new Tag("bulldog");
    tag1.setId(1);
    tag2.setId(2);

    Pet newPet = new Pet(null, "newpet", AVAILABLE);
    newPet.setTags(Arrays.asList(tag1, tag2));

    Pet expectedReturnedPet = new Pet(null, "newpet", AVAILABLE);
    expectedReturnedPet.setTags(Arrays.asList(tag1, tag2));
    expectedReturnedPet.setId(1);

    when(mockedPetRepository.save(newPet)).thenReturn(expectedReturnedPet);
    when(mockedPetRepository.exists(any(long.class))).thenReturn(false);
    when(mockedTagRepository.exists(any(long.class))).thenReturn(false);
    when(mockedCategoryRepository.exists(any(long.class))).thenReturn(false);
    when(mockedTagRepository.findByName(any(String.class))).thenReturn(null);
    Pet returnedPet = petService.addPet(newPet);

    System.out.println(returnedPet);
    verify(mockedPetRepository, times(1)).save(newPet);
    verify(mockedTagRepository, times(1)).save(tag1);
    verify(mockedTagRepository, times(1)).save(tag2);
    verify(mockedCategoryRepository, times(0)).save(any(Category.class));
    assertEquals(expectedReturnedPet, returnedPet);
  }

  @Test
  public void addPet_DoesNotAddPet_WhenPetHasCategoryAndDatabaseIsEmpty() {
    Category category = new Category("doggie");
    category.setId(1);
    Pet newPet = new Pet(category, "newpet", AVAILABLE);

    when(mockedPetRepository.exists(any(long.class))).thenReturn(false);
    when(mockedTagRepository.exists(any(long.class))).thenReturn(false);
    when(mockedCategoryRepository.exists(any(long.class))).thenReturn(false);

    Pet returnedPet = petService.addPet(newPet);
    System.out.println(returnedPet);
    verify(mockedPetRepository, times(0)).save(newPet);
    verify(mockedTagRepository, times(0)).save(any(Tag.class));
    verify(mockedCategoryRepository, times(0)).save(any(Category.class));
    assertNull(returnedPet);
  }

  @Test
  public void addPet_AddsPetToDatabase_WhenPetHasACategoryStoredInDatabase() {
    Category category = new Category("doggie");
    Pet newPet = new Pet(category, "newpet", AVAILABLE);
    Category storedCategory = new Category("doggie");
    storedCategory.setId(1);

    Pet expectedReturnedPet = new Pet(storedCategory, "newpet", AVAILABLE);
    expectedReturnedPet.setId(1);

    when(mockedPetRepository.save(newPet)).thenReturn(expectedReturnedPet);
    when(mockedPetRepository.exists(any(long.class))).thenReturn(false);
    when(mockedTagRepository.exists(any(long.class))).thenReturn(false);
    when(mockedCategoryRepository.exists(1L)).thenReturn(true);
    when(mockedCategoryRepository.findByName(category.getName())).thenReturn(category);

    Pet returnedPet = petService.addPet(newPet);
    System.out.println(returnedPet);
    verify(mockedPetRepository, times(1)).save(newPet);
    verify(mockedTagRepository, times(0)).save(any(Tag.class));
    verify(mockedCategoryRepository, times(0)).save(any(Category.class));
    assertEquals(expectedReturnedPet, returnedPet);
  }

  @Test
  public void addPet_AddsPetAndOnlyUnstoredTagsToDatabase_WhenPetHasTagsAndSomeAreStoredInDatabase() {
    Tag tag1 = new Tag("brown");
    Tag tag2 = new Tag("bulldog");
    Tag tag3 = new Tag("short tail");
    tag1.setId(1);
    tag2.setId(2);
    tag3.setId(3); // Only 3 is stored in database

    Pet newPet = new Pet(null, "newpet", SOLD);
    newPet.setTags(Arrays.asList(mockedTag1, mockedTag2, mockedTag3));
    Pet expectedReturnedPet = new Pet(null, "newpet", AVAILABLE);
    expectedReturnedPet.setTags(Arrays.asList(tag1, tag2, tag3));
    expectedReturnedPet.setId(1);

    when(mockedTag1.getPets()).thenReturn(new HashSet<>());
    when(mockedTag2.getPets()).thenReturn(new HashSet<>());
    when(mockedTag3.getPets()).thenReturn(new HashSet<>());
    when(mockedTag1.getName()).thenReturn(tag1.getName());
    when(mockedTag2.getName()).thenReturn(tag2.getName());
    when(mockedTag3.getName()).thenReturn(tag3.getName());

    when(mockedPetRepository.save(newPet)).thenReturn(expectedReturnedPet);
    when(mockedTagRepository.exists(1L)).thenReturn(false);
    when(mockedTagRepository.exists(2L)).thenReturn(false);
    when(mockedTagRepository.exists(3L)).thenReturn(true);
    when(mockedTagRepository.findByName(tag1.getName())).thenReturn(null);
    when(mockedTagRepository.findByName(tag2.getName())).thenReturn(null);
    when(mockedTagRepository.findByName(tag3.getName())).thenReturn(tag3);
    when(mockedCategoryRepository.exists(any(long.class))).thenReturn(false);

    Pet returnedPet = petService.addPet(newPet);
    System.out.println(returnedPet);
    verify(mockedPetRepository, times(1)).save(newPet);
    verify(mockedTag1, times(0)).setId(any(long.class));
    verify(mockedTag2, times(0)).setId(any(long.class));
    verify(mockedTag3, times(1)).setId(tag3.getId());
    verify(mockedTagRepository, times(1)).save(mockedTag1);
    verify(mockedTagRepository, times(1)).save(mockedTag2);
    verify(mockedTagRepository, times(1)).save(mockedTag3);
    verify(mockedCategoryRepository, times(0)).save(any(Category.class));
    assertEquals(expectedReturnedPet, returnedPet);
  }

  @Test
  public void deletePet_DoesNotDeletePetAndReturnsFalse_WhenPetRepositoryDoesNotHaveSpecifiedPet() {
    Pet petToDelete = new Pet(null, "newpet", SOLD);
    petToDelete.setId(1);

    when(mockedPetRepository.exists(petToDelete.getId())).thenReturn(false);

    boolean deleteResult = petService.deletePet(petToDelete);
    System.out.println(deleteResult);
    verify(mockedPetRepository, times(0)).delete(any(Pet.class));
    assertFalse(deleteResult);
  }

  @Test
  public void deletePet_DeletesPetAndReturnsTrue_WhenPetRepositoryHasSpecifiedPet() {
    Pet petToDelete = new Pet(null, "newpet", SOLD);
    petToDelete.setId(1);

    when(mockedPetRepository.exists(petToDelete.getId())).thenReturn(true);

    boolean deleteResult = petService.deletePet(petToDelete);
    System.out.println(deleteResult);
    verify(mockedPetRepository, times(1)).delete(petToDelete);
    assertTrue(deleteResult);
  }
}
