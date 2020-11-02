
package hms;

/**
 *
 * @author Haziq
 */
public class PaymentInfo {
    private String DeseaseName;
    private int Total;
    private int Paid;
    private int Remaining;

    public PaymentInfo(String DeseaseName, int Total, int Paid, int Remaining) {
        this.DeseaseName = DeseaseName;
        this.Total = Total;
        this.Paid = Paid;
        this.Remaining = Remaining;
    }

    public String getDeseaseName() {
        return DeseaseName;
    }

    public int getTotal() {
        return Total;
    }

    public int getPaid() {
        return Paid;
    }

    public int getRemaining() {
        return Remaining;
    }
    
      
}
