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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleBackBtn(ActionEvent event){
        try{
            Parent register = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Scene scene = new Scene(register);
            
            Stage oStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            oStage.setScene(scene);
            oStage.setTitle("Add Details");
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
    
}
