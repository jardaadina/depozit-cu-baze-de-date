# depozit-cu-baze-de-date
  Această aplicație Java este un sistem de gestiune care permite adăugarea, modificarea și ștergerea clienților și produselor, precum și plasarea comenzilor și generarea automată a facturilor. Aplicația este dezvoltată folosind principii OOP și o arhitectură modulară, incluzând un sistem de validare a datelor și interacțiune cu o bază de date.
  
Funcționalități principale
Gestionarea clienților (adăugare, editare, ștergere)
Gestionarea produselor (adăugare, editare, ștergere)
Plasarea comenzilor și actualizarea automată a stocului
Generarea automată a facturilor pentru comenzile plasate
Interfață grafică intuitivă pentru gestionarea operațiunilor
Persistență a datelor utilizând o bază de date relațională
Validare a datelor de intrare (ex: email, număr de telefon, prețuri, stocuri)

Structura proiectului

src/  
Client.java – Reprezentarea unui client
Product.java – Reprezentarea unui produs
Orders.java – Gestionarea comenzilor
ClientBLL.java, ProductBLL.java, OrderBLL.java – Logică de business pentru validarea datelor
ClientDAO.java, ProductDAO.java, OrderDAO.java – Acces la baza de date
ConnectionFactory.java – Gestionarea conexiunii la baza de date
View.java – Interfața grafică
Controller.java – Gestionarea interacțiunii utilizatorilor
Main.java – Punctul de intrare în aplicație

test/
Teste unitare pentru verificarea funcționalităților

Îmbunătățiri viitoare
Implementarea unui sistem de autentificare și autorizare
O interfață grafică mai prietenoasă
Optimizarea performanței pentru volume mari de date
