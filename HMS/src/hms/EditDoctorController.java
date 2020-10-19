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
public class EditDoctorController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField contact_number;
    @FXML
    private TextField email;
    @FXML
    private TextField address;
    @FXML
    private TextField speciality;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button Back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        new Doctor().BindData(Doctor.oDoctor.Id, name, contact_number, email, address, speciality);
    }    


    @FXML
    private void handleButtonActionUpdate(ActionEvent event) {
        if(name.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Name Reguired");
        }
        else if(contact_number.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Contact Number Reguired");
        }
        else if(address.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Address Reguired");
        }
        else if(speciality.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Speciality Reguired");
        }
        else if(email.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Email Reguired");
        }
        else{
            try{
                if(event.getSource()==btnUpdate){

                    Doctor oDoctor=new Doctor();
                    oDoctor.name = name.getText();
                    oDoctor.email = email.getText();
                    oDoctor.phone_no = contact_number.getText();
                    oDoctor.speciality = speciality.getText();
                    oDoctor.UpdateDoctor(oDoctor, Doctor.oDoctor.Id, address.getText());
                    
                }
                
            }
            catch(Exception ex){
                
                JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
            }
        }
    }

    @FXML
    private void handleButtonActionBack(ActionEvent event) {
        try{
            Stage stage;
            Parent root;
            if(event.getSource()==Back){
                root=FXMLLoader.load(getClass().getResource("AllDoctors.fxml"));
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
