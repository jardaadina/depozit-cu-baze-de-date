package dataAccess;

import connection.ConnectionFactory;
import model.Orders;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa OrderDAO este responsabilă pentru gestionarea accesului la date asociate comenzilor în baza de date.
 * Această clasă extinde AbstractDAO și implementează operații specifice necesare pentru obiectele Orders.
 */
public class OrderDAO extends AbstractDAO<Orders>
{
    /** Logger pentru înregistrarea mesajelor de avertizare sau erori.*/
    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    /** Declarația pentru procedura stocată de inserare a unei comenzi.*/
    private static final String insertStatementString = "CALL CreateOrder(?, ?, ?)";

    /**
     * Inserează o nouă comandă în baza de date, utilizând informațiile furnizate.
     *
     * @param clientID   ID-ul clientului pentru comandă.
     * @param productID  ID-ul produsului comandat.
     * @param quantity   Cantitatea de produse comandate.
     * @return ID-ul comenzii inserate.
     */
    @Override
    public int insert(int clientID, int productID, int quantity)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        CallableStatement insertStatement = null;
        int orderID = 0;
        try
        {
            insertStatement = dbConnection.prepareCall(insertStatementString);
            insertStatement.setInt(1, clientID);
            insertStatement.setInt(2, productID);
            insertStatement.setInt(3, quantity);

            ResultSet rs = insertStatement.executeQuery();

            while (rs.next())
            {
                String message = rs.getString("Message");
                if (message.equals("Order created successfully"))
                {
                    orderID = getLastInsertID(dbConnection);
                }
                else
                {
                    LOGGER.log(Level.WARNING, "OrderDAO:insertOrder " + message);
                }
            }
            rs.close();
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "OrderDAO:insertOrder " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return orderID;
    }
    /**
     * Returnează ID-ul ultimei înregistrări inserate în baza de date.
     *
     * @param dbConnection Conexiunea la baza de date.
     * @return ID-ul ultimei înregistrări inserate.
     * @throws SQLException Excepție în cazul unei erori SQL.
     */
   private static int getLastInsertID(Connection dbConnection) throws SQLException
   {
        Statement statement = dbConnection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID()");
        rs.next();

        int lastInsertID = rs.getInt(1);

        rs.close();
        statement.close();

        return lastInsertID;
    }
}
