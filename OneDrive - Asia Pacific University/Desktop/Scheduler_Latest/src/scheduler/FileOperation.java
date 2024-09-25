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

public class FileOperation {
    
    public void FileWriting(String fname, ArrayList<String> a){
        try{
            File Finput = new File(fname);
            FileWriter fw = new FileWriter(fname, true);
            PrintWriter pw = new PrintWriter(fw);
           for (String line : a) {
                pw.println(line); // Write each line from the ArrayList to the file
            }
            pw.close();
            System.out.println("Document successfully write");
        }
        catch (IOException EX)
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
    
    public static void DeleteFile(String fname){
        File f = new File(fname);
        f.delete();
        System.out.println("File Deleted");
    }
    
}
