package DAO;

import java.sql.SQLException;
import java.util.List;

import DTO.Animal;
import Exceptions.DaoException;

public interface AnimalDao {
    //Feature 1
    List<Animal> getAllAnimals() throws SQLException;

    //Feature 2
    Animal getAnimalById(int id) throws SQLException;

    //Feature 3
    Animal deleteAnimalById(int id) throws SQLException;

    //Feature 4
    Animal createAnimal(Animal animal) throws SQLException;
}
