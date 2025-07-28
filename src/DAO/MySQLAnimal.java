package DAO;

import DTO.Animal;

import java.sql.ResultSet;
import java.sql.SQLException;

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
}
