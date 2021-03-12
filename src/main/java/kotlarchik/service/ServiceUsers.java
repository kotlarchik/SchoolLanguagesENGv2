package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceUsers implements DAO<Users, Integer> {
    private final SessionFactory factory;

    public ServiceUsers(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public Users read(Integer id) {
        try (Session session = factory.openSession()){
            Users users = session.get(Users.class, id);
            return users;
        }
    }

    @Override
    public List<Users> readAll() {
        try (Session session = factory.openSession()){
            Query<Users> query = session.createQuery("FROM Users");
            return query.list();
        }
    }
}
