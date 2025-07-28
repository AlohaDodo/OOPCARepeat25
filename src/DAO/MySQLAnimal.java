package DAO;

import DTO.Animal;
import DAO.AnimalDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLAnimal extends MySQL {

    //Helper method to extract Animal from ResultSet
    private Animal extractAnimalFromResultSet(ResultSet rs) throws SQLException {
        return new Animal(
                rs.getInt("id"),
                rs.getString("type"),
                rs.getString("breed"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getFloat("weight"),
                rs.getBoolean("neutered"),
                rs.getString("health"),
                rs.getDate("admitted").toLocalDate(),
                rs.getString("gender"),
                rs.getInt("donor_id")
        );
    }

    //Feature 1 - Get all entities
    public List<Animal> getAllAnimals() throws SQLException {
        String query = "SELECT * FROM animal";
        List<Animal> animals = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query);

             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                animals.add(extractAnimalFromResultSet(rs));
            }
            return animals;
        }
    }
}
