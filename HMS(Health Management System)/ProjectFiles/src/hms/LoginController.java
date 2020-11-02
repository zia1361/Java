/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import hms.Hospital;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author A.U Computer
 */
public class LoginController implements Initializable {
    
    @FXML
    TextField loginId;
    @FXML
    PasswordField password;
    @FXML
    ProgressIndicator progress;
    @FXML
    Pane progressPane;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       
        if(loginId.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Kindly Enter LoginId");
            return;
        }
        if(password.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Kindly Enter Password");
            return;
        }
        progressPane.setDisable(false);
        progress.setVisible(true);
        
        
        Hospital oHospital = new Hospital(loginId.getText(), password.getText(), progress, progressPane);
        if(oHospital.IsAutenticated){
           progress.setVisible(false);
           progressPane.setDisable(true); 
           try{
               Parent register = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                Scene scene = new Scene(register);
                Stage oStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                oStage.setScene(scene);
                oStage.setTitle("DASHBOARD");
                oStage.show();
           }catch(Exception ex){
               ex.printStackTrace();
               JOptionPane.showMessageDialog(null, "Oops! Something went wrong. Please try again later or Contact our Support.");
           }
        }else{
            progress.setVisible(false);
            progressPane.setDisable(true);
            JOptionPane.showMessageDialog(null, "Login Failed. Inavlid Credentials");
        }
    }
    
    @FXML
    private void handleRegisterBtn(ActionEvent event){
        try{
            Parent register = FXMLLoader.load(getClass().getResource("Register.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            oStage.setScene(scene);
            oStage.setTitle("REGISTER");
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
