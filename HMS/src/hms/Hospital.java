/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author A.U Computer
 */
public class Hospital {
    public int Id;
    public String Name;
    public String Email;
    public String Address;
    public String PhoneNumber;
    public String LoginId;
    public String Password;
    public int TotalICU;
    public int StatusId;
    private int TotalDoctors;
    private int TotalPatients;
    private int TotalStaff;
    public boolean HasCoronaWards;
    public int TotalCrWards;
    public int TotalCrRemaingWards;
    public int TotalCrConsumedWards;
    public boolean IsAutenticated;
    private List<Hospital> oHospitals;
    public static Hospital oHospital;
    
    Hospital(){
        
    }
    
    Hospital(String loginId, String password, ProgressIndicator oIndicator, Pane oPane)
    {
        
        try{
            Connection con = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HMS;user=TECHFIXER;password=0786;");
            System.out.println("connected to db");
            String query = "SELECT * from Hospital where loginId = '" + loginId + "' AND password = '" + password + "' AND isActive = 1 AND isDeleted = 0";
            PreparedStatement pst=con.prepareStatement(query);
            
            ResultSet oResult = pst.executeQuery();
            if(oResult.next())
            {
                oHospital = new Hospital();
                oHospital.LoginId = loginId;
                oHospital.Password = password;
                oHospital.Id = oResult.getInt(1);
                this.IsAutenticated = true;
                System.out.println(oResult.getInt(1));
                this.Id = oResult.getInt(1);
                this.Name = oResult.getString(2);
                this.Address = oResult.getString(3);
                this.PhoneNumber = oResult.getString(4);
                this.Email = oResult.getString(5);
                
                query = "SELECT * from HospitalDetails where HospitalId = " + this.Id;
                pst=con.prepareStatement(query);
                oResult = null;
                oResult = pst.executeQuery();
                if(oResult.next()){
                    this.TotalICU = oResult.getInt(3);
                    this.HasCoronaWards = oResult.getBoolean(5);
                    this.TotalCrConsumedWards = oResult.getInt(6);
                    this.TotalCrRemaingWards = oResult.getInt(7);
                    this.TotalCrWards = oResult.getInt(8);
                }
                
                
                query = "SELECT * from Patient where HospitalId = " + this.Id + " AND isActive = 1 AND isDeleted = 0";
                pst=con.prepareStatement(query);
                oResult = pst.executeQuery();
                oResult.next();
                this.TotalPatients = oResult.getRow();
                query = "SELECT * from Doctor where HospitalId = " + this.Id + " AND isActive = 1 AND isDeleted = 0";
                pst=con.prepareStatement(query);
                oResult = pst.executeQuery();
                oResult.next();
                this.TotalDoctors = oResult.getRow();
                
                query = "SELECT * from Staff where HospitalId = " + this.Id + " AND isActive = 1 AND isDeleted = 0";
                pst=con.prepareStatement(query);
                oResult = pst.executeQuery();
                oResult.next();
                this.TotalStaff = oResult.getRow();
                
            }
            else{
                this.IsAutenticated = false;
            }
        }
        catch(Exception ex)
        {
            oIndicator.setVisible(false);
            oPane.setDisable(true);
            JOptionPane.showMessageDialog(null, ex.getMessage());
            this.IsAutenticated = false;
        }    
    }
    
    public int GetId(){
        return this.Id;
    }
    
    public String GetName(){
        return this.Name;
    }
    
    public String GetEmail(){
        return this.Email;
    }
    
    public String GetAddress(){
        return this.Address;
    }
    
    public String GetPhone(){
        return this.PhoneNumber;
    }
    
    public int GetTotalCrWards(){
        return this.TotalCrWards;
    }
    
    public int GetRemainingCrWards(){
        return this.TotalCrRemaingWards;
    }
    
    public int GetCosumedCrWards(){
        return this.TotalCrConsumedWards;
    }
    
    public int GetNoOfICU(){
        return this.TotalICU;
    }
    
    public int GetNoOfStaff(){
        return this.TotalStaff;
    }
    
    public int GetNoOfDoctors(){
        return this.TotalDoctors;
    }
    
    public int GetNoOfPatients(){
        return this.TotalPatients;
    }
    
