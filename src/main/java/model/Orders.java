package model;
/**
 * Clasa Orders reprezintă un obiect model pentru informațiile despre o comandă.
 */
public class Orders
{
    private int orderID;
    private int clientID;
    private int productID;
    private float totalPrice;
    private int quantity;
    /**
     * Constructorul fără parametri al clasei Orders.
     */
    public Orders() {}
    /**
     * Constructorul cu parametri al clasei Orders.
     *
     * @param orderID     ID-ul comenzii.
     * @param productID   ID-ul produsului din comandă.
     * @param clientID    ID-ul clientului care a plasat comanda.
     * @param totalPrice  Prețul total al comenzii.
     * @param quantity    Cantitatea de produse comandate.
     */
    public Orders(int orderID, int productID, int clientID, float totalPrice, int quantity)
    {
        this.orderID = orderID;
        this.clientID = clientID;
        this.productID = productID;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }
    /**
     * Constructorul cu parametri al clasei Orders, fără ID-ul comenzii.
     *
     * @param productID   ID-ul produsului din comandă.
     * @param clientID    ID-ul clientului care a plasat comanda.
     * @param quantity    Cantitatea de produse comandate.
     */
    public Orders( int productID, int clientID, int quantity)
    {
        this.clientID = clientID;
        this.productID = productID;
        this.quantity = quantity;
    }
    /**
     * Setează ID-ul comenzii.
     *
     * @param orderID Noul ID al comenzii.
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    /**
     * Setează ID-ul clientului care a plasat comanda.
     *
     * @param clientID Noul ID al clientului.
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
    /**
     * Setează ID-ul produsului din comandă.
     *
     * @param productID Noul ID al produsului.
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }
    /**
     * Setează prețul total al comenzii.
     *
     * @param totalPrice Noul preț total al comenzii.
     */
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
    /**
     * Setează cantitatea de produse comandate.
     *
     * @param quantity Noua cantitate de produse comandate.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * Returnează ID-ul comenzii.
     *
     * @return ID-ul comenzii.
     */
    public int getOrderID() {
        return orderID;
    }
    /**
     * Returnează ID-ul clientului care a plasat comanda.
     *
     * @return ID-ul clientului.
     */
    public int getClientID() {
        return clientID;
    }
    /**
     * Returnează prețul total al comenzii.
     *
     * @return Prețul total al comenzii.
     */
    public float getTotalPrice() {
        return totalPrice;
    }
    /**
     * Returnează ID-ul produsului din comandă.
     *
     * @return ID-ul produsului.
     */
    public int getProductID() {
        return productID;
    }
    /**
     * Returnează cantitatea de produse comandate.
     *
     * @return Cantitatea de produse comandate.
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Suprascrie metoda toString pentru a afișa informații despre comandă sub formă de șir de caractere.
     *
     * @return Șirul de caractere care conține informațiile despre comandă.
     */
    @Override
    public String toString()
    {
        return "Order [id=" + orderID + ", product id=" + productID + ", client id=" + clientID + ", totalPrice=" + totalPrice + ", quantity=" + quantity +"]";
    }
}