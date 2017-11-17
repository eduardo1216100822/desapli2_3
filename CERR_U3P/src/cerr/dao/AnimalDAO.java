package cerr.dao;

import java.util.List;

import cerr.model.Animal;

public interface AnimalDAO {
	void createAnimal(Animal animal);
	Animal readAnimal(Long id);
	List<Animal> readAllAnimals();
	void updateAnimal(Animal animal);
	void deleteAnimal(Long id);
}
