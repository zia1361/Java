/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Hp
 */
public class HelperStaff {
    private final SimpleStringProperty Name;
    private final SimpleStringProperty PhoneNumber;
    private final SimpleStringProperty ShiftStart;
    private final SimpleStringProperty ShiftEnd;
    private final SimpleStringProperty ApointedOn;
    private final SimpleIntegerProperty Id;

    
    HelperStaff(int Id, String Name, String PhoneNumber, String ShiftStart, String ShiftEnd, String ApointedOn){
        this.Name = new SimpleStringProperty(Name);
        this.PhoneNumber = new SimpleStringProperty(PhoneNumber);
        
        String time = Integer.parseInt(ShiftStart.split(":")[0]) > 12 ?
                (Integer.parseInt(ShiftStart.split(":")[0]) - 12) + "pm"
                : Integer.parseInt(ShiftStart.split(":")[0]) + "am";
        this.ShiftStart = new SimpleStringProperty(time);
        time = Integer.parseInt(ShiftEnd.split(":")[0]) > 12 ?
                (Integer.parseInt(ShiftEnd.split(":")[0]) - 12) + "pm"
                : Integer.parseInt(ShiftEnd.split(":")[0]) + "am";
        this.ShiftEnd = new SimpleStringProperty(time);
        this.ApointedOn = new SimpleStringProperty(ApointedOn);
        this.Id = new SimpleIntegerProperty(Id);
        
    }

    public String getName() {
        return Name.get();
    }

    public String getPhoneNumber() {
        return PhoneNumber.get();
    }

    public String getShiftStart() {
        return ShiftStart.get();
    }

    public String getShiftEnd() {
        return ShiftEnd.get();
    }

    

    public String getApointedOn() {
        return ApointedOn.get();
    }

    public int getId() {
        return Id.get();
    }
    
    
}
