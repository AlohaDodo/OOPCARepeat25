package DAO;

import DTO.Donor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDonor extends MySQL{

    //Helper method to extract Donor from Result Set
    private Donor extractDonorFromResultSet(ResultSet rs) throws SQLException {
        return new Donor(
                rs.getInt("id"),
                rs.getString("firstName"),
                rs.getString("secondName"),
                rs.getString("teleNumber")
        );
    }

    //Useful methods
    //getAllDonors method, lists out all the donors from the database
    public List<Donor> getAllDonors() throws SQLException {
        String query = "SELECT * FROM donor";
        List<Donor> donors = new ArrayList<Donor>();

        try(Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query))
            {
                while (rs.next())
                {
                    donors.add(extractDonorFromResultSet(rs));
                }
                return donors;
            }

    }

    //Feature 4 - If a donor needs to be created
    public int createDonor(Donor donor) throws SQLException {
        String query = "INSERT INTO donor (FIRST_NAME, SECOND_NAME, TELENUMBER) VALUES (?, ?, ?)";

        try(Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, donor.getFirstName());
            pstmt.setString(2, donor.getLastName());
            pstmt.setString(3, donor.getTelephone());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                else
                {
                    throw new SQLException("Failed to insert new donor");
                }
            }
        }
    }
}
