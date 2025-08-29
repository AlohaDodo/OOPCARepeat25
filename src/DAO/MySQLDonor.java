package DAO;

import DTO.Donor;
import DTO.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class MySQLDonor extends MySQL implements DonorDao{

    @Override
    public void getDonorBySecondName(String donorSecondName) throws SQLException {
        String sql = "SELECT * FROM donor WHERE second_name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, donorSecondName);
            ResultSet rs = pstmt.executeQuery();
        }
    }

    //Helper method to extract Donor from Result Set
    private Donor extractDonorFromResultSet(ResultSet rs) throws SQLException {
        return new Donor(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("second_name"),
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

    //Feature 5 - Filtering a donors second name
    public List<Donor> filtersecondName(String secondName) throws SQLException {
        String query = "SELECT * FROM donor WHERE second_name LIKE ?";
        List<Donor> donorSecondNames = new ArrayList<>();

        try (Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setString(1, "%" + secondName + "%");

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next())
                {
                    donorSecondNames.add(extractDonorFromResultSet(rs));
                }
            }
        }
        return donorSecondNames;
    }
}
