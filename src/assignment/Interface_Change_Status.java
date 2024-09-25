/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package assignment;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kairuisam
 */
public interface Interface_Change_Status extends Interface_All_Of_Manager 
{
    void addDataToComboBox(Data_Maintenance maintance_data);

    //------------------------------------------------------------------------------------
    
    default public ArrayList<Data_Maintenance> getAfterFilterMaintenanceData()
    {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    default public ArrayList<Data_Sales> getAfterFilterSalesData()
    {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    default public void addDataToComboBox(Date date)
    {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    default public void Change_Label_Text(int x, double i)
    {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    default public void addDataToComboBox(Data_Sales sales_data)
    {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}


