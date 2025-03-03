package businessLogic;

import model.Product;
import javax.swing.*;

/**
 * Clasa ProductBLL furnizează operațiile logice de afaceri legate de produse.
 */
public class ProductBLL
{
    /**
     * Constructor pentru clasa ProductBLL.
     *
     * @param product Produsul pentru care se efectuează validările.
     */
    public ProductBLL(Product product)
    {
        validatePrice(product);
        valideateStock(product);
    }
    /**
     * Validează prețul produsului dat.
     *
     * @param product Produsul pentru care se validează prețul.
     * @throws IllegalArgumentException Dacă prețul este mai mic sau egal cu zero.
     */
    public void validatePrice(Product product)
    {
        if(product.getPrice() <= 0)
        {
            JOptionPane.showMessageDialog(null, "price is not a valid price!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("price is not a valid price!");
        }
    }
    /**
     * Validează stocul produsului dat.
     *
     * @param product Produsul pentru care se validează stocul.
     * @throws IllegalArgumentException Dacă stocul este mai mic decât zero.
     */
    public void valideateStock(Product product)
    {
        if(product.getStock() < 0)
        {
            JOptionPane.showMessageDialog(null, "stock is not a valid stock!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("stock is not a valid stock!");
        }
    }
}
