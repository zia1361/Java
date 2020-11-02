package FactoryClasses;


import java.util.List;


public class Antibiotic extends Medicine {
private String Generation;
private String Reaction;
// consurcter

    public Antibiotic() {
        
    }


    public Antibiotic(int Id, String Name, int Price, String Manufacturer, String Desease, String Type, String Generation, String Reaction) {
        super(Id, Name, Price, Manufacturer, Desease, Type);
        this.Generation = Generation;
        this.Reaction = Reaction;
    }

    public String getGeneration() {
        return Generation;
    }

    public String getReaction() {
        return Reaction;
    }










} // end of class

