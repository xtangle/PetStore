package com.example.transform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.bo.INamedObject;

@RunWith(MockitoJUnitRunner.class)
public class NamedObjectTransformerTest {

	@Mock
	INamedObject mockedNamedObject;

	@InjectMocks
	NamedObjectTransformer fixture;

	@Before
	public void setUp() {
		initMocks(this);
	}

	@Test
	public void toDTO_shouldTransformToDTO() {
		assertNull(fixture.toDTO(null));

		when(mockedNamedObject.getName()).thenReturn("test name");

		String actual = fixture.toDTO(mockedNamedObject);
		assertNotNull(actual);
		assertEquals("test name", actual);
	}

}
