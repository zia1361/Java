/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class AllDoctorsController implements Initializable {

    

    /**
     * Initializes the controller class.
     */
    
    ResultSet rs=null;
    
    @FXML
    private TableColumn clName;
    @FXML
    private TableColumn clEmail;
    @FXML
    private TableColumn clContact;
    @FXML
    private TableColumn clSpeciality;
    @FXML
    private TableColumn clAppointed;
    @FXML
    private TableView<DoctorHelper> doctorTable;
    
    @FXML
    private TableColumn edit;
    @FXML
    private TableColumn delete;
    @FXML
    private Button Back;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        final ObservableList<DoctorHelper> data = FXCollections.observableArrayList();
        clName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clContact.setCellValueFactory(new PropertyValueFactory<>("phone_no"));
        clEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clSpeciality.setCellValueFactory(new PropertyValueFactory<>("speciality"));
        clAppointed.setCellValueFactory(new PropertyValueFactory<>("appointed_on"));
        edit.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
        delete.setCellValueFactory(new PropertyValueFactory<>("DELETE"));    
                
                
        Callback<TableColumn<DoctorHelper, String>, TableCell<DoctorHelper, String>> cellFactory
                = //
                new Callback<TableColumn<DoctorHelper, String>, TableCell<DoctorHelper, String>>() {
            @Override
            public TableCell call(final TableColumn<DoctorHelper, String> param) {
                 TableCell<DoctorHelper, String> cell = new TableCell<DoctorHelper, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        final Button btn = new Button("EDIT");
                        btn.setStyle("-fx-background-color: lightgreen; -fx-text-fill: white;");
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                DoctorHelper oHelper = getTableView().getItems().get(getIndex());
                                
                                Doctor oDoctor = new Doctor();
                                oDoctor.Id = oHelper.getId();
                                Doctor.oDoctor = oDoctor;
                                 try{
            Parent register = FXMLLoader.load(getClass().getResource("EditDoctor.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            oStage.setScene(scene);
            oStage.setTitle("EDIT DOCTOR");
            oStage.show();
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
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
        edit.setCellFactory(cellFactory);
        Callback<TableColumn<DoctorHelper, String>, TableCell<DoctorHelper, String>> cellFactory2
                = //
                new Callback<TableColumn<DoctorHelper, String>, TableCell<DoctorHelper, String>>() {
            @Override
            public TableCell call(final TableColumn<DoctorHelper, String> param) {
                 TableCell<DoctorHelper, String> cell = new TableCell<DoctorHelper, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        final Button btn = new Button("DELETE");
                        btn.setStyle("-fx-background-color: lightgreen; -fx-text-fill: white;");
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                DoctorHelper oHelper = getTableView().getItems().get(getIndex());
                                
                                Doctor oDoctor = new Doctor();
                                oDoctor.DeleteDoctor(oHelper.getId());
                                doctorTable.getItems().remove(oHelper);
//                                
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
         delete.setCellFactory(cellFactory2);
        for(Doctor oDoctor: new Doctor().GetListOfDoctors()){
            data.add(new DoctorHelper(oDoctor.Id ,oDoctor.name, oDoctor.phone_no, oDoctor.email, oDoctor.speciality, oDoctor.appointed_on));
        }
        doctorTable.setItems(data);
        
    }    
    
    @FXML
    private void handleButtonActionBack(ActionEvent event) {
        try{
            Stage stage;
            Parent root;
            if(event.getSource()==Back){
                root=FXMLLoader.load(getClass().getResource("AddDoctor.fxml"));
                stage=(Stage)Back.getScene().getWindow();
                Scene scene =new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("ADD DOCTOR");
                stage.show();
            }
        }
        catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    
}
