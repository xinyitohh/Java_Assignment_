
package manager;

import admin.User;
import admin.Frame_Login;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Frame_Sales_Dashboard extends javax.swing.JFrame implements Interface_DashBoard
{
    private User currentUser = User.getLoggedInUser();
    public ArrayList<Data_Sales> sales_data = new ArrayList<>();
    public ArrayList<Data_Sales> after_filter_sales_data = new ArrayList<>(); 
    String filePath = "TXT_DATA/sam.txt";;
    Class_Function_Dashboard Function = new Class_Function_Dashboard();
    
   //------------------------------------------------------------------------------------
    
    public void create_chart()
    {
         for (int i = 1; i <= 3; i++) 
         {
            JPanel panel;
            switch (i) 
            {
                case 1 -> panel = JPanel_1;
                case 2 -> panel = JPanel_2;
                case 3 -> panel = JPanel_3;
                default-> throw new IllegalArgumentException("Invalid panel index: " + i);
            }
            
            Generate_DashBoard_Chart c = new Generate_DashBoard_Chart("DashBoard", i, after_filter_sales_data, panel);
        }
    }
    
    //------------------------------------------------------------------------------------
    
    @Override
    public ArrayList<Data_Sales> getAfterFilterSalesData() 
    {
        return after_filter_sales_data; 
    }
    
    //------------------------------------------------------------------------------------
    
    @Override
    public void addDataToComboBox(Date date)
    {
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
            
        String week = cal.get(Calendar.WEEK_OF_YEAR)+"";

        if (((DefaultComboBoxModel<String>) J_Weekly_CB.getModel()).getIndexOf(week) == -1) {
            J_Weekly_CB.addItem(week);
        }
        
        String month = (cal.get(Calendar.MONTH) + 1)+"";

        if (((DefaultComboBoxModel<String>) J_Monthly_CB.getModel()).getIndexOf(month) == -1) {
                J_Monthly_CB.addItem(month);
        }
        
        String year = cal.get(Calendar.YEAR)+"";

        if (((DefaultComboBoxModel<String>) J_Yearly_CB.getModel()).getIndexOf(year) == -1) {
            J_Yearly_CB.addItem(year);
        }

    }
    
    //------------------------------------------------------------------------------------
    
    @Override
    public void addDataToComboBox(Data_Sales sales_data)
    {
        DefaultComboBoxModel<String> model =  (DefaultComboBoxModel<String>) J_Hall_CB.getModel();
        if (model.getIndexOf(sales_data.getHall()) == -1) 
        {
            model.addElement(sales_data.getHall());
        }
    }

    
    //------------------------------------------------------------------------------------
    
    @Override
    public void Change_Label_Text(int x,double i)
    {
        J_L_4.setText(Integer.toString(x));
        J_L_6.setText(Double.toString(i));
        J_L_2.setText(Integer.toString(t_1.getRowCount()));
    }
    
    //------------------------------------------------------------------------------------
    
    public Frame_Sales_Dashboard() throws IOException 
    {
        initComponents();
        Function.Populate_Data_Into_Table(filePath,sales_data,t_1,this);
        Function.sortAllComboBoxes(1,J_Weekly_CB, J_Monthly_CB, J_Yearly_CB);
        Function.sortAllComboBoxes(2,J_Hall_CB);
        create_chart();
    }
    
    //------------------------------------------------------------------------------------ 
    
    private int calculateTotalSales() 
    {
        int totalSales = 0;
        for (Data_Sales s : after_filter_sales_data) 
        {
            totalSales += s.getSales();
        }
        return totalSales;
    }
    
    //------------------------------------------------------------------------------------  

    public void fitler_function() throws IOException 
    {
        after_filter_sales_data.clear();

        String selectedWeek = (String) J_Weekly_CB.getSelectedItem();
        String selectedMonth = (String) J_Monthly_CB.getSelectedItem();
        String selectedYear = (String) J_Yearly_CB.getSelectedItem();
        String selectedHall = (String) J_Hall_CB.getSelectedItem();

        DefaultTableModel model = (DefaultTableModel) t_1.getModel();
        model.setRowCount(0);

        ArrayList<String> count_hall = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        J_Weekly_CB.removeAllItems();
        J_Monthly_CB.removeAllItems();
        J_Yearly_CB.removeAllItems();
        J_Hall_CB.removeAllItems();

        Function.read_file_data(filePath, sales_data);
        
        for (Data_Sales s : sales_data) 
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(s.getDate());
            String week = String.valueOf(cal.get(Calendar.WEEK_OF_YEAR));
            String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
            String year = String.valueOf(cal.get(Calendar.YEAR));
            String hall = s.getHall();

            if (matchesSelection(selectedWeek, week) && matchesSelection(selectedMonth, month) && matchesSelection(selectedYear, year) && matchesSelection(selectedHall, hall))
            {
                addHallIfUnique(count_hall, s.getHall());
                after_filter_sales_data.add(s);
                model.addRow(new Object[]{s.getId(), s.getSales(), s.getHall(), dateFormat.format(s.getDate())});
            }
            addDataToComboBox(s);
            addDataToComboBox(s.getDate());
            
        }

        Change_Label_Text(count_hall.size(), calculateTotalSales());
        sales_data.clear();
        Function.sortAllComboBoxes(1,J_Weekly_CB, J_Monthly_CB, J_Yearly_CB);
        Function.sortAllComboBoxes(2,J_Hall_CB);
        create_chart();
    }

    //------------------------------------------------------------------------------------
    
    private boolean matchesSelection(String selected, String actual)
    {
        return selected.equals("ALL") || actual.equals(selected);
    }
    
    //------------------------------------------------------------------------------------
    
    private void addHallIfUnique(ArrayList<String> count_hall, String hall)   
    {
        if (!count_hall.contains(hall)) 
        {
            count_hall.add(hall);
        }
    }
    
    
    //------------------------------------------------------------------------------------
    
    public void reset() throws IOException
            
    {
        J_Weekly_CB.removeAllItems();
        J_Monthly_CB.removeAllItems();
        J_Yearly_CB.removeAllItems();
        J_Hall_CB.removeAllItems();
        after_filter_sales_data.clear();
        
        Function.Populate_Data_Into_Table(filePath,sales_data,t_1,this);
        Function.sortAllComboBoxes(1,J_Weekly_CB, J_Monthly_CB, J_Yearly_CB);
        Function.sortAllComboBoxes(2,J_Hall_CB);
        create_chart();
    }
    
    //------------------------------------------------------------------------------------
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        t_1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        J_L_4 = new javax.swing.JLabel();
        J_L_5 = new javax.swing.JLabel();
        J_L_6 = new javax.swing.JLabel();
        J_L_1 = new javax.swing.JLabel();
        J_L_3 = new javax.swing.JLabel();
        J_L_2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        J_L_18 = new javax.swing.JLabel();
        J_L_19 = new javax.swing.JLabel();
        J_L_20 = new javax.swing.JLabel();
        J_L_21 = new javax.swing.JLabel();
        J_L_22 = new javax.swing.JLabel();
        J_L_23 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        J_L_24 = new javax.swing.JLabel();
        J_L_25 = new javax.swing.JLabel();
        J_L_26 = new javax.swing.JLabel();
        J_L_27 = new javax.swing.JLabel();
        J_L_28 = new javax.swing.JLabel();
        J_L_29 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        J_L_30 = new javax.swing.JLabel();
        J_L_31 = new javax.swing.JLabel();
        J_L_32 = new javax.swing.JLabel();
        J_L_33 = new javax.swing.JLabel();
        J_L_34 = new javax.swing.JLabel();
        J_L_35 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        J_L_12 = new javax.swing.JLabel();
        J_L_13 = new javax.swing.JLabel();
        J_L_14 = new javax.swing.JLabel();
        J_L_17 = new javax.swing.JLabel();
        J_L_16 = new javax.swing.JLabel();
        J_L_15 = new javax.swing.JLabel();
        JPanel_3 = new javax.swing.JPanel();
        JPanel_2 = new javax.swing.JPanel();
        JPanel_1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        J_Weekly_CB = new javax.swing.JComboBox<>();
        J_Monthly_CB = new javax.swing.JComboBox<>();
        J_Yearly_CB = new javax.swing.JComboBox<>();
        J_L_7 = new javax.swing.JLabel();
        J_L_8 = new javax.swing.JLabel();
        J_L_9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        Btn_Fitler = new javax.swing.JButton();
        Btn_Reset = new javax.swing.JButton();
        J_Hall_CB = new javax.swing.JComboBox<>();
        J_L_10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        Btn_MO = new javax.swing.JButton();
        Btn_Main = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        t_1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        t_1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "SALES", "HALL", "DATE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        t_1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                t_1ComponentAdded(evt);
            }
        });
        t_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_1);
        if (t_1.getColumnModel().getColumnCount() > 0) {
            t_1.getColumnModel().getColumn(0).setResizable(false);
            t_1.getColumnModel().getColumn(1).setResizable(false);
            t_1.getColumnModel().getColumn(2).setResizable(false);
            t_1.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 870, 310));

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        J_L_4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        J_L_4.setForeground(new java.awt.Color(0, 51, 51));
        J_L_4.setText("jLabel1:");
        jPanel2.add(J_L_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 10, 60, 40));

        J_L_5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        J_L_5.setForeground(new java.awt.Color(0, 51, 51));
        J_L_5.setText("TOTAL SALES:");
        jPanel2.add(J_L_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 100, 40));

        J_L_6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        J_L_6.setForeground(new java.awt.Color(0, 51, 51));
        J_L_6.setText("jLabel1:");
        jPanel2.add(J_L_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 120, 40));

        J_L_1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        J_L_1.setForeground(new java.awt.Color(0, 51, 51));
        J_L_1.setText("ROW：");
        jPanel2.add(J_L_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 90, 40));

        J_L_3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        J_L_3.setForeground(new java.awt.Color(0, 51, 51));
        J_L_3.setText("HALL COUNT：");
        jPanel2.add(J_L_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 120, 40));

        J_L_2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        J_L_2.setForeground(new java.awt.Color(0, 51, 51));
        J_L_2.setText("jLabel1:");
        jPanel2.add(J_L_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 10, 60, 40));

        jPanel3.setBackground(new java.awt.Color(153, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        J_L_18.setForeground(new java.awt.Color(0, 51, 51));
        J_L_18.setText("jLabel1:");
        jPanel3.add(J_L_18, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 65, 40));

        J_L_19.setForeground(new java.awt.Color(0, 51, 51));
        J_L_19.setText("TOTAL SALES:");
        jPanel3.add(J_L_19, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 90, 40));

        J_L_20.setForeground(new java.awt.Color(0, 51, 51));
        J_L_20.setText("jLabel1:");
        jPanel3.add(J_L_20, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 65, 40));

        J_L_21.setForeground(new java.awt.Color(0, 51, 51));
        J_L_21.setText("ROW：");
        jPanel3.add(J_L_21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 40));

        J_L_22.setForeground(new java.awt.Color(0, 51, 51));
        J_L_22.setText("PEOPLE COUNT：");
        jPanel3.add(J_L_22, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 110, 40));

        J_L_23.setForeground(new java.awt.Color(0, 51, 51));
        J_L_23.setText("jLabel1:");
        jPanel3.add(J_L_23, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 65, 40));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 730, 60));

        jPanel4.setBackground(new java.awt.Color(153, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        J_L_24.setForeground(new java.awt.Color(0, 51, 51));
        J_L_24.setText("jLabel1:");
        jPanel4.add(J_L_24, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 65, 40));

        J_L_25.setForeground(new java.awt.Color(0, 51, 51));
        J_L_25.setText("TOTAL SALES:");
        jPanel4.add(J_L_25, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 90, 40));

        J_L_26.setForeground(new java.awt.Color(0, 51, 51));
        J_L_26.setText("jLabel1:");
        jPanel4.add(J_L_26, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 65, 40));

        J_L_27.setForeground(new java.awt.Color(0, 51, 51));
        J_L_27.setText("ROW：");
        jPanel4.add(J_L_27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 40));

        J_L_28.setForeground(new java.awt.Color(0, 51, 51));
        J_L_28.setText("PEOPLE COUNT：");
        jPanel4.add(J_L_28, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 110, 40));

        J_L_29.setForeground(new java.awt.Color(0, 51, 51));
        J_L_29.setText("jLabel1:");
        jPanel4.add(J_L_29, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 65, 40));

        jPanel5.setBackground(new java.awt.Color(153, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        J_L_30.setForeground(new java.awt.Color(0, 51, 51));
        J_L_30.setText("jLabel1:");
        jPanel5.add(J_L_30, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 65, 40));

        J_L_31.setForeground(new java.awt.Color(0, 51, 51));
        J_L_31.setText("TOTAL SALES:");
        jPanel5.add(J_L_31, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 90, 40));

        J_L_32.setForeground(new java.awt.Color(0, 51, 51));
        J_L_32.setText("jLabel1:");
        jPanel5.add(J_L_32, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 65, 40));

        J_L_33.setForeground(new java.awt.Color(0, 51, 51));
        J_L_33.setText("ROW：");
        jPanel5.add(J_L_33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 40));

        J_L_34.setForeground(new java.awt.Color(0, 51, 51));
        J_L_34.setText("PEOPLE COUNT：");
        jPanel5.add(J_L_34, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 110, 40));

        J_L_35.setForeground(new java.awt.Color(0, 51, 51));
        J_L_35.setText("jLabel1:");
        jPanel5.add(J_L_35, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 65, 40));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 730, 60));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 730, 60));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 710, 870, 60));

        jPanel6.setBackground(new java.awt.Color(255, 153, 153));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        J_L_12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        J_L_12.setForeground(new java.awt.Color(0, 51, 51));
        J_L_12.setText("ID：");
        jPanel6.add(J_L_12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 80, 40));

        J_L_13.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        J_L_13.setForeground(new java.awt.Color(0, 51, 51));
        jPanel6.add(J_L_13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 65, 40));

        J_L_14.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        J_L_14.setForeground(new java.awt.Color(0, 51, 51));
        J_L_14.setText("SALES：");
        jPanel6.add(J_L_14, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 70, 40));

        J_L_17.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        J_L_17.setForeground(new java.awt.Color(0, 51, 51));
        jPanel6.add(J_L_17, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 110, 40));

        J_L_16.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        J_L_16.setForeground(new java.awt.Color(0, 51, 51));
        J_L_16.setText("HALL ：");
        jPanel6.add(J_L_16, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 60, 40));

        J_L_15.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        J_L_15.setForeground(new java.awt.Color(0, 51, 51));
        jPanel6.add(J_L_15, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 130, 40));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 770, 870, 60));

        JPanel_3.setBackground(new java.awt.Color(102, 102, 255));
        JPanel_3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(JPanel_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, 400, 360));

        JPanel_2.setBackground(new java.awt.Color(102, 102, 255));
        JPanel_2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(JPanel_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 430, 360));

        JPanel_1.setBackground(new java.awt.Color(102, 102, 255));
        JPanel_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPanel_1MouseClicked(evt);
            }
        });
        JPanel_1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(JPanel_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 430, 360));

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        J_Weekly_CB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        J_Weekly_CB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J_Weekly_CBActionPerformed(evt);
            }
        });
        jPanel9.add(J_Weekly_CB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 180, 50));

        J_Monthly_CB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jPanel9.add(J_Monthly_CB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 180, 50));

        J_Yearly_CB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jPanel9.add(J_Yearly_CB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 180, 50));

        J_L_7.setBackground(new java.awt.Color(255, 255, 255));
        J_L_7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        J_L_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J_L_7.setText("WEEK");
        jPanel9.add(J_L_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 80, 40));

        J_L_8.setBackground(new java.awt.Color(255, 255, 255));
        J_L_8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        J_L_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J_L_8.setText("MONTH");
        jPanel9.add(J_L_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 80, 40));

        J_L_9.setBackground(new java.awt.Color(255, 255, 255));
        J_L_9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        J_L_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J_L_9.setText("YEAR");
        jPanel9.add(J_L_9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 80, 40));

        jPanel7.setBackground(new java.awt.Color(255, 204, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Btn_Fitler.setForeground(new java.awt.Color(51, 51, 0));
        Btn_Fitler.setText("Fitler");
        Btn_Fitler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_FitlerMouseClicked(evt);
            }
        });
        Btn_Fitler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_FitlerActionPerformed(evt);
            }
        });
        jPanel7.add(Btn_Fitler, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 140, 50));

        Btn_Reset.setForeground(new java.awt.Color(51, 51, 0));
        Btn_Reset.setText("RESET");
        Btn_Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ResetActionPerformed(evt);
            }
        });
        jPanel7.add(Btn_Reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 140, 50));

        jPanel9.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 180, 440));

        J_Hall_CB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jPanel9.add(J_Hall_CB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 180, 50));

        J_L_10.setBackground(new java.awt.Color(255, 255, 255));
        J_L_10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        J_L_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J_L_10.setText("HALL");
        jPanel9.add(J_L_10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 80, 40));

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 390, 400, 440));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 120, 40));

        Btn_MO.setText("Maintenance");
        Btn_MO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_MOActionPerformed(evt);
            }
        });
        jPanel1.add(Btn_MO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 120, 50));

        Btn_Main.setText("Main");
        Btn_Main.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_MainActionPerformed(evt);
            }
        });
        jPanel1.add(Btn_Main, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 120, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 840));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void t_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_1MouseClicked
        int row = t_1.rowAtPoint(evt.getPoint());
        if (row >= 0)
        {
            
            J_L_13.setText(t_1.getValueAt(row, 0).toString()); 

            J_L_15.setText(t_1.getValueAt(row, 2).toString()); 

            J_L_17.setText(t_1.getValueAt(row, 1).toString());
         
            Object selectedValue = t_1.getValueAt(row, 2);
            if (selectedValue != null)
            {
                J_Hall_CB.setSelectedItem(selectedValue.toString());  
            }
        }
        
    }//GEN-LAST:event_t_1MouseClicked

    private void t_1ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_t_1ComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_t_1ComponentAdded

    private void Btn_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ResetActionPerformed
        try 
        {
            reset();
        } 
        catch (IOException ex)
        {
            Logger.getLogger(Frame_Sales_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_Btn_ResetActionPerformed

    private void Btn_FitlerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_FitlerActionPerformed
        try 
        {
            // TODO add your handling code here:
            fitler_function();
        } 
        catch (IOException ex)
        {
            Logger.getLogger(Frame_Sales_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_Btn_FitlerActionPerformed

    private void J_Weekly_CBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J_Weekly_CBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_J_Weekly_CBActionPerformed

    private void JPanel_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPanel_1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JPanel_1MouseClicked

    private void Btn_FitlerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_FitlerMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_FitlerMouseClicked

    private void Btn_MOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_MOActionPerformed
        try 
        {
            Function.Change_Frame(this,new Frame_Maintenance_Operation());
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Frame_Sales_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_MOActionPerformed

    private void Btn_MainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_MainActionPerformed
        Function.Change_Frame(this,new Frame_Main());   
    }//GEN-LAST:event_Btn_MainActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                int response = JOptionPane.showConfirmDialog(this, 
                        "Are you sure to logout?", 
                        "Confirm", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                
                if (response == JOptionPane.YES_OPTION) 
                {
                    currentUser.logout();
                    this.dispose();
                    Frame_Login login = new Frame_Login();
                    login.setVisible(true);
                }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame_Sales_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame_Sales_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame_Sales_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame_Sales_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new Frame_Sales_Dashboard();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null); 
                } catch (IOException ex) {
                    Logger.getLogger(Frame_Sales_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Fitler;
    private javax.swing.JButton Btn_MO;
    private javax.swing.JButton Btn_Main;
    private javax.swing.JButton Btn_Reset;
    private javax.swing.JPanel JPanel_1;
    private javax.swing.JPanel JPanel_2;
    private javax.swing.JPanel JPanel_3;
    private javax.swing.JComboBox<String> J_Hall_CB;
    private javax.swing.JLabel J_L_1;
    private javax.swing.JLabel J_L_10;
    private javax.swing.JLabel J_L_12;
    private javax.swing.JLabel J_L_13;
    private javax.swing.JLabel J_L_14;
    private javax.swing.JLabel J_L_15;
    private javax.swing.JLabel J_L_16;
    private javax.swing.JLabel J_L_17;
    private javax.swing.JLabel J_L_18;
    private javax.swing.JLabel J_L_19;
    private javax.swing.JLabel J_L_2;
    private javax.swing.JLabel J_L_20;
    private javax.swing.JLabel J_L_21;
    private javax.swing.JLabel J_L_22;
    private javax.swing.JLabel J_L_23;
    private javax.swing.JLabel J_L_24;
    private javax.swing.JLabel J_L_25;
    private javax.swing.JLabel J_L_26;
    private javax.swing.JLabel J_L_27;
    private javax.swing.JLabel J_L_28;
    private javax.swing.JLabel J_L_29;
    private javax.swing.JLabel J_L_3;
    private javax.swing.JLabel J_L_30;
    private javax.swing.JLabel J_L_31;
    private javax.swing.JLabel J_L_32;
    private javax.swing.JLabel J_L_33;
    private javax.swing.JLabel J_L_34;
    private javax.swing.JLabel J_L_35;
    private javax.swing.JLabel J_L_4;
    private javax.swing.JLabel J_L_5;
    private javax.swing.JLabel J_L_6;
    private javax.swing.JLabel J_L_7;
    private javax.swing.JLabel J_L_8;
    private javax.swing.JLabel J_L_9;
    private javax.swing.JComboBox<String> J_Monthly_CB;
    private javax.swing.JComboBox<String> J_Weekly_CB;
    private javax.swing.JComboBox<String> J_Yearly_CB;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_1;
    // End of variables declaration//GEN-END:variables

}
