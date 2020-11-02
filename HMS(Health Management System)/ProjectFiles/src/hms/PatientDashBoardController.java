package hms;

import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
public class PatientDashBoardController implements Initializable {

    @FXML
    private Button btnLOGOUT;
    @FXML
    private Button btnOtherInfo;
    @FXML
    private Button btnAddMedicine;
    @FXML
    private Button btnProfile;
    @FXML
    private Label patientID;
    @FXML
    private Label hospitalID;
    @FXML
    private Label patientName;
    @FXML
    private Label patientContact;
    @FXML
    private Label patientWard;
    @FXML
    private Label patientDocName;
    @FXML
    private Label registeredOn;
    @FXML
    private Button btnPayment;
    @FXML
    private Button btnDisease;
    @FXML
    private ImageView imageProfile;
    @FXML
    private ImageView iamgePayment;
    @FXML
    private ImageView imageMedicine;
    @FXML
    private ImageView imageDisease;
    @FXML
    private ImageView imageOtherinfo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            show();
        } catch (Exception ex) {
            Logger.getLogger(PatientDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
        if (event.getSource() == btnLOGOUT) {
            root = FXMLLoader.load(getClass().getResource("SearchPatient.fxml"));
            stage = (Stage) btnLOGOUT.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("SEARCH PATIENT");
            stage.show();
        } else if (event.getSource() == btnOtherInfo) {
            root = FXMLLoader.load(getClass().getResource("OtherInfo.fxml"));
            stage = (Stage) btnOtherInfo.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("PATIENT DETAILS");
            stage.show();
        } else if (event.getSource() == btnAddMedicine) {
            root = FXMLLoader.load(getClass().getResource("AddMedicine.fxml"));
            stage = (Stage) btnAddMedicine.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("ADD MEDICINE");
            stage.show();
        } else if (event.getSource() == btnPayment) {
            root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
            stage = (Stage) btnPayment.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("PAYMENT");
            stage.show();
        } else if (event.getSource() == btnProfile) {
            root = FXMLLoader.load(getClass().getResource("EditProfile.fxml"));
            stage = (Stage) btnProfile.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("PATIENT PROFILE");
            stage.show();
        } else if (event.getSource() == btnDisease) {
            root = FXMLLoader.load(getClass().getResource("Disease.fxml"));
            stage = (Stage) btnDisease.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("ADD DESEASE");
            stage.show();
        }
    }

    @FXML
    private void handleOtherInfo() throws IOException {
        Parent root;
        Stage stage;
        root = FXMLLoader.load(getClass().getResource("OtherInfo.fxml"));
        stage = (Stage) imageOtherinfo.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("PATIENT DETAILS");
        stage.show();
    }

    @FXML
    private void handlePayment() throws IOException {
        Parent root;
        Stage stage;
        root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
        stage = (Stage) iamgePayment.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("MAKE PAYMENT");
        stage.show();
    }

    @FXML
    private void handleProfile() throws IOException {
        Parent root;
        Stage stage;
        root = FXMLLoader.load(getClass().getResource("EditProfile.fxml"));
        stage = (Stage) imageProfile.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("EDIT DATA");
        stage.show();
    }

    @FXML
    private void handleDisease() throws IOException {
        Parent root;
        Stage stage;
        root = FXMLLoader.load(getClass().getResource("Disease.fxml"));
        stage = (Stage) imageDisease.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("ADD DESEASE");
        stage.show();
    }

    @FXML
    private void handleMedicine() throws Exception {
        Parent root;
        Stage stage;
        root = FXMLLoader.load(getClass().getResource("AddMedicine.fxml"));
        stage = (Stage) imageMedicine.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("ADD MEDICINE");
        stage.show();
    }

    public void show() throws Exception {
        patientID.setText(Integer.toString(Patient.oInformation.getId()));
        patientName.setText(Patient.oInformation.getName());
        patientContact.setText(Patient.oInformation.getContact());
        patientWard.setText(Patient.oInformation.getWardNo());
        patientDocName.setText(DocName());
        registeredOn.setText(Patient.oInformation.registeredOn != null ? Patient.oInformation.registeredOn.toString() : "");
    }

    public String DocName() throws Exception {
        String Name = "";
        String query = "Select Name from Doctor where Id = " + Patient.oInformation.getDoctorId();
        ResultSet rs;
        try {
            rs = new Utils().GetResult(query);
            while (rs.next()) {
                Name = rs.getString("Name");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(Name);
        return Name;
    }

    public String Status(boolean value) {
        String s;
        if (value) {
            s = "Yes";
        } else {
            s = "No";
        }
        return s;
    }

    


}
