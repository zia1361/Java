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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author A.U Computer
 */
public class RegisterController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    TextField loginId;
    @FXML
    TextField email;
    @FXML
    TextField phoneNumber;
    @FXML
    TextField address;
    @FXML
    TextField name;
    @FXML
    PasswordField password;
    @FXML
    ProgressIndicator progressIndicator;
    @FXML
    Pane pane;
    
    @FXML
     private void handleRegisterBtn(ActionEvent event) {
        System.out.println("You clicked me!");
//        System.out.println(Hospital.oHospital.LoginId);
//        System.out.println(Hospital.oHospital.Id);
//        System.out.println(new Hospital().IsAuthenticated()); 
        if(loginId.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Kindly Enter LoginId");
            return;
        }
        if(password.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Kindly Enter Password");
            return;
        }
        if(name.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Kindly Enter Hospital Name");
            return;
        }
        if(email.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Kindly Enter Email");
            return;
        }
        if(address.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Kindly Enter Address");
            return;
        }
        if(phoneNumber.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Kindly Enter Phone#");
            return;
        }
        
        
        System.out.println(loginId.getText());
        System.out.println(password.getText());
        System.out.println(email.getText());
        System.out.println(phoneNumber.getText());
        System.out.println(name.getText());
        System.out.println(address.getText());
        pane.setDisable(false);
        progressIndicator.setVisible(true);
        
        Hospital oHospital = new Hospital();
        oHospital.Name = name.getText();
        oHospital.Email = email.getText();
        oHospital.Address = address.getText();
        oHospital.PhoneNumber = phoneNumber.getText();
        oHospital.LoginId = loginId.getText();
        oHospital.Password = password.getText();
        int id = oHospital.RegisterHospital(oHospital, progressIndicator, pane);
        
        
//        int Id = oHospital.RegisterHospital(oHospital, progressIndicator, pane);
//        System.out.println(Id);
//        if(oHospital.IsAutenticated){
//            
//        }else{
//            progressIndicator.setVisible(false);
//            pane.setDisable(true);
//            JOptionPane.showMessageDialog(null, "Registeration Failed. Try Again or Contact Our Support");
//        }
    }
    
    @FXML
    private void handleLoginBtn(ActionEvent event){
        try{
            Parent register = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            oStage.setScene(scene);
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
