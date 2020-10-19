package hms;

import java.util.Date;

public class PatientHelper extends Patient {

    private Date DischargedOn;
    private boolean IsActive;

    
    public PatientHelper(Date AdmittedOn, Date DischargedOn, boolean IsActive) {
        this.admittedOn = AdmittedOn;
        this.DischargedOn = DischargedOn;
        this.IsActive = IsActive;

    }

    public Date getAdmittedOn() {
        return admittedOn;
    }

    public Date getDischargedOn() {
        return DischargedOn;
    }

    public boolean isIsActive() {
        return IsActive;
    }

}
