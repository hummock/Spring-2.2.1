package hiber.dao;

import hiber.model.User;
import org.hibernate.NonUniqueResultException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   //Получение пользователя по модели и серийному номеру машины
   @Override
   public User getUserByCar(String model, int series) {
      String hql = "FROM User u LEFT JOIN FETCH u.car WHERE u.car.model = :model AND u.car.series = :series";
      try {
         TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
         query.setParameter("model", model);
         query.setParameter("series", series);
         return query.getSingleResult();
      } catch (NoResultException | NonUniqueResultException e) {
         e.printStackTrace();
      }
      return null;
   }

}
