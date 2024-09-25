package Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class Generate_DashBoard_Chart extends Generate_Chart_Class<Data_Sales> 
{
    public Generate_DashBoard_Chart(String model, int choosen, ArrayList<Data_Sales> sales_data, JPanel panel)       
    {
        super(model,choosen,sales_data,panel);
    }
    
    //------------------------------------------------------------------------------------

    @Override
    public void Generate_Chart(String time) 
    {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
            Map<Integer, Double> timeValueSalaries = new HashMap<>();
            
            for (Data_Sales s : data) {
                int timeValue;
                if (time.equalsIgnoreCase("week")) 
                {
                    timeValue = s.getWeek();
                } 
                else if (time.equalsIgnoreCase("month"))
                {
                    timeValue = s.getMonth();
                } 
                else if (time.equalsIgnoreCase("year"))
                {
                    timeValue = s.getYear();
                } 
                else 
                {
                    throw new IllegalArgumentException("Invalid time unit: " + time);
                }

                double salary = s.getSales();
                timeValueSalaries.put(timeValue, timeValueSalaries.getOrDefault(timeValue, 0.0) + salary);
            }

            for (Map.Entry<Integer, Double> entry : timeValueSalaries.entrySet()) {
                int timeValue = entry.getKey();
                double totalSalary = entry.getValue();
                String label = time + " " + timeValue + ": $" + totalSalary;
                dataset.setValue(label, totalSalary);
            }

            JFreeChart pieChart = ChartFactory.createPieChart(
                "Sales Pie Chart",
                dataset,
                true,
                true,
                false
            );

            generate_frame_into_JPanel(pieChart,panel);

    }
}