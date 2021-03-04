package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.EntityManufact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceManufact implements DAO<EntityManufact, Integer> {
    private final SessionFactory factory;

    public ServiceManufact(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public EntityManufact read(Integer id) {
        try (Session session = factory.openSession()){
            EntityManufact entityManufact = session.get(EntityManufact.class, id);
            return entityManufact;
        }
    }

    public List<EntityManufact> readAll() {
        try (Session session = factory.openSession()){
            Query<EntityManufact> query = session.createQuery("FROM EntityManufact");
            return query.list();
        }
    }
}
