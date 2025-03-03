package businessLogic;

import dataAccess.ClientDAO;
import dataAccess.ProductDAO;
import model.Client;
import model.Orders;

import javax.swing.*;
import java.util.regex.Pattern;

/**
 * Clasa OrderBLL furnizează operațiile logice de afaceri legate de comenzile efectuate.
 */
public class OrderBLL
{
    /**
     * Constructor pentru clasa OrderBLL.
     *
     * @param order Comanda pentru care se efectuează validările.
     */
    public OrderBLL(Orders order)
    {
        try
        {
            validateProductID(order);
            validateClientID(order);
            validateQuantity(order);
        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Validează ID-ul produsului din comanda dată.
     *
     * @param order Comanda pentru care se validează ID-ul produsului.
     * @throws IllegalArgumentException Dacă ID-ul produsului nu există în baza de date.
     */
    public void validateProductID(Orders order)
    {
        int productID= order.getProductID();
        ProductDAO p =new ProductDAO();
        if (p.findById(productID)==null)
        {
            JOptionPane.showMessageDialog(null, "Product with ID " + productID + " does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Product with ID " + productID + " does not exist!");
        }
    }
    /**
     * Validează ID-ul clientului din comanda dată.
     *
     * @param order Comanda pentru care se validează ID-ul clientului.
     * @throws IllegalArgumentException Dacă ID-ul clientului nu există în baza de date.
     */
    public void validateClientID(Orders order)
    {
        int clientID= order.getClientID();
        ClientDAO c = new ClientDAO();
        if (c.findById(clientID)==null)
        {
            JOptionPane.showMessageDialog(null, "Client with ID " + clientID + " does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Client with ID " + clientID + " does not exist!");
        }
    }
    /**
     * Validează cantitatea din comanda dată.
     *
     * @param order Comanda pentru care se validează cantitatea.
     * @throws IllegalArgumentException Dacă cantitatea este mai mică sau egală cu zero.
     */
    public void validateQuantity(Orders order)
    {
        if (order.getQuantity() <= 0)
        {
            JOptionPane.showMessageDialog(null, "Quantity is not a valid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Quantity is not a valid quantity!");
        }
    }
}
