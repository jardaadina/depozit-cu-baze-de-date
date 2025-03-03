package dataAccess;

import model.Client;

/**
 * Clasa ClientDAO este responsabilă pentru accesul la date asociate obiectelor de tip Client în baza de date.
 * Această clasă extinde AbstractDAO și implementează operațiile specifice necesare pentru obiectele Client.
 */
public class ClientDAO extends AbstractDAO<Client>
{
    @Override
    public int insert(int clientID, int productID, int quantity) {
        return 0;
    }
}

