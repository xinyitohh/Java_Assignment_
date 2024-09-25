/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package manager;
import java.util.ArrayList;
import java.util.Date;


public interface Interface_DashBoard extends Interface_All_Of_Manager 
{
    void addDataToComboBox(Date date);
    void Change_Label_Text(int x, double i);
    ArrayList<Data_Sales> getAfterFilterSalesData();
    public void addDataToComboBox(Data_Sales sales_data);
    
    //------------------------------------------------------------------------------------

    default public void addDataToComboBox(Data_Maintenance maintance_data) 
    {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    default public ArrayList<Data_Maintenance> getAfterFilterMaintenanceData()
    {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
