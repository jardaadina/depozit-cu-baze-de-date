package dataAccess;

import model.Product;

/**
 * Clasa ProductDAO este responsabilă pentru gestionarea accesului la date asociate produselor în baza de date.
 * Această clasă extinde AbstractDAO și implementează operații specifice necesare pentru obiectele Product.
 */
public class ProductDAO extends AbstractDAO<Product>
{
    @Override
    public int insert(int clientID, int productID, int quantity) {
        return 0;
    }
}
