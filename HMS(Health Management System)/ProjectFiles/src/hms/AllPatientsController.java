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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Haziq
 */
public class AllPatientsController implements Initializable {

    @FXML
    private TableColumn tablepatientId;
    @FXML
    private TableColumn tablepatientName;
    @FXML
    private TableColumn tableRegisteredOn;
    @FXML
    private Button btnBack;
    @FXML
    private TableView<AllPatientInfo> tableView;
    @FXML
    private TableColumn<?, ?> tabelContactNumber;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getAllRecord();
            showOnTable();
        } catch (Exception ex) {
            Logger.getLogger(AllPatientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnBack) {
            Stage stage;
            Parent root;
            root = FXMLLoader.load(getClass().getResource("SearchPatient.fxml"));
            stage = (Stage) btnBack.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("SEARCH PATIENT");
            stage.show();
        }
    }

   

    
    public ObservableList<AllPatientInfo> getAllRecord() throws Exception {
        ObservableList<AllPatientInfo> recordList = FXCollections.observableArrayList();
        String query = "select o.Id, o.Name, i.EmergencyContactNumber, i.RegisteredOn from Patient o left join PatientDetails i on o.Id = i.PatientId"
                + " WHERE o.HospitalId = " + Hospital.oHospital.Id;
        ResultSet rs;
        try {
            rs = new Utils().GetResult(query);
            AllPatientInfo info;
            while (rs.next()) {
                info = new AllPatientInfo(rs.getInt("Id"),rs.getString("Name"),rs.getObject("EmergencyContactNumber") != null
                ? rs.getString("EmergencyContactNumber") : "",rs.getObject("RegisteredOn") != null ? rs.getDate("RegisteredOn") : null);
                recordList.add(info);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return recordList;
    }

    public void showOnTable() throws Exception {
        ObservableList<AllPatientInfo> list = getAllRecord();
        tablepatientId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tablepatientName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tabelContactNumber.setCellValueFactory(new PropertyValueFactory<>("EmergencyContactNumber"));
        tableRegisteredOn.setCellValueFactory(new PropertyValueFactory<>("RegisteredOn"));
        tableView.setItems(list);
    }
    
}
