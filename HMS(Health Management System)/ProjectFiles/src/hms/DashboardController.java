/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.LocalTime;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    TableView<HospitalHelper> hospitals;
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
        final ObservableList<HospitalHelper> data = FXCollections.observableArrayList();
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        HasCoronaWards.setCellValueFactory(new PropertyValueFactory<>("HasCoronaWards"));
        TotalCrWards.setCellValueFactory(new PropertyValueFactory<>("TotalCrWards"));
        TotalCrConsumedWards.setCellValueFactory(new PropertyValueFactory<>("TotalCrConsumedWards"));
        TotalCrRemaingWards.setCellValueFactory(new PropertyValueFactory<>("TotalCrRemaingWards"));
        
        for(Hospital oHospital: new Hospital().GetHospitalsData()){
            data.add(new HospitalHelper(oHospital.Id ,oHospital.Name, oHospital.Address, oHospital.HasCoronaWards, oHospital.TotalCrWards, oHospital.TotalCrConsumedWards, oHospital.TotalCrRemaingWards)
            );
            
        }
        hospitals.setItems(data);
        
    }    
    
    
    
    @FXML
    private void handleLogoutBtn(ActionEvent event){
        try{
            Parent register = FXMLLoader.load(getClass().getResource("Login.fxml"));
            
            Scene scene = new Scene(register);
            Stage oStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            oStage.setScene(scene);
            oStage.setTitle("LOGIN");
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    @FXML
    private void handleLogoutImage(){
        try{
            Parent register = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)imgPreview.getScene().getWindow();
            oStage.setScene(scene);
            oStage.setTitle("LOGIN");
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
     @FXML
    private void handleAddDetailsBtn(ActionEvent event){
        if(new Hospital().HasDetails()){
            JOptionPane.showMessageDialog(null, "Details Already Added. Kindly try editing them.");
            return;
        }
        try{
            Parent register = FXMLLoader.load(getClass().getResource("HospitalDetails.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            oStage.setScene(scene);
            oStage.setTitle("ADD DETAILS");
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    @FXML
    private void handleAddDetailsImage(){
        if(new Hospital().HasDetails()){
            JOptionPane.showMessageDialog(null, "Details Already Added. Kindly try editing them.");
            return;
        }
        try{
            Parent register = FXMLLoader.load(getClass().getResource("HospitalDetails.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)imgPreview.getScene().getWindow();
            oStage.setScene(scene);
            oStage.setTitle("ADD DETAILS");
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
            oStage.setTitle("MY PROFILE");
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
            oStage.setTitle("MY PROFILE");
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    @FXML
    private void handleDoctorBtn(ActionEvent event){
        try{
            Parent register = FXMLLoader.load(getClass().getResource("AddDoctor.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            oStage.setScene(scene);
            oStage.setTitle("ADD DOCTOR");
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    @FXML
    private void handleDoctorImage(){
        System.out.println("Clicked");
        try{
            Parent register = FXMLLoader.load(getClass().getResource("AddDoctor.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)imgPreview.getScene().getWindow();
            oStage.setScene(scene);
            oStage.setTitle("ADD DOCTOR");
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    @FXML
    private void handleStaffBtn(ActionEvent event){
        try{
            Parent register = FXMLLoader.load(getClass().getResource("AddStaff.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            oStage.setScene(scene);
            oStage.setTitle("ADD STAFF");
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    @FXML
    private void handleStaffImage(){
        try{
            Parent register = FXMLLoader.load(getClass().getResource("AddStaff.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)imgPreview.getScene().getWindow();
            oStage.setScene(scene);
            oStage.setTitle("ADD STAFF");
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    @FXML
    private void handlePatientBtn(ActionEvent event){
        try{
            Parent register = FXMLLoader.load(getClass().getResource("RegisterPatient.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            oStage.setScene(scene);
            oStage.setTitle("ADD PATIENT");
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    @FXML
    private void handlePatientImage(){
        try{
            Parent register = FXMLLoader.load(getClass().getResource("RegisterPatient.fxml"));
            Scene scene = new Scene(register);
            Stage oStage = (Stage)imgPreview.getScene().getWindow();
            oStage.setScene(scene);
            oStage.setTitle("ADD PATIENT");
            oStage.show();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oops! Something Went Wrong. Please Try again or Contact Our Support.");
        }
    }
    
}
