package DAO;

//Importing Exception class
import Exceptions.DaoException;

//Importing SQL
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    //Adding the link to the database
    String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/animal_shelter";
    private String user = "root";
    private String password = "";
    Connection connection = null;

    //Connecting to the database - if it doesn't connect to the driver or database it will throw an error message
    Connection getConnection() throws SQLException {
        try
        {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Failed to load driver" + e.getMessage());
            System.exit(1);
        }
        catch (SQLException e)
        {
            System.out.println("Failed to get database connection" + e.getMessage());
            System.exit(2);
        }
        return connection;
    }

    //Freeing the connection - if it fails to free it will throw error
    public void freeConnection(Connection connection) throws DaoException {
        try
        {
            if (connection != null)
            {
                connection.close();
                connection = null;
            }
        }
        catch (SQLException e)
        {
            System.out.println("Failed to free connection" + e.getMessage());
            System.exit(1);
        }
    }


}
