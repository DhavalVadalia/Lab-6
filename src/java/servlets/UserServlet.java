package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

public class UserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        UserService us = new UserService();
        RoleService ros = new RoleService();
        ArrayList<User> users = null;

  try 
        {
            users = us.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        String action = request.getParameter("action");
        if (action != null && action.equals("edit")) {
            try {
                String email = request.getParameter("email").replace(" ", "+"); 
                User user = us.get(email);
                ArrayList<Role> roles = ros.getAll();
                request.setAttribute("selectedUser", user);
                request.setAttribute("roles", roles);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (action != null && action.equals("delete")) {
            try {
               String email = request.getParameter("email").replace(" ", "+");
                us.delete(email);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("users", users);
        }
        
        if (action != null && action.equals("add")) {
            ArrayList<Role> roles;
            try {
                roles = ros.getAll();
                request.setAttribute("roles", roles);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("adduser", true);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        
       

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserService us = new UserService();
        Short active;

   
        String action = request.getParameter("action");
        
        String email = request.getParameter("email");
        String ac = request.getParameter("active");
        if(ac!=null){
           active = 1; 
        }else{ active = 0;}
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String password = request.getParameter("password");
        int role = Integer.parseInt(request.getParameter("role"));

        try {
            switch (action) {
                case "create":
                    us.insert(email, active, fname, lname, password, role);
                    break;
                case "update":
                    us.update(email, active, fname, lname, password, role);
                    break;
            }
            request.setAttribute("users", us.getAll());
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
}
