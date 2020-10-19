/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class AddStaffController implements Initializable {
    ObservableList<String>ShiftStartTime=FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
    ObservableList<String>ShiftStartpm_am=FXCollections.observableArrayList("am","pm");
    
    ObservableList<String>ShiftEndTime=FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
    ObservableList<String>ShiftEndpm_am=FXCollections.observableArrayList("am","pm");
    
    
    

    
    @FXML
    private Button Back;
    @FXML
    private TextField Name;
    @FXML
    private TextField PhoneNumber;
    @FXML
    private TextField Address;
    @FXML
    private ChoiceBox<String> ShiftStartPM_AM;
    @FXML
    private ChoiceBox<String> ShiftEndPM_AM;
    @FXML
    private ChoiceBox<String> TypeId;
    @FXML
    private ChoiceBox<String> ShiftStart;
    @FXML
    private ChoiceBox<String> ShiftEnd;
    @FXML
    private Button Register;
    @FXML
    private Button ViewAllStaffs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ShiftStart.setItems(ShiftStartTime);
        ShiftStartPM_AM.setItems(ShiftStartpm_am);
        
        ShiftEnd.setItems(ShiftEndTime);
        ShiftEndPM_AM.setItems(ShiftEndpm_am);
        
         try{
                       
            String query = "SELECT * from StaffType where isActive = 1 AND isDeleted = 0";
            ResultSet oResults = new Utils().GetResult(query);
            
            ObservableList typeList = FXCollections.observableArrayList();
            while(oResults.next()){
                
                typeList.add(oResults.getInt(1)+ " "+ oResults.getString(2));
            }
            
            TypeId.setItems(typeList);
            
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, "Oops! Something went Wrong");
        }
        
    }    


    @FXML
    private void handleButtonActionRegister(ActionEvent event) throws Exception{
        
        
        if(Name.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Name Reguired");
        }
        else if(PhoneNumber.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Contact Number Reguired");
        }
        else if(Address.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Address Reguired");
        }
        else if(TypeId.getValue() == null){
            JOptionPane.showMessageDialog(null, "Staff Type Reguired");
        }
        else if(ShiftStart.getValue() == null || ShiftStartPM_AM.getValue() == null){
            JOptionPane.showMessageDialog(null, "Shift Start Time Reguired");
        }
        else if (ShiftEnd.getValue() == null || ShiftEndPM_AM.getValue() == null){
            JOptionPane.showMessageDialog(null, "Shift End Time Reguired");
        }
        else if(ShiftStartPM_AM.getValue().equals(ShiftEndPM_AM.getValue()) && ShiftStart.getValue().equals(ShiftEnd.getValue())){
            JOptionPane.showMessageDialog(null, "Shift Start Time And Shift End Time Cannot Be Same");
        }
        else if(ShiftStartPM_AM.getValue().equals(ShiftEndPM_AM.getValue()) && Integer.parseInt(ShiftStart.getValue()) >= Integer.parseInt(ShiftEnd.getValue())){
            JOptionPane.showMessageDialog(null, "Shift Start Time And End Time Missmatch");
        }
        
        
        else{
            int splitted_type_id = 0;
            for (String s : TypeId.getValue().split(" ")) {
                splitted_type_id = Integer.parseInt(s);
                break;
            }
            Staff oStaff = new Staff(Name.getText(), PhoneNumber.getText(), ShiftStart.getValue()+ShiftStartPM_AM.getValue(), ShiftEnd.getValue()+ShiftEndPM_AM.getValue(), Address.getText(), splitted_type_id);
        }
    }


    @FXML
    private void handleButtonActionBack(ActionEvent event) {
        try{
            Stage stage;
            Parent root;
            if(event.getSource()==Back){
                root=FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                stage=(Stage)Back.getScene().getWindow();
                Scene scene =new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("ADD STAFF");
                stage.show();
            }
        }
        catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }

    @FXML
    private void handleButtonActionViewAllStaffs(ActionEvent event) {
        try{
            Stage stage;
            Parent root;
            if(event.getSource()==ViewAllStaffs){
                root=FXMLLoader.load(getClass().getResource("AllStaff.fxml"));
                stage=(Stage)ViewAllStaffs.getScene().getWindow();
                Scene scene =new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("ALL STAFF");
                stage.show();
            }
        }
        catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    
}
