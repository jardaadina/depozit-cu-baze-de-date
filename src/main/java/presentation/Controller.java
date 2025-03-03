package presentation;

import businessLogic.ClientBLL;
import businessLogic.OrderBLL;
import businessLogic.ProductBLL;
import dataAccess.ClientDAO;
import dataAccess.OrderDAO;
import dataAccess.ProductDAO;
import model.Client;
import model.Orders;
import model.Product;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa Controller acționează ca un controler în arhitectura MVC (Model-View-Controller).
 * Se ocupă de gestionarea interacțiunii utilizatorului cu interfața grafică și comunică cu straturile logice și de acces la date.
 */
public class Controller implements ActionListener
{
    private View view;
    /**
     * Constructor pentru clasa Controller.
     *
     * @param view O referință către obiectul de tip View cu care va interacționa controlerul.
     */
    public Controller(View view)
    {
        this.view = view;
        view.getClientsButton().addActionListener(this);
        view.getProductsButton().addActionListener(this);
        view.getOrdersButton().addActionListener(this);
        view.getInsertClient().addActionListener(this);
        view.getEditClient().addActionListener(this);
        view.getDeleteClient().addActionListener(this);
        view.getInsertProduct().addActionListener(this);
        view.getEditProduct().addActionListener(this);
        view.getDeleteProduct().addActionListener(this);
        view.getCreateOrder().addActionListener(this);
        view.getInsertButton().addActionListener(this);
        view.getUpdateButton().addActionListener(this);
        view.getDeleteButton().addActionListener(this);
        view.getInsertProductButton().addActionListener(this);
        view.getUpdateProductButton().addActionListener(this);
        view.getDeleteProductButton().addActionListener(this);
        view.getCreateOrderButtonforOrder().addActionListener(this);
    }
    /**
     * Metoda care gestionează evenimentele de acțiune generate de interacțiunea utilizatorului cu interfața grafică.
     *
     * @param e Evenimentul de acțiune generat.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == view.getClientsButton())
        {
            view.getButtonPanel().setVisible(true);
            view.getInsertClient().setVisible(true);
            view.getEditClient().setVisible(true);
            view.getDeleteClient().setVisible(true);
            view.getInsertProduct().setVisible(false);
            view.getEditProduct().setVisible(false);
            view.getDeleteProduct().setVisible(false);
            view.getCreateOrder().setVisible(false);
            view.showALL(new Client());
            view.getScrollPane2().setVisible(false);
            view.getScrollPane3().setVisible(false);

        }
        else if (e.getSource() == view.getProductsButton())
        {
            view.getButtonPanel().setVisible(true);
            view.getInsertProduct().setVisible(true);
            view.getEditProduct().setVisible(true);
            view.getDeleteProduct().setVisible(true);
            view.getInsertClient().setVisible(false);
            view.getEditClient().setVisible(false);
            view.getDeleteClient().setVisible(false);
            view.getCreateOrder().setVisible(false);
            view.showALL(new Product());
            view.getScrollPane1().setVisible(false);
            view.getScrollPane3().setVisible(false);

        }
        else if (e.getSource() == view.getOrdersButton())
        {
            view.getButtonPanel().setVisible(true);
            view.getCreateOrder().setVisible(true);
            view.getInsertClient().setVisible(false);
            view.getEditClient().setVisible(false);
            view.getDeleteClient().setVisible(false);
            view.getInsertProduct().setVisible(false);
            view.getEditProduct().setVisible(false);
            view.getDeleteProduct().setVisible(false);
            view.showALL(new Orders());
            view.getScrollPane1().setVisible(false);
            view.getScrollPane2().setVisible(false);

        }
        else if (e.getSource() == view.getInsertClient())
        {
            view.getEditClient().setVisible(false);
            view.getDeleteClient().setVisible(false);
            view.getScrollPane1().setVisible(false);
            view.getFrameInsertClients().setVisible(true);
        }
        else if(e.getSource() == view.getInsertButton())
        {
            int id = Integer.parseInt(view.getTextFielclientId1().getText());
            String name= view.getTextFielname1().getText();
            String firstName=view.getTextFielfirstName1().getText();
            String phoneNumber=view.getTextFielphoneNumber1().getText();
            String mailAddress=view.getTextFielmailAddress1().getText();
            String address=view.getTextFieldaddress1().getText();

            Client client=new Client(id, name, firstName, phoneNumber, mailAddress, address);
            ClientBLL clientBLL=new ClientBLL(client);
            clientBLL.validateEmail(client);
            clientBLL.validatePhoneNumber(client);
            ClientDAO c =new ClientDAO();
            c.insert(client);

            view.showALL(new Client());
            view.getScrollPane1().setVisible(true);
            view.getFrameInsertClients().setVisible(false);
        }
        else if (e.getSource() == view.getEditClient())
        {
            view.getInsertClient().setVisible(false);
            view.getDeleteClient().setVisible(false);
            view.getScrollPane1().setVisible(false);
            view.getFrameUpdateClients().setVisible(true);
        }
        else if(e.getSource() == view.getUpdateButton())
        {
            int id = Integer.parseInt(view.getTextFielclientId2().getText());
            String name = view.getTextFielname2().getText();
            String firstName = view.getTextFielfirstName2().getText();
            String phoneNumber = view.getTextFielphoneNumber2().getText();
            String mailAddress = view.getTextFielmailAddress2().getText();
            String address = view.getTextFieldaddress2().getText();

            Client client = new Client(id, name, firstName, phoneNumber, mailAddress, address);
            ClientBLL clientBLL = new ClientBLL(client);
            clientBLL.validateEmail(client);
            clientBLL.validatePhoneNumber(client);
            ClientDAO c =new ClientDAO();
            c.update(client);

            view.showALL(new Client());
            view.getScrollPane1().setVisible(true);
            view.getFrameUpdateClients().setVisible(false);
        }
        else if (e.getSource() == view.getDeleteClient())
        {
            view.getInsertClient().setVisible(false);
            view.getEditClient().setVisible(false);
            view.getScrollPane1().setVisible(false);
            view.getFrameDeleteClients().setVisible(true);
        }
        else if(e.getSource() == view.getDeleteButton())
        {
            int id = Integer.parseInt(view.getTextFieldid3().getText());

            ClientDAO c = new ClientDAO();
            c.delete(id);

            view.showALL(new Client());
            view.getScrollPane1().setVisible(true);
            view.getFrameDeleteClients().setVisible(false);
        }
        else if (e.getSource() == view.getInsertProduct())
        {
            view.getEditProduct().setVisible(false);
            view.getDeleteProduct().setVisible(false);
            view.getScrollPane2().setVisible(false);
            view.getFrameinsertProducts().setVisible(true);
        }
        else if(e.getSource() == view.getInsertProductButton())
        {
            int id = Integer.parseInt(view.getTextFieldProductId1().getText());
            String name=view.getTextFieldProductname1().getText();
            float price= Float.parseFloat(view.getTextFieldProductprice1().getText());
            int stock= Integer.parseInt(view.getTextFieldProductstock1().getText());

            Product product=new Product(id, name, stock, price);
            ProductBLL productBll=new ProductBLL(product);
            productBll.validatePrice(product);
            productBll.valideateStock(product);
            ProductDAO p = new ProductDAO();
            p.insert(product);

            view.showALL(new Product());
            view.getScrollPane2().setVisible(true);
            view.getFrameinsertProducts().setVisible(false);
        }
        else if (e.getSource() == view.getEditProduct())
        {
            view.getInsertProduct().setVisible(false);
            view.getDeleteProduct().setVisible(false);
            view.getScrollPane2().setVisible(false);
            view.getFrameUpdateProducts().setVisible(true);
        }
        else if(e.getSource() == view.getUpdateProductButton())
        {
            int id = Integer.parseInt(view.getTextFieldProductId2().getText());
            String name=view.getTextFieldProductname2().getText();
            float price= Float.parseFloat(view.getTextFieldProductprice2().getText());
            int stock= Integer.parseInt(view.getTextFieldProductstock2().getText());

            Product product=new Product(id, name, stock, price);
            ProductBLL productBll=new ProductBLL(product);
            productBll.validatePrice(product);
            productBll.valideateStock(product);
            ProductDAO p = new ProductDAO();
            p.update(product);

            view.showALL(new Product());
            view.getScrollPane2().setVisible(true);
            view.getFrameUpdateProducts().setVisible(false);
        }
        else if (e.getSource() == view.getDeleteProduct())
        {
            view.getInsertProduct().setVisible(false);
            view.getEditProduct().setVisible(false);
            view.getScrollPane2().setVisible(false);
            view.getFrameDeleteProduct().setVisible(true);
        }
        else if(e.getSource() == view.getDeleteProductButton())
        {
            int id = Integer.parseInt(view.getTextFieldproductid3().getText());

            ProductDAO p =new ProductDAO();
            p.delete(id);

            view.showALL(new Product());
            view.getScrollPane2().setVisible(true);
            view.getFrameDeleteProduct().setVisible(false);

        }
        else if (e.getSource() == view.getCreateOrder())
        {
            view.getScrollPane3().setVisible(false);
            view.getScrollPane1().setVisible(true);
            view.getScrollPane2().setVisible(true);
            view.showALL(new Client());
            view.showALL(new Product());
            view.getCreateOrder().setVisible(false);
            view.getFrameCreateOrder().setVisible(true);

        }
        else if(e.getSource() == view.getCreateOrderButtonforOrder())
        {
            int clientId = Integer.parseInt(view.getTextFieldClientIDforOrder().getText());
            int productId = Integer.parseInt(view.getTextFieldProductIDforOrder().getText());
            int quantity = Integer.parseInt(view.getTextFieldQuantityforOrder().getText());

            OrderDAO o =new OrderDAO();
            int orderId = o.insert(clientId, productId, quantity);
            new OrderBLL(new Orders(productId, clientId, quantity));

            if (orderId != 0)
            {
                System.out.println("Order created successfully with ID: " + orderId);
            }
            else
            {
                System.out.println("Error creating order. Please try again.");
            }

            view.showALL(new Orders());
            view.getScrollPane3().setVisible(true);
            view.getScrollPane1().setVisible(false);
            view.getScrollPane2().setVisible(false);
            view.getCreateOrder().setVisible(false);
            view.getFrameCreateOrder().setVisible(false);
        }
    }
}

