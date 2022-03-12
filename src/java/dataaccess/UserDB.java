package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.User;


public class UserDB 
{
    public ArrayList<User> getAll() throws Exception
    {
        ArrayList<User> users = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM user u,role r WHERE u.role=r.role_id";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString(1);
                short active = rs.getShort(2);
                String fname =rs.getString(3);
                String lname = rs.getString(4);
                String password = rs.getString(5);
                int role = rs.getInt(6);
                String rolename = rs.getString(8);
                User user = new User(email, active, fname, lname, password, role);
                user.setRolename(rolename);
                users.add(user);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return users;
    }
    
    public User get(String email) throws Exception {
        User user = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM user WHERE email=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                short active = rs.getShort(2);
                String fname =rs.getString(3);
                String lname = rs.getString(4);
                String password = rs.getString(5);
                int role = rs.getInt(6);
                user = new User(email, active, fname, lname, password, role);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return user;
    }

    public void insert(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO user (email, active, first_name, last_name, password, role) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setShort(2, user.getActive());
            ps.setString(3, user.getFname());
            ps.setString(4, user.getLname());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getRole());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void update(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE user SET active=?, first_name=?, last_name=?, password=?, role=? WHERE email=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setShort(1, user.getActive());
            ps.setString(2, user.getFname());
            ps.setString(3, user.getLname());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRole());
            ps.setString(6, user.getEmail());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void delete(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM user WHERE email=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}
