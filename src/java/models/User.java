package models;


public class User 
{
    private String email;
    private short active;
    private String fname;
    private String lname;
    private String password;
    private int role;
    private String rolename;
    
    public User(){
        
    }

    public User(String email, short active, String fname, String lname, String password, int role) 
    {
        this.email = email;
        this.active = active;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public short getActive() {
        return active;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public String getRolename() {
        return rolename;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(short active) {
        this.active = active;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
