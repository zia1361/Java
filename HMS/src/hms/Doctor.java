/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author Hp
 */
public class Doctor extends Hospital {
    private int hospital_id;
    private String address;
    public String name,speciality,phone_no,email;
    public LocalDate appointed_on;
    public static Doctor oDoctor;
    private List<Doctor> oDoctors;
    
    public Doctor(){
    
    }

    public Doctor(String name, String address, String phone_no, String speciality, String email) {
        
        
        this.name = name;
        this.address = address;
        this.speciality = speciality;
        this.appointed_on=LocalDate.now();
        this.phone_no=phone_no;
        this.email=email;
        try{
             String query = "insert into Doctor (Name, Address, Email, ContactNumber, ApointedOn, HospitalId, Speciality) values('" + name.toUpperCase() + "','" + address.toUpperCase() + "','" + email + "','" + phone_no+"','"+ appointed_on+"',"+Hospital.oHospital.Id+",'"+speciality.toUpperCase()+"')";//dummy Id's Recived from Other Module
             int isUpdated = new Utils().InsertData(query);
             if(isUpdated > 0){
                 JOptionPane.showMessageDialog(null, "Successfully Registered");
             }else{
                 JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support." );
             }
        }
        catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage() );
        }
        
    }
    
    public int get_id(){
        return this.Id;
    }
    public String get_name(){
        return this.name;
    }
    public String get_address(){
        return this.address;
    }
    public String get_speciality(){
        return this.speciality;
    }
    
    public int get_hospital_id(){
        return this.hospital_id;
    }
  
    public String get_phone_no(){
        return this.phone_no;
    }
    
    public void UpdateDoctor(Doctor oDoctor, int Id, String Address){
        try{
            String query = "UPDATE Doctor SET ";
            query += "Name='" + oDoctor.name.toUpperCase() +"',";
            query += "Address='" + Address.toUpperCase() +"',";
            query += "ContactNumber='" + oDoctor.phone_no +"',";
            query += "Email='" + oDoctor.email +"',";
            query += "Speciality='" + oDoctor.speciality.toUpperCase() +"'";
            query += " WHERE Id = " + Id;
            int isUpdated = new Utils().InsertData(query);
            if(isUpdated > 0){
                JOptionPane.showMessageDialog(null, "Successfully Updated");
            }else{
                JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support." );
            }
        }
        catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage() );
        }
    }
    public void DeleteDoctor(int Id){
        try{
            String query = "UPDATE Doctor SET ";
            query += "IsActive = 0, IsDeleted = 1";
            query += " WHERE Id = " + Id;
            int isUpdated = new Utils().InsertData(query);
           if(isUpdated > 0){
                JOptionPane.showMessageDialog(null, "Successfully Deleted");
            }else{
                JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support." );
            }
        }
        catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage() );
        }
    }
    
    
    public List<Doctor> GetListOfDoctors(){
        oDoctors = new ArrayList<>();
        try{
            
            String query = "SELECT * from Doctor WHERE HospitalId = " + Hospital.oHospital.Id + " AND isActive = 1 AND isDeleted = 0";
            ResultSet oResult = new Utils().GetResult(query);
            while(oResult.next())
            {
                Doctor oDoctor = new Doctor();
                oDoctor.Id = oResult.getInt(1);
                oDoctor.name = oResult.getString(2);
                oDoctor.address = oResult.getString(3);
                oDoctor.email = oResult.getString(4);
                oDoctor.phone_no = oResult.getString(5);
                oDoctor.speciality = oResult.getString(10);
                oDoctor.appointed_on = LocalDate.parse(oResult.getDate(6).toString());
                
                
                
                oDoctors.add(oDoctor);
                
            }
            
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        return oDoctors;
    }
    
        public void BindData(int doctorId, TextField name, TextField contact_number, TextField email, TextField address, TextField speciality){
        try{
            
            String query = "SELECT * from Doctor where Id = " + doctorId + "AND isActive = 1 AND isDeleted = 0";
            ResultSet oResult = new Utils().GetResult(query);
            if(oResult.next())
            {
                name.setText(oResult.getString(2));
                email.setText(oResult.getString(4));
                address.setText(oResult.getString(3));
                contact_number.setText(oResult.getString(5));
                speciality.setText(oResult.getString(10));
                
            }
        }
        catch(Exception ex){
           JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }

}
