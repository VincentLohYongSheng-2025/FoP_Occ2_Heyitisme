import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class allAttendanceLog {
    private List<Employee> employees = new ArrayList<>();
    private List<AttendanceLog> attendance = new ArrayList<>();
    
    private static final String ATTENDANCE_FILE_NAME = "Attendance Log.csv";
    private static final String EMPLOYEE_FILE_NAME = "Employee.csv";

    // Helper to find the active log
    private AttendanceLog findTodayActiveLog(String EmployeeID, LocalDate date) {
        for (AttendanceLog log : attendance) {
        if (log.getEmployeeID().equals(EmployeeID) && log.getdate().equals(date) && log.getClockOut() == null) {
            return log;
            }
        }
        return null;
    }

    // Load employees from file
    public void loadEmployees(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    employees.add(new Employee(parts[0], parts[1], parts[2]));
                }
            }
            System.out.println("Employees loaded: " + employees.size());
        } catch(IOException e) {
            System.out.println("Error loading employees: " + e.getMessage());
        } 
    }

    // Load attendance logs from file
    public void loadAttendanceLog(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    LocalDate date = LocalDate.parse(parts[1]);
                    AttendanceLog log = new AttendanceLog(parts[0], date);
                    log.setClockIn(LocalTime.parse(parts[2]));

                    // Check if ClockOut exists (not empty string) before trying to parse
                    if (parts.length >= 4 && !parts[3].isEmpty()) {
                        log.setClockOut(LocalTime.parse(parts[3]));
                    }
                    attendance.add(log);
                }
            }
            System.out.println("Attendance logs loaded: " + attendance.size());
        } catch(IOException e) {
            System.out.println("Error loading attendance logs: " + e.getMessage());
        } catch(Exception e) {
            System.out.println("Error parsing attendance log line: " + e.getMessage());
        }
    }

    // Clock in
    public void ClockIn(String EmployeeID) {
        Employee emp = findEmployeeByID(EmployeeID);
        if (emp == null) {
            System.out.println("Employee not found!");
            return;
        }

        LocalDate today = LocalDate.now();
        // Check for double clock-in
        if (findTodayActiveLog(EmployeeID, today) != null) {
            System.out.println("Error: Employee " + emp.getName() + " is already clocked in today!");
            return;
        }

        LocalTime now = LocalTime.now();
        AttendanceLog log = new AttendanceLog(EmployeeID, today);
        log.setClockIn(now);
        attendance.add(log);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = now.format(timeFormat).toLowerCase();

        System.out.println("\n=== Attendance Clock In ===");
        System.out.println("Employee ID: " + emp.getEmployeeID());
        System.out.println("Name: " + emp.getName());
        System.out.println("Outlet: " + emp.getOutlet());
        System.out.println();
        System.out.println("Clock In Successful!");
        System.out.println("Date: " + today.format(dateFormat));
        System.out.println("Time: " + formattedTime);
    }

    //Clock Out
    public void ClockOut(String EmployeeID) {
        Employee emp = findEmployeeByID(EmployeeID);
        if (emp == null) {
            System.out.println("Employee not found!");
            return;
        }

        LocalDate today = LocalDate.now();
        // Find today's active log
        AttendanceLog log = findTodayActiveLog(EmployeeID, today);
        
        if (log != null) { 
            LocalTime now = LocalTime.now();
            log.setClockOut(now); //calculates total_hours_worked

            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
            String formattedTime = now.format(timeFormat).toLowerCase();

            System.out.println("\n=== Attendance Clock Out ===");
            System.out.println("Employee ID: " + emp.getEmployeeID());
            System.out.println("Name: " + emp.getName());
            System.out.println("Outlet: " + emp.getOutlet());
            System.out.println();
            System.out.println("Clock Out Successful!");
            System.out.println("Date: " + today.format(dateFormat));
            System.out.println("Time: " + formattedTime);
            System.out.printf("Total Hours Worked:%.2f hours%n", log.gettotal_hours_worked()); 

            SaveAttendanceLog(ATTENDANCE_FILE_NAME);
            return;
        }
        
        System.out.println("No active clock-in record found for " + EmployeeID + " today. Please Clock In first."); 
    }

    //Save attendance log to file
    public void SaveAttendanceLog(String filename) {
        try (PrintWriter pw = new PrintWriter (new FileWriter(filename))) {
            pw.println("EmployeeID,Date,ClockIn,ClockOut,TotalHoursWorked");
            for (AttendanceLog log : attendance) {
                String clockOutStr = (log.getClockOut() != null) ? log.getClockOut().toString() : "";
                double totalHours = (log.getClockOut() != null) ? log.gettotal_hours_worked() : 0.0;

                pw.printf("%s,%s,%s,%s,%.2f%n",
                log.getEmployeeID(),
                log.getdate().toString(), 
                log.getClockIn().toString(),
                clockOutStr, 
                totalHours); 
            }
            System.out.println("Attendance saved to " + filename);
            }catch (IOException e){
            System.out.println("Error saving attendance: " + e.getMessage());
        }
    }
    
    //Find employee by ID 
    private Employee findEmployeeByID(String EmployeeID) {
        for (Employee emp : employees) {
            if (emp.getEmployeeID().equals(EmployeeID)) {
                return emp;
            }
        }
        return null;
    }
}