
package hms;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class DiseaseInfo {
    private int PatientId;
    public int Id;
    private String DeseaseName;
    private boolean IsActive;
    private Date AdmittedOn;
    private Date DischargedOn;
    
    DiseaseInfo(){
        
    }
    public DiseaseInfo(int Id, int PatientId, String DeseaseName, boolean IsActive, Date AdmittedOn, Date DischargedOn) {
        this.PatientId = PatientId;
        this.DeseaseName = DeseaseName;
        this.IsActive = IsActive;
        this.AdmittedOn = AdmittedOn;
        this.DischargedOn = DischargedOn;
        this.Id = Id;
    }

    public int getPatientId() {
        return PatientId;
    }

    public String getDeseaseName() {
        return DeseaseName;
    }

    public boolean isIsActive() {
        return IsActive;
    }
    public Date getAdmittedOn(){
        return AdmittedOn;
    }
    public Date getDischargedOn(){
        return DischargedOn;
    }
    
    public void DisCharge(int deseaseId){
        try{
           String query = "select * from Payment WHERE IsActive = 1 AND IsDeleted = 0 AND DeseaseId = " + deseaseId +" order by Id DESC " ;
           ResultSet oResult = new Utils().GetResult(query);
           if(oResult.next()){
               int remainingAmount = oResult.getInt("Remaining");
               if(remainingAmount > 0){
                   JOptionPane.showMessageDialog(null, "Patient Can't be Discharged Because of Remaining bill. Amount: "+remainingAmount);
               }else{
                   query = "UPDATE Desease SET IsActive = 0, DischargedOn='" + LocalDate.now() + "' WHERE Id = " + deseaseId;
                   int result = new Utils().InsertData(query);
                   if(result > 0){
                       JOptionPane.showMessageDialog(null, "Patient DisCharged");
                   }else{
                       JOptionPane.showMessageDialog(null, "Oops! Something went wrong. Kindly try again or contact our Support.");
                   }
               }
                       
           }else{
               JOptionPane.showMessageDialog(null, "Patient Can't be Discharged Because of Remaining bill.");
           }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    

    
    
}
