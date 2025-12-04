import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;

public class AttendanceLog {
    private String EmployeeID;
    private LocalDate date;
    private LocalTime ClockIn;
    private LocalTime ClockOut;
    private double total_hours_worked;
   
    //Constructor
   public AttendanceLog(String EmployeeID, LocalDate date) {
    this.EmployeeID = EmployeeID;
    this.date = date;
   }
   
   //Clock-in
   public void setClockIn(LocalTime ClockIn) {
    this.ClockIn = ClockIn;
   }

   //Clock-out and calculate Hours of worked
   public void setClockOut(LocalTime ClockOut) {
    this.ClockOut = ClockOut;
    calculateTotalHoursWorked();
   }

   private void calculateTotalHoursWorked() {
    if (ClockIn != null && ClockOut != null) {
        Duration duration= Duration.between(ClockIn, ClockOut);
        total_hours_worked = duration.toMinutes() / 60.0; //convert minutes to hours
    } else {
        total_hours_worked = 0.0; //if either Clock-in or Clock-out is null
    }
   }

   //Getters
   public String getEmployeeID() {
    return EmployeeID;
   }
   public LocalDate getdate() {
    return date;
   }
   public LocalTime getClockIn() {
    return ClockIn;
   }
   public LocalTime getClockOut() {
    return ClockOut;
   }
   public double gettotal_hours_worked() {
    return total_hours_worked;
   }
}