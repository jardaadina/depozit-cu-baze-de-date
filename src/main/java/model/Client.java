package model;

/**
 * Clasa Client reprezintă un obiect model pentru informațiile despre un client.
 */
public class Client
{
    private int clientID;
    private String name;
    private String firstName;
    private String phoneNumber;
    private String mailAddress;
    private String address;

    /**
     * Constructorul fără parametri al clasei Client.
     */
    public Client(){}
    /**
     * Constructorul cu parametri al clasei Client.
     *
     * @param clientID     ID-ul clientului.
     * @param name         Numele de familie al clientului.
     * @param firstName    Prenumele clientului.
     * @param phoneNumber  Numărul de telefon al clientului.
     * @param mailAddress  Adresa de email a clientului.
     * @param address      Adresa fizică a clientului.
     */
    public Client(int clientID, String name, String firstName, String phoneNumber, String mailAddress, String address)
    {
        this.clientID = clientID;
        this.name = name;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
        this.address = address;
    }
    /**
     * Returnează ID-ul clientului.
     *
     * @return ID-ul clientului.
     */
    public int getClientID() {
        return clientID;
    }
    /**
     * Returnează prenumele clientului.
     *
     * @return Prenumele clientului.
     */
    public String getName() {
        return name;
    }
    /**
     * Returnează numele de familie al clientului.
     *
     * @return Numele de familie al clientului.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Returnează numărul de telefon al clientului.
     *
     * @return Numărul de telefon al clientului.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Returnează adresa de email a clientului.
     *
     * @return Adresa de email a clientului.
     */
    public String getMailAddress() {
        return mailAddress;
    }
    /**
     * Returnează adresa fizică a clientului.
     *
     * @return Adresa fizică a clientului.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Setează prenumele clientului.
     *
     * @param name Noul prenume al clientului.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Setează adresa fizică a clientului.
     *
     * @param address Noua adresă fizică a clientului.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Setează ID-ul clientului.
     *
     * @param clientID Noul ID al clientului.
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
    /**
     * Setează numele de familie al clientului.
     *
     * @param firstName Noul nume de familie al clientului.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Setează numărul de telefon al clientului.
     *
     * @param phoneNumber Noul număr de telefon al clientului.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * Setează adresa de email a clientului.
     *
     * @param mailAddress Noua adresă de email a clientului.
     */
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
    /**
     * Suprascrie metoda toString pentru a afișa informații despre client sub formă de șir de caractere.
     *
     * @return Șirul de caractere care conține informațiile despre client.
     */
    @Override
    public String toString()
    {
        return "Client [id=" + clientID + ", last name=" + name + ", first name=" + firstName +", phone number"+ phoneNumber +", email=" + mailAddress + ", address=" + address
                + "]";
    }
}
