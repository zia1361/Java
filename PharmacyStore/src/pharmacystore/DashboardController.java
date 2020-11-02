/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacystore;

import FactoryClasses.Antibiotic;
import FactoryClasses.Medicine;
import FactoryClasses.Services;
import FactoryClasses.Utils;
import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javax.swing.JOptionPane;


/**
 *
 * @author A.U Computer
 */
public class DashboardController implements Initializable {
    
    @FXML
    TextField name;
    @FXML
    TextField price;
    @FXML
    TextField manufacturer;
    @FXML
    TextField desease;
    @FXML
    TextField type;
    @FXML
    TextField generation;
    @FXML
    TextField reaction;
    @FXML
    TableColumn nameColumn;
    @FXML
    TableColumn priceColumn;
    @FXML
    TableColumn manufecturerColumn;
    @FXML
    TableColumn deseaseColumn;
    @FXML
    TableColumn generationColumn;
    @FXML
    TableColumn reactionColumn;
    @FXML
    TableColumn typeColumn;
    @FXML
    TableColumn actionColumn;
    @FXML
    TableView<Antibiotic> medicinesTable;
    
    @FXML
    private void handleAddBtn(ActionEvent event) {
        try{
            if(name.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Kindly Enter Medicine Name");
                return;
            }
            if(price.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Kindly Enter Medicine Price");
                return;
            }
            if(manufacturer.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Kindly Enter Medicine Manufecturer");
                return;
            }
            if(desease.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Kindly Enter Desease");
                return;
            }
            if(type.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Kindly Enter Type");
                return;
            }
            if(generation.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Kindly Enter Generation");
                return;
            }
            if(reaction.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Kindly Enter Reaction");
                return;
            }
            try{
                int price = Integer.parseInt(this.price.getText());
                new Services().AddMedicine(name.getText(), price, manufacturer.getText(), desease.getText(), type.getText(), generation.getText(), reaction.getText());
                BindData();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Price Should be in Number");
                return;
            }
            
                
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        BindData();
    }  
    
    void BindData(){
        try{
            final ObservableList<Antibiotic> data = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        manufecturerColumn.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
        deseaseColumn.setCellValueFactory(new PropertyValueFactory<>("Desease"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        generationColumn.setCellValueFactory(new PropertyValueFactory<>("Generation"));
        reactionColumn.setCellValueFactory(new PropertyValueFactory<>("Reaction"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("DELETE"));    
                
                
        Callback<TableColumn<Antibiotic, String>, TableCell<Antibiotic, String>> cellFactory
                = //
                new Callback<TableColumn<Antibiotic, String>, TableCell<Antibiotic, String>>() {
            @Override
            public TableCell call(final TableColumn<Antibiotic, String> param) {
                 TableCell<Antibiotic, String> cell = new TableCell<Antibiotic, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        final Button btn = new Button("DELETE");
                        btn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Antibiotic oBiotic = getTableView().getItems().get(getIndex());
                                new Services().DeleteMedicine(oBiotic.getId());
                                medicinesTable.getItems().remove(oBiotic);
                                try{
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
        actionColumn.setCellFactory(cellFactory);
        for(Antibiotic oBiotic: new Services().GetMedicines()){
            data.add(new Antibiotic(oBiotic.getId() , oBiotic.getName(), oBiotic.getPrice(), oBiotic.getManufacturer(), oBiotic.getDesease(), oBiotic.getType(), oBiotic.getGeneration(), oBiotic.getReaction()));
        }
        medicinesTable.setItems(data);
        
        }
        catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
}
