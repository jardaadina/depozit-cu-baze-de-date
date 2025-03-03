package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa ConnectionFactory furnizează o metodă statică pentru obținerea și închiderea conexiunilor la baza de date.
 */
public class ConnectionFactory
{
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/schooldb";
    private static final String USER = "root";
    private static final String PASS = "Luci2004";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * Constructor privat care încarcă driver-ul pentru baza de date.
     */
    private ConnectionFactory()
    {
        try
        {
            Class.forName(DRIVER);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * Creează și returnează o conexiune la baza de date.
     *
     * @return Conexiunea la baza de date.
     */
    private Connection createConnection()
    {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }
    /**
     * Returnează o conexiune la baza de date.
     *
     * @return Conexiunea la baza de date.
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }
    /**
     * Închide conexiunea la baza de date.
     *
     * @param connection Conexiunea care trebuie închisă.
     */
    public static void close(Connection connection)
    {
        if (connection != null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }
    /**
     * Închide un obiect Statement.
     *
     * @param statement Obiectul Statement care trebuie închis.
     */
    public static void close(Statement statement)
    {
        if (statement != null)
        {
            try
            {
                statement.close();
            }
            catch (SQLException e)
            {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }
    /**
     * Închide un ResultSet.
     *
     * @param resultSet ResultSet-ul care trebuie închis.
     */
    public static void close(ResultSet resultSet)
    {
        if (resultSet != null)
        {
            try
            {
                resultSet.close();
            }
            catch (SQLException e)
            {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }
}
