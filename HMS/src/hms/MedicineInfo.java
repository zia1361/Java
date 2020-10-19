
package hms;

import java.sql.Time;
import javafx.scene.control.Button;


public class MedicineInfo {
    private int MedicineId;
    private String MedicineName;
    private boolean IsActive;
    private Time Timing;
//    private Button btn;

    public MedicineInfo(String MedicineName, boolean IsActive, Time Timing, int MedicineId) {
        this.MedicineName = MedicineName;
        this.IsActive = IsActive;
        this.Timing = Timing;
        this.MedicineId = MedicineId;
    }

    public int getMedicineId(){
        return this.MedicineId;
    }
    public String getMedicineName() {
        return MedicineName;
    }

    public boolean isIsActive() {
        return IsActive;
    }

    public Time getTiming() {
        return Timing;
    }
    
    
}
