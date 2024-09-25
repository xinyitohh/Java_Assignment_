/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import manager.Data_Maintenance;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Class_Function_Maintenance extends Class_Function
{
    
    public boolean status = true;
    public boolean change = false;
    
    @Override
    public <T> void updateFileData(String filepath, String targetId, String newValue, ArrayList<T> arraylist) throws IOException
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) 
        {
            for (T item : arraylist) 
            {
                if (item instanceof Data_Maintenance) 
                {
                    Data_Maintenance md = (Data_Maintenance) item;
                    if (targetId.equals(md.ID)) {
                        if (md.Status.equals("Pending") || md.Status.equals("Cancelled")) {
                            if (newValue.equals("Pending") || newValue.equals("Cancelled")) {
                                // Allow interchange between Pending and Cancelled
                                md.setStatus(newValue);
                                md.setStaff(""); // Reset staff when switching between Pending and Cancelled
                            } else {
                                // Switching to any other status
                                status = false; 
                                md.setStatus(newValue);
                                
                            }
                        } else {
                            // Current status is neither Pending nor Cancelled
                            if (newValue.equals("Pending") || newValue.equals("Cancelled")) {
                                // Changing to Pending or Cancelled
                                md.setStaff(""); // Reset staff when changing to Pending or Cancelled
                                md.setStatus(newValue);
                            } else {
                                // Switching to other statuses
                                md.setStatus(newValue);
                            }
                        }

                    }
                    bw.write(md.WriteFile());
                    bw.newLine();
                }
            }
        }
    }
    //------------------------------------------------------------------------------------
    
    @Override
    public <T> void read_file_data(String filepath, ArrayList<T> data) throws FileNotFoundException, IOException 
    {

        // Using try-with-resources to ensure BufferedReader is closed after use
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] parts = line.split(";");

                if (data instanceof ArrayList<?>) 
                {

                    ArrayList<Data_Maintenance> maintenanceDataList = (ArrayList<Data_Maintenance>) data;
                    Data_Maintenance m = new Data_Maintenance(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim(), parts[4].trim());
                    maintenanceDataList.add(m);
                }
            }
        }
    }
    
    //------------------------------------------------------------------------------------
    
    @Override
    public <T> void Populate_Data_Into_Table(String filepath, ArrayList<T> data, JTable table, Interface_All_Of_Manager interface_) throws IOException
    {
        read_file_data(filepath, data);
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        Data_Maintenance maintence_data;
        
        for (T item : data)
        {
            
            if (item instanceof Data_Maintenance) 
            {
                maintence_data = (Data_Maintenance)item;

                interface_.getAfterFilterMaintenanceData().add(maintence_data);
                interface_.addDataToComboBox(maintence_data);
                model.addRow(new Object[]{maintence_data.getID(), maintence_data.getIssue(), maintence_data.getCustomer(), maintence_data.getStaff(), maintence_data.getStatus(), maintence_data.getHall()});

            }
        }
        data.clear(); 
    }
}