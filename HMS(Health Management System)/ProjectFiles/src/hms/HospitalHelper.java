/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author A.U Computer
 */
public class HospitalHelper {
     private final SimpleStringProperty Name;
     private final SimpleStringProperty Address;
     private final SimpleBooleanProperty HasCoronaWards;
     private final SimpleIntegerProperty TotalCrWards;
     private final SimpleIntegerProperty TotalCrConsumedWards;
     private final SimpleIntegerProperty TotalCrRemaingWards;
     private final SimpleIntegerProperty HospitalId;
     
     
     HospitalHelper(int HospitalId, String Name, String Address, boolean HasCoronaWards, int TotalCrWards, int TotalCrConsumedWards, int TotalCrRemaingWards){
         this.HospitalId = new SimpleIntegerProperty(HospitalId);
         this.Name = new SimpleStringProperty(Name);
         this.Address = new SimpleStringProperty(Address);
         this.HasCoronaWards = new SimpleBooleanProperty(HasCoronaWards);
         this.TotalCrWards = new SimpleIntegerProperty(TotalCrWards);
         this.TotalCrConsumedWards = new SimpleIntegerProperty(TotalCrConsumedWards);
         this.TotalCrRemaingWards = new SimpleIntegerProperty(TotalCrRemaingWards);
     }

    public int getId(){
        return HospitalId.get();
    } 
     
    public String getName() {
        return Name.get();
    }

    public String getAddress() {
        return Address.get();
    }

    public Boolean getHasCoronaWards() {
        return HasCoronaWards.get();
    }

    public int getTotalCrWards() {
        return TotalCrWards.get();
    }

    public int getTotalCrConsumedWards() {
        return TotalCrConsumedWards.get();
    }

    public int getTotalCrRemaingWards() {
        return TotalCrRemaingWards.get();
    }
     
     
}
