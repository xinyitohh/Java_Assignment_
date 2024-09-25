/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kairuisam
 */
public class Class_Function_Assign extends Class_Function 
{
    
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
                    if (targetId.equals(md.ID)) 
                    {
                        md.setStaff(newValue);
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
        
        Data_Maintenance md;
        
        for (T item : data)
        {
            
            if (item instanceof Data_Maintenance) 
            {
                md = (Data_Maintenance)item;
                if(md.getStaff()=="")
                {
                    interface_.addDataToComboBox(md);
                    model.addRow(new Object[]{md.getID(), md.getIssue(), md.getStatus(), md.getHall(), md.getStaff()});
                }
            }
        }
        data.clear(); 
    }
    
    //------------------------------------------------------------------------------------
    
    public void read_staff_data(String filepath, ArrayList<String> data, JComboBox<String> comboBox) throws FileNotFoundException, IOException 
    {

        try (BufferedReader br = new BufferedReader(new FileReader(filepath)))
        {
            String line;
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
            
            while ((line = br.readLine()) != null)
            {
                String[] parts = line.split(";");
                
                if (parts.length > 0)
                {
                    String staff_id = parts[0];
                    data.add(staff_id);
                    if (model.getIndexOf(staff_id) == -1) 
                    {
                        model.addElement(staff_id);
                    }
                }
            }
        }
    }
}
