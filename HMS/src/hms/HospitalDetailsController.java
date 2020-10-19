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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author A.U Computer
 */
public class HospitalDetailsController implements Initializable {

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        new HospitalStatus().BindStatuses(status);
        
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
            totalCrWards.setText("");
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
     @FXML
    private void handleAddBtn(ActionEvent event){
        try{
//            System.out.println("Checked");
            
            System.out.println(status.getValue());
            System.out.println(oCBox.isSelected());
            System.out.println(totalCrWards.getText());
            System.out.println(totalIcu.getText());
            System.out.println(Hospital.oHospital.Id);
            new Hospital().AddHospitalDetails(Hospital.oHospital.Id, (totalIcu.getText() != "" ? Integer.parseInt(totalIcu.getText()) : 0), oCBox.isSelected(),
                    oCBox.isSelected() ? totalCrWards.getText() != "" ? Integer.parseInt(totalCrWards.getText()) :0 : 0, Integer.parseInt(status.getValue().toString().split(" ")[0]));
        }
        catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    
}
