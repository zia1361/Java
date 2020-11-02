package hms;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

public class DiseaseController implements Initializable {

    @FXML
    private TextField enterDisease;
    @FXML
    private Button btnBack;
    @FXML
    private TableColumn DiseasePatientId;
    @FXML
    private TableColumn DiseaseName;
    @FXML
    private TableColumn DiseaseActiveStatus;
    @FXML
    private TableColumn admittedOn;
    @FXML
    private TableColumn dischargedOn;
    @FXML
    private TableColumn actions;
    @FXML
    private Button btnUpdateDisease;
    @FXML
    private TableView<DiseaseInfo> view;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getDiseaseList();
            showOnTable();
        } catch (Exception ex) {
            Logger.getLogger(DiseaseController.class.getName()).log(Level.SEVERE, null, ex);
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
        if (event.getSource() == btnUpdateDisease) { 
            if (enterDisease.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "Kindly Enter Disease");
            } else {
                insertRecord();
            }
        }
    }

    

    private void insertRecord() throws Exception {
        String query = "insert into Desease(PatientId, DeseaseName,AdmittedOn) values(" + Patient.oInformation.getId() + ",'" + enterDisease.getText() + "','"+ LocalDate.now() + "');";
        int result = new Utils().InsertData(query);
        showOnTable();
    }

    

    public ObservableList<DiseaseInfo> getDiseaseList() throws ClassNotFoundException, Exception {
        ObservableList<DiseaseInfo> Dlist = FXCollections.observableArrayList();
        String query = "Select * from Desease where PatientId = " + Patient.oInformation.getId() + ";";
        ResultSet rs;
        try {
            rs = new Utils().GetResult(query);
            DiseaseInfo dis;
            while (rs.next()) {
                dis = new DiseaseInfo(rs.getInt("Id"), rs.getInt("PatientId"), rs.getString("DeseaseName"), rs.getBoolean("IsActive"), 
                rs.getDate("AdmittedOn"), rs.getObject("DischargedOn") != null ? rs.getDate("DischargedOn") : null);
                Dlist.add(dis);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Dlist;
    }
int counter = 0;
    public void showOnTable() throws Exception {
        ObservableList<DiseaseInfo> list = getDiseaseList();
        DiseasePatientId.setCellValueFactory(new PropertyValueFactory<>("PatientId"));
        DiseaseName.setCellValueFactory(new PropertyValueFactory<>("DeseaseName"));
        DiseaseActiveStatus.setCellValueFactory(new PropertyValueFactory<>("IsActive"));
        admittedOn.setCellValueFactory(new PropertyValueFactory<>("AdmittedOn"));
        dischargedOn.setCellValueFactory(new PropertyValueFactory<>("DischargedOn"));
        actions.setCellValueFactory(new PropertyValueFactory<>("Discharge"));
        
         Callback<TableColumn<DiseaseInfo, String>, TableCell<DiseaseInfo, String>> cellFactory
                = //
                new Callback<TableColumn<DiseaseInfo, String>, TableCell<DiseaseInfo, String>>() {
                   
            @Override
            public TableCell call(final TableColumn<DiseaseInfo, String> param) {
                 TableCell<DiseaseInfo, String> cell = new TableCell<DiseaseInfo, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        final Button btn = new Button("DISCHARGE");
                        btn.setStyle("-fx-background-color: lightgreen; -fx-text-fill: white;");
                        btn.setCursor(Cursor.HAND);
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            DiseaseInfo oInfo = getTableView().getItems().get(getIndex());
                            if(counter > 0){
                                System.out.println(oInfo.Id);
                                if(!oInfo.isIsActive()){
                                    btn.setDisable(true);
                                }
                            }
                              counter++;  
                            
                            
                            
                            btn.setOnAction(event -> {
                                
//                                DiseaseInfo oInfo = getTableView().getItems().get(getIndex());
                                System.out.println(oInfo.Id);
                                oInfo.DisCharge(oInfo.Id);
                                try {
                                    showOnTable();
                                } catch (Exception ex) {
                                    Logger.getLogger(DiseaseController.class.getName()).log(Level.SEVERE, null, ex);
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
        actions.setCellFactory(cellFactory);
        view.setItems(list);
    }
}
