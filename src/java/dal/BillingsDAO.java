/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Billing;
import model.User;

/**
 *
 * 
 */
public class BillingsDAO extends DBContext{
    public List<Billing> getAllBillings(){
        List<Billing> list = new ArrayList<>();
        String SQLCommand = "SELECT * FROM Billings";
        try {
            PreparedStatement st = connection.prepareStatement(SQLCommand);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String username = rs.getNString(2);
                int planId = rs.getInt(3);
                int accountId = rs.getInt(4);
                String date = rs.getString(5);
                int duration = rs.getInt(6);
                int price = rs.getInt(7);
                list.add(new Billing(id, username, planId, accountId, date, duration, price));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }
    
    public List<Billing> getAllBillingsSort(String sort){
        List<Billing> list = new ArrayList<>();
        String SQLCommand = "SELECT * FROM Billings order by " + sort;
        try {
            PreparedStatement st = connection.prepareStatement(SQLCommand);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String username = rs.getNString(2);
                int planId = rs.getInt(3);
                int accountId = rs.getInt(4);
                String date = rs.getString(5);
                int duration = rs.getInt(6);
                int price = rs.getInt(7);
                list.add(new Billing(id, username, planId, accountId, date, duration, price));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }
    
     public List<Billing> getAllBillingsSortRev(String sort){
        List<Billing> list = new ArrayList<>();
        String SQLCommand = "SELECT * FROM Billings order by " + sort + " desc";
        try {
            PreparedStatement st = connection.prepareStatement(SQLCommand);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String username = rs.getNString(2);
                int planId = rs.getInt(3);
                int accountId = rs.getInt(4);
                String date = rs.getString(5);
                int duration = rs.getInt(6);
                int price = rs.getInt(7);
                list.add(new Billing(id, username, planId, accountId, date, duration, price));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }
    
    public List<Billing> getListByPage(List<Billing> list,int start,int end){
        List<Billing> arr = new ArrayList<>();
        for(int i = start; i < end; i++){
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<Billing> choseOnly(List<Billing> list,String plan){	
        List<Billing> arr = new ArrayList<>();	
        for(int i = 0; i < list.size(); i++){	
            if(list.get(i).getPlanId()==Integer.parseInt(plan))	
                arr.add(list.get(i));	
        }	
        return arr;	
    }

    public List<Billing> revSortListBy(List<Billing> list,int mode){	
        for(int i = 0; i < list.size(); i++){	
            for(int j=i+1;j<list.size();j++){	
                int check=0;	
                if(mode==0)check=(list.get(j).getDate().compareTo(list.get(i).getDate()));	
                if(mode==1){	
                    if(list.get(j).getDuration()>list.get(i).getDuration()){	
                        check=1;	
                    }else{	
                        check=-1;	
                    }	
                }	
                if(mode==2){	
                    if(list.get(j).getPrice()>list.get(i).getPrice()){	
                        check=1;	
                    }else{	
                        check=-1;	
                    }	
                }	
                if(check<0){	
                    Collections.swap(list, i, j);	
                }	
            }	
        }	
        return list;	
    }

    public List<Billing> sortListBy(List<Billing> list,int mode){	
        for(int i = 0; i < list.size(); i++){	
            for(int j=i+1;j<list.size();j++){	
                int check=0;	
                if(mode==0)check=(list.get(j).getDate().compareTo(list.get(i).getDate()));	
                if(mode==1){	
                    if(list.get(j).getDuration()>list.get(i).getDuration()){	
                        check=1;	
                    }else{	
                        check=-1;	
                    }	
                }	
                if(mode==2){	
                    if(list.get(j).getPrice()>list.get(i).getPrice()){	
                        check=1;	
                    }else{	
                        check=-1;	
                    }	
                }	
                if(check>0){	
                    Collections.swap(list, i, j);	
                }	
            }	
        }	
        return list;	
    }

    public List<Billing> getBillingsByName(String xCode) {	
        String xSql = "select * from Billings where username = '" + xCode+"'";	
        List<Billing> list = new ArrayList<Billing>();	
        try {	
            PreparedStatement ps = connection.prepareStatement(xSql);	
            ResultSet rs = ps.executeQuery();	
            while(rs.next()) {	
                int id = rs.getInt(1);	
                String username = rs.getString(2);	
                int planId = rs.getInt(3);	
                int accountId = rs.getInt(4);	
                String date = rs.getString(5);	
                int duration = rs.getInt(6);	
                int price = rs.getInt(7);	
                Billing res = new Billing(id, username, planId, accountId, date, duration, price);	
                list.add(res);	
            }	
            rs.close();        	
            ps.close();	
        }	
            catch(Exception e) {	
            e.printStackTrace();	
        }       	
        return list;	
    }
    
    
    public int isOnlyNumber(String xCode){
        for(int i = 0; i < xCode.length(); i++)
            if(xCode.charAt(i) < '0' || '9' < xCode.charAt(i))
                return 0;
        return 1;
    }
    
    public List<Billing> getBillingsByKey(String key){	
        List<Billing> list = new ArrayList<>();	
        	
        String SQLCommand;	
        if(isOnlyNumber(key) == 1)	
            SQLCommand = "SELECT * FROM Billings WHERE id = "+key	
                    + "OR username like '%" + key + "%' "	
                    + "OR planId = "   + key	
                    + "OR accountId = "+ key	
                    + "OR date like '%"+ key + "%' "	
                    + "OR duration = " + key	
                    + "OR price = "    + key;	
        else	
            SQLCommand = "SELECT * FROM Billings where  username like '%" + key + "%' OR date like '%"  + key + "%' ";	
            	
        try {	
            PreparedStatement st = connection.prepareStatement(SQLCommand);	
            ResultSet rs = st.executeQuery();	
            while(rs.next()){	
                int id = rs.getInt(1);	
                String username = rs.getNString(2);	
                int planId = rs.getInt(3);	
                int accountId = rs.getInt(4);	
                String date = rs.getString(5);	
                int duration = rs.getInt(6);	
                int price = rs.getInt(7);	
                list.add(new Billing(id, username, planId, accountId, date, duration, price));	
            }	
        } catch (Exception e) {	
            System.err.println(e);	
        }	
        return list;	
    }
    
    
    public int createBill(String username, int planId, int accountId, String date, int duration, int price){
        String SQLCommand = "INSERT INTO Billings VALUES ('" + username + "', " + planId + ", " + accountId + ", '" + date + "'," + duration + "," + price + ");";
        System.out.println(SQLCommand);
        try {
            PreparedStatement st = connection.prepareStatement(SQLCommand);
            st.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.err.println(e);
            return -1;
        }
    }
    
    public int getTotalIncomeByMonth(int month, int year){
        String firstDayOfMonth = month + "/1/" + year;
        System.out.println(firstDayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date convertedDate;
        try {
            convertedDate = dateFormat.parse(firstDayOfMonth);    
            Date today = convertedDate;  

            Calendar calendar = Calendar.getInstance();  
            calendar.setTime(today);  

            calendar.add(Calendar.MONTH, 1);  
            calendar.set(Calendar.DAY_OF_MONTH, 1);  
            calendar.add(Calendar.DATE, -1);  

            Date lastDayOfMonth = calendar.getTime();  

            String SQLCommand = "SELECT SUM(PRICE) FROM BILLINGS WHERE '" + firstDayOfMonth + "' <= Date AND Date <= '" + dateFormat.format(lastDayOfMonth) + "'";
            
            System.out.println(SQLCommand);
            
            PreparedStatement st = connection.prepareStatement(SQLCommand);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (ParseException ex) {
            Logger.getLogger(BillingsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BillingsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    public void deleteUserBill(String name) {	
        String xSql = "delete from billings where username=?";	
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
    
    public static void main(String[] args) {
        BillingsDAO bd = new BillingsDAO();
//        bd.createBill("admin", 1, 1, "01/01/2022", 12, 720);
        System.out.println(bd.getTotalIncomeByMonth(3, 2022));
//        List<Billing> list = bd.getAllBillings();
//        System.out.println(list.get(0).getUsername());
    }
}
