package com.springboot1.springboot1.dao;

import org.springframework.stereotype.Repository;
import com.springboot1.springboot1.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(Integer id) {
        TypedQuery<User> q = entityManager.createQuery("select u from User u where u.id = :id", User.class);
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(Integer id, User updateUser) {
        entityManager.merge(updateUser);
        entityManager.flush();
    }

    @Override
    public void removeUser(Integer id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
}
