package manager;
import java.util.Date;
import java.util.Calendar;


class Data_Sales 
{
    String id;
    double sales;
    String hall;
    Date date;

    //------------------------------------------------------------------------------------
    
    public Data_Sales(String id, double sales, String hall, Date date)
    {
        this.id = id;
        this.sales = sales;
        this.hall = hall;
        this.date = date;
    }

    //------------------------------------------------------------------------------------
    
    public String getId()
    {
        return id;
    }

    public double getSales() 
    {
        return sales;
    }

    public String getHall() 
    {
        return hall;
    }

    public Date getDate()
    {
        return date;
    }

    public int getWeek() 
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); 
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public int getMonth() 
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public int getYear() 
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

}

