package hms;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

public class OtherInfoController implements Initializable {

    @FXML
    private DatePicker patientDischargedDate;
    @FXML
    private Button btnBack;
    @FXML
    private TableColumn otherAdmittedOn;
    @FXML
    private TableColumn OtherDischargedOn;
    @FXML
    private TableColumn OtherActiveStatus;
    @FXML
    private Button btnUpdateInfo;
    @FXML
    private TableView<PatientHelper> viewTable;
    @FXML
    private TableColumn tblAction;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            checkDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        if (event.getSource() == btnUpdateInfo) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
            LocalDate date = patientDischargedDate.getValue();
            String abc = formatter.format(date).toString();
            if (patientDischargedDate.getEditor().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Kindly Enter Discharged Time");
            } else {
                String query = "update PatientDetails set DischargedOn = '" + formatter.format(date) + "' where PatientId = " + Patient.oInformation.getId();
        int result = new Utils().InsertData(query);
        showOnTable();
            }
        }
    }

    

    

   

    public ObservableList<PatientHelper> getOtherList() throws Exception {
        ObservableList<PatientHelper> Olist = FXCollections.observableArrayList();
        String query = "SELECT o.AdmittedOn, o.DischargedOn, i.IsActive "
                + "FROM PatientDetails o "
                + "right JOIN Patient i "
                + "ON o.PatientId = " + Patient.oInformation.getId()
                + " and i.Id = " + Patient.oInformation.getId() + ";";
        ResultSet rs;
        try {
            rs = new Utils().GetResult(query);
            PatientHelper otherclass;
            if (rs.next()) {
                otherclass = new PatientHelper(rs.getDate("AdmittedOn"), rs.getDate("DischargedOn"), rs.getBoolean("IsActive"));
                Olist.add(otherclass);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Olist;
    }

    @FXML
    public void showOnTable() throws Exception {
        ObservableList<PatientHelper> olist = getOtherList();
        otherAdmittedOn.setCellValueFactory(new PropertyValueFactory<>("AdmittedOn"));
        OtherDischargedOn.setCellValueFactory(new PropertyValueFactory<>("DischargedOn"));
        OtherActiveStatus.setCellValueFactory(new PropertyValueFactory<>("IsActive"));

        Callback<TableColumn<PatientHelper, String>, TableCell<PatientHelper, String>> cellFactory
                = //
                new Callback<TableColumn<PatientHelper, String>, TableCell<PatientHelper, String>>() {
            @Override
            public TableCell call(final TableColumn<PatientHelper, String> param) {
                final TableCell<PatientHelper, String> cell = new TableCell<PatientHelper, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        final Button btn = new Button("CHECK PAYMENT");
                        btn.setStyle("-fx-background-color: white; -fx-border-color: lightgreen; -fx-border-radius: 30; -fx-border-width: 2; -fx-font-size: 13; -fx-cursor: hand");
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                PatientHelper oInfo = getTableView().getItems().get(getIndex());
                                String query = "select Id from Desease where PatientId = " + Patient.oInformation.getId();
                                int count = 0;
                                int x;
                                int keyId;
                                ResultSet rs;
                                try {
                                    rs = new Utils().GetResult(query);
                                    while (rs.next()) {
                                        keyId = rs.getInt("Id");
                                        query = "select Remaining from Payment where DeseaseId = " + keyId + ";";
                                        try {
                                            rs = new Utils().GetResult(query);
                                            while (rs.next()) {
                                                x = rs.getInt("Remaining");
                                                if (x == 0) {
                                                    count += 0;
                                                } else {
                                                    count += 1;
                                                }
                                            }
                                        } catch (Exception ex) {
                                            Logger.getLogger(AddMedicineController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }

                                } catch (Exception ex) {
                                    Logger.getLogger(AddMedicineController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.out.println(count);
                                if (count == 0) {
                                    String q = "update Patient set IsActive = 0 where Id = " + Patient.oInformation.getId();
                                    try {
                                        int reult = new Utils().InsertData(q);
                                        showOnTable();
                                    } catch (Exception ex) {
                                        Logger.getLogger(OtherInfoController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    JOptionPane.showMessageDialog(null, "No Payment Left");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Payment Remaining, Kindly Pay It", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        tblAction.setCellFactory(cellFactory);
        
        viewTable.setItems(olist);
    }

    public void checkDate() throws Exception {
        String date = "";
        String query = "select DischargedOn from PatientDetails where PatientId = " + Patient.oInformation.getId() + ";";
        ResultSet rs;
        try {
            rs = new Utils().GetResult(query);
            if (rs.next()) {
                date = rs.getString("DischargedOn");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if ("1900-01-01 00:00:00.0".equals(date)) {
        } else {
            System.out.println(date);
            getOtherList();
            showOnTable();
        }
    }
}
