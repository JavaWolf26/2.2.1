package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserCar(String model, int series) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from User where car.model = :model and car.series = :series");
        query.setParameter("model", model).setParameter("series", series);
        return (User) query.setMaxResults(1).getSingleResult();
    }

    @Override
    public void clean() {
        sessionFactory.getCurrentSession().createQuery("Delete FROM Car").executeUpdate();
        sessionFactory.getCurrentSession().createQuery("delete from User").executeUpdate();
    }
}
