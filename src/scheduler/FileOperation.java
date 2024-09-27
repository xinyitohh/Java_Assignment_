package scheduler;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yeo yu le
 */
import java.io.*;
import java.util.ArrayList;
import manager.Data_Maintenance;
import admin.User;

public class FileOperation {
    private User currentUser = User.getLoggedInUser();
    
    public static <T> void FileWriting(String fname, ArrayList<T> genericArray){
        try{
            File file = new File(fname);
            if ( ! file.exists()) { 
                file.createNewFile(); 
                }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for (T lines : genericArray) {
            String line = lines.toString();
            bw.write(line + "\n");
        }
        bw.close();
        System.out.println("Document successfully write");
        }catch (IOException EX)
        {}
    }
    
    public static ArrayList<Hall> ReadHall(String fname) throws IOException{
        ArrayList<Hall> store = new ArrayList<>();
        File file = new File(fname);
        if (!file.exists()){
            file.createNewFile();
        }
        
        BufferedReader reader = new BufferedReader(new FileReader(fname));
        String line;
        
        while ((line = reader.readLine()) != null){
            String[] part = line.split(";");
            
            if (part.length == 4){
                String type = part[0];
                String name = part[1];
                String capacity = part[2];
                String equipment = part[3];
                
                store.add(new Hall(type,name,capacity,equipment));
            }
        }
        reader.close();
        return store;
    }
    
    public static ArrayList<Schedule> ReadSchedule(String fname) throws IOException{
        ArrayList<Schedule> store = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fname));
        String line;
        
        while ((line = reader.readLine()) != null){
            String[] part = line.split(";");
            
            if (part.length == 7){
                String ScheduleType = part[0];
                String HallName = part[1];
                String StartDate = part[2];
                String StartTime = part[3];
                String EndDate = part[4];
                String EndTime = part[5];
                String remark = part[6];

                store.add(new Schedule(ScheduleType, HallName, StartDate, StartTime, EndDate, EndTime, remark));
            }
        }
        reader.close();
        return store;
    }
    
    public ArrayList<Data_Maintenance> ReadMaintenance(String fname) throws IOException{
        ArrayList<Data_Maintenance> issueData = new ArrayList<>();
        File file = new File(fname);      
        BufferedReader reader = new BufferedReader(new FileReader(fname));
        String line;

        while ((line = reader.readLine()) != null){
            String[] part = line.split(";");

            if (part.length == 6){
                String id = part[0];
                String issue = part[1];
                String customer = part[2];
                String staff = part[3];
                String status = part[4];
                String hall = part[5];

                issueData.add(new Data_Maintenance(id,issue,customer,staff,status,hall));
            }
        }    
        reader.close();
        return issueData;
    }
    
}
