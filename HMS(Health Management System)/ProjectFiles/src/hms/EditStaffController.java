/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class EditStaffController implements Initializable {

    @FXML
    private TextField Name;
    @FXML
    private TextField PhoneNumber;
    @FXML
    private TextField ShiftStart;
    @FXML
    private TextField ShiftEnd;
    @FXML
    private TextField Address;
    @FXML
    private Button Update;
    @FXML
    private Button Back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        new Staff().BindData(Staff.oStaff.Id, Name, PhoneNumber, ShiftStart, ShiftEnd, Address);

    }    

    @FXML
    private void handleButtonActionUpdate(ActionEvent event) {
        if(Name.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Name Reguired");
        }
        else if(PhoneNumber.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Contact Number Reguired");
        }
        else if(Address.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Address Reguired");
        }
        else if(ShiftStart.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Shift Start Time Reguired");
        }
        else if(ShiftEnd.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Shift End Time Reguired");
        }
        
        else if(ShiftEnd.getText().trim().equals(ShiftStart.getText().trim())){
         
            JOptionPane.showMessageDialog(null, "Shift Start Time And Shift End Time Cannot Be Same");
        }
        else{
            int splitted_time_end = 0;
            for (String s : ShiftEnd.getText().trim().split(" ")) {
                splitted_time_end = Integer.parseInt(s);
                break;
            }
            int splitted_time_start = 0;
            for (String s : ShiftStart.getText().trim().split(" ")) {
                splitted_time_start = Integer.parseInt(s);
                break;
            }
            
            if(splitted_time_end < splitted_time_start 
                    && ((ShiftEnd.getText().trim().contains("am") && ShiftStart.getText().trim().contains("am"))
                    || ((ShiftEnd.getText().trim().contains("pm") && ShiftStart.getText().trim().contains("pm"))))){
                JOptionPane.showMessageDialog(null, "Shift Start Time And End Time Mismatch");
            }
            
            else{
                try{
                   
                    if(event.getSource()==Update){

                        Staff oStaff=new Staff();
                        oStaff.Name = Name.getText();
                        oStaff.PhoneNumber = PhoneNumber.getText();
                        oStaff.ShiftStart = ShiftStart.getText();
                        oStaff.ShiftEnd = ShiftEnd.getText();
                        oStaff.UpdateStaff(oStaff, Staff.oStaff.Id, Address.getText());
                        
                    }
                    
                }
                catch(Exception ex){
                    
                    JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
                }
            }
        }
    }

    @FXML
    private void handleButtonActionBack(ActionEvent event) {
        try{
            Stage stage;
            Parent root;
            if(event.getSource()==Back){
                root=FXMLLoader.load(getClass().getResource("AllStaff.fxml"));
                stage=(Stage)Back.getScene().getWindow();
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
