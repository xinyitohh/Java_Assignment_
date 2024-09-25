/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author kairuisam
 */
public class Frame_Assign_Staff extends javax.swing.JFrame implements Interface_Assign
{

    public ArrayList<Data_Maintenance> maintance_data = new ArrayList<>(); 
    String filePath =  "TXT_DATA/sam_love.txt";
    String file_of_Staff_Path = "scheduler.txt";

    Class_Function_Assign Function = new Class_Function_Assign();
    public ArrayList<String> Arread_staff_data = new ArrayList<>(); 
    String target_ID;
    String newValue;
    
    
    
     //------------------------------------------------------------------------------------
    
    @Override
    public void addDataToComboBox(Data_Maintenance maintance_data) 
    {
        DefaultComboBoxModel<String> modelID = (DefaultComboBoxModel<String>) CB_M_ID.getModel();
        if (modelID.getIndexOf(maintance_data.getID()) == -1) {
            modelID.addElement(maintance_data.getID());
        }
    }
   
    //------------------------------------------------------------------------------------
    
    public Frame_Assign_Staff() throws IOException 
    {
        initComponents();
        Function.Populate_Data_Into_Table(filePath, maintance_data, t_1, this);
        Function.read_file_data(filePath, maintance_data);
        Function.read_staff_data(file_of_Staff_Path,Arread_staff_data,CB_S_ID);
    }

    //------------------------------------------------------------------------------------
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        t_1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        CB_M_ID = new javax.swing.JComboBox<>();
        CB_S_ID = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Btn_Return = new javax.swing.JButton();
        Btn_Assign = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        t_1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        t_1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Issus", "Status", "Hall", "Staff"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

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
        }

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        CB_M_ID.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        CB_M_ID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        CB_S_ID.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        CB_S_ID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setText("ISSUSE  ID");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel2.setText("STAFF  ID");

        Btn_Return.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        Btn_Return.setText("Return");
        Btn_Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ReturnActionPerformed(evt);
            }
        });

        Btn_Assign.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        Btn_Assign.setText("Assign");
        Btn_Assign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_AssignActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(447, 447, 447)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Btn_Assign, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Return, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(376, 376, 376))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(CB_M_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(CB_S_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(153, 153, 153))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Btn_Assign, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Btn_Return, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CB_S_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CB_M_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(256, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_AssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_AssignActionPerformed
        int response = JOptionPane.showConfirmDialog(this, 
                        "Are you sure to assign?", 
                        "Confirm", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                
                if (response == JOptionPane.YES_OPTION) 
                {
                    try 
                    {
                        target_ID = (String)CB_M_ID.getSelectedItem();
                        newValue = (String)CB_S_ID.getSelectedItem();
                        if (target_ID != null)
                        {
                            Function.updateFileData(filePath, target_ID, newValue, maintance_data);
                        } 
                        maintance_data.clear();
                        CB_M_ID.removeAllItems();
                        Function.Populate_Data_Into_Table(filePath, maintance_data, t_1, this);
                        Function.read_file_data(filePath, maintance_data);
                        Function.read_staff_data(file_of_Staff_Path,Arread_staff_data,CB_S_ID);
                    } 
                    catch (IOException ex) 
                    {
                        Logger.getLogger(Frame_Assign_Staff.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        
    }//GEN-LAST:event_Btn_AssignActionPerformed

    private void Btn_ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ReturnActionPerformed
        try 
        {
            Function.Change_Frame(this,new Frame_Maintenance_Operation());
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Frame_Maintenance_Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Btn_ReturnActionPerformed

    private void t_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_1MouseClicked
    
        int row = t_1.rowAtPoint(evt.getPoint());

        if (row >= 0)
        {
 
            Object selectedValue = t_1.getValueAt(row, 0);
            if (selectedValue != null) 
            {
                CB_M_ID.setSelectedItem(selectedValue.toString());
            }
        }
    }//GEN-LAST:event_t_1MouseClicked

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
            java.util.logging.Logger.getLogger(Frame_Assign_Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame_Assign_Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame_Assign_Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame_Assign_Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try 
                {
                    Frame_Assign_Staff frame = new Frame_Assign_Staff();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null); 
                } 
                
                catch (IOException ex) 
                {
                    Logger.getLogger(Frame_Assign_Staff.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Assign;
    private javax.swing.JButton Btn_Return;
    private javax.swing.JComboBox<String> CB_M_ID;
    private javax.swing.JComboBox<String> CB_S_ID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_1;
    // End of variables declaration//GEN-END:variables
}
