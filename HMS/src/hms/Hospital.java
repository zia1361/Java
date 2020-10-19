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
    private String LoginId;
    private String Password;
    private int TotalICU;
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
    
    public Hospital(){
        
    }
    
    public Hospital(String loginId, String password, ProgressIndicator oIndicator, Pane oPane)
    {
        
        try{
            Utils oUtils = new Utils();
            String query = "SELECT Hospital.Id,Hospital.Name,Hospital.Address,Hospital.PhoneNumber, Hospital.Email,"
                    + " TotalICU, HasCoronaWards, ConsumedCoronaWards, RemainingCoronaWards, TotalCoronaWards,"
                    + " Patient.Id, Doctor.Id, Staff.Id from Hospital left join HospitalDetails on Hospital.Id = HospitalDetails.HospitalId "
                    +"left join Patient on Hospital.Id = Patient.HospitalId left join Doctor on Hospital.Id = Doctor.HospitalId"
                    + " left join Staff on Hospital.Id = Staff.HospitalId where loginId = '" + loginId + "' "
                    + "AND password = '" + password + "' AND Hospital.IsActive=1 and Hospital.IsDeleted = 0";
            
            ResultSet oResult = oUtils.GetResult(query);
            int counter = 0;
            int patients = 0;
            int doctors = 0;
            int staff = 0;
            while(oResult.next())
            {
                if(counter == 0){
                oHospital = new Hospital();
                oHospital.LoginId = loginId;
                oHospital.Password = password;
                oHospital.Id = oResult.getInt(1);
                this.IsAutenticated = true;
                this.Id = oResult.getInt(1);
                this.Name = oResult.getString(2);
                this.Address = oResult.getString(3);
                this.PhoneNumber = oResult.getString(4);
                this.Email = oResult.getString(5);
                this.TotalICU = oResult.getInt(6);
                this.HasCoronaWards = oResult.getBoolean(7);
                this.TotalCrConsumedWards = oResult.getInt(8);
                this.TotalCrRemaingWards = oResult.getInt(9);
                this.TotalCrWards = oResult.getInt(10);
                oHospital.TotalCrRemaingWards = oResult.getInt(9);
                }
                if(oResult.getObject(11) != null){
                    patients++;
                }
                if(oResult.getObject(12) != null)
                    doctors++;
                if(oResult.getObject(13) != null)
                    staff++;
                
                counter++;
                
            }
            if(counter == 0){
                this.IsAutenticated = false;
            }else{
                this.TotalPatients = patients;
                this.TotalDoctors = doctors;
                this.TotalStaff = staff;
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
           
            String query = "SELECT Hospital.Id,Hospital.Name,Hospital.Address,Hospital.PhoneNumber, Hospital.Email,"
                    + " TotalICU, HasCoronaWards, ConsumedCoronaWards, RemainingCoronaWards, TotalCoronaWards from"
                    + " Hospital left join HospitalDetails on Hospital.Id = HospitalDetails.HospitalId WHERE isActive = 1 AND isDeleted = 0";
            
            ResultSet oResult = new Utils().GetResult(query);
            while(oResult.next())
            {
                Hospital oHospital = new Hospital();
                oHospital.Id = oResult.getInt(1);
                oHospital.Name = oResult.getString(2);
                oHospital.Address = oResult.getString(3);
                oHospital.PhoneNumber = oResult.getString(4);
                oHospital.Email = oResult.getString(5);
                oHospital.TotalICU = oResult.getObject(6) != null ? oResult.getInt(6): 0;
                oHospital.HasCoronaWards = oResult.getObject(7) != null ? oResult.getBoolean(7): false;
                oHospital.TotalCrConsumedWards = oResult.getObject(8) != null ? oResult.getInt(8): 0;
                oHospital.TotalCrRemaingWards = oResult.getObject(9) != null ? oResult.getInt(9): 0;
                oHospital.TotalCrWards = oResult.getObject(10) != null ? oResult.getInt(10): 0;
                this.oHospitals.add(oHospital);
                
            }
            
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        return this.oHospitals;
    }
    
    public void RegisterHospital(Hospital oHospital, ProgressIndicator oIndicator, Pane oPane, String loginId, String password){
        int id = 0;
        try{
           
            String query = "insert into Hospital(Name, Address, PhoneNumber, Email, RegisteredOn, LoginId, Password) values ('";
            query += oHospital.Name +"','";
            query += oHospital.Address +"','";
            query += oHospital.PhoneNumber +"','";
            query += oHospital.Email +"','";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
            query += LocalDateTime.now().format(dtf) +"','";
            query += loginId +"','";
            query += password +"')";
            oIndicator.setVisible(false);
            oPane.setDisable(true);
            if(new Utils().InsertData(query) > 0)
                JOptionPane.showMessageDialog(null, "Successfully Registered. Try Loging In");
            else
                JOptionPane.showMessageDialog(null, "Oops! Something went wrong. Please Try again or Contact Our Support");
            
        }   
        catch(Exception ex){
            oIndicator.setVisible(false);
            oPane.setDisable(true);
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        }
    }
    
    public void AddHospitalDetails(int hospitalId, int totalICU, boolean hasCoronaWards, int totalCrWards, int StatusId){
        try{
            String query = "insert into HospitalDetails(HospitalId, StatusId, TotalICU, HasCoronaWards, TotalCoronaWards) values (";
            query += hospitalId + ",";
            query += StatusId + ",";
            query += totalICU + ",";
            query += (hasCoronaWards ? 1 : 0) + ",";
            query += totalCrWards + ")";
            int result = new Utils().InsertData(query);
            JOptionPane.showMessageDialog(null, "Details Added");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void UpdateHospital(Hospital oHospital, String loginId, String password, int totalICU){
         try{
            String query = "UPDATE Hospital SET ";
            query += "Name='" + oHospital.Name +"',";
            query += "Address='" + oHospital.Address +"',";
            query += "PhoneNumber='" + oHospital.PhoneNumber +"',";
            query += "Email='" + oHospital.Email +"',";
            query += "LoginId='" + loginId +"',";
            query += "Password='" + password +"' WHERE Id = " + Hospital.oHospital.Id;
            int result = new Utils().InsertData(query);
            if(result > 0){
                query = "SELECT * from HospitalDetails WHERE HospitalId = " + Hospital.oHospital.Id;
                ResultSet oSet = new Utils().GetResult(query);
                if(oSet.next()){
                    query = "UPDATE HospitalDetails SET ";
                    query += "StatusId=" + oHospital.StatusId +",";
                    query += "TotalICU=" + totalICU +",";
                    if(oHospital.HasCoronaWards)
                        query += "HasCoronaWards=1,";
                    else
                        query += "HasCoronaWards=0,";
                    query += "TotalCoronaWards=" + oHospital.TotalCrWards + ",";
                    query += "RemainingCoronaWards=" + (oHospital.TotalCrWards - oSet.getInt(6)) +" WHERE HospitalId = " + Hospital.oHospital.Id;
                    result = new Utils().InsertData(query);
                
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
    
    public boolean HasDetails(){
        boolean hasDetails = false;
        try{
             String query = "SELECT * from HospitalDetails WHERE HospitalId = " + Hospital.oHospital.Id;
             ResultSet oSet = new Utils().GetResult(query);
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
            String query = "SELECT Hospital.Name,Hospital.Address,Hospital.PhoneNumber, Hospital.Email, Hospital.LoginId, Hospital.Password,"
                    + " TotalICU, HasCoronaWards, TotalCoronaWards, HospitalDetails.StatusId from Hospital left join HospitalDetails on Hospital.Id = HospitalDetails.HospitalId "
                    + "where Hospital.Id = " + Hospital.oHospital.Id + "AND Hospital.IsActive = 1 AND Hospital.IsDeleted = 0";
            ResultSet oResult = new Utils().GetResult(query);
            if(oResult.next())
            {
                name.setText(oResult.getString(1));
                address.setText(oResult.getString(2));
                phoneNumber.setText(oResult.getString(3));
                email.setText(oResult.getString(4));
                loginId.setText(oResult.getString(5));
                password.setText(oResult.getString(6));
                totalIcu.setText(String.valueOf(oResult.getObject(7) != null ? oResult.getObject(7).toString() : ""));
                oCBox.setSelected(oResult.getObject(8) != null ? oResult.getBoolean(8) : false);
                totalCrWards.setDisable(!(oResult.getObject(8) != null ? oResult.getBoolean(8) : false));
                totalCrWards.setText(String.valueOf(oResult.getObject(9) != null ? oResult.getInt(9) : ""));
                int index = oResult.getObject(10) != null ? (oResult.getInt(10) - 1) : 0;
                status.getSelectionModel().select(index);
        }
        }
        catch(Exception ex){
           ex.printStackTrace();
        }
        
    }
    
    

}
