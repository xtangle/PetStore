package com.example.transform;

import static org.junit.Assert.assertNull;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
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
import com.example.bo.PetStatus;
import com.example.bo.TagBO;
import com.example.repository.CategoryRepository;
import com.example.repository.TagRepository;

@RunWith(MockitoJUnitRunner.class)
public class PetTransformerTest {

	@Mock
	CategoryRepository categoryRepository;

	@Mock
	TagRepository tagRepository;

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


	}

	@Test
	public void toDO_shouldTransformToDO() {

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
}