    public boolean IsAuthenticated(){
        return this.IsAutenticated;
    }
    
    
    
    public List<Hospital> GetHospitalsData(){
        oHospitals = new ArrayList<Hospital>();
        try{
            
            Connection con = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HMS;user=TECHFIXER;password=0786;");
            System.out.println("connected to db");
            String query = "SELECT * from Hospital WHERE isActive = 1 AND isDeleted = 0";
            PreparedStatement pst = con.prepareStatement(query);
            
            ResultSet oResult = pst.executeQuery();
            while(oResult.next())
            {
                Hospital oHospital = new Hospital();
                oHospital.Id = oResult.getInt(1);
                oHospital.Name = oResult.getString(2);
                oHospital.Address = oResult.getString(3);
                oHospital.PhoneNumber = oResult.getString(4);
                oHospital.Email = oResult.getString(5);
                
                query = "SELECT * from HospitalDetails where HospitalId = " + oResult.getInt(1);
                pst=con.prepareStatement(query);
                ResultSet oResult2 = pst.executeQuery();
                if(oResult2.next()){
                    oHospital.TotalICU = oResult2.getInt(3);
                    oHospital.HasCoronaWards = oResult2.getBoolean(5);
                    oHospital.TotalCrConsumedWards = oResult2.getInt(6);
                    oHospital.TotalCrRemaingWards = oResult2.getInt(7);
                    oHospital.TotalCrWards = oResult2.getInt(8);
                }
                this.oHospitals.add(oHospital);
                
            }
            
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        return this.oHospitals;
    }
    
    public int RegisterHospital(Hospital oHospital, ProgressIndicator oIndicator, Pane oPane){
        int id = 0;
         Connection con = null;
        try{
           
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HMS;user=TECHFIXER;password=0786;");
            String query = "insert into Hospital(Name, Address, PhoneNumber, Email, RegisteredOn, LoginId, Password) values ('";
            query += oHospital.Name +"','";
            query += oHospital.Address +"','";
            query += oHospital.PhoneNumber +"','";
            query += oHospital.Email +"','";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
            query += LocalDateTime.now().format(dtf) +"','";
            query += oHospital.LoginId +"','";
            query += oHospital.Password +"')";
            PreparedStatement pst = con.prepareStatement(query);
            
            pst.executeUpdate();
            System.out.println("inserted Data");
//            pst.close();
//            con.close();
//            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HMS;user=TECHFIXER;password=0786;");
//            if(oResult.next()){
//                System.out.println("isRegistered");
//                System.out.println(oResult.next());
//            }
//            String loginId = oHospital.LoginId;
//            String password = oHospital.Password;
//           
            query = "SELECT * from Hospital where loginId ='" + oHospital.LoginId + "' AND password ='" + oHospital.Password + "' AND isActive = 1 AND isDeleted = 0";
            PreparedStatement pstm = con.prepareStatement(query); 
            ResultSet oResult = pstm.executeQuery();
            System.out.println("*************");
            System.out.println(oHospital.LoginId);
            System.out.println(oHospital.Password);
            System.out.println(oResult.next());
            if(oResult.next()){
                System.out.println("Fetched Data");
                id = oResult.getInt(1);
            }
            oIndicator.setVisible(false);
            oPane.setDisable(true);
            JOptionPane.showMessageDialog(null, "Successfully Registered");
        }   
        catch(Exception ex){
            oIndicator.setVisible(false);
            oPane.setDisable(true);
            JOptionPane.showMessageDialog(null, ex.getMessage());
            try{
                con.close();
            }catch(Exception exc){
                
            }
        }
        return id;
    }
    
    public void AddHospitalDetails(int hospitalId, int totalICU, boolean hasCoronaWards, int totalCrWards, int StatusId){
        try{
            Connection con = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HMS;user=TECHFIXER;password=0786;");
            String query = "insert into HospitalDetails(HospitalId, StatusId, TotalICU, HasCoronaWards, TotalCoronaWards) values (";
            query += hospitalId + ",";
            query += StatusId + ",";
            query += totalICU + ",";
            query += (hasCoronaWards ? 1 : 0) + ",";
            query += totalCrWards + ")";
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Details Added");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void UpdateHospital(Hospital oHospital, int id){
         try{
            Connection con = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HMS;user=TECHFIXER;password=0786;");
            String query = "UPDATE Hospital SET ";
            query += "Name='" + oHospital.Name +"',";
            query += "Address='" + oHospital.Address +"',";
            query += "PhoneNumber='" + oHospital.PhoneNumber +"',";
            query += "Email='" + oHospital.Email +"',";
            query += "LoginId='" + oHospital.LoginId +"',";
            query += "Password='" + oHospital.Password +"' WHERE Id = " + id;
            PreparedStatement pst = con.prepareStatement(query);
            int result = pst.executeUpdate();
            if(result > 0){
                query = "SELECT * from HospitalDetails WHERE Id = " + id;
                pst = con.prepareStatement(query);
                ResultSet oSet = pst.executeQuery();
                if(oSet.next()){
                    query = "UPDATE HospitalDetails SET ";
                    query += "StatusId=" + oHospital.StatusId +",";
                    query += "TotalICU=" + oHospital.TotalICU +",";
                    if(oHospital.HasCoronaWards)
                        query += "HasCoronaWards=1,";
                    else
                        query += "HasCoronaWards=0,";
                    query += "TotalCoronaWards=" + oHospital.TotalCrWards + ",";
//                    query += "ConsumedCoronaWards=" + oHospital.TotalCrConsumedWards + ",";
                    query += "RemainingCoronaWards=" + (oHospital.TotalCrWards - oSet.getInt(6)) +" WHERE HospitalId = " + id;
                    pst = con.prepareStatement(query);
                    result = pst.executeUpdate();
                
                    if(result > 0)
                        JOptionPane.showMessageDialog(null, "Successfully Updated");
                    else
                        JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support." );
                }
                
            }
                
            else
                JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
            
        }   
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public boolean HasDetails(int Id){
        boolean hasDetails = false;
        try{
            Connection con = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HMS;user=TECHFIXER;password=0786;");
            
             String query = "SELECT * from HospitalDetails WHERE Id = " + Id;
             PreparedStatement pst = con.prepareStatement(query);
             pst = con.prepareStatement(query);
             ResultSet oSet = pst.executeQuery();
             if(oSet.next()){
                hasDetails = true;
             }else{
                hasDetails = false;
             }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return hasDetails;
    }
    
    public void BindData(CheckBox oCBox, TextField totalCrWards, TextField totalIcu, ComboBox status, TextField loginId, TextField email, TextField phoneNumber, TextField address, TextField name, TextField password){
        try{
            Connection con = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HMS;user=TECHFIXER;password=0786;");
            System.out.println("connected to db");
            String query = "SELECT * from Hospital where Id = " + Hospital.oHospital.Id + "AND isActive = 1 AND isDeleted = 0";
            PreparedStatement pst=con.prepareStatement(query);
            ResultSet oResult = pst.executeQuery();
            if(oResult.next())
            {
                name.setText(oResult.getString(2));
                email.setText(oResult.getString(5));
                address.setText(oResult.getString(3));
                phoneNumber.setText(oResult.getString(4));
                loginId.setText(oResult.getString(9));
                password.setText(oResult.getString(10));
                
                query = "SELECT * from HospitalDetails where HospitalId = " + Hospital.oHospital.Id;
                pst=con.prepareStatement(query);
                oResult = null;
                oResult = pst.executeQuery();
                if(oResult.next()){
                    
                    totalIcu.setText(String.valueOf(oResult.getInt(3)));
                    totalCrWards.setText(String.valueOf(oResult.getInt(8)));
                    oCBox.setSelected(oResult.getBoolean(5));
                    status.getSelectionModel().select(oResult.getInt(4) - 1);
                    totalCrWards.setDisable(!oResult.getBoolean(5));
//                    this.HasCoronaWards = oResult.getBoolean(5);
//                    this.TotalCrConsumedWards = oResult.getInt(6);
//                    this.TotalCrRemaingWards = oResult.getInt(7);
//                    this.TotalCrWards = oResult.getInt(8);
                }
        }
        }
        catch(Exception ex){
           ex.printStackTrace();
        }
        
    }
    
    

}
