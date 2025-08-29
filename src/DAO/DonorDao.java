package DAO;

import DTO.Donor;

import java.sql.SQLException;
import java.util.List;

public interface DonorDao {
    //Feature 5
    void getDonorBySecondName(String secondName) throws SQLException;
    List<Donor> filtersecondName(String secondName) throws SQLException;
}
