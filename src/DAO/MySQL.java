package DAO;

//Importing Exception class
import Exceptions.DaoException;

//Importing SQL
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    //Adding the link to the database
    private String url = "jdbc:mysql://localhost:3306/animal_shelter";
    private String user = "root";
    private String password = "";

    //Connecting to the database - if it doesn't connect to the driver or database it will throw an error message
    Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        }
        catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found", e);
        }
    }
}
