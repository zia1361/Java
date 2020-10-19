package hms;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class SearchPatient implements Initializable {

    @FXML
    private Button patientLBACK;
    @FXML
    private Button btnLRegisterNow;
    @FXML
    private TextField tbLPatientId;
    @FXML
    private Button btnLSearch;
    @FXML
    private Button btnClickHere;

    @FXML
    private void handleBtnSearchAction(ActionEvent event) throws Exception {
        
        
            if (tbLPatientId.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Kindly Enter ID");
            } else {
                Login();
            }
    }
    @FXML
    private void handleBtnBackAction(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
        
            root = FXMLLoader.load(getClass().getResource("RegisterPatient.fxml"));
            stage = (Stage) btnLRegisterNow.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("ADD PATIENT");
            stage.show();
       
       

    }
    @FXML
    private void handleBtnViewAllAction(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
        
            root = FXMLLoader.load(getClass().getResource("AllPatients.fxml"));
            stage = (Stage) btnClickHere.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("ALL PATIENTS");
            stage.show();
       

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public int setId() throws Exception {

        
        int Id = 0;
        String query = "select Id from Patient where Id = " + tbLPatientId.getText() + ";";
        
        try {
            ResultSet rs = new Utils().GetResult(query);
            if (rs.next()) {
                Id = rs.getInt(1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Id;
    }

    

    private void Login() throws Exception {
        Parent root;
        Stage stage;
        int convert = Integer.parseInt(tbLPatientId.getText());
        if (convert == setId()) {
            Patient oInfo = new Patient(convert);
            
            root = FXMLLoader.load(getClass().getResource("PatientDashBoard.fxml"));
            stage = (Stage) btnLRegisterNow.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("PATIENT DATA");

        } else {
            JOptionPane.showMessageDialog(null, "No Record Found", "Failure", JOptionPane.ERROR_MESSAGE);
        }
    }
}
