/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Manager;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kairuisam
 */
interface Interface_Maintenance extends Interface_All_Of_Manager {
    
    void addDataToComboBox(Data_Maintenance maintance_data);
    ArrayList<Data_Maintenance> getAfterFilterMaintenanceData();
    
    //------------------------------------------------------------------------------------

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
