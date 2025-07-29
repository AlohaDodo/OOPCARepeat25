package DAO;

import java.sql.SQLException;
import java.util.List;

import DTO.Animal;
import Exceptions.DaoException;

public interface AnimalDao {
    List<Animal> getAllAnimals() throws SQLException;
    Animal getAnimalById(int id) throws SQLException;
}
