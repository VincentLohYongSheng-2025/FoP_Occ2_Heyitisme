public class Employee{
    private String EmployeeID;
    private String Name;
    private String Outlet;

    //Constructor
    public Employee(String EmployeeID, String Name, String Outlet) {
        this.EmployeeID = EmployeeID;
        this.Name = Name;
        this.Outlet = Outlet;
    }

    //Getter and Setter
    public String getEmployeeID() {
        return EmployeeID;
    }
    public void setEmployeeID(String EmployeeID) {
        this.EmployeeID = EmployeeID;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getOutlet() {
        return Outlet;
    }
    public void setOutlet(String Outlet) {
        this.Outlet = Outlet;
    }
}