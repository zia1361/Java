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
public class AllStaffController implements Initializable {

    Connection con = null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    
    @FXML
    private Button Back;
    @FXML
    private TableColumn Name;
    @FXML
    private TableColumn PhoneNumber;
    @FXML
    private TableColumn ShiftStart;
    @FXML
    private TableColumn ShiftEnd;
    @FXML
    private TableColumn ApointedOn;
    @FXML
    private TableColumn EDIT;
    @FXML
    private TableColumn DELETE;
    @FXML
    private TableView<HelperStaff> Stafftb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        final ObservableList<HelperStaff> data = FXCollections.observableArrayList();
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        ShiftStart.setCellValueFactory(new PropertyValueFactory<>("ShiftStart"));
        ShiftEnd.setCellValueFactory(new PropertyValueFactory<>("ShiftEnd"));
        ApointedOn.setCellValueFactory(new PropertyValueFactory<>("ApointedOn"));

        EDIT.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
        DELETE.setCellValueFactory(new PropertyValueFactory<>("DELETE"));    
                
                
        Callback<TableColumn<HelperStaff, String>, TableCell<HelperStaff, String>> cellFactory
                = //
                new Callback<TableColumn<HelperStaff, String>, TableCell<HelperStaff, String>>() {
            @Override
            public TableCell call(final TableColumn<HelperStaff, String> param) {
                 TableCell<HelperStaff, String> cell = new TableCell<HelperStaff, String>() {
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
                                HelperStaff oHelperStaff = getTableView().getItems().get(getIndex());
                                
                                Staff oStaff = new Staff();
                                oStaff.Id = oHelperStaff.getId();
                                Staff.oStaff = oStaff;
                                 try{
            Parent register = FXMLLoader.load(getClass().getResource("EditStaff.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            oStage.setScene(scene);
            oStage.setTitle("EDIT STAFF");
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
        EDIT.setCellFactory(cellFactory);
        Callback<TableColumn<HelperStaff, String>, TableCell<HelperStaff, String>> cellFactory2
                = //
                new Callback<TableColumn<HelperStaff, String>, TableCell<HelperStaff, String>>() {
            @Override
            public TableCell call(final TableColumn<HelperStaff, String> param) {
                 TableCell<HelperStaff, String> cell = new TableCell<HelperStaff, String>() {
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
                                HelperStaff oHelperStaff = getTableView().getItems().get(getIndex());
                                
                                Staff oStaff = new Staff();
                                oStaff.DeleteStaff(oHelperStaff.getId());
                                Stafftb.getItems().remove(oHelperStaff);
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
         DELETE.setCellFactory(cellFactory2);
        for(Staff oStaff: new Staff().GetListOfStaff()){
            data.add(new HelperStaff(oStaff.Id ,oStaff.Name, oStaff.PhoneNumber, oStaff.ShiftStart, oStaff.ShiftEnd, oStaff.ApointedOn.toString()));
        }
        Stafftb.setItems(data);
        
    }    
    
    
    
   

    @FXML
    private void handleButtonActionBack(ActionEvent event) {
        try{
            Stage stage;
            Parent root;
            if(event.getSource()==Back){
                root=FXMLLoader.load(getClass().getResource("AddStaff.fxml"));
                stage=(Stage)Back.getScene().getWindow();
                Scene scene =new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("ADD STAFF");
                stage.show();
            }
        }
        catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }

    
}
