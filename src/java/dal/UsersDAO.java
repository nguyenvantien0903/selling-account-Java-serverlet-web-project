/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.View;

/**
 *
 * 
 */
public class UsersDAO extends DBContext{
    public List<User> getAllUsers(){
        List<User> list = new ArrayList<>();
        String SQLCommand = "SELECT * FROM Users";
        try {
            PreparedStatement st = connection.prepareStatement(SQLCommand);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String username  = rs.getNString(1);
                String password  = rs.getNString(2);
                String email     = rs.getNString(3);
                String phone     = rs.getNString(4);
                int role         = rs.getInt(5);
                list.add(new User(username, password, email, phone, role));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }
    
    public List<User> getListByPage(List<User> list,int start,int end){
        List<User> arr=new ArrayList<>();
        for(int i=start;i<end;i++){
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<View> getUserAccount(String name){
        List<View> list = new ArrayList<>();
        String SQLCommand = "select plans.name,accounts.account,accounts.password, Dateadd(day, billings.duration * 30, billings.date) as expireDate from users\n" +
                            "join billings on users.username=billings.username\n" +
                            "join plans on billings.planid=plans.id\n" +
                            "join accounts on billings.accountid=accounts.id\n" +
                            "where users.username ='"+name+"'  and Dateadd(day, billings.duration * 30, billings.date) >= GETDATE()";
        System.out.println(SQLCommand);
        try {
            PreparedStatement st = connection.prepareStatement(SQLCommand);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String planname  = rs.getNString(1);
                String account  = rs.getNString(2);
                String pasword    = rs.getNString(3);
                String expireDate  = rs.getString(4);
                list.add(new View(planname, account, pasword, expireDate));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }
    public void update(String xCode,User x) {
        String xSql = "Update Users set password=?,email=?,phone=?,role=? where username=?";
        
        try {
         PreparedStatement ps = connection.prepareStatement(xSql);
         ps.setString(1, x.getPassword());
         ps.setString(2, x.getEmail());
         ps.setString(3, x.getPhone());
         ps.setInt(4, x.getRole());
         ps.setString(5, x.getUsername());
         ResultSet rs = ps.executeQuery();
         
          rs.close();        
          ps.close();
         }
         catch(Exception e) {
           e.printStackTrace();
         }      
    }
    
    public void deleteByName(String name) {
        String xSql = "delete from Users where username=?";
        try {
           PreparedStatement ps = connection.prepareStatement(xSql);
           ps.setString(1, name);
           ps.executeUpdate();
           ps.close();
        }
        catch(Exception e) {
           e.printStackTrace();
        }
    }
    
    public User getUserByID(String xCode) {
        String xSql = "select * from Users where username like '%" + xCode + "%'";
        User res = null;
        
        try {
         PreparedStatement ps = connection.prepareStatement(xSql);
         ResultSet rs = ps.executeQuery();
         
         if(rs.next()) {
            String username  = rs.getNString(1);
            String password  = rs.getNString(2);
            String email     = rs.getNString(3);
            String phone     = rs.getNString(4);
            int role         = rs.getInt(5);
            res = new User(username,password,email,phone,role);
          }
          rs.close();        
          ps.close();
         }
         catch(Exception e) {
           e.printStackTrace();
         }       
        return res;
    }
    
    public List<User> getUsersByKey(String key){
        List<User> list = new ArrayList<>();
        String SQLCommand = "SELECT * FROM Users WHERE Username like  '%" + key + "%' OR Email Like '%" + key + "%' OR Phone like '%" + key + "%'";
        try {
            PreparedStatement st = connection.prepareStatement(SQLCommand);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String username  = rs.getNString(1);
                String password  = rs.getNString(2);
                String email     = rs.getNString(3);
                String phone     = rs.getNString(4);
                int role         = rs.getInt(5);
                list.add(new User(username, password, email, phone, role));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }
    
    public int checkLogin(String user, String pass){
        List<User> list = getAllUsers();
        for(User u : list){
            if(u.getUsername().equalsIgnoreCase(user)){
                if(u.getPassword().equals(pass))
                    return u.getRole();
                else
                    return -1;
            }
        }
        return -2;
    }
    
    public int checkInfo(String data){
        List<User> list = getAllUsers();
        String SQLCommand = "SELECT * FROM Users WHERE Username = '" + data + "' OR Email = '" + data + "' OR Phone = '" + data + "'";
        
//        System.out.println(SQLCommand);
        try {
            PreparedStatement st = connection.prepareStatement(SQLCommand);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return 1;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return 0;
    }
    
    public void createAccount(String username, String password, String email, String phone){
        String SQLCommand = "INSERT INTO Users VALUES ('" + username + "', '" + password + "', '" + email + "', '" + phone + "', 1);";
        try {
            PreparedStatement st = connection.prepareStatement(SQLCommand);
            ResultSet rs = st.executeQuery();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void main(String[] args) {
        UsersDAO ud = new UsersDAO();
        System.out.println(ud.getUserAccount("user"));
//        List<User> list = pd.getUsersByKey("a");
//        User u = pd.getUserByID("aamaya1u");
//        System.out.println(u.getEmail());
//        ud.createAccount("123123", "123", "123123" + "@gmail.com", "0000");
//        System.out.println(ud.getUserByID("user").getEmail());
    }
}
