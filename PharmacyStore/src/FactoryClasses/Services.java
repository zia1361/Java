/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FactoryClasses;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Services {
    public void AddMedicine(String name, int price, String manufacturer, String desease, String type, String generation, String reaction){
        String query = "INSERT INTO Medicine(Name, Price, Manufecturer, Desease, Type, Generation, Reaction)";
        query += "VALUES('" + name + "', " + price + ", '" + manufacturer + "', '" + desease + "', '" + type + "', '" + generation + "', '" + reaction + "')";
        
        try{
            int result = new Utils().InsertData(query);
            if(result > 0)
                JOptionPane.showMessageDialog(null, "Medicine Added.");
            else
                JOptionPane.showMessageDialog(null, "Oops! Something went wrong. Please try again later.");
            
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public ArrayList<Antibiotic> GetMedicines(){
        ArrayList<Antibiotic> oBiotics = new ArrayList<Antibiotic>();
        try{
            String query = "SELECT * FROM Medicine";
            ResultSet oResults = new Utils().GetResult(query);
            while(oResults.next()){
                oBiotics.add(new Antibiotic(oResults.getInt(1), oResults.getString(2), oResults.getInt(3), oResults.getString(4), oResults.getString(5), oResults.getString(6), oResults.getString(7), oResults.getString(8)));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return oBiotics;
    }
    
    public void DeleteMedicine(int Id){
        try{
            String query = "DELETE FROM Medicine WHERE Id = " + Id;
            int result = new Utils().DeleteData(query);
            if(result > 0)
                JOptionPane.showMessageDialog(null, "Medicine Deleted.");
            else
              JOptionPane.showMessageDialog(null, "Oops! Something went wrong. Please try again later.");  
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
