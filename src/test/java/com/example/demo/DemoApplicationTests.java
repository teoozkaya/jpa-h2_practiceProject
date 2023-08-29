package com.example.demo;

import com.example.demo.model.Dog;
import com.example.demo.repository.DogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	DogRepository dogRepository;


	@Test @Sql("/clear.sql")
	public void dogSaveComplete() {
		Dog dog = new Dog("Cavalier", "Kobe", "Brown", true);
		dogRepository.save(dog);
		Optional<Dog> dog2 = dogRepository.findById(Long.valueOf(1));

		assertEquals("Kobe",dog2.get().getName());
	}

	@Test @Sql("/clear.sql")
	public void dogUpdateComplete() {
		Dog dog = new Dog("Cavalier", "Kobe", "Brown", true);
		dogRepository.save(dog);
		Optional<Dog> dog2 = dogRepository.findById(Long.valueOf(1));
		dog2.get().setCanSit(true);
		assertEquals(true, dog2.get().isCanSit());
	}

	@Test @Sql("/clear.sql")
	public void dogNotFound() {
		Optional<Dog> dog2 = dogRepository.findById(Long.valueOf(1));
		assertEquals(false, dog2.isPresent());
	}


	@Test @Sql("/clear.sql")
	public void dogDelete() {
		Dog dog = new Dog("Cavalier", "Kobe", "Brown", true);
		dogRepository.save(dog);
		Optional<Dog> dog2 = dogRepository.findById(Long.valueOf(1));
		assertEquals(true, dog2.isPresent());
		dogRepository.deleteById(1L);
		Optional<Dog> dog3 = dogRepository.findById(Long.valueOf(1));
		assertEquals(false, dog3.isPresent());
	}

}
