package dataAccess;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;

/**
 * Clasa abstractă AbstractDAO reprezintă un DAO (Data Access Object) generic care furnizează operațiile de bază pentru accesul la date.
 * @param <T> Tipul de obiect cu care lucrează acest DAO.
 */
public abstract class AbstractDAO<T>
{
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;
    /**
     * Constructorul clasei AbstractDAO.
     * Inițializează tipul generic T folosind reflexia.
     */
    public AbstractDAO()
    {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    /**
     * Creează o interogare SELECT bazată pe numele tabelei și pe un câmp specificat (opțional).
     * @param field Câmpul după care se va filtra interogarea (opțional).
     * @return Interogarea SELECT generată sub formă de șir de caractere.
     */
    private String createSelectQuery(String field)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        if (!field.isEmpty())
        {
            sb.append(" WHERE " + field + " =?");
        }
        return sb.toString();
    }
    /**
     * Returnează o listă de toate obiectele din tabel.
     * @return Lista obiectelor recuperate din baza de date.
     */
    public List<T> findAll()
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("");
        System.out.println("Query: " + query);
        List<T> resultList = new ArrayList<>();
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            resultList = createObjects(resultSet);
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return resultList;
    }
    /**
     * Obține numele coloanei ID pentru clasa specificată.
     * @return Numele coloanei ID.
     */
    private String getIdColumnName()
    {
        return type.getSimpleName() + "ID";
    }
    /**
     * Creează o interogare SELECT bazată pe numele tabelei și pe coloana ID specificată.
     * @param field Numele coloanei ID.
     * @return Interogarea SELECT generată sub formă de șir de caractere.
     */
    private String createSelectQuery1(String field)
    {
        String idColumnName = getIdColumnName();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        if (!field.isEmpty())
        {
            sb.append(" WHERE " + idColumnName + " =?");
        }
        return sb.toString();
    }
    /**
     * Găsește un obiect în baza de date după ID-ul specificat.
     * @param id ID-ul obiectului căutat.
     * @return Obiectul găsit.
     * @throws IllegalArgumentException Aruncă o excepție dacă obiectul nu există.
     */
    public T findById(int id)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String idColumnName = getIdColumnName();
        String query = createSelectQuery1(idColumnName);
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            List<T> resultObjects = createObjects(resultSet);
            if (!resultObjects.isEmpty())
            {
                return resultObjects.get(0);
            }
            else
            {
                throw new IllegalArgumentException(type.getSimpleName() + " with ID " + id + " does not exist!");
            }
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     * Creează un obiect de tipul specificat pe baza datelor din ResultSet.
     * @param resultSet ResultSet-ul care conține datele pentru obiect.
     * @return Listă de obiecte create din ResultSet.
     */
    private List<T> createObjects(ResultSet resultSet)
    {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;

        for (int i = 0; i < ctors.length; i++)
        {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try
        {
            while (resultSet.next())
            {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields())
                {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * Inserează un nou obiect în baza de date.
     * @param t Obiectul de inserat.
     * @return Obiectul inserat.
     */
    public T insert(T t)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            connection = ConnectionFactory.getConnection();
            String query = createInsertQuery(t);
            statement = connection.prepareStatement(query);
            setStatementParameters(statement, t);
            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Insert operation executed successfully" + "(" + t.getClass().getSimpleName() + ")");
        }
        catch (SQLException | IntrospectionException | IllegalAccessException | InvocationTargetException e)
        {
            LOGGER.log(Level.WARNING, "Error executing insert operation for " + t.getClass().getSimpleName() + ": " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }
    /**
     * Creează o interogare de inserare (INSERT) bazată pe obiectul specificat și pe proprietățile acestuia.
     * @param t Obiectul din care se vor extrage proprietățile pentru a crea interogarea.
     * @return Interogarea de inserare sub formă de șir de caractere.
     * @throws IntrospectionException Aruncă o excepție în cazul unei erori de introspecție.
     */
    private String createInsertQuery(T t) throws IntrospectionException
    {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");

        BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors)
        {
            if (!propertyDescriptor.getName().equals("class"))
            {
                sb.append(propertyDescriptor.getName()).append(", ");
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(") VALUES (");

        for (int i = 0; i < propertyDescriptors.length - 1; i++)
        {
            sb.append("?, ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(")");

        return sb.toString();
    }
    /**
     * Setează parametrii unei instrucțiuni PreparedStatement pe baza proprietăților obiectului specificat.
     * @param statement Instrucțiunea PreparedStatement pentru care se vor seta parametrii.
     * @param t Obiectul din care se vor extrage proprietățile pentru a seta parametrii instrucțiunii PreparedStatement.
     * @throws IntrospectionException Aruncă o excepție în cazul unei erori de introspecție.
     * @throws SQLException Aruncă o excepție în cazul unei erori de SQL.
     * @throws IllegalAccessException Aruncă o excepție în cazul în care accesul la proprietăți este ilegal.
     * @throws InvocationTargetException Aruncă o excepție în cazul în care o metodă invocată prin reflexie aruncă o excepție.
     */
    private void setStatementParameters(PreparedStatement statement, T t) throws IntrospectionException, SQLException, IllegalAccessException, InvocationTargetException
    {
        BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        int index = 1;
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors)
        {
            if (!propertyDescriptor.getName().equals("class"))
            {
                Method getter = propertyDescriptor.getReadMethod();
                Object value = getter.invoke(t);
                statement.setObject(index++, value);
            }
        }
    }
    /**
     * Actualizează un obiect în baza de date.
     * @param t Obiectul de actualizat.
     * @return Obiectul actualizat.
     */
    public T update(T t)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            connection = ConnectionFactory.getConnection();
            String query = createUpdateQuery(t);
            statement = connection.prepareStatement(query);

            setUpdateStatementParameters(statement, t);
            setWhereParameter(statement, t);

            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Update operation executed successfully" + "(" + t.getClass().getSimpleName() + ")");
        }
        catch (SQLException | IntrospectionException | IllegalAccessException | InvocationTargetException e)
        {
            LOGGER.log(Level.WARNING, "Error executing update operation for " + t.getClass().getSimpleName() + ": " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }
    /**
     * Setează parametrii unei instrucțiuni PreparedStatement pentru operația de actualizare pe baza proprietăților obiectului specificat.
     * @param statement Instrucțiunea PreparedStatement pentru care se vor seta parametrii.
     * @param t Obiectul din care se vor extrage proprietățile pentru a seta parametrii instrucțiunii PreparedStatement.
     * @throws IntrospectionException Aruncă o excepție în cazul unei erori de introspecție.
     * @throws SQLException Aruncă o excepție în cazul unei erori de SQL.
     * @throws IllegalAccessException Aruncă o excepție în cazul în care accesul la proprietăți este ilegal.
     * @throws InvocationTargetException Aruncă o excepție în cazul în care o metodă invocată prin reflexie aruncă o excepție.
     */
    private void setUpdateStatementParameters(PreparedStatement statement, T t) throws IntrospectionException, SQLException, IllegalAccessException, InvocationTargetException
    {
        BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        int index = 1;
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors)
        {
            if (!propertyDescriptor.getName().equals("class") && !propertyDescriptor.getName().equals("clientID") && !propertyDescriptor.getName().equals("productID"))
            {
                Method getter = propertyDescriptor.getReadMethod();
                Object value = getter.invoke(t);
                statement.setObject(index++, value);
            }
        }
    }
    /**
     * Setează parametrul pentru clauza WHERE a unei instrucțiuni PreparedStatement pe baza proprietăților obiectului specificat.
     * @param statement Instrucțiunea PreparedStatement pentru care se va seta parametrul pentru clauza WHERE.
     * @param t Obiectul din care se vor extrage proprietățile pentru a seta parametrul pentru clauza WHERE.
     * @throws IntrospectionException Aruncă o excepție în cazul unei erori de introspecție.
     * @throws SQLException Aruncă o excepție în cazul unei erori de SQL.
     * @throws IllegalAccessException Aruncă o excepție în cazul în care accesul la proprietăți este ilegal.
     * @throws InvocationTargetException Aruncă o excepție în cazul în care o metodă invocată prin reflexie aruncă o excepție.
     */
    private void setWhereParameter(PreparedStatement statement, T t) throws IntrospectionException, SQLException, IllegalAccessException, InvocationTargetException
    {
        BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors)
        {
            if (propertyDescriptor.getName().equals("clientID") || propertyDescriptor.getName().equals("productID"))
            {
                Method getter = propertyDescriptor.getReadMethod();
                int id = (int) getter.invoke(t);
                statement.setInt(propertyDescriptors.length-1, id);
                break;
            }
        }
    }
    /**
     * Creează o interogare de actualizare (UPDATE) bazată pe obiectul specificat și pe proprietățile acestuia.
     * @param t Obiectul din care se vor extrage proprietățile pentru a crea interogarea.
     * @return Interogarea de actualizare sub formă de șir de caractere.
     * @throws IntrospectionException Aruncă o excepție în cazul unei erori de introspecție.
     */
    private String createUpdateQuery(T t) throws IntrospectionException
    {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors)
        {
            String propertyName = propertyDescriptor.getName();
            if (!propertyName.equals("class") && !propertyName.equals("clientID") && !propertyName.equals("productID"))
            {
                sb.append(propertyName).append("=?, ");
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(" WHERE ");
        if (type.getSimpleName().equals("Client"))
        {
            sb.append("clientID=?");
        }
        else if (type.getSimpleName().equals("Product"))
        {
            sb.append("productID=?");
        }
        return sb.toString();
    }
    /**
     * Șterge un obiect din baza de date pe baza ID-ului specificat.
     * @param id ID-ul obiectului de șters.
     */
    public void delete(int id)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            connection = ConnectionFactory.getConnection();
            String query = "DELETE FROM " + type.getSimpleName() + " WHERE ";
            if (type.getSimpleName().equals("Client"))
            {
                query += "clientID=?";
            }
            else if (type.getSimpleName().equals("Product"))
            {
                query += "productID=?";
            }
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Delete operation executed successfully" + "(" + type.getSimpleName() + ")");
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "Error executing delete operation for " + type.getSimpleName() + ": " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    /**
     * Metodă abstractă care trebuie implementată pentru a insera o relație între un client, un produs și o cantitate.
     * @param clientID ID-ul clientului.
     * @param productID ID-ul produsului.
     * @param quantity Cantitatea.
     * @return Numărul de înregistrări afectate.
     */
    public abstract int insert(int clientID, int productID, int quantity);
}
