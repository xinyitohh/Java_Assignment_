/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package manager;

import admin.User;
import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kairuisam
 */
public class Cus_View_Respond extends javax.swing.JFrame
{
    private User currentUser = User.getLoggedInUser();
    
    String customer_id = currentUser.getUserId() ;
    public ArrayList<Data_Maintenance> maintance_data = new ArrayList<>(); 
    String filePath = "issue.txt";
    
    //------------------------------------------------------------------------------------
    
    public <T> void Populate_Data_Into_Table(String filepath, ArrayList<T> data, JTable table) throws IOException
    {
        read_file_data(filepath, data);
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        Data_Maintenance maintence_data;
        
        for (T item : data)
        {

            if (item instanceof Data_Maintenance) 
            {
                maintence_data = (Data_Maintenance)item;
                if(maintence_data.getCustomer().equals(customer_id))
                {
                    model.addRow(new Object[]{maintence_data.getID(), maintence_data.getIssue(), maintence_data.getCustomer(), maintence_data.getStaff(), maintence_data.getStatus(), maintence_data.getHall()});
                }
              
            }
        }
        data.clear(); 
    }
    

    //------------------------------------------------------------------------------------
    
    public <T> void read_file_data(String filepath, ArrayList<T> data) throws FileNotFoundException, IOException 
    {

        // Using try-with-resources to ensure BufferedReader is closed after use
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] parts = line.split(";");

                if (data instanceof ArrayList<?>) 
                {

                    ArrayList<Data_Maintenance> maintenanceDataList = (ArrayList<Data_Maintenance>) data;
                    Data_Maintenance m = new Data_Maintenance(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim(), parts[5].trim());
                    maintenanceDataList.add(m);
                }
            }
        }
    }
    
    //------------------------------------------------------------------------------------
    
    public Cus_View_Respond() 
    {
        initComponents();
        try 
        {
            Populate_Data_Into_Table(filePath, maintance_data, t_1);
            read_file_data(filePath, maintance_data);
            for (int i = 0; i < t_1.getColumnCount(); i++) 
            {
                t_1.getColumnModel().getColumn(i).setCellRenderer(renderer);
                
            }  
           
            for (Data_Maintenance maintenance : maintance_data) {
                updateComboBoxWithMaintenanceID(maintenance,customer_id);
            }
        
        } 
        catch (IOException ex)
        {
            Logger.getLogger(Cus_View_Respond.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
    

    private void updateComboBoxWithMaintenanceID(Data_Maintenance maintenance,String customer_id) 
    {
    DefaultComboBoxModel<String> modelID = (DefaultComboBoxModel<String>) CB_ID.getModel();

        boolean isCustomerID = maintenance.getCustomer().equals(customer_id);
        boolean isStatusValid = "Pending".equals(maintenance.getStatus()) || "In Progress".equals(maintenance.getStatus());

        if (isCustomerID && isStatusValid) 
        {
            modelID.addElement(maintenance.getID());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        t_1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        CB_ID = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        t_1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        t_1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Issue", "Customer", "Staff", "Status", "Hall"
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

        jButton1.setText("QUIT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel issue");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        CB_ID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(191, 191, 191)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addComponent(CB_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CB_ID, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void t_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_1MouseClicked

    }//GEN-LAST:event_t_1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
                
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        String targetID = (String) CB_ID.getSelectedItem();
        String newValue = "Cancelled";  // Assuming the action is to cancel the selected ID
        if (targetID != null)
        {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) 
            {
                for (Data_Maintenance md : maintance_data) 
                {
                    if (targetID.equals(md.getID())) 
                    {
                        if ("Pending".equals(md.getStatus())||"In Progress".equals(md.getStatus()))
                        {
                            md.setStaff("");
                            md.setStatus(newValue);
                        }
                    }
                    bw.write(md.WriteFile());
                    bw.newLine();
                }
            } catch (IOException e) {
                Logger.getLogger(Cus_View_Respond.class.getName()).log(Level.SEVERE, null, e);
            }

        } 


        
        maintance_data.clear();    
        try {
            Populate_Data_Into_Table(filePath, maintance_data, t_1);
            read_file_data(filePath, maintance_data);
            for (int i = 0; i < t_1.getColumnCount(); i++) 
            {
                t_1.getColumnModel().getColumn(i).setCellRenderer(renderer);

            }  
            CB_ID.removeAllItems();
            for (Data_Maintenance maintenance : maintance_data) {
                updateComboBoxWithMaintenanceID(maintenance,customer_id);
            }
        } catch (IOException ex) {
            Logger.getLogger(Cus_View_Respond.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
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
            java.util.logging.Logger.getLogger(Cus_View_Respond.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cus_View_Respond.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cus_View_Respond.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cus_View_Respond.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {

                JFrame frame = new Cus_View_Respond();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null); 
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CB_ID;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_1;
    // End of variables declaration//GEN-END:variables
}
