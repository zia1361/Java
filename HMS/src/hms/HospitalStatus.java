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
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author A.U Computer
 */
public class HospitalStatus extends Hospital{
    public SimpleIntegerProperty Id;
    public SimpleStringProperty StatusName;
    private List<HospitalStatus> oStatuses;
    
    HospitalStatus(){
        
    }
    HospitalStatus(int Id, String StatusName){
        this.Id = new SimpleIntegerProperty(Id);
        this.StatusName = new SimpleStringProperty(StatusName);
    }

    public int getId() {
        return Id.get();
    }

    public void setId(SimpleIntegerProperty Id) {
        this.Id = Id;
    }

    public String getStatusName() {
        return StatusName.get();
    }

    public void setStatusName(SimpleStringProperty StatusName) {
        this.StatusName = StatusName;
    }
    
    public List<HospitalStatus> GetStatuses(){
        
        try{
            oStatuses = new ArrayList<HospitalStatus>();
            Connection con = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HMS;user=TECHFIXER;password=0786;");
            System.out.println("connected to db");
            String query = "SELECT * from HospitalStatus where isActive = 1 AND isDeleted = 0";
            PreparedStatement pst=con.prepareStatement(query);
            ResultSet oResults = pst.executeQuery();
            while(oResults.next()){
                HospitalStatus oStatus = new HospitalStatus();
                oStatus.Id.set(oResults.getInt(1));
                oStatus.StatusName.set(oResults.getString(2));
                oStatuses.add(oStatus);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something went Wrong");
        }
        return oStatuses;
    }
    
    public void BindStatuses(ComboBox oBox){
        
        try{
           
            Connection con = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HMS;user=TECHFIXER;password=0786;");
            System.out.println("connected to db");
            String query = "SELECT * from HospitalStatus where isActive = 1 AND isDeleted = 0";
            PreparedStatement pst=con.prepareStatement(query);
            ResultSet oResults = pst.executeQuery();
//            oBox.setCellFactory(new PropertyValueFactory<HospitalStatus,String>("StatusName"));
            oStatuses = new ArrayList<HospitalStatus>();
            ObservableList parentList = FXCollections.observableArrayList();
            while(oResults.next()){
                System.out.println(oResults.getInt(1)+ ""+ oResults.getString(2));
                parentList.add(oResults.getInt(1)+ " "+ oResults.getString(2));
//                oStatuses.add(new HospitalStatus(oResults.getInt(1), oResults.getString(2)));
//                oBox.getItems().add(oResults.getInt(1), oResults.getString(2));
                
            }
            oBox.setItems(parentList);
            
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something went Wrong");
        }
        
    }
}
