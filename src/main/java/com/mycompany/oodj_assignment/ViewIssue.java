package com.mycompany.oodj_assignment;

import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ViewIssue extends javax.swing.JFrame { 
    private Customer customer; 
    private DefaultTableModel tableModel;
    
    public ViewIssue() {
        this.customer = Session.getInstance().getCustomer();
        initComponents();
        loadIssues();
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblIssue = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Issues");

        tblIssue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Booking ID", "Description", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblIssue);

        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("*Only Issues with \"Pending\" status can be cancelled");

        btnCancel.setText("Cancel Issue");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnBack.setText("BACK");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(196, 196, 196)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel2)
                        .addGap(239, 239, 239)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        int selectedRow = tblIssue.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an issue.");
            return;
        }
        
        // only perform cancellation if the status is "Pending"
        String status = (String) tblIssue.getValueAt(selectedRow, 2);
        if ("Pending".equals(status)) {
            String bookingId = (String) tblIssue.getValueAt(selectedRow, 0);
            cancelIssue(bookingId, selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Action Not Permitted");
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new RaiseIssue().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed
    
    private void loadIssues() {
        tableModel = new DefaultTableModel(new String[]{"Booking ID", "Description", "Status"}, 0);
        List<String[]> issues = getIssuesForCustomer(customer.getUserid());
        for (String[] issue : issues) {
            Object[] row = new Object[]{
            issue[0],
            issue[1],
            issue[4],
        };
        tableModel.addRow(row);
        }
        tblIssue.setModel(tableModel);
    }

    private List<String[]> getIssuesForCustomer(String customerId) {
        List<String[]> issues = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("issue.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data[2].equals(customerId)) {
                    issues.add(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return issues;
    }

    private void cancelIssue(String bookingId, int selectedRow) {
        List<String[]> issues = getAllIssues();
        List<String> updatedData = new ArrayList<>();
        
        // change status to "Cancelled" if it matches the booking id
        for (String[] issue : issues) {
            if (issue[0].equals(bookingId)) {
                issue[2] = "Cancelled";
            }
            updatedData.add(String.join(";", issue));
        }
        
        FileManager.writeToFile("issue.txt", updatedData.toArray(new String[0]), false); // false = overwrite the file

        // update status to "Cancelled"
        tableModel.setValueAt("Cancelled", selectedRow, 2);
        JOptionPane.showMessageDialog(this, "Issue has been canceled.");
    }

    private List<String[]> getAllIssues() {
        List<String[]> issues = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("issue.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                issues.add(line.split(";"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return issues;
    }



    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new ViewIssue().setVisible(true));
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblIssue;
    // End of variables declaration//GEN-END:variables
}