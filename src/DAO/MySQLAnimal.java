package DAO;

import DTO.Animal;
import DAO.AnimalDao;

import java.sql.*;
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
}
