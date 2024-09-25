
package Manager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kairuisam
 */
public class Class_Function_Dashboard extends Class_Function
{
    
     @Override
    public <T> void updateFileData(String filepath, String targetId, String newValue, ArrayList<T> arraylist) throws IOException{}
    
    //------------------------------------------------------------------------------------

           
    @Override
    public <T> void read_file_data(String filepath, ArrayList<T> data) throws FileNotFoundException, IOException 
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Using try-with-resources to ensure BufferedReader is closed after use
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] parts = line.split(";");
                try 
                {
                    if (data instanceof ArrayList<?>) 
                    {

                        Date date = dateFormat.parse(parts[7].trim());

                        // 将 Date 对象格式化为目标字符串格式
                        String formattedDateStr = targetFormat.format(date);

                        // 再次将格式化后的字符串解析回 Date 对象
                        Date finalDate = targetFormat.parse(formattedDateStr);

                        ArrayList<Data_Sales> salesDataList = (ArrayList<Data_Sales>) data;
                        double sales = Double.parseDouble(parts[6]);
                        Data_Sales s = new Data_Sales(parts[0].trim(), sales, parts[2].trim(), finalDate);
                        salesDataList.add(s);
                    }
                } 
                catch (ParseException e) 
                {
                    System.out.println("Date parsing failed for: " + parts[3] + ". Error: " + e.getMessage());
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

        ArrayList<String> people = new ArrayList<>();
        double sales_count = 0;
        
        for (T item : data)
        {
           
            if (item instanceof Data_Sales) 
            {
                Data_Sales s = (Data_Sales) item;

                interface_.getAfterFilterSalesData().add(s);
                interface_.addDataToComboBox(s.getDate());
                interface_.addDataToComboBox(s);

                if (!people.contains(s.getHall()))
                {
                    people.add(s.getHall());
                }

                sales_count += s.getSales();
                model.addRow(new Object[]{s.getId(), s.getSales(), s.getHall(), new SimpleDateFormat("yyyy-MM-dd").format(s.getDate())});

            }
            interface_.Change_Label_Text(people.size(), sales_count); 
        }
        data.clear();
    }
}
