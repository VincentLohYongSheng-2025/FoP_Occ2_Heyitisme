// [Class] This class defines what an "Employee" looks like.
public class Employee {
    
    // Attributes
    private String id;
    private String name;
    private String role; // "Manager" or "Part-time"
    private String password;

    // Constructor
    public Employee(String id, String name, String role, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.password = password;
    }

    // Getters (Methods to access the data)
    public String getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getPassword() { return password; }

    // This helps us print the employee details nicely
    @Override
    public String toString() {
        return id + " | " + name + " (" + role + ")";
    }
}
