/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import java.awt.event.MouseEvent;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author A.U Computer
 */
public class DashboardController implements Initializable {

    
    /**
     * Initializes the controller class.
     */
    @FXML
    ImageView imgPreview;
    @FXML
    TableView<Helper> hospitals;
    @FXML
    TableColumn Name;
    @FXML
    TableColumn Address;
    @FXML
    TableColumn HasCoronaWards;
    @FXML
    TableColumn TotalCrWards;
    @FXML
    TableColumn TotalCrConsumedWards;
    @FXML
    TableColumn TotalCrRemaingWards;
    @FXML
    ImageView detailsImage;
    @FXML
    Button detailsBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("loaded");
        Name.setCellValueFactory(new PropertyValueFactory<Helper,String>("Name"));
        Address.setCellValueFactory(new PropertyValueFactory<Helper,String>("Address"));
        HasCoronaWards.setCellValueFactory(new PropertyValueFactory<Helper,Boolean>("HasCoronaWards"));
        TotalCrWards.setCellValueFactory(new PropertyValueFactory<Helper,Integer>("TotalCrWards"));
        TotalCrConsumedWards.setCellValueFactory(new PropertyValueFactory<Helper,Integer>("TotalCrConsumedWards"));
        TotalCrRemaingWards.setCellValueFactory(new PropertyValueFactory<Helper,Integer>("TotalCrRemaingWards"));
        
        final ObservableList<Helper> data = FXCollections.observableArrayList();
        for(Hospital oHospital: new Hospital().GetHospitalsData()){
            data.add(new Helper(oHospital.Name, oHospital.Address, oHospital.HasCoronaWards, oHospital.TotalCrWards, oHospital.TotalCrConsumedWards, oHospital.TotalCrRemaingWards)
            );
            
        }
        hospitals.setItems(data);
        if(new Hospital().HasDetails(Hospital.oHospital.Id)){
            detailsImage.setDisable(true);
            detailsBtn.setDisable(true);
        }
        
    }    
    
    
    
    @FXML
    private void handleLogoutBtn(ActionEvent event){
        try{
            Parent register = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            oStage.setScene(scene);
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    @FXML
    private void handleLogoutImage(){
        System.out.println("Clicked");
        try{
            Parent register = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)imgPreview.getScene().getWindow();
            oStage.setScene(scene);
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
     @FXML
    private void handleAddDetailsBtn(ActionEvent event){
        try{
            Parent register = FXMLLoader.load(getClass().getResource("HospitalDetails.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            oStage.setScene(scene);
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    @FXML
    private void handleAddDetailsImage(){
        System.out.println("Clicked");
        try{
            Parent register = FXMLLoader.load(getClass().getResource("HospitalDetails.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)imgPreview.getScene().getWindow();
            oStage.setScene(scene);
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    
    @FXML
    private void handleProfileBtn(ActionEvent event){
        try{
            Parent register = FXMLLoader.load(getClass().getResource("MyProfile.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            oStage.setScene(scene);
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    @FXML
    private void handleProfileImage(){
        System.out.println("Clicked");
        try{
            Parent register = FXMLLoader.load(getClass().getResource("MyProfile.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)imgPreview.getScene().getWindow();
            oStage.setScene(scene);
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
}
