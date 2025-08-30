package DAO;

import DTO.Animal;
import DAO.AnimalDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

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
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query);)
            {
                while (rs.next())
                    {
                        animals.add(extractAnimalFromResultSet(rs));
                    }
                return animals;
            }
    }

    //Feature 2 - Filtering out animal by ID
    public Animal getAnimalById(int id) throws SQLException {
        String query = "SELECT * FROM animal WHERE id = ?";

        try (Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()){
                    return extractAnimalFromResultSet(rs);
                }
            }
        }
        return null;
    }

    //Feature 3 - Delete animal by ID
    public void deleteAnimalById(int id) throws SQLException {
        String query = "DELETE FROM animal WHERE id = ?";

        try (Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    //Feature 4 - Creating animal
    public int createAnimal(Animal animal) throws SQLException {
        String query =  "INSERT INTO animal (type, breed, name, age, weight, neutered, health, admitted, gender, donor_id) " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, animal.getType());
            pstmt.setString(2, animal.getBreed());
            pstmt.setString(3, animal.getName());
            pstmt.setInt(4, animal.getAge());
            pstmt.setFloat(5, animal.getWeight());
            pstmt.setBoolean(6, animal.isNeutered());
            pstmt.setString(7, animal.getHealth());
            pstmt.setDate(8, Date.valueOf(animal.getAdmitted()));
            pstmt.setString(9, animal.getGender());
            pstmt.setInt(10, animal.getDonorId());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to insert new animal");
                }
            }
        }
    }

}
