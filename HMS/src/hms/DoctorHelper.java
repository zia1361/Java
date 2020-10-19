/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import java.time.LocalDate;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Hp
 */
public class DoctorHelper extends Doctor {
    
    DoctorHelper(int Id, String name, String phone_no, String email, String speciality, LocalDate appointed_on){
        this.name = name;
        this.email = email;
        this.phone_no = phone_no;
        this.speciality = speciality;
        this.appointed_on = appointed_on;
        this.Id = Id;
        
    }

    public String getName() {
        return name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getEmail() {
        return email;
    }

    public String getSpeciality() {
        return speciality;
    }

    public LocalDate getAppointed_on() {
        return appointed_on;
    }
    public int getId(){
        return Id;
    }
    
    
}
