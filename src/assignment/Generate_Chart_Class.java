package assignment;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public abstract class Generate_Chart_Class<T> 
{
    protected ArrayList<T> data;
    protected String model;
    protected JPanel panel;
    
    //------------------------------------------------------------------------------------
    
    public Generate_Chart_Class(String model, int choosen, ArrayList<T> data,JPanel panel) 
    {
        this.model = model;
        this.data = data;
        this.panel = panel;
        switch (model) 
        {
            case "DashBoard":
                handleDashboardChoice(choosen);
                break;
            case "Operation":
                handleOperationChoice();
                break;
            default:
                throw new IllegalArgumentException("Invalid model: " + model);
        }
    }

    //------------------------------------------------------------------------------------
    
    private void handleDashboardChoice(int choosen) 
    {
        switch (choosen)
        {
            case 1:
                Generate_Chart("week");
                break;
            case 2:
                Generate_Chart("month");
                break;
            case 3:
                Generate_Chart("year");
                break;
            default:
                throw new IllegalArgumentException("Invalid choice: " + choosen);
        }
    }

    //------------------------------------------------------------------------------------
    
    private void handleOperationChoice() 
    {
        Generate_Chart("");
    }

    //------------------------------------------------------------------------------------
    
    public abstract void Generate_Chart(String optionalParam);
    
    //------------------------------------------------------------------------------------
    
    public void generate_frame_into_JPanel(JFreeChart pieChart, JPanel panel) 
    {

        ChartPanel chartPanel = new ChartPanel(pieChart);
        panel.setLayout(new BorderLayout());
        panel.add(chartPanel, BorderLayout.CENTER);
        
    }
}