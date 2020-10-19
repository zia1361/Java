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
import java.util.Date;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author Hp
 */
public class AddDoctorController implements Initializable {

 
    @FXML
    private TextField doctorName;
    @FXML
    private TextField doctorContact;
    @FXML
    private TextField doctorAddress;
    @FXML
    private TextField doctorSpeciality;
    @FXML
    private Button doctorBtnRegister;

    /**
     * Initializes the controller class.
     */
    
    
    
    Date date;
    @FXML
    private TextField doctorEmail;
    @FXML
    private Button btnViewAllDoctors;
    @FXML
    private Button Back;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    


    

    @FXML
    private void handleButtonActionRegister(ActionEvent event){
        
        if(doctorName.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Name Reguired");
        }
        else if(doctorContact.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Contact Number Reguired");
        }
        else if(doctorAddress.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Address Reguired");
        }
        else if(doctorSpeciality.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Speciality Reguired");
        }
        else if(doctorEmail.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Email Reguired");
        }
        else{
            Doctor oDoctor = new Doctor(doctorName.getText(), doctorAddress.getText(), doctorContact.getText(), doctorSpeciality.getText(), doctorEmail.getText());
        }
        
    }
    

    

    @FXML
    private void handleButtonActionViewAllDoctors(ActionEvent event) {
        try{
            Stage stage;
            Parent root;
            if(event.getSource()==btnViewAllDoctors){
                root=FXMLLoader.load(getClass().getResource("AllDoctors.fxml"));
                stage=(Stage)btnViewAllDoctors.getScene().getWindow();
                Scene scene =new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("ALL DOCTORS");
                stage.show();
            }
        }
        catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
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
                stage.setTitle("DASHBOARD");
                stage.show();
            }
        }
        catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    
}
