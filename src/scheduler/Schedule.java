package scheduler;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yeo yu le
 */
import java.text.ParseException;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.io.IOException;

public class Schedule implements DataValidation{
    private String Schedule_Type;
    private String Hall_Name;
    private String Start_Date;
    private String Start_Time;
    private String End_Date;
    private String End_Time;
    private String Remarks;
    private int indexToEdit;

    public Schedule() {
    }

    
    public Schedule(String type,String name,String Start_Date, String Start_Time, String End_Date, String End_Time, String Remarks) {
        this.Schedule_Type = type;
        this.Hall_Name = name;
        this.Start_Date = Start_Date;
        this.Start_Time = Start_Time;
        this.End_Date = End_Date;
        this.End_Time = End_Time;
        this.Remarks = Remarks;
        this.indexToEdit = -1;
    }

    public Schedule(String Schedule_Type, String Hall_Name, String Start_Date, String Start_Time, String End_Date, String End_Time, 
            String Remarks, int indexToEdit) {
        this.Schedule_Type = Schedule_Type;
        this.Hall_Name = Hall_Name;
        this.Start_Date = Start_Date;
        this.Start_Time = Start_Time;
        this.End_Date = End_Date;
        this.End_Time = End_Time;
        this.Remarks = Remarks;
        this.indexToEdit = indexToEdit;
    }
    
    
    
    public String getSchedule_Type() {
        return Schedule_Type;
    }

    public String getHall_Name() {
        return Hall_Name;
    }

    public String getStart_Date() {
        return Start_Date;
    }

    public String getStart_Time() {
        return Start_Time;
    }

    public String getEnd_Date() {
        return End_Date;
    }

    public String getEnd_Time() {
        return End_Time;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setSchedule_Type(String Schedule_Type) {
        this.Schedule_Type = Schedule_Type;
    }

    public void setHall_Name(String Hall_Name) {
        this.Hall_Name = Hall_Name;
    }

    public void setStart_Date(String Start_Date) {
        this.Start_Date = Start_Date;
    }

    public void setStart_Time(String Start_Time) {
        this.Start_Time = Start_Time;
    }

    public void setEnd_Date(String End_Date) {
        this.End_Date = End_Date;
    }

    public void setEnd_Time(String End_Time) {
        this.End_Time = End_Time;
    }

    public void setRemarks(String Remarks) {
        this.Remarks = Remarks;
    }
    
    

    @Override
    public String toString() {
        return Schedule_Type + ";" + Hall_Name + ";" + Start_Date + ";" + Start_Time + ";" + End_Date + ";" + End_Time + ";" + Remarks;
    }
    
    
    public void showTime(JComboBox combo, JTextField text){
        try{
            int hour = Integer.parseInt(combo.getSelectedItem().toString());
        
            if (hour >= 8 && hour < 12){
                text.setText("AM");
            }
            else if (hour == 12 || (hour >= 1 && hour <=6)){
                text.setText("PM");
            }      
        }catch( Exception e){System.out.println(e + "no way");}      
    }

    @Override
    public boolean ValidateData() {
        boolean correct = true;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");

        // Parse start and end times
        LocalTime StartTime = LocalTime.parse(Start_Time, timeFormatter);
        LocalTime EndTime = LocalTime.parse(End_Time, timeFormatter);

        // Validate time range
        if (StartTime.equals(EndTime) || EndTime.isBefore(StartTime)) {
            JOptionPane.showMessageDialog(null, 
                "The end time must be after the start time and should not be the same.",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
            correct = false;
        }

        // Declaring all the date format and value
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate today = LocalDate.now();

        // Parse start and end dates
        LocalDate StartDate = LocalDate.parse(Start_Date,dateFormat);
        LocalDate EndDate = LocalDate.parse(End_Date, dateFormat);

        // Validate date range
        if (StartDate.isBefore(today) || EndDate.isBefore(today) || EndDate.isBefore(StartDate)) {
            JOptionPane.showMessageDialog(null, 
                "Start and end dates cannot be earlier than today, and the end date cannot be earlier than the start date.", 
                "Warning", 
                JOptionPane.WARNING_MESSAGE);
            correct = false;
        }    

        try{
            ArrayList<Schedule> schedule = FileOperation.ReadSchedule("Schedule.txt");
            for (int i = 0; i < schedule.size();i++) {
                Schedule sche = schedule.get(i);
                
                if (i == indexToEdit){continue;}
                
                if (sche.getHall_Name().equals(Hall_Name) && !sche.equals(this)){
                    LocalDate existingStartDate = LocalDate.parse(sche.getStart_Date(),dateFormat);
                    LocalDate existingEndDate = LocalDate.parse(sche.getEnd_Date(),dateFormat);

                    boolean isOverlap = (StartDate.isBefore(existingEndDate) || StartDate.equals(existingEndDate))
                                        && (EndDate.isAfter(existingStartDate) || EndDate.equals(existingStartDate));

                    LocalTime existingStartTime = LocalTime.parse(sche.getStart_Time(), timeFormatter);
                    LocalTime existingEndTime = LocalTime.parse(sche.getEnd_Time(), timeFormatter);

                    // Check if the time ranges overlap
                    boolean timeOverlap = (StartTime.isBefore(existingEndTime) || StartTime.equals(existingEndTime))
                                          && (EndTime.isAfter(existingStartTime) || EndTime.equals(existingStartTime));

                    // Show warning if there is an overlap
                    if (isOverlap && timeOverlap) {
                        JOptionPane.showMessageDialog(null, 
                            "This time slot overlaps with an existing schedule. Please choose a different time slot.",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                        correct = false;
                        break; // Exit the loop if you only need to detect the first occurrence
                    }
               }
            }
        } catch (IOException ex){}
        return correct;
    }
    
}
