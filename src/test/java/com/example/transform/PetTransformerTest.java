package com.example.transform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.bo.CategoryBO;
import com.example.bo.PetBO;
import com.example.bo.PetStatus;
import com.example.bo.TagBO;
import com.example.dto.PetDTO;
import com.example.repository.CategoryRepository;
import com.example.repository.TagRepository;

@RunWith(MockitoJUnitRunner.class)
public class PetTransformerTest {

	@Mock
	CategoryRepository mockedCategoryRepository;

	@Mock
	TagRepository mockedTagRepository;

	@InjectMocks
	PetTransformer fixture;

	private long PET_ID;
	private String PET_NAME;
	private PetStatus PET_STATUS;
	private CategoryBO CATEGORY_BO;
	private List<TagBO> TAG_BO_LIST;
	private List<String> PHOTO_URL_LIST;

	@Before
	public void setUp() {
		initMocks(this);
		PET_ID = 1L;
		PET_NAME = "pet name";
		PET_STATUS = PetStatus.SOLD;
		CATEGORY_BO = new CategoryBO();
		CATEGORY_BO.setName("pet category");

		TagBO tag1 = new TagBO();
		tag1.setName("tag 1");
		TagBO tag2 = new TagBO();
		tag2.setName("tag 2");
		TAG_BO_LIST = new ArrayList<>();
		TAG_BO_LIST.add(tag1);
		TAG_BO_LIST.add(tag2);

		PHOTO_URL_LIST = Arrays.asList("photo url 1", "photo url 2");
	}

	@Test
	public void toDTO_shouldTransformToDTO() {
		assertNull(fixture.toDTO(null));

		PetBO petBO = createPetBO(PET_ID, PET_NAME, PET_STATUS, CATEGORY_BO, TAG_BO_LIST, PHOTO_URL_LIST);
		PetDTO actual = fixture.toDTO(petBO);

		assertNotNull(actual);
		assertEquals(PET_ID, actual.getId());
		assertEquals(PET_NAME, actual.getName());
		assertEquals(PET_STATUS.getName(), actual.getStatus());
		assertEquals(CATEGORY_BO.getName(), actual.getCategory());

		assertNotNull(actual.getTags());
		assertEquals(TAG_BO_LIST.size(), actual.getTags().size());
		for (int i = 0; i < TAG_BO_LIST.size(); i++) {
			assertEquals(TAG_BO_LIST.get(i).getName(), actual.getTags().get(i));
		}

		assertNotNull(actual.getPhotoURLs());
		assertEquals(PHOTO_URL_LIST.size(), actual.getPhotoURLs().size());
		for (int i = 0; i < PHOTO_URL_LIST.size(); i++) {
			assertEquals(PHOTO_URL_LIST.get(i), actual.getPhotoURLs().get(i));
		}
	}

	@Test
	public void toDO_shouldTransformToDO() {
		assertNull(fixture.toBO(null));

		List<String> tagNames = TAG_BO_LIST.stream()
			.map(TagBO::getName)
			.collect(Collectors.toList());
		PetDTO petDTO = createPetDTO(PET_ID, PET_NAME, PET_STATUS.getName(), CATEGORY_BO.getName(), tagNames, PHOTO_URL_LIST);

		when(mockedCategoryRepository.findByName(petDTO.getCategory())).thenReturn(CATEGORY_BO);
		for (int i = 0; i < tagNames.size(); i++) {
			when(mockedTagRepository.findByName(tagNames.get(i))).thenReturn(TAG_BO_LIST.get(i));
		}

		PetBO actual = fixture.toBO(petDTO);

		assertNotNull(actual);
		assertEquals(PET_ID, actual.getId());
		assertEquals(PET_NAME, actual.getName());
		assertEquals(PET_STATUS, actual.getStatus());
		assertEquals(CATEGORY_BO, actual.getCategory());

		assertNotNull(actual.getTags());
		assertEquals(tagNames.size(), actual.getTags().size());
		for (int i = 0; i < tagNames.size(); i++) {
			assertEquals(tagNames.get(i), actual.getTags().get(i).getName());
		}

		assertNotNull(actual.getPhotoURLs());
		assertEquals(PHOTO_URL_LIST.size(), actual.getPhotoURLs().size());
		for (int i = 0; i < PHOTO_URL_LIST.size(); i++) {
			assertEquals(PHOTO_URL_LIST.get(i), actual.getPhotoURLs().get(i));
		}
	}

	private PetBO createPetBO(long id, String petName, PetStatus status, CategoryBO category, List<TagBO> tags, List<String> photoURLs) {
		PetBO petBO = new PetBO();
		petBO.setId(id);
		petBO.setName(petName);
		petBO.setStatus(status);
		petBO.setCategory(category);
		petBO.setTags(tags);
		petBO.setPhotoURLs(photoURLs);
		return petBO;
	}

	private PetDTO createPetDTO(long id, String petName, String status, String category, List<String> tags, List<String> photoURLs) {
		PetDTO petDTO = new PetDTO();
		petDTO.setId(id);
		petDTO.setName(petName);
		petDTO.setStatus(status);
		petDTO.setCategory(category);
		petDTO.setTags(tags);
		petDTO.setPhotoURLs(photoURLs);
		return petDTO;
	}
}
