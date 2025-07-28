package DAO;

import DTO.Donor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLDonor {

    //Helper method to extract Donor from Result Set
    private Donor extractDonorFromResultSet(ResultSet rs) throws SQLException {
        return new Donor(
                rs.getInt("id"),
                rs.getString("firstName"),
                rs.getString("secondName"),
                rs.getString("teleNumber")
        );
    }
}
