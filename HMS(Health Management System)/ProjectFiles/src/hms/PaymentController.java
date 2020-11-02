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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class PaymentController implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private TableView<PaymentInfo> paymentTable;
    @FXML
    private TextField totalPayment;
    @FXML
    private TextField paymentToPaid;
    @FXML
    private Button btnAddPayment;
    @FXML
    private ComboBox addDisease;
    @FXML
    private TableColumn tblpaymentDisease;
    @FXML
    private TableColumn tbltotalPayment;
    @FXML
    private TableColumn tblpaymentPaid;
    @FXML
    private TableColumn tblremaningPayment;
    public int check;
    int rem = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ShowDeseseName();
            getPaymentList();
            showOnTable();
        } catch (Exception ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
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
        if (event.getSource() == btnAddPayment) {

            if (totalPayment.getText().equals("") && paymentToPaid.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Kindly Enter Complete Details");
            } else if (!addDisease.getSelectionModel().getSelectedItem().toString().isEmpty()) {
                Calculations();
            }

        }

    }

    public void ShowDeseseName() throws Exception {
        ObservableList odis = FXCollections.observableArrayList();
        String query = "select Id, DeseaseName from Desease where PatientId = " + Patient.oInformation.getId() + " AND IsActive = 1 AND IsDeleted = 0";
        ResultSet rs;
        try {
            rs = new Utils().GetResult(query);

            while (rs.next()) {
                odis.add(rs.getInt("Id") + " " + rs.getString("DeseaseName"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        addDisease.setItems(odis);
    }

    

    

    public int getId() throws Exception {
        return Integer.parseInt(addDisease.getValue().toString().split(" ")[0]);
    }

    private void Calculations() throws Exception {
        checkPayment();
        int totalPay = Integer.parseInt(totalPayment.getText());
        int paid = Integer.parseInt(paymentToPaid.getText());
        int remaningPayment = totalPay - paid;

        if (check == 1) {
            String query = "insert into Payment(DeseaseId, Total, Paid, Remaining) VALUES(" + getId() + "," + Integer.parseInt(totalPayment.getText()) + ","
                        + Integer.parseInt(paymentToPaid.getText()) + "," + (rem - Integer.parseInt(paymentToPaid.getText())) + ")";
                int result = new Utils().InsertData(query);
                JOptionPane.showMessageDialog(null,
                        "Payment Successful!",
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE);
                showOnTable();
        }
    }

    public ObservableList<PaymentInfo> getPaymentList() throws ClassNotFoundException, Exception {
        ObservableList<PaymentInfo> Plist = FXCollections.observableArrayList();
        String query = "select o.DeseaseName, i.Total, i.Paid, i.Remaining from Desease o join Payment i on o.Id = i.DeseaseId where o.PatientId = " + Patient.oInformation.getId() + ";";
        ResultSet rs;
        try {
            rs = new Utils().GetResult(query);
            PaymentInfo py;
            while (rs.next()) {
                py = new PaymentInfo(rs.getString("DeseaseName"), rs.getInt("Total"), rs.getInt("Paid"), rs.getInt("Remaining"));
                Plist.add(py);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Plist;
    }

    public void checkPayment() throws Exception {
        check = 0;
        
        String query = "select Remaining from Payment where DeseaseId = " + getId() + " ORDER BY Id DESC";
        ResultSet rs;
        try {
            rs = new Utils().GetResult(query);
            if(rs.next()){
                rem = rs.getInt(1);
            }else{
                check = 1;
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(rem == 0){
            JOptionPane.showMessageDialog(null,
                    "Already Paid. Thank You",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else if (rem < Integer.parseInt(paymentToPaid.getText())) {
            JOptionPane.showMessageDialog(null,
                    "You have to Pay " + rem + " Rs",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
            check = 0;
        } else {
            check = 1;
        }
    }

    public void showOnTable() throws Exception {
        ObservableList<PaymentInfo> list = getPaymentList();
        tblpaymentDisease.setCellValueFactory(new PropertyValueFactory<>("DeseaseName"));
        tbltotalPayment.setCellValueFactory(new PropertyValueFactory<>("Total"));
        tblpaymentPaid.setCellValueFactory(new PropertyValueFactory<>("Paid"));
        tblremaningPayment.setCellValueFactory(new PropertyValueFactory<>("Remaining"));

        paymentTable.setItems(list);
    }
}
