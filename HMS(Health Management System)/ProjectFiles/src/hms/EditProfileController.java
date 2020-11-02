package hms;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class EditProfileController implements Initializable {

    @FXML
    private TextField UpatientName;
    @FXML
    private TextField UpatientContact;
    @FXML
    private RadioButton UpatientCoronaYes;
    @FXML
    private RadioButton UpatientCoronaNo;
    @FXML
    private DatePicker UpatientAdmittedDate;
    @FXML
    private Button patientBtnUpdate;
    @FXML
    private Button btnBack;
    @FXML
    private ComboBox updateDoc;
    @FXML
    private TextField UpatientWardNo;
    @FXML
    private ToggleGroup Corona;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ShowDocId();
            showProfile();
        } catch (Exception ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String coronaS;
    public String store;

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
        if (event.getSource() == btnBack) {
            root = FXMLLoader.load(getClass().getResource("PatientDashBoard.fxml"));
            stage = (Stage) btnBack.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Patient DashBoard");
            stage.show();
        }
        if (UpatientCoronaYes.isSelected()) {
            coronaS = UpatientCoronaYes.getId();
        } else if (UpatientCoronaNo.isSelected()) {
            coronaS = UpatientCoronaNo.getId();
        } else {
            UpatientCoronaYes.setSelected(false);
            UpatientCoronaNo.setSelected(false);
        }

        if (event.getSource() == patientBtnUpdate) {
              String[] str = updateDoc.getValue().toString().split(" ", 0);
            for (String a : str) {
                store = a;
            }
            updateRecord();
            JOptionPane.showMessageDialog(null,
                    "Update Successful! ThankYou",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public Byte getCoronaStatus() {
        if ("UpatientCoronaYes".equals(coronaS)) {
            return 1;
        } else {
            return 0;
        }
    }

    

    

    public void ShowDocId() throws Exception {
        ObservableList ob = FXCollections.observableArrayList();
        String query = "Select Name, Id from Doctor";
        ResultSet rs;
        try {
            rs = new Utils().GetResult(query);

            while (rs.next()) {
                ob.add("Dr "+rs.getString("Name") + " " + rs.getInt("Id"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        updateDoc.setItems(ob);
    }

    private void updateRecord() throws Exception {
        String query = "update Patient set Name = '" + UpatientName.getText() +  "', IsCoronaPatient = " + getCoronaStatus()
                + " where Id = " + Patient.oInformation.getId();
        int result = new Utils().InsertData(query);
        if(new Patient().HasDetails()){
            query = "update PatientDetails set  WardNumber = '" + UpatientWardNo.getText() + "', EmergencyContactNumber = '"
                + UpatientContact.getText() + "', DoctorId = " + store + " where PatientId = " + Patient.oInformation.getId();
        result = new Utils().InsertData(query);
        }else{
            query = "Insert into PatientDetails(PatientId, WardNumber, EmergencyContactNumber, DoctorId)"
                    + " values(" + Patient.oInformation.getId() +",'";
            query += UpatientWardNo.getText() + "','" + UpatientContact.getText() + "'," + store + ")";
            result = new Utils().InsertData(query);
        }
        
    }

    private void showProfile() {

        UpatientName.setText(Patient.oInformation.getName());
        UpatientCoronaYes.setSelected(Patient.oInformation.isCoronaStatus());
        UpatientContact.setText(Patient.oInformation.getContact());
        UpatientWardNo.setText(Patient.oInformation.getWardNo());
        updateDoc.setPromptText(Integer.toString(Patient.oInformation.getDoctorId()));
    }
}
