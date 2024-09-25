/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package manager;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kairuisam
 */
public interface Interface_All_Of_Manager
{
    void addDataToComboBox(Data_Maintenance maintance_data);
    void addDataToComboBox(Date date);
    void Change_Label_Text(int x, double i);
    void addDataToComboBox(Data_Sales sales_data);
    ArrayList<Data_Sales> getAfterFilterSalesData();
    ArrayList<Data_Maintenance> getAfterFilterMaintenanceData();
}
