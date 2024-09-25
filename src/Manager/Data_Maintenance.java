/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import java.util.ArrayList;

/**
 *
 * @author kairuisam
 */
public class Data_Maintenance 
{
    String ID;
    String Issue;
    String Customer;
    String Staff;
    String Status;
    String Hall;

    //------------------------------------------------------------------------------------
    
    public Data_Maintenance(String ID, String Issue, String Customer, String Staff, String Status,String Hall)
    {
        this.ID = ID;
        this.Issue = Issue;
        this.Customer = Customer;
        this.Staff = Staff;
        this.Status = Status;
        this.Hall = Hall;
    }
    
    //------------------------------------------------------------------------------------

    public void setHall(String Hall) 
    {
        this.Hall = Hall;
    }
    
    public void setID(String ID) 
    {
        this.ID = ID;
    }

    public void setIssue(String Issue) 
    {
        this.Issue = Issue;
    }

    public void setCustomer(String Customer) 
    {
        this.Customer = Customer;
    }

    public void setStaff(String Staff) 
    {
        this.Staff = Staff;
    }

    public void setStatus(String Status)
    {
        this.Status = Status;
    }

    public String getID() 
    {
        return ID;
    }

    public String getIssue() 
    {
        return Issue;
    }

    public String getCustomer() 
    {
        return Customer;
    }

    public String getStaff() 
    {
        return Staff;
    }

    public String getStatus()
    {
        return Status;
    }
    
    public String getHall() 
    {
        return Hall;
    }
   
        
    public String WriteFile() 
    {
        String line = ID + ";" + Issue + ";" + Customer + ";" + Staff + ";" + Status + ";" + Hall;
        return line;
    }

   
}
