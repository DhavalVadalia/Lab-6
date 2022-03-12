package services;

import dataaccess.UserDB;
import java.util.ArrayList;
import models.User;


public class UserService 
{
    public ArrayList<User> getAll() throws Exception 
    {
        UserDB userDB = new UserDB();
        ArrayList<User> users = userDB.getAll();
        return users;
    }
    
    public User get(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        return user;
    }
    
    public void insert(String email, short active, String fname, String lname, String password, int role) throws Exception {
        User user = new User(email, active, fname, lname, password, role);
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }
    
    public void update(String email, short active, String fname, String lname, String password, int role) throws Exception {
        User user = new User(email, active, fname, lname, password, role);
        UserDB userDB = new UserDB();
        userDB.update(user);
    }
    
    public void delete(String email) throws Exception {
        User user = new User();
        user.setEmail(email);
        UserDB userDB = new UserDB();
        userDB.delete(user);
    }
}
