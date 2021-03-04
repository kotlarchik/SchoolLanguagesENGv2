package kotlarchik;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kotlarchik.dao.DAO;
import kotlarchik.model.EntityManufact;
import kotlarchik.service.ServiceManufact;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Console {
    public static void main(String[] args) {
        final SessionFactory factory = new Configuration().configure().buildSessionFactory();

        ObservableList<EntityManufact> manufacts = FXCollections.observableArrayList();
        DAO<EntityManufact, Integer> manufactDAO = new ServiceManufact(factory);
        manufactDAO.readAll();

        for(EntityManufact entityManufact : manufacts){
            System.out.println(manufacts.addAll(manufactDAO.readAll()));
        }
    }
}
