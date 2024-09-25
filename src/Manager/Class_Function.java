/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Class_Function 
{
    
    public abstract <T> void updateFileData(String filepath, String targetId, String newValue, ArrayList<T> arraylist) throws IOException;

    //------------------------------------------------------------------------------------
    
    public abstract <T> void read_file_data(String filepath, ArrayList<T> data) throws FileNotFoundException, IOException;

    //------------------------------------------------------------------------------------
    
    public abstract <T> void Populate_Data_Into_Table(String filepath, ArrayList<T> data, JTable table, Interface_All_Of_Manager interface_) throws IOException;
    
    //------------------------------------------------------------------------------------
    
    public static void sortAllComboBoxes(int chosen,JComboBox<String>... comboBoxes) 
    {
        for (JComboBox<String> comboBox : comboBoxes)
        {
            ArrayList<String> items = new ArrayList<>();
            for (int i = 0; i < comboBox.getItemCount(); i++) 
            {
                String item = comboBox.getItemAt(i);
                if (!"ALL".equals(item))
                { 
                    items.add(item);
                }
            }
            switch(chosen)
            {
                case 1->//lambda expressions
                    Collections.sort(items, (s1, s2) -> Integer.compare(Integer.parseInt(s1), Integer.parseInt(s2)));
                case 2 ->//lambda expressions
                    Collections.sort(items, String::compareTo);
            }
            
            
            comboBox.removeAllItems();
            comboBox.addItem("ALL");

            for (String item : items) 
            {
                comboBox.addItem(item);
            }
        }
    }
    
    //------------------------------------------------------------------------------------
    
    public static void Change_Frame(JFrame currentFrame, JFrame newFrame) 
    {
        currentFrame.dispose();
        try 
        {

            newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newFrame.setVisible(true);
            newFrame.setLocationRelativeTo(null); 
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace(); 
        }
    }
}
