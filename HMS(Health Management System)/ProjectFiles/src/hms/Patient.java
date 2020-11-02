package hms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;


public class Patient extends Hospital {

    public int Id;
    private String Name;
    private String Disease;
    private String contact;
    public Date registeredOn;
    private String wardNo;
    private int doctorId;
    private boolean coronaStatus;
    protected Date admittedOn;
    private boolean activeStatus;
    private boolean deletedStatus;
    public static Patient oInformation;

    public Patient() {
    }

    public Patient(int Id) {
        oInformation = new Patient();
        oInformation.Id = Id;
        String query = "select * from Patient where id = " + Id + "";
        ResultSet rs;
        try {
            rs = new Utils().GetResult(query);
            if (rs.next()) {
                oInformation.setId(Id);
                oInformation.setCoronaStatus(rs.getBoolean("IsCoronaPatient"));
                oInformation.setName(rs.getString("Name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        query = "select * from PatientDetails where PatientId = " + Id + ";";
        try {
            rs = new Utils().GetResult(query);
            if (rs.next()) {
                oInformation.setContact(rs.getNString("EmergencyContactNumber"));
                oInformation.setWardNo(rs.getString("WardNumber"));
                oInformation.setDoctorId(rs.getInt("DoctorId"));
                oInformation.registeredOn = rs.getObject("RegisteredOn") != null ? rs.getDate("RegisteredOn") : null ;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public void setDisease(String Disease) {
        this.Disease = Disease;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }

    public void setDoctorId(int doctorName) {
        this.doctorId = doctorName;
    }

    public void setCoronaStatus(boolean coronaStatus) {
        this.coronaStatus = coronaStatus;
    }

    public void setAdmittedOn(Date admittedOn) {
        this.admittedOn = admittedOn;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void setDeletedStatus(boolean deletedStatus) {
        this.deletedStatus = deletedStatus;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    

    public String getDisease() {
        return Disease;
    }

    public String getContact() {
        return contact;
    }

    public String getWardNo() {
        return wardNo;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public boolean isCoronaStatus() {
        return coronaStatus;
    }

    public Date getAdmittedOn() {
        return admittedOn;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public boolean isDeletedStatus() {
        return deletedStatus;
    }
    
    public boolean HasDetails(){
        boolean hasDetails = false;
        try{
            String query = "SELECT * from PatientDetails WHERE PatientId = " + Patient.oInformation.Id;
             ResultSet oSet = new Utils().GetResult(query);
             if(oSet.next()){
                hasDetails = true;
             }else{
                hasDetails = false;
             }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return hasDetails;
    }
}
