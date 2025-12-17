import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageManager {

    // File Names
    private static final String SALES_FILE = "sales_data.csv";
    private static final String EMP_FILE = "employees.csv";

    //String SALES_FILE = "sales_data.csv";
    // ===========================
    // SECTION 1: SALES OPERATIONS
    // ===========================

    // Load Sales from CSV
    public static ArrayList<SalesRecord> loadSales() {
        ArrayList<SalesRecord> records = new ArrayList<>();
        try {
            File file = new File(SALES_FILE);
            if (file.exists()) {
                Scanner input = new Scanner(file);
                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    String[] data = line.split(",");
                    if (data.length == 4) {
                        // Create SalesRecord object and add to list
                        records.add(new SalesRecord(data[0], data[1], data[2], Double.parseDouble(data[3])));
                    }
                }
            }
        } 
        catch (FileNotFoundException e) {
            // This runs ONLY if the file is missing (Safe to ignore for new store)
            System.out.println("Sales file not found. Starting with empty database.");
        }catch (Exception e) {
            System.out.println("Error reading sales file.");
        }
        return records;
    }

    // Save a NEW Sale to CSV (For Teammate D)
    public static void saveSale(SalesRecord record) {
        try {
            FileWriter fw = new FileWriter(SALES_FILE, true); // true = append mode
            PrintWriter pw = new PrintWriter(fw);
            pw.println(record.getDate() + "," + record.getCustomerName() + "," + 
                       record.getItemModel() + "," + record.getAmount());
            pw.close();
        } catch (IOException e) {
            System.out.println("Error saving sale.");
        }
    }

    // ==============================
    // SECTION 2: EMPLOYEE OPERATIONS (New!)
    // ==============================

    // Load Employees from CSV (For Teammate A - Login)
    public static ArrayList<Employee> loadEmployees() {
        ArrayList<Employee> list = new ArrayList<>();
        try {
            File file = new File(EMP_FILE);
            if (file.exists()) {
                Scanner input = new Scanner(file);
                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    String[] data = line.split(",");
                    // Expecting: ID, Name, Role, Password
                    if (data.length == 4) {
                        list.add(new Employee(data[0], data[1], data[2], data[3]));
                    }
                }
            }
        } catch (FileNotFoundException e) {
             // This runs ONLY if the file is missing
            System.out.println("Employee file not found. No users registered yet.");
        }
        catch (Exception e) {
            System.out.println("Error reading employee file.");
        }
        return list;
    }
    
    // Register New Employee (For Manager)
    public static void saveEmployee(Employee emp) {
        try {
            FileWriter fw = new FileWriter(EMP_FILE, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(emp.getId() + "," + emp.getName() + "," + 
                       emp.getRole() + "," + emp.getPassword());
            pw.close();
        } catch (IOException e) {
            System.out.println("Error saving employee.");
        }
    }
}