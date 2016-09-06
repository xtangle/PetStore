package com.example.transform;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

	@Before
	public void setUp() {
		initMocks(this);
	}

}
