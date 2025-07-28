package Exceptions;

import java.sql.SQLException;

public class DaoException extends SQLException {
    public DaoException()
    {
        //Not used
    }

    public DaoException(String aMessage)
    {
        super(aMessage);
    }
}
