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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

public class AddMedicineController implements Initializable {

    @FXML
    private TextField MedName;
    @FXML
    private Button MedAddBtn;
    @FXML
    private Button btnBack;
    @FXML
    private ComboBox hour;
    @FXML
    private ComboBox min;
    @FXML
    private TableView<MedicineInfo> medicineView;
    @FXML
    private TableColumn tableMedName;
    @FXML
    private TableColumn tablemedActiveS;
    @FXML
    private TableColumn tablemedTime;
    @FXML
    private TableColumn tableAction;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (int i = 1; i <= 24; i++) {
            if (i <= 9) {
                hour.getItems().add("0" + i);
            } else {
                hour.getItems().add(i);
            }
        }
        for (int i = 1; i <= 60; i++) {
            if (i <= 9) {
                min.getItems().add("0" + i);
            } else {
                min.getItems().add(i);
            }
        }
        try {
            getMedList();
            showOnTable();
        } catch (Exception ex) {
            Logger.getLogger(AddMedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    

    private void insert() throws Exception {
        String str = hour.getValue() + ":" + min.getValue() + ":00";
        String query = "Insert into PatientMedicine values(" + Patient.oInformation.getId() + ",'" + MedName.getText() + "',1,0);";
        int result = new Utils().InsertData(query);
        query = "Insert into MedicineTiming values(" + getPrimary() + ",'" + str + "',1,0);";
        result = new Utils().InsertData(query);
        showOnTable();
    }

    

    private int getPrimary() throws Exception {
        int key = 0;
        String query = "select Id from PatientMedicine where PatientId = " + Patient.oInformation.getId();
        ResultSet rs;
        try {
            rs = new Utils().GetResult(query);
            while (rs.next()) {
                key = rs.getInt(1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return key;
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

        if (event.getSource() == MedAddBtn) {

            if (MedName.getText().equals("") && hour.getEditor().getText().equals("") && min.getEditor().getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Kindly Enter Complete Details");
            } else {
                insert();
                MedName.setText("");
                hour.setValue("Hour");
                min.setValue("Minuites");

            }
        }
    }

    public ObservableList<MedicineInfo> getMedList() throws Exception {
        ObservableList<MedicineInfo> list = FXCollections.observableArrayList();
        String query = "SELECT o.Id, o.MedicineName,o.IsActive,i.Timing "
                + "FROM PatientMedicine o "
                + "JOIN MedicineTiming i "
                + "ON o.Id = i.PatientMedicineId "
                + "where PatientId = " + Patient.oInformation.getId() + " and o.IsActive = 1 and o.IsDeleted = 0;";
        ResultSet rs;
        try {
            rs = new Utils().GetResult(query);
            MedicineInfo med;
            while (rs.next()) {
                med = new MedicineInfo(rs.getNString("MedicineName"), rs.getBoolean("IsActive"), rs.getTime("Timing"), rs.getInt(1));
                list.add(med);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void showOnTable() throws Exception {
        ObservableList<MedicineInfo> olist = getMedList();
        tableMedName.setCellValueFactory(new PropertyValueFactory<>("MedicineName"));
        tablemedActiveS.setCellValueFactory(new PropertyValueFactory<>("IsActive"));
        tablemedTime.setCellValueFactory(new PropertyValueFactory<>("Timing"));
        tableAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
        Callback<TableColumn<MedicineInfo, String>, TableCell<MedicineInfo, String>> cellFactory
                = //
                new Callback<TableColumn<MedicineInfo, String>, TableCell<MedicineInfo, String>>() {
            @Override
            public TableCell call(final TableColumn<MedicineInfo, String> param) {
                final TableCell<MedicineInfo, String> cell = new TableCell<MedicineInfo, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        final Button btn = new Button("DELETED");
                        btn.setStyle("-fx-background-color: white; -fx-border-color: lightgreen; -fx-border-radius: 30; -fx-border-width: 2; -fx-font-size: 13; -fx-cursor: hand");
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                MedicineInfo oMedicine = getTableView().getItems().get(getIndex());
                                medicineView.getItems().remove(oMedicine);
                                String query = "update PatientMedicine set IsActive = 0, IsDeleted = 1 where Id = " + oMedicine.getMedicineId();
                                String query1 = "update MedicineTiming set IsActive = 0, IsDeleted = 1 where PatientMedicineID = " + oMedicine.getMedicineId();
                                try {
                                    int result = new Utils().InsertData(query);
                                    result = new Utils().InsertData(query1);
                                    showOnTable();

                                } catch (Exception ex) {
                                    Logger.getLogger(AddMedicineController.class.getName()).log(Level.SEVERE, null, ex);
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
        tableAction.setCellFactory(cellFactory);
        medicineView.setItems(olist);
    }

}
