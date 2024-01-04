package com.springboot1.springboot1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springboot1.springboot1.dao.UserDao;
import com.springboot1.springboot1.dao.UserDaoImpl;
import com.springboot1.springboot1.model.User;

import java.util.List;

@Service
//@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }
    @Transactional
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
    @Transactional
    @Override
    public void removeUser(Integer id) {
        userDao.removeUser(id);
    }
    @Transactional
    @Override
    public void updateUser(Integer id, User updateUser) {
        userDao.updateUser(id, updateUser);
    }
}
