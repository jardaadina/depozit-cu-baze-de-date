package presentation;

import dataAccess.ClientDAO;
import dataAccess.OrderDAO;
import dataAccess.ProductDAO;
import model.Client;
import model.Orders;
import model.Product;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 * Aceasta clasă reprezintă interfața grafică a aplicației de gestionare a comenzilor.
 * Ea conține metode pentru afișarea, inserarea, editarea și ștergerea datelor despre clienți, produse și comenzi.
 */
public class View<T> extends JFrame
{
    private Class<T> type;
    private JButton clientsButton;
    private JButton productsButton;
    private JButton ordersButton;
    private JButton insertClient;
    private JButton editClient;
    private JButton deleteClient;
    private JButton insertProduct;
    private JButton editProduct;
    private JButton deleteProduct;
    private JButton createOrder;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JScrollPane scrollPane3;
    private DefaultTableModel tableModel1;
    private DefaultTableModel tableModel2;
    private DefaultTableModel tableModel3;
    private JPanel buttonPanel;
    private JPanel topPanel;
    private JFrame frame;
    private JFrame frameInsertClients;
    private JLabel labelClientId1;
    private JLabel labelname1;
    private JLabel labelfirstName1;
    private JLabel labelphoneNumber1;
    private JLabel labelmailAddress1;
    private JLabel labeladdress1;
    private JTextField textFielclientId1;
    private JTextField textFielname1;
    private JTextField textFielfirstName1;
    private JTextField textFielphoneNumber1;
    private JTextField textFielmailAddress1;
    private JTextField textFieldaddress1;
    private JButton insertButton;
    private JFrame frameUpdateClients;
    private JLabel labelClientId2;
    private JLabel labelname2;
    private JLabel labelfirstName2;
    private JLabel labelphoneNumber2;
    private JLabel labelmailAddress2;
    private JLabel labeladdress2;
    private JTextField textFielclientId2;
    private JTextField textFielname2;
    private JTextField textFielfirstName2;
    private JTextField textFielphoneNumber2;
    private JTextField textFielmailAddress2;
    private JTextField textFieldaddress2;
    private JButton updateButton;
    private JFrame frameDeleteClients;
    private JLabel labelid3;
    private JTextField textFieldid3;
    private JButton deleteButton;
    private JFrame frameinsertProducts;
    private JLabel labelProductId1;
    private JLabel labelProductname1;
    private JLabel labelProductprice1;
    private JLabel labelProductstock1;
    private JTextField textFieldProductId1;
    private JTextField textFieldProductname1;
    private JTextField textFieldProductprice1;
    private JTextField textFieldProductstock1;
    private JButton insertProductButton;
    private JFrame frameUpdateProducts;
    private JLabel labelProductId2;
    private JLabel labelProductname2;
    private JLabel labelProductprice2;
    private JLabel labelProductstock2;
    private JTextField textFieldProductId2;
    private JTextField textFieldProductname2;
    private JTextField textFieldProductprice2;
    private JTextField textFieldProductstock2;
    private JButton updateProductButton;
    private JFrame frameDeleteProduct;
    private JLabel labelproductid3;
    private JTextField textFieldproductid3;
    private JButton deleteProductButton;
    private JFrame frameCreateOrder;
    private JLabel labelClientIDforOrder;
    private JLabel labelProductIDforOrder;
    private JLabel labelQuantityforOrder;
    private JTextField textFieldClientIDforOrder;
    private JTextField textFieldProductIDforOrder;
    private JTextField textFieldQuantityforOrder;
    private JButton createOrderButtonforOrder;
    /**
     * Constructorul clasei View, inițializează interfața grafică și componentele asociate.
     */
    public View()
    {
        this.type = (Class<T>) (getClass().getGenericSuperclass()).getClass();
        frame=new JFrame();
        frame.setPreferredSize(new Dimension(550, 400));
        frame.setVisible(true);
        setTitle("Orders Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        topPanel = new JPanel();
        clientsButton = new JButton("clients");
        productsButton = new JButton("products");
        ordersButton = new JButton("orders");
        topPanel.add(clientsButton);
        topPanel.add(productsButton);
        topPanel.add(ordersButton);
        topPanel.setBackground(new Color(173, 216, 230));
        frame.add(topPanel, BorderLayout.NORTH);

        buttonPanel = new JPanel();

        insertClient = new JButton("insert client");
        insertClient.setBackground(new Color(2, 154, 234));
        insertClient.setForeground(Color.WHITE);

        editClient = new JButton("edit client");
        editClient.setBackground(new Color(2, 154, 234));
        editClient.setForeground(Color.WHITE);

        deleteClient = new JButton("delete client");
        deleteClient.setBackground(new Color(2, 154, 234));
        deleteClient.setForeground(Color.WHITE);

        insertProduct = new JButton("insert product");
        insertProduct.setBackground(new Color(2, 154, 234));
        insertProduct.setForeground(Color.WHITE);

        editProduct = new JButton("edit product");
        editProduct.setBackground(new Color(2, 154, 234));
        editProduct.setForeground(Color.WHITE);

        deleteProduct = new JButton("delete product");
        deleteProduct.setBackground(new Color(2, 154, 234));
        deleteProduct.setForeground(Color.WHITE);

        createOrder = new JButton("create order");
        createOrder.setBackground(new Color(2, 154, 234));
        createOrder.setForeground(Color.WHITE);

        buttonPanel.add(insertClient);
        insertClient.setVisible(false);

        buttonPanel.add(editClient);
        editClient.setVisible(false);

        buttonPanel.add(deleteClient);
        deleteClient.setVisible(false);

        buttonPanel.add(insertProduct);
        insertProduct.setVisible(false);

        buttonPanel.add(editProduct);
        editProduct.setVisible(false);

        buttonPanel.add(deleteProduct);
        deleteProduct.setVisible(false);

        buttonPanel.add(createOrder);
        createOrder.setVisible(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setVisible(false);
        buttonPanel.setBackground(new Color(173, 216, 230));
        frame.add(buttonPanel);

        String[] columnNames1 = {"clientID", "name", "firstName", "phoneNumber", "mailAddress", "address"};
        tableModel1 = new DefaultTableModel(columnNames1, 0);
        table1 = new JTable(tableModel1);
        scrollPane1 = new JScrollPane(table1);

        String[] columnNames2 = {"productID", "name", "stock", "price"};
        tableModel2 = new DefaultTableModel(columnNames2, 0);
        table2 = new JTable(tableModel2);
        scrollPane2 = new JScrollPane(table2);

        String[] columnNames3 = {"OrderID", "ClientID", "ProductID", "totalPrice", "quantity"};
        tableModel3 = new DefaultTableModel(columnNames3, 0);
        table3 = new JTable(tableModel3);
        scrollPane3 = new JScrollPane(table3);

        table1.setPreferredSize(new Dimension(400, 500));
        table1.setBackground(new Color(93, 198, 248));
        table1.setForeground(Color.WHITE);

        table2.setPreferredSize(new Dimension(400, 500));
        table2.setBackground(new Color(93, 198, 248));
        table2.setForeground(Color.WHITE);

        table3.setPreferredSize(new Dimension(400, 500));
        table3.setBackground(new Color(93, 198, 248));
        table3.setForeground(Color.WHITE);
        scrollPane3.setPreferredSize(new Dimension(400, 200));

        add(scrollPane1, BorderLayout.CENTER);
        add(scrollPane2, BorderLayout.CENTER);
        add(scrollPane3, BorderLayout.CENTER);

        scrollPane1.setVisible(false);
        scrollPane2.setVisible(false);
        scrollPane3.setVisible(false);

        buttonPanel.add(scrollPane1);
        buttonPanel.add(scrollPane2);
        buttonPanel.add(scrollPane3);

        clientsButton.setBackground(new Color(2, 154, 234));
        clientsButton.setForeground(Color.WHITE);

        productsButton.setBackground(new Color(2, 154, 234));
        productsButton.setForeground(Color.WHITE);

        ordersButton.setBackground(new Color(2, 154, 234));
        ordersButton.setForeground(Color.WHITE);

        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.pack();

        frameInsertClients = new JFrame();
        frameInsertClients.getContentPane().setBackground(new Color(173, 216, 230));
        frameInsertClients.setPreferredSize(new Dimension(400, 400));
        frameInsertClients.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        labelClientId1 = new JLabel("client ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frameInsertClients.add(labelClientId1, gbc);

        textFielclientId1 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        frameInsertClients.add(textFielclientId1, gbc);

        labelname1 = new JLabel("client Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frameInsertClients.add(labelname1, gbc);

        textFielname1 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        frameInsertClients.add(textFielname1, gbc);

        labelfirstName1 = new JLabel("client First Name:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        frameInsertClients.add(labelfirstName1, gbc);

        textFielfirstName1 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        frameInsertClients.add(textFielfirstName1, gbc);

        labelphoneNumber1 = new JLabel("client's Phone Number:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        frameInsertClients.add(labelphoneNumber1, gbc);

        textFielphoneNumber1 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        frameInsertClients.add(textFielphoneNumber1, gbc);

        labelmailAddress1 = new JLabel("client's Mail Address:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        frameInsertClients.add(labelmailAddress1, gbc);

        textFielmailAddress1 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        frameInsertClients.add(textFielmailAddress1, gbc);

        labeladdress1 = new JLabel("client's Address:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        frameInsertClients.add(labeladdress1, gbc);

        textFieldaddress1 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        frameInsertClients.add(textFieldaddress1, gbc);

        insertButton = new JButton("apply");
        insertButton.setBackground(new Color(2, 154, 234));
        insertButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        frameInsertClients.add(insertButton, gbc);

        frameInsertClients.pack();
        frameInsertClients.setVisible(false);

        frameUpdateClients = new JFrame();
        frameUpdateClients.getContentPane().setBackground(new Color(173, 216, 230));
        frameUpdateClients.setPreferredSize(new Dimension(400, 400));
        frameUpdateClients.setLayout(new GridBagLayout());

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        labelClientId2 = new JLabel("client ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frameUpdateClients.add(labelClientId2, gbc);

        textFielclientId2 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        frameUpdateClients.add(textFielclientId2, gbc);

        labelname2 = new JLabel("client Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frameUpdateClients.add(labelname2, gbc);

        textFielname2 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        frameUpdateClients.add(textFielname2, gbc);

        labelfirstName2 = new JLabel("client First Name:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        frameUpdateClients.add(labelfirstName2, gbc);

        textFielfirstName2 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        frameUpdateClients.add(textFielfirstName2, gbc);

        labelphoneNumber2 = new JLabel("client's Phone Number:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        frameUpdateClients.add(labelphoneNumber2, gbc);

        textFielphoneNumber2 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        frameUpdateClients.add(textFielphoneNumber2, gbc);

        labelmailAddress2 = new JLabel("client's Mail Address:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        frameUpdateClients.add(labelmailAddress2, gbc);

        textFielmailAddress2 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        frameUpdateClients.add(textFielmailAddress2, gbc);

        labeladdress2 = new JLabel("client's Address:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        frameUpdateClients.add(labeladdress2, gbc);

        textFieldaddress2 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        frameUpdateClients.add(textFieldaddress2, gbc);

        updateButton = new JButton("apply");
        updateButton.setBackground(new Color(2, 154, 234));
        updateButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        frameUpdateClients.add(updateButton, gbc);

        frameUpdateClients.pack();
        frameUpdateClients.setVisible(false);

        frameDeleteClients = new JFrame();
        frameDeleteClients.getContentPane().setBackground(new Color(173, 216, 230));
        frameDeleteClients.setPreferredSize(new Dimension(400, 400));
        frameDeleteClients.setLayout(new GridBagLayout());

        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.anchor = GridBagConstraints.WEST;

        labelid3 = new JLabel("client ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frameDeleteClients.add(labelid3, gbc);

        textFieldid3 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        frameDeleteClients.add(textFieldid3, gbc);

        deleteButton = new JButton("apply");
        deleteButton.setBackground(new Color(2, 154, 234));
        deleteButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        frameDeleteClients.add(deleteButton, gbc);

        frameDeleteClients.pack();
        frameDeleteClients.setVisible(false);

        frameinsertProducts = new JFrame();
        frameinsertProducts.getContentPane().setBackground(new Color(173, 216, 230));
        frameinsertProducts.setPreferredSize(new Dimension(350, 350));
        frameinsertProducts.setLayout(new GridBagLayout());

        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        labelProductId1 = new JLabel("product ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frameinsertProducts.add(labelProductId1, gbc);

        textFieldProductId1 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        frameinsertProducts.add(textFieldProductId1, gbc);

        labelProductname1 = new JLabel("product name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frameinsertProducts.add(labelProductname1, gbc);

        textFieldProductname1 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        frameinsertProducts.add(textFieldProductname1, gbc);

        labelProductprice1 = new JLabel("product price:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        frameinsertProducts.add(labelProductprice1, gbc);

        textFieldProductprice1 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        frameinsertProducts.add(textFieldProductprice1, gbc);

        labelProductstock1 = new JLabel("product stock:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        frameinsertProducts.add(labelProductstock1, gbc);

        textFieldProductstock1 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        frameinsertProducts.add(textFieldProductstock1, gbc);

        insertProductButton = new JButton("apply");
        insertProductButton.setBackground(new Color(2, 154, 234));
        insertProductButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        frameinsertProducts.add(insertProductButton, gbc);

        frameinsertProducts.pack();
        frameinsertProducts.setVisible(false);

        frameUpdateProducts = new JFrame();
        frameUpdateProducts.getContentPane().setBackground(new Color(173, 216, 230));
        frameUpdateProducts.setPreferredSize(new Dimension(350, 350));
        frameUpdateProducts.setLayout(new GridBagLayout());

        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        labelProductId2 = new JLabel("product ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frameUpdateProducts.add(labelProductId2, gbc);

        textFieldProductId2 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        frameUpdateProducts.add(textFieldProductId2, gbc);

        labelProductname2 = new JLabel("product name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frameUpdateProducts.add(labelProductname2, gbc);

        textFieldProductname2 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        frameUpdateProducts.add(textFieldProductname2, gbc);

        labelProductprice2 = new JLabel("product price:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        frameUpdateProducts.add(labelProductprice2, gbc);

        textFieldProductprice2 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        frameUpdateProducts.add(textFieldProductprice2, gbc);

        labelProductstock2 = new JLabel("product stock:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        frameUpdateProducts.add(labelProductstock2, gbc);

        textFieldProductstock2 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        frameUpdateProducts.add(textFieldProductstock2, gbc);

        updateProductButton = new JButton("apply");
        updateProductButton.setBackground(new Color(2, 154, 234));
        updateProductButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        frameUpdateProducts.add(updateProductButton, gbc);

        frameUpdateProducts.pack();
        frameUpdateProducts.setVisible(false);

        frameDeleteProduct = new JFrame();
        frameDeleteProduct.getContentPane().setBackground(new Color(173, 216, 230));
        frameDeleteProduct.setPreferredSize(new Dimension(300, 300));
        frameDeleteProduct.setLayout(new GridBagLayout());

        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.anchor = GridBagConstraints.WEST;

        labelproductid3 = new JLabel("product ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frameDeleteProduct.add(labelproductid3, gbc);

        textFieldproductid3 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        frameDeleteProduct.add(textFieldproductid3, gbc);

        deleteProductButton = new JButton("apply");
        deleteProductButton.setBackground(new Color(2, 154, 234));
        deleteProductButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        frameDeleteProduct.add(deleteProductButton, gbc);

        frameDeleteProduct.pack();
        frameDeleteProduct.setVisible(false);

        frameCreateOrder = new JFrame();
        frameCreateOrder.getContentPane().setBackground(new Color(173, 216, 230));
        frameCreateOrder.setPreferredSize(new Dimension(300, 300));
        frameCreateOrder.setLayout(new GridBagLayout());

        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;

        labelClientIDforOrder = new JLabel("client ID:");
        frameCreateOrder.add(labelClientIDforOrder, gbc);

        gbc.gridy = 1;
        textFieldClientIDforOrder = new JTextField(20);
        frameCreateOrder.add(textFieldClientIDforOrder, gbc);

        gbc.gridy = 2;
        labelProductIDforOrder = new JLabel("product ID:");
        frameCreateOrder.add(labelProductIDforOrder, gbc);

        gbc.gridy = 3;
        textFieldProductIDforOrder = new JTextField(20);
        frameCreateOrder.add(textFieldProductIDforOrder, gbc);

        gbc.gridy = 4;
        labelQuantityforOrder = new JLabel("quantity:");
        frameCreateOrder.add(labelQuantityforOrder, gbc);

        gbc.gridy = 5;
        textFieldQuantityforOrder = new JTextField(20);
        frameCreateOrder.add(textFieldQuantityforOrder, gbc);

        createOrderButtonforOrder = new JButton("apply");
        createOrderButtonforOrder.setBackground(new Color(2, 154, 234));
        createOrderButtonforOrder.setForeground(Color.WHITE);
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        frameCreateOrder.add(createOrderButtonforOrder, gbc);

        scrollPane1.setPreferredSize(new Dimension(500, 100));
        scrollPane2.setPreferredSize(new Dimension(500, 100));

        frameCreateOrder.pack();
        frameCreateOrder.setVisible(false);
    }
    /**
     * Metoda pentru afișarea tuturor datelor dintr-o anumită entitate în tabelul corespunzător.
     *
     * @param t Tipul de date (Client, Product, Orders)
     */
    public void showALL(T t)
    {
        if(t instanceof Client)
        {
            ClientDAO c=new ClientDAO();
            List<Client> clientList=c.findAll();
            if (!clientList.isEmpty())
            {
                Object[][] data = new Object[clientList.size()][6];
                int i = 0;
                for (Client client : clientList)
                {
                    data[i][0] = client.getClientID();
                    data[i][1] = client.getName();
                    data[i][2] = client.getFirstName();
                    data[i][3] = client.getPhoneNumber();
                    data[i][4] = client.getMailAddress();
                    data[i][5] = client.getAddress();
                    i++;
                }
                displayTable1(new String[]{"clientID", "name", "firstName", "phoneNumber", "mailAddress", "address"}, data);
            }
        }
        else if( t instanceof Product)
        {
            ProductDAO p=new ProductDAO();
            List<Product> productList=p.findAll();
            if (!productList.isEmpty())
            {
                Object[][] data = new Object[productList.size()][4];
                int i = 0;
                for (Product product : productList)
                {
                    data[i][0] = product.getProductID();
                    data[i][1] = product.getName();
                    data[i][2] = product.getStock();
                    data[i][3] = product.getPrice();
                    i++;
                }
                displayTable2(new String[]{"productID", "name", "stock", "price"}, data);
            }
        }
        else if(t instanceof Orders)
        {
            OrderDAO o = new OrderDAO();
            List<Orders> orderList=o.findAll();
            if (!orderList.isEmpty())
            {
                Object[][] data = new Object[orderList.size()][5];
                int i = 0;
                for (Orders order : orderList)
                {
                    data[i][0] = order.getOrderID();
                    data[i][1] = order.getClientID();
                    data[i][2] = order.getProductID();
                    data[i][3] = order.getTotalPrice();
                    data[i][4] = order.getQuantity();
                    i++;
                }
                displayTable3(new String[]{"OrderID", "ClientID", "ProductID", "totalPrice", "quantity"}, data);
            }
        }
    }
    /**
     * Metoda pentru afișarea datelor în tabelul corespunzător entității Client.
     *
     * @param columnNames Numele coloanelor tabelului
     * @param data        Datele de afișat în tabel
     */
    public void displayTable1(String[] columnNames, Object[][] data)
    {
        tableModel1.setDataVector(data, columnNames);
        scrollPane1.setVisible(true);
    }
    /**
     * Metoda pentru afișarea datelor în tabelul corespunzător entității Product.
     *
     * @param columnNames Numele coloanelor tabelului
     * @param data        Datele de afișat în tabel
     */
    public void displayTable2(String[] columnNames, Object[][] data)
    {
        tableModel2.setDataVector(data, columnNames);
        scrollPane2.setVisible(true);
    }
    /**
     * Metoda pentru afișarea datelor în tabelul corespunzător entității Orders.
     *
     * @param columnNames Numele coloanelor tabelului
     * @param data        Datele de afișat în tabel
     */
    public void displayTable3(String[] columnNames, Object[][] data)
    {
        tableModel3.setDataVector(data, columnNames);
        scrollPane3.setVisible(true);
    }
    public JButton getClientsButton() {
        return clientsButton;
    }
    public JButton getProductsButton() {
        return productsButton;
    }
    public JButton getOrdersButton() {
        return ordersButton;
    }
    public JButton getInsertClient() {
        return insertClient;
    }
    public JButton getEditClient() {
        return editClient;
    }
    public JButton getDeleteClient() {
        return deleteClient;
    }
    public JButton getInsertProduct() {
        return insertProduct;
    }
    public JButton getEditProduct() {
        return editProduct;
    }
    public JButton getDeleteProduct() {
        return deleteProduct;
    }
    public JButton getCreateOrder() {
        return createOrder;
    }
    public JScrollPane getScrollPane1() {
        return scrollPane1;
    }
    public JScrollPane getScrollPane2() {
        return scrollPane2;
    }
    public JScrollPane getScrollPane3() {
        return scrollPane3;
    }
    public JPanel getButtonPanel() {
        return buttonPanel;
    }
    public JFrame getFrameInsertClients() {
        return frameInsertClients;
    }
    public JButton getInsertButton() {
        return insertButton;
    }
    public JFrame getFrame() {
        return frame;
    }
    public JTextField getTextFielclientId1() {
        return textFielclientId1;
    }
    public JTextField getTextFielname1() {
        return textFielname1;
    }
    public JTextField getTextFielfirstName1() {
        return textFielfirstName1;
    }
    public JTextField getTextFielphoneNumber1() {
        return textFielphoneNumber1;
    }
    public JTextField getTextFielmailAddress1() {
        return textFielmailAddress1;
    }
    public JTextField getTextFieldaddress1() {
        return textFieldaddress1;
    }
    public JFrame getFrameUpdateClients() {return frameUpdateClients;}
    public JTextField getTextFielclientId2() {return textFielclientId2;}
    public JTextField getTextFielname2() {
        return textFielname2;
    }
    public JTextField getTextFielfirstName2() {
        return textFielfirstName2;
    }
    public JTextField getTextFielphoneNumber2() {
        return textFielphoneNumber2;
    }
    public JTextField getTextFielmailAddress2() {
        return textFielmailAddress2;
    }
    public JTextField getTextFieldaddress2() {
        return textFieldaddress2;
    }
    public JButton getUpdateButton() {
        return updateButton;
    }
    public JFrame getFrameDeleteClients() {
        return frameDeleteClients;
    }
    public JTextField getTextFieldid3() {
        return textFieldid3;
    }
    public JButton getDeleteButton() {
        return deleteButton;
    }
    public JFrame getFrameinsertProducts() {
        return frameinsertProducts;
    }
    public JTextField getTextFieldProductId1() {
        return textFieldProductId1;
    }
    public JTextField getTextFieldProductname1() {
        return textFieldProductname1;
    }
    public JTextField getTextFieldProductprice1() {
        return textFieldProductprice1;
    }
    public JTextField getTextFieldProductstock1() {
        return textFieldProductstock1;
    }
    public JButton getInsertProductButton() {
        return insertProductButton;
    }
    public JFrame getFrameUpdateProducts() {
        return frameUpdateProducts;
    }
    public JTextField getTextFieldProductId2() {
        return textFieldProductId2;
    }
    public JTextField getTextFieldProductname2() {
        return textFieldProductname2;
    }
    public JTextField getTextFieldProductprice2() {
        return textFieldProductprice2;
    }
    public JTextField getTextFieldProductstock2() {
        return textFieldProductstock2;
    }
    public JButton getUpdateProductButton() {
        return updateProductButton;
    }
    public JFrame getFrameDeleteProduct() {
        return frameDeleteProduct;
    }
    public JTextField getTextFieldproductid3() {
        return textFieldproductid3;
    }
    public JButton getDeleteProductButton() {
        return deleteProductButton;
    }
    public JFrame getFrameCreateOrder() {
        return frameCreateOrder;
    }
    public JTextField getTextFieldClientIDforOrder() {
        return textFieldClientIDforOrder;
    }
    public JTextField getTextFieldProductIDforOrder() {
        return textFieldProductIDforOrder;
    }
    public JTextField getTextFieldQuantityforOrder() {
        return textFieldQuantityforOrder;
    }
    public JButton getCreateOrderButtonforOrder() {
        return createOrderButtonforOrder;
    }
}
