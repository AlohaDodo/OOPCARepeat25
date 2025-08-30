package DAO;

import DTO.Donor;

import java.sql.SQLException;
import java.util.List;

public interface DonorDao {
    //Feature 5
    void getDonorBySecondName(String secondName) throws SQLException;
    List<Donor> filterSecondName(String secondName) throws SQLException;

    //Feature 6
    List<Donor> getAllDonors() throws SQLException;
}
