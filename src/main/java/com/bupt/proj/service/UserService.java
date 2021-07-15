package com.bupt.proj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bupt.proj.dao.UserDAO;
import com.bupt.proj.model.User;
@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public int addUser(User user){return userDAO.addUser(user);}
    public User getUser (String phone){return userDAO.selectByPhone(phone);}
    public User getUser(int id){return userDAO.selectById(id);}
}
