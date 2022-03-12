package services;

import dataaccess.RoleDB;
import java.util.ArrayList;
import models.Role;


public class RoleService 
{
    public ArrayList<Role> getAll() throws Exception 
    {
        RoleDB roleDB = new RoleDB();
        ArrayList<Role> roles = roleDB.getAll();
        return roles;
    }
}
