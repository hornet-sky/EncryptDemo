package my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.dao.UserDAO;
import my.entity.User;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    
    public User add(User user) {
        return userDAO.save(user);
    }
}
