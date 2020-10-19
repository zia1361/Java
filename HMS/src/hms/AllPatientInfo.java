
package hms;

import java.util.Date;

/**
 *
 * @author Haziq
 */
public class AllPatientInfo {
    private int Id;
    private String Name;
    private String EmergencyContactNumber;
    private Date RegisteredOn;


    public AllPatientInfo(int Id, String Name, String EmergencyContactNumber, Date RegisteredOn) {
        this.Id = Id;
        this.Name = Name;
        this.EmergencyContactNumber = EmergencyContactNumber;
        this.RegisteredOn = RegisteredOn;

    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getEmergencyContactNumber() {
        return EmergencyContactNumber;
    }

    public Date getRegisteredOn() {
        return RegisteredOn;
    }


 
    
}
