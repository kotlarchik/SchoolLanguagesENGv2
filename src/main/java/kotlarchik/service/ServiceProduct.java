package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.EntityProduct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceProduct implements DAO<EntityProduct, Integer> {
    private final SessionFactory factory;

    public ServiceProduct(SessionFactory factory){
        this.factory = factory;
    }
    @Override
    public EntityProduct read(Integer id) {
        try (Session session = factory.openSession()){
            EntityProduct entityProduct = session.get(EntityProduct.class, id);
            return entityProduct;
        }
    }

    @Override
    public List<EntityProduct> readAll() {
        try (Session session = factory.openSession()){
            Query<EntityProduct> query = session.createQuery("FROM EntityProduct ");
            return query.list();
        }
    }
}
