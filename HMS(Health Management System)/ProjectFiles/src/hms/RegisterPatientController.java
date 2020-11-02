package hms;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
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

public class RegisterPatientController implements Initializable {

    @FXML
    private Button btnRLoginNow;
    @FXML
    private TextField patientName;
    @FXML
    private TextField patientWardNo;
    @FXML
    private Button patientBtnReset;
    @FXML
    private Button patientBtnRegister;
    @FXML
    private TextField patientContact;
    @FXML
    private RadioButton patientCoronaYes;
    @FXML
    private RadioButton patientCoronaNo;
    @FXML
    private DatePicker patientAdmittedDate;
    @FXML
    private ToggleGroup Corona;
    @FXML
    private ComboBox DocId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            SetDoc();

        } catch (Exception ex) {
            Logger.getLogger(RegisterPatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String coronaS;
    public String store;

    @FXML
    private void handleBtnBackAction(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
        
            root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            stage = (Stage) btnRLoginNow.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("DASHBOARD");
            stage.show();
            getPrimaryKey();
       
       

    }
    @FXML
    private void handleBtnSearchAction(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
        
            root = FXMLLoader.load(getClass().getResource("SearchPatient.fxml"));
            stage = (Stage) btnRLoginNow.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("PATIENT LOGIN");
            stage.show();
            getPrimaryKey();
       
       

    }
    @FXML
    private void handleBtnAddAction(ActionEvent event) throws Exception {
        
        if (patientCoronaYes.isSelected()) {
            if(Hospital.oHospital.TotalCrRemaingWards <=0){
                JOptionPane.showMessageDialog(null, "No Corona Ward Available");
                return;
            }
            coronaS = patientCoronaYes.getId();
        } else if (patientCoronaNo.isSelected()) {
            coronaS = patientCoronaNo.getId();
        } else {
            patientCoronaYes.setSelected(false);
            patientCoronaNo.setSelected(false);
        }
        
            String[] str = DocId.getValue().toString().split(" ", 0);
            for (String a : str) {
                store = a;
            }
            insertRecord();
            JOptionPane.showMessageDialog(null,
                    "Registration Successful!",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
            Clear();
       

        

    }
    

    public Byte getCoronaStatus() {
        if ("patientCoronaYes".equals(coronaS)) {
            return 1;
        } else {
            return 0;
        }
    }

   

    private void insertRecord() throws Exception {
        int coronaStatus = getCoronaStatus();
        String query = "insert into Patient values('" + patientName.getText() + "'," + Hospital.oHospital.Id + "," + coronaStatus + ",1,0)";
        int result = new Utils().InsertData(query);
        if(coronaStatus == 1){
            query = "UPDATE HospitalDetails set ConsumedCoronaWards+=1, RemainingCoronaWards-=1 WHERE HospitalId=" + Hospital.oHospital.Id;
        result = new Utils().InsertData(query);
        }
        query = "insert into PatientDetails values(" + getPrimaryKey() + patientWardNo.getText() + "','" + patientContact.getText() + "'," + Integer.parseInt(store) + ",'" + LocalDate.now() + "')";
        result = new Utils().InsertData(query);
    }

    public void Clear() {
        patientName.clear();
        patientContact.clear();
        patientCoronaNo.setSelected(false);
        patientCoronaYes.setSelected(false);
        patientWardNo.clear();
        DocId.setValue("Enter Doctor Id");
    }

    private int getPrimaryKey() throws Exception {
       
        String query = "select * from Patient where name = '" + patientName.getText() + "' and IsActive = 1 and IsDeleted = 0";
        int key = 0;
        try {
            ResultSet rs = new Utils().GetResult(query);
            while (rs.next()) {
                key = rs.getInt(1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return key;
    }

   

    public void SetDoc() throws Exception {
        ObservableList ob = FXCollections.observableArrayList();
        
        String query = "Select Name, Id from Doctor";
        
        try {
            ResultSet rs = new Utils().GetResult(query);

            while (rs.next()) {
                ob.add("Dr "+rs.getString("Name") + " " + rs.getInt("Id"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        DocId.setItems(ob);
    }

}
