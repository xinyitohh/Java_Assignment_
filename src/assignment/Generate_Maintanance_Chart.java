
package assignment;


import assignment.Data_Maintenance;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.JPanel; 

public class Generate_Maintanance_Chart extends Generate_Chart_Class<Data_Maintenance> 
{
    public Generate_Maintanance_Chart(String model, int choosen, ArrayList<Data_Maintenance> maintenance_data, JPanel panel)
    {
        super(model, choosen, maintenance_data, panel);
    }

    //------------------------------------------------------------------------------------
    
    @Override
    public void Generate_Chart(String string) 
    {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Map<String, Integer> howMuchStatus = new HashMap<>();

        for (Data_Maintenance m : data) 
        {
            String status;
            status = m.getStatus();
            howMuchStatus.put(status, howMuchStatus.getOrDefault(status, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : howMuchStatus.entrySet()) {
            String status = entry.getKey();
            int quantity = entry.getValue();
            String label = status+ ": " + quantity;
            dataset.setValue(label, quantity);
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
            "Maintenance Status Pie Chart",
            dataset,
            true,
            true,
            false
        );

        generate_frame_into_JPanel(pieChart, panel);

    }
}