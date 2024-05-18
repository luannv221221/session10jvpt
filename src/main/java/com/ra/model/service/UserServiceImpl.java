package com.ra.model.service;

import com.ra.model.dao.UserDAO;
import com.ra.model.entity.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;
    @Override
    public Boolean register(User user) {
        String passwordHash = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(12));
        user.setPassword(passwordHash);
        userDAO.save(user);
        return userDAO.save(user);
    }

    @Override
    public User login(User user) {
        User userFromDatabase = userDAO.findByEmail(user.getEmail());
        if(userFromDatabase != null){
            // check password
            if(BCrypt.checkpw(user.getPassword(),userFromDatabase.getPassword())){
                return  userFromDatabase;
            }
        }
        return null;
    }
}
