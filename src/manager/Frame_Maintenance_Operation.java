/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package manager;

import admin.User;
import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Frame_Maintenance_Operation extends javax.swing.JFrame implements Interface_Maintenance
{

    public ArrayList<Data_Maintenance> maintance_data = new ArrayList<>(); 
    public ArrayList<Data_Maintenance> after_filter_maintance_data = new ArrayList<>(); 
    String filePath = "issue.txt";
    Class_Function_Maintenance Function = new Class_Function_Maintenance();
    private User currentUser = User.getLoggedInUser();
    
    
    //------------------------------------------------------------------------------------
    
    @Override
    public void addDataToComboBox(Data_Maintenance maintance_data) 
    {

        DefaultComboBoxModel<String> modelC = (DefaultComboBoxModel<String>) CB_C.getModel();
        if (modelC.getIndexOf(maintance_data.getCustomer()) == -1) {
            modelC.addElement(maintance_data.getCustomer());
        }


        DefaultComboBoxModel<String> modelS = (DefaultComboBoxModel<String>) CB_S.getModel();
        if (modelS.getIndexOf(maintance_data.getStaff()) == -1) {
            modelS.addElement(maintance_data.getStaff());
        }


        DefaultComboBoxModel<String> modelST = (DefaultComboBoxModel<String>) CB_ST.getModel();
        if (modelST.getIndexOf(maintance_data.getStatus()) == -1) {
            modelST.addElement(maintance_data.getStatus());
        }

        
        DefaultComboBoxModel<String> modelH = (DefaultComboBoxModel<String>) CB_H.getModel();
        if (modelH.getIndexOf(maintance_data.getHall()) == -1) {
            modelH.addElement(maintance_data.getHall());
        }
        
        
        DefaultComboBoxModel<String> modelID = (DefaultComboBoxModel<String>) CB_ID.getModel();
        if (modelID.getIndexOf(maintance_data.getID()) == -1) 
        {
            modelID.addElement(maintance_data.getID());
        }
    }
    
    //------------------------------------------------------------------------------------
    
    @Override
    public ArrayList<Data_Maintenance> getAfterFilterMaintenanceData() 
    {
        return after_filter_maintance_data; 
    }
    
    //------------------------------------------------------------------------------------
    
    public void create_chart()
    {
        Generate_Maintanance_Chart c = new Generate_Maintanance_Chart("Operation", 0, after_filter_maintance_data, JPanel_1);
    }
     
    //------------------------------------------------------------------------------------
    
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() 
    {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
        {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String status = String.valueOf(table.getValueAt(row, 4)); 
            Color color = switch (status)
            {
                case "Pending" -> new Color(255, 183, 77); // Pastel Orange
                case "Cancelled" -> new Color(255, 99, 71);  // Pastel Red 
                case "Closed" -> new Color(100, 149, 237); // Deeper Blue (Cornflower Blue)
                case "In Progress" -> new Color(204, 204, 0); // Deeper Yellow (Olive)
                case "Done" -> new Color(50, 205, 50); // Deeper Green (Green)
                default -> Color.WHITE;
            };
            c.setBackground(color);
            return c;
        }
    };
    
    //------------------------------------------------------------------------------------

    public Frame_Maintenance_Operation() throws IOException 
    {

        initComponents();
        User.setProfile(jLabel1, currentUser);
        status_selection("In Progress", "Done", "Closed", "Cancelled", "Pending");
        Function.Populate_Data_Into_Table(filePath, maintance_data, t_1, this);
        Function.read_file_data(filePath, maintance_data);
        Function.sortAllComboBoxes(2, CB_C, CB_S, CB_ST, CB_H);
        create_chart();
        CB_ST_2.removeItem("ALL");
        lbl_Issue.setText("Please press element in the table");

        for (int i = 0; i < t_1.getColumnCount(); i++) 
        {
            t_1.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }  
        
    }
   
    //------------------------------------------------------------------------------------
    
    public void status_selection(String... x)
    {
        DefaultComboBoxModel<String> modelST2 = (DefaultComboBoxModel<String>) CB_ST_2.getModel();
        for (String item : x) 
        {
            if (modelST2.getIndexOf(item) == -1) 
            {
                modelST2.addElement(item);
            }
        }
    }
    
    //------------------------------------------------------------------------------------
    
    public void filter() 
    {
        DefaultTableModel model = (DefaultTableModel) t_1.getModel();
        model.setRowCount(0);

        after_filter_maintance_data = new ArrayList<>(maintance_data);
        CB_ID.removeAllItems();
        
        // Apply filters based on each JComboBox
        matchesSelection(CB_C, "getCustomer");
        matchesSelection(CB_S, "getStuff");   
        matchesSelection(CB_ST, "getStatus"); 
        matchesSelection(CB_H, "getHall"); 
        
        CB_ST_2.removeItem("ALL");
        Function.sortAllComboBoxes(2,CB_C, CB_S,CB_ST,CB_H);
        
        for(Data_Maintenance maintenance_data : after_filter_maintance_data)
        {
            model.addRow(new Object[]{maintenance_data.getID(), maintenance_data.getIssue(), maintenance_data.getCustomer(), maintenance_data.getStaff(), maintenance_data.getStatus(), maintenance_data.getHall()});
            DefaultComboBoxModel<String> modelID = (DefaultComboBoxModel<String>) CB_ID.getModel();
            if (modelID.getIndexOf(maintenance_data.getID()) == -1) 
            {
                modelID.addElement(maintenance_data.getID());
            }
        }
        
        create_chart();
        lbl_Issue.setText("Please press element in the table");
    }
    
    //------------------------------------------------------------------------------------

    public void matchesSelection(JComboBox<String> x, String fieldName) 
    {
        String selected = (String) x.getSelectedItem();

        if (!"ALL".equals(selected)) 
        {
            after_filter_maintance_data = (ArrayList<Data_Maintenance>) after_filter_maintance_data.stream().filter
            (
                data -> 
                {
                    switch (fieldName) 
                    {
                        case "getCustomer":
                            return selected.equals(data.getCustomer());
                        case "getStuff":
                            return selected.equals(data.getStaff());
                        case "getStatus":
                            return selected.equals(data.getStatus());
                        case "getHall":
                            return selected.equals(data.getHall());
                        default:
                            return false;
                    }
                }
            )
            .collect(Collectors.toList());
        }
    }
    
    //------------------------------------------------------------------------------------
    
    public void reset() throws IOException
    {
        CB_C.removeAllItems();
        CB_S.removeAllItems();
        CB_ST.removeAllItems();
        CB_H.removeAllItems();
        maintance_data.clear();
        after_filter_maintance_data.clear();     
        Function.Populate_Data_Into_Table(filePath, maintance_data, t_1, this);
        Function.read_file_data(filePath, maintance_data);
        Function.sortAllComboBoxes(2, CB_C, CB_S, CB_ST, CB_H);
        create_chart();
        CB_ST_2.removeItem("ALL");
        lbl_Issue.setText("Please press element in the table");
    }
    
    //------------------------------------------------------------------------------------
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        t_1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        JPanel_1 = new javax.swing.JPanel();
        Btn_Assign = new javax.swing.JButton();
        Btn_Change_Status = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        CB_C = new javax.swing.JComboBox<>();
        CB_S = new javax.swing.JComboBox<>();
        CB_ST = new javax.swing.JComboBox<>();
        CB_H = new javax.swing.JComboBox<>();
        Btn_Reset = new javax.swing.JButton();
        Btn_Fitler = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Btn_Main = new javax.swing.JButton();
        Btn_DB = new javax.swing.JButton();
        Btn_Logout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        CB_ST_2 = new javax.swing.JComboBox<>();
        CB_ID = new javax.swing.JComboBox<>();
        lbl_Issue = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        t_1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        t_1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Issue", "Customer ID", "Staff ID", "Status", "Hall ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        t_1.getTableHeader().setReorderingAllowed(false);
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
            t_1.getColumnModel().getColumn(4).setResizable(false);
            t_1.getColumnModel().getColumn(5).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 820, 410));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPanel_1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(JPanel_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 410));

        Btn_Assign.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        Btn_Assign.setText("Assign scheduler (Staff)");
        Btn_Assign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_AssignActionPerformed(evt);
            }
        });
        jPanel1.add(Btn_Assign, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, 240, 60));

        Btn_Change_Status.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        Btn_Change_Status.setText("Change Status");
        Btn_Change_Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Change_StatusActionPerformed(evt);
            }
        });
        jPanel1.add(Btn_Change_Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 520, 240, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 10, 440, 610));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CB_C.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        CB_C.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jPanel3.add(CB_C, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 170, 50));

        CB_S.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        CB_S.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jPanel3.add(CB_S, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 170, 50));

        CB_ST.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        CB_ST.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jPanel3.add(CB_ST, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 180, 50));

        CB_H.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        CB_H.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jPanel3.add(CB_H, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, 160, 50));

        Btn_Reset.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        Btn_Reset.setText("Reset");
        Btn_Reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_ResetMouseClicked(evt);
            }
        });
        Btn_Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ResetActionPerformed(evt);
            }
        });
        jPanel3.add(Btn_Reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 320, 50));

        Btn_Fitler.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
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
        jPanel3.add(Btn_Fitler, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 320, 50));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel3.setText("Hall");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, -1, 40));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel4.setText("Customer");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, 40));

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel5.setText("Staff");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, 40));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel6.setText("Status");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, -1, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, 820, 200));

        jPanel2.setBackground(new java.awt.Color(255, 241, 241));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Btn_Main.setText("Main");
        Btn_Main.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_MainActionPerformed(evt);
            }
        });
        jPanel2.add(Btn_Main, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 110, 40));

        Btn_DB.setText("Dash Board");
        Btn_DB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_DBActionPerformed(evt);
            }
        });
        jPanel2.add(Btn_DB, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 110, 40));

        Btn_Logout.setText("Logout");
        Btn_Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LogoutActionPerformed(evt);
            }
        });
        jPanel2.add(Btn_Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 110, 40));

        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 16, 90, 110));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 890));

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CB_ST_2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        CB_ST_2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        CB_ST_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_ST_2ActionPerformed(evt);
            }
        });
        jPanel4.add(CB_ST_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 100, 270, 60));

        CB_ID.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        CB_ID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jPanel4.add(CB_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 260, 60));

        lbl_Issue.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbl_Issue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(lbl_Issue, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 1210, 40));

        jPanel5.setBackground(new java.awt.Color(255, 255, 204));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel2.setText("Change Status");
        jPanel5.add(jLabel2);

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 40));

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel7.setText("Status");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 110, 100, 40));

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel8.setText("ISSUSE  ID");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 150, 40));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 640, 1290, 220));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_ResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_ResetMouseClicked
   // TODO add your handling code here:
    }//GEN-LAST:event_Btn_ResetMouseClicked

    private void Btn_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ResetActionPerformed
        try 
        {
            reset();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Frame_Maintenance_Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Btn_ResetActionPerformed

    private void Btn_FitlerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_FitlerMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_FitlerMouseClicked

    private void Btn_FitlerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_FitlerActionPerformed
        // TODO add your handling code here:
        filter(); 
    }//GEN-LAST:event_Btn_FitlerActionPerformed

    private void Btn_DBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_DBActionPerformed
        try 
        {
            Function.Change_Frame(this,new Frame_Sales_Dashboard());
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Frame_Maintenance_Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Btn_DBActionPerformed
    
    private void Btn_AssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_AssignActionPerformed
        try 
        {
            String target_ID = (String)CB_ID.getSelectedItem();
            Function.Change_Frame(this,new Frame_Assign_Staff());
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Frame_Maintenance_Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Btn_AssignActionPerformed

    private void Btn_Change_StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Change_StatusActionPerformed

        
         int response = JOptionPane.showConfirmDialog(this, 
                        "Are you sure to change?", 
                        "Confirm", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                
                if (response == JOptionPane.YES_OPTION) 
                {
                    try 
                    {
                        String target_ID = (String)CB_ID.getSelectedItem();
                        String newValue = (String)CB_ST_2.getSelectedItem();

                        Function.updateFileData(filePath,target_ID,newValue,maintance_data);
                        
                        if(!Function.status)
                        {
                            
                            Function.Change_Frame(this,new Frame_Comfirm(target_ID));
                            
                        }
                        reset();

                    } 
                    catch (IOException ex) 
                    {
                        Logger.getLogger(Frame_Maintenance_Operation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }//GEN-LAST:event_Btn_Change_StatusActionPerformed

    private void t_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_1MouseClicked
 
        int row = t_1.rowAtPoint(evt.getPoint()); 
        Object selectedValue;
        
        
        if (row >= 0)
        {
            lbl_Issue.setText(t_1.getValueAt(row, 1).toString());
            
            selectedValue = t_1.getValueAt(row, 0);
            if (selectedValue != null)
            {
                CB_ID.setSelectedItem(selectedValue.toString());  
            }
            
            selectedValue = t_1.getValueAt(row, 5); 
            if (selectedValue != null) 
            {
                CB_ST_2.setSelectedItem(selectedValue.toString()); 
            }
            
            selectedValue = t_1.getValueAt(row, 4); 

        }
    }//GEN-LAST:event_t_1MouseClicked

    private void Btn_MainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_MainActionPerformed
        Function.Change_Frame(this,new Frame_Main());
    }//GEN-LAST:event_Btn_MainActionPerformed

    private void Btn_LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LogoutActionPerformed
         int response = JOptionPane.showConfirmDialog(this, 
                        "Are you sure to logout?", 
                        "Confirm", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                
                if (response == JOptionPane.YES_OPTION) 
                {
                    currentUser.logout();
                    this.dispose();
                    admin.Frame_Login login = new admin.Frame_Login();
                    login.setVisible(true);
                }
        
    }//GEN-LAST:event_Btn_LogoutActionPerformed

    private void CB_ST_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_ST_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CB_ST_2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
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
            java.util.logging.Logger.getLogger(Frame_Maintenance_Operation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame_Maintenance_Operation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame_Maintenance_Operation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame_Maintenance_Operation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() 
            {
                try {
                    JFrame frame = new Frame_Maintenance_Operation();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null); 
                } catch (IOException ex) {
                    Logger.getLogger(Frame_Maintenance_Operation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Assign;
    private javax.swing.JButton Btn_Change_Status;
    private javax.swing.JButton Btn_DB;
    private javax.swing.JButton Btn_Fitler;
    private javax.swing.JButton Btn_Logout;
    private javax.swing.JButton Btn_Main;
    private javax.swing.JButton Btn_Reset;
    private javax.swing.JComboBox<String> CB_C;
    private javax.swing.JComboBox<String> CB_H;
    private javax.swing.JComboBox<String> CB_ID;
    private javax.swing.JComboBox<String> CB_S;
    private javax.swing.JComboBox<String> CB_ST;
    private javax.swing.JComboBox<String> CB_ST_2;
    private javax.swing.JPanel JPanel_1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_Issue;
    private javax.swing.JTable t_1;
    // End of variables declaration//GEN-END:variables
}

