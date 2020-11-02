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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author A.U Computer
 */
public class MyProfileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    CheckBox oCBox;
    @FXML
    TextField totalCrWards;
    @FXML
    TextField totalIcu;
    @FXML
    ComboBox status;
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
    TextField password;
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        if(!new Hospital().HasDetails()){
            oCBox.setDisable(true);
            status.setDisable(true);
            totalCrWards.setDisable(true);
            totalIcu.setDisable(true);
            new HospitalStatus().BindStatuses(status);
            new Hospital().BindData(oCBox, totalCrWards, totalIcu, status, loginId, email, phoneNumber, address, name, password);
        }else{
            oCBox.setDisable(false);
            status.setDisable(false);
            totalCrWards.setDisable(false);
            totalIcu.setDisable(false);
            new HospitalStatus().BindStatuses(status);
            new Hospital().BindData(oCBox, totalCrWards, totalIcu, status, loginId, email, phoneNumber, address, name, password);
        }
    }

    @FXML
    private void handleBackBtn(ActionEvent event){
        try{
            Parent register = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Scene scene = new Scene(register);
            
            Stage oStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            oStage.setScene(scene);
            oStage.setTitle("DASHBOARD");
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    @FXML
    private void handleCheckBtn(ActionEvent event){
        try{
            System.out.println("Checked");
            System.out.println(oCBox.isSelected());
            totalCrWards.setDisable(!oCBox.isSelected());
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    @FXML
    private void handleUpdateBtn(ActionEvent event){
        try{
            Hospital oHospital = new Hospital();
            oHospital.Name = name.getText();
            oHospital.Email = email.getText();
            oHospital.Address = address.getText();
            oHospital.PhoneNumber = phoneNumber.getText();
            oHospital.TotalCrWards = Integer.parseInt(totalCrWards.getText());
            oHospital.HasCoronaWards = oCBox.isSelected();
            oHospital.StatusId = Integer.parseInt(status.getValue().toString().split(" ")[0]);
            new Hospital().UpdateHospital(oHospital, loginId.getText(), password.getText(), Integer.parseInt(totalIcu.getText()));
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    
}
