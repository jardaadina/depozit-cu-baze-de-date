package model;
/**
 * Clasa Product reprezintă un obiect model pentru informațiile despre un produs.
 */
public class Product
{
    private int productID;
    private String name;
    private int stock;
    private float price;
    /**
     * Constructorul cu parametri al clasei Product.
     *
     * @param productID ID-ul produsului.
     * @param name      Numele produsului.
     * @param stock     Stocul disponibil pentru produs.
     * @param price     Prețul produsului.
     */
    public Product(int productID, String name, int stock, float price) {
        this.productID = productID;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }
    /**
     * Constructorul fără parametri al clasei Product.
     */
    public Product() {}

    /**
     * Setează ID-ul produsului.
     *
     * @param productID Noul ID al produsului.
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }
    /**
     * Setează stocul disponibil pentru produs.
     *
     * @param stock Noua valoare a stocului.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    /**
     * Setează prețul produsului.
     *
     * @param price Noul preț al produsului.
     */
    public void setPrice(float price) {
        this.price = price;
    }
    /**
     * Returnează ID-ul produsului.
     *
     * @return ID-ul produsului.
     */
    public int getProductID() {
        return productID;
    }
    /**
     * Returnează numele produsului.
     *
     * @return Numele produsului.
     */
    public String getName() {
        return name;
    }
    /**
     * Setează numele produsului.
     *
     * @param name Noul nume al produsului.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returnează stocul disponibil pentru produs.
     *
     * @return Stocul disponibil pentru produs.
     */
    public int getStock() {
        return stock;
    }
    /**
     * Returnează prețul produsului.
     *
     * @return Prețul produsului.
     */
    public float getPrice() {
        return price;
    }
    /**
     * Suprascrie metoda toString pentru a afișa informații despre produs sub formă de șir de caractere.
     *
     * @return Șirul de caractere care conține informațiile despre produs.
     */
    @Override
    public String toString()
    {
        return "Product [id=" + productID+", name=" + name+", stock=" + stock+", price=" + price + "]";
    }
}