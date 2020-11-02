/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FactoryClasses;

/**
 *
 * @author A.U Computer
 */
public class Medicine {
    protected int Id;
    protected String Name;
    protected int Price;
    protected String Manufacturer;
    protected String Desease;
    protected String Type;
    
    public Medicine(){
        
    }

    public int getId() {
        return Id;
    }
    
    public String getName() {
        return Name;
    }

    public int getPrice() {
        return Price;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public String getDesease() {
        return Desease;
    }

    public String getType() {
        return Type;
    }

    public Medicine(int Id, String Name, int Price, String Manufacturer, String Desease, String Type) {
        this.Id = Id;
        this.Name = Name;
        this.Price = Price;
        this.Manufacturer = Manufacturer;
        this.Desease = Desease;
        this.Type = Type;
    }
 
}
