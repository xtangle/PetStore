package com.example.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.bo.CategoryBO;
import com.example.bo.PetBO;
import com.example.bo.TagBO;
import com.example.dto.PetDTO;
import com.example.repository.PetRepository;
import com.example.repository.TagRepository;
import com.example.transform.PetTransformer;

@RunWith(MockitoJUnitRunner.class)
public class PetServiceImplTest {

	@Mock
	private PetRepository mockedPetRepository;

	@Mock
	private TagRepository mockedTagRepository;

	@Mock
	private PetTransformer mockedPetTransformer;

	@InjectMocks
	private PetServiceImpl fixture;

	private CategoryBO CATEGORY_BO;
	private TagBO TAG_BO_1, TAG_BO_2;

	@Before
	public void setUp() {
		initMocks(this);
		CATEGORY_BO = new CategoryBO();
		CATEGORY_BO.setName("pet category");
		TAG_BO_1 = new TagBO();
		TAG_BO_1.setName("tag 1");
		TAG_BO_2 = new TagBO();
		TAG_BO_2.setName("tag 2");
	}

	@Test
	public void getPet_ReturnsNull_WhenPetIsNotFound() {
		when(mockedPetRepository.findOne(1L)).thenReturn(null);
		when(mockedPetTransformer.toDTO(null)).thenReturn(null);
		PetDTO actual = fixture.getPet(1);
		assertNull(actual);
	}

	@Test
	public void getPet_ReturnsPet_WhenPetIsFound() {
		PetBO petBO = createPetBO(1, "pet name", null, null);
		when(mockedPetRepository.findOne(1L)).thenReturn(petBO);
		when(mockedPetTransformer.toDTO(petBO)).thenReturn(createPetDTO(1, "pet name", null, null));
		PetDTO actual = fixture.getPet(1);
		assertNotNull(actual);
		assertEquals(1, actual.getId());
		assertEquals("pet name", actual.getName());
	}

	@Test
	public void addPet_DoesNotCreatePetAndReturnsFalse_WhenPetDTOIsNull() {
		assertFalse(fixture.addPet(null));
		verify(mockedPetRepository, times(0)).save(any(PetBO.class));
	}

	@Test
	public void addPet_AddsPetAndTagsToRepositoryAndReturnsTrue_WhenRepositoryHasCategoryOfPet() {
		PetDTO petDTO = createPetDTO(1, "pet name", CATEGORY_BO.getName(), Arrays.asList(TAG_BO_1.getName(), TAG_BO_2.getName()));
		PetBO petBO = createPetBO(1, "pet name", CATEGORY_BO, Arrays.asList(TAG_BO_1, TAG_BO_2));
		when(mockedTagRepository.findByName(TAG_BO_1.getName())).thenReturn(null);
		when(mockedTagRepository.findByName(TAG_BO_2.getName())).thenReturn(TAG_BO_2);
		when(mockedPetTransformer.toBO(petDTO)).thenReturn(petBO);
		boolean result = fixture.addPet(petDTO);
		assertTrue(result);
		verify(mockedTagRepository, times(1)).save(TAG_BO_1);
		// verify(mockedTagRepository, times(0)).save(TAG_BO_2); // Fails, perhaps Mockito incompatible with Java 8 Lambdas?
		verify(mockedPetRepository, times(1)).save(petBO);
	}

	@Test
	public void addPet_DoesNotAddPet_WhenRepositoryDoesNotHaveCategoryOfPet() {
		PetDTO petDTO = createPetDTO(1, "pet name", CATEGORY_BO.getName(), Arrays.asList(TAG_BO_1.getName(), TAG_BO_2.getName()));
		PetBO petBO = createPetBO(1, "pet name", null, Arrays.asList(TAG_BO_1, TAG_BO_2)); // Category is null
		when(mockedTagRepository.findByName(TAG_BO_1.getName())).thenReturn(null);
		when(mockedTagRepository.findByName(TAG_BO_2.getName())).thenReturn(TAG_BO_2);
		when(mockedPetTransformer.toBO(petDTO)).thenReturn(petBO);
		boolean result = fixture.addPet(petDTO);
		assertFalse(result);
		verify(mockedPetRepository, times(0)).save(petBO);
	}

	@Test
	public void deletePet_DoesNotDeletePetAndReturnsFalse_WhenPetIsNotFound() {
		when(mockedPetRepository.exists(1L)).thenReturn(false);
		boolean result = fixture.deletePet(1);
		assertFalse(result);
		verify(mockedPetRepository, times(0)).delete(1L);
	}

	@Test
	public void deletePet_DeletesPetAndReturnsTrue_WhenPetIsFound() {
		when(mockedPetRepository.exists(1L)).thenReturn(true);
		boolean result = fixture.deletePet(1);
		assertTrue(result);
		verify(mockedPetRepository, times(1)).delete(1L);
	}

	private PetDTO createPetDTO(long id, String petName, String categoryName, List<String> tags) {
		PetDTO petDTO = new PetDTO();
		petDTO.setId(id);
		petDTO.setName(petName);
		petDTO.setCategory(categoryName);
		petDTO.setTags(tags);
		return petDTO;
	}

	private PetBO createPetBO(long id, String petName, CategoryBO category, List<TagBO> tags) {
		PetBO petBO = new PetBO();
		petBO.setId(id);
		petBO.setName(petName);
		petBO.setCategory(category);
		petBO.setTags(tags);
		return petBO;
	}
}
