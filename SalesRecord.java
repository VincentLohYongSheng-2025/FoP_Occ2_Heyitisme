// [Class] This class defines what a "SalesRecord" looks like.
// It is a blueprint for every sale made in the store.
public class SalesRecord {
    
    // Private attributes (Encapsulation)
    private String date;
    private String customerName;
    private String itemModel;
    private double amount;

    // [Constructor] This method runs when we create a new SalesRecord object.
    public SalesRecord(String date, String customerName, String itemModel, double amount) {
        this.date = date;
        this.customerName = customerName;
        this.itemModel = itemModel;
        this.amount = amount;
    }

    // [Methods] Getters to access the private data
    public String getDate() { return date; }
    public String getCustomerName() { return customerName; }
    public String getItemModel() { return itemModel; }
    public double getAmount() { return amount; }

    // [Polymorphism] Overriding the toString() method from the Object class.
    // This helps us print the object easily for debugging.
    @Override
    public String toString() {
        return String.format("%-12s | %-20s | RM%8.2f", date, itemModel, amount);
    }
}