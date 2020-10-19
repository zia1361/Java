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
public class Staff extends Hospital {
    public int Id;
    private int TypeId, HospitalId;
    private String Address;
    public String Name, PhoneNumber, ShiftStart, ShiftEnd;
    public LocalDate ApointedOn;
    public static Staff oStaff;
    private List<Staff> oStaffs;
    
    public Staff(){
        
    }
    
    public Staff(String Name, String PhoneNumber, String ShiftStart, String ShiftEnd, String Address, int TypeId){
        
        this.Name=Name;
        this.PhoneNumber=PhoneNumber;
        this.ShiftStart=ShiftStart;
        this.ShiftEnd=ShiftEnd;
        this.Address=Address;
        this.ApointedOn = LocalDate.now();
        this.TypeId=TypeId;
        
        try{
             String query = "insert into Staff (Name, PhoneNumber, ShiftStart, ShiftEnd, Address, ApointedOn, TypeId, HospitalId) values('" + Name.toUpperCase() + "','" + PhoneNumber + "','" + ShiftStart + "','" + ShiftEnd+"','"+ Address.toUpperCase()+"','"+ApointedOn+"',"+TypeId+","+Hospital.oHospital.Id+")";
             int isUpdated = new Utils().InsertData(query);
             if(isUpdated > 0){
                 JOptionPane.showMessageDialog(null, "Successfully Added");
             }else{
                 JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support." );
             }
        }
        catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage() );
        }
    }

    public int getId() {
        return Id;
    }

    public int getTypeId() {
        return TypeId;
    }

    public int getHospitalId() {
        return HospitalId;
    }

    public String getName() {
        return Name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getShiftStart() {
        return ShiftStart;
    }

    public String getShiftEnd() {
        return ShiftEnd;
    }

    public String getAddress() {
        return Address;
    }
    
    

        public void UpdateStaff(Staff oStaff, int Id, String Address){
        try{
            String query = "UPDATE Staff SET ";
            query += "Name='" + oStaff.Name.toUpperCase() +"',";
            query += "PhoneNumber='" + oStaff.PhoneNumber +"',";
            query += "ShiftStart='" + oStaff.ShiftStart +"',";
            query += "ShiftEnd='" + oStaff.ShiftEnd +"',";
            query += "Address='" + Address.toUpperCase() +"'";
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

    
        
        public void DeleteStaff(int Id){
        try{
            String query = "UPDATE Staff SET ";
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

        
        public List<Staff> GetListOfStaff(){
        oStaffs = new ArrayList<>();
        try{
            
            String query = "SELECT * from Staff WHERE isActive = 1 AND isDeleted = 0";
            ResultSet oResult = new Utils().GetResult(query);
            while(oResult.next())
            {
                Staff oStaff = new Staff();
                oStaff.Id = oResult.getInt(1);
                oStaff.Name = oResult.getString(2);
                oStaff.PhoneNumber = oResult.getString(3);
                oStaff.ShiftStart = oResult.getString(4);
                oStaff.ShiftEnd = oResult.getString(5);
                oStaff.Address = oResult.getString(6);
                oStaff.ApointedOn = LocalDate.parse(oResult.getDate(7).toString());
                oStaff.TypeId = oResult.getInt(8);
                
                
                oStaffs.add(oStaff);
                
            }
            
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        return oStaffs;
    }

        public void BindData(int StaffId, TextField Name, TextField PhoneNumber, TextField ShiftStart, TextField ShiftEnd, TextField Address){
        try{
            String query = "SELECT * from Staff where Id = " + StaffId + "AND isActive = 1 AND isDeleted = 0";
            ResultSet oResult = new Utils().GetResult(query);
            if(oResult.next())
            {
                Name.setText(oResult.getString(2));
                PhoneNumber.setText(oResult.getString(3));
                
                String time = Integer.parseInt(oResult.getString(4).split(":")[0]) > 12 ?
                (Integer.parseInt(oResult.getString(4).split(":")[0]) - 12) + " pm"
                : Integer.parseInt(oResult.getString(4).split(":")[0]) + " am";
                ShiftStart.setText(time);
                
                time = Integer.parseInt(oResult.getString(5).split(":")[0]) > 12 ?
                (Integer.parseInt(oResult.getString(5).split(":")[0]) - 12) + " pm"
                : Integer.parseInt(oResult.getString(5).split(":")[0]) + " am";
                ShiftEnd.setText(time);
                
                
                Address.setText(oResult.getString(6));
                
                
            }
        }
        catch(Exception ex){
           JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }

}
