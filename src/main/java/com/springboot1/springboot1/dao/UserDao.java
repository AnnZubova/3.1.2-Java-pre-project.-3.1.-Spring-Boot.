package com.springboot1.springboot1.dao;
import com.springboot1.springboot1.model.User;
import java.util.List;

public interface UserDao {
    public List<User> getAllUsers();

    public User getUserById(Integer id);

    public void addUser(User user);

    public void updateUser(Integer id, User updateUser);

    public void removeUser(Integer id);
}
