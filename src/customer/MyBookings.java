package customer;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import admin.User;
import admin.Customer;
import admin.Frame_Login;

public class MyBookings extends javax.swing.JFrame {
    private User currentUser = User.getLoggedInUser();
    Customer customer = (Customer) currentUser;

    public MyBookings() {
        initComponents();
        // upcoming tab
        loadBookingsForCustomer(customer.getUserId(), tblUpcomingBookings, "upcoming");

        // past tab
        loadBookingsForCustomer(customer.getUserId(), tblPastBookings, "past");

        // cancelled tab
        loadBookingsForCustomer(customer.getUserId(), tblCancelledBookings, "cancelled");
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUpcomingBookings = new javax.swing.JTable();
        btnCancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPastBookings = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCancelledBookings = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel11.setBackground(new java.awt.Color(51, 0, 102));
        jPanel11.setPreferredSize(new java.awt.Dimension(756, 471));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("MY BOOKINGS");

        jButton11.setBackground(new java.awt.Color(255, 102, 255));
        jButton11.setText("My Profile");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(255, 102, 255));
        jButton12.setText("Book a Hall");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(255, 255, 0));
        jButton13.setText("Dashboard");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(255, 102, 255));
        jButton14.setText("Raise an Issue");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));

        tblUpcomingBookings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Booking ID", "Hall Type", "Hall Name", "Booking Date", "Booking Time", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblUpcomingBookings);

        btnCancel.setBackground(new java.awt.Color(255, 51, 51));
        btnCancel.setText("Cancel Booking");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Upcoming", jPanel2);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        tblPastBookings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Booking ID", "Hall Type", "Hall Name", "Booking Date", "Booking Time", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblPastBookings);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("History", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        tblCancelledBookings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Booking ID", "Hall Type", "Hall Name", "Booking Date", "Booking Time", "Refund"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblCancelledBookings);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Cancelled", jPanel3);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        int selectedRow = tblUpcomingBookings.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a booking to cancel.");
            return;
        }

        // get bookingid and start date from selected row
        String bookingId = tblUpcomingBookings.getValueAt(selectedRow, 0).toString(); // bookingid
        String startDateTime = tblUpcomingBookings.getValueAt(selectedRow, 3).toString(); // start date

        String bookingDateStr = startDateTime.split(" ")[0]; // get the date part
        LocalDate bookingDate = LocalDate.parse(bookingDateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // check if the booking can be cancelled
        LocalDate currentDate = LocalDate.now();
        if (bookingDate.isBefore(currentDate.plusDays(3))) {
            JOptionPane.showMessageDialog(this, "You can only cancel bookings at least 3 days in advance.");
            return;
        }

        // cancel confirmation
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel this booking?", "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return; // no cancel
        }

        // get the amount paid and minus 10
        double amount = Double.parseDouble(tblUpcomingBookings.getValueAt(selectedRow, 5).toString());
        double refund = amount - 10; // RM10 is charged for cancellation

        // update the status to cancel in booking.txt
        updateBookingStatus(bookingId, "cancelled");

        // add the booking back to Schedule.txt
        addToScheduleFile(bookingId);
        
        // update customer balance
        updateCustomerBalance(customer.getUserId(), refund);
        //Customer updatedCustomer = Session.getInstance().getCustomer();
        double currentBalance = customer.getBalance();
        double newBalance = currentBalance + refund;
        customer.setBalance(newBalance); // update balance in session
        //Session.getInstance().setCustomer(updatedCustomer);

        // notify user
        JOptionPane.showMessageDialog(this, "Booking cancelled successfully. Refund: RM" + String.format("%.2f", refund));

        // refresh table after the new changes
        loadBookingsForCustomer(customer.getUserId(), tblUpcomingBookings, "upcoming");
        loadBookingsForCustomer(customer.getUserId(), tblCancelledBookings, "cancelled");
    
    }//GEN-LAST:event_btnCancelActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        new HallBooking().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        new CustomerDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        new MyProfile().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        new RaiseIssue().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton14ActionPerformed
    
    private void updateBookingStatus(String bookingId, String newStatus) {
        File inputFile = new File("booking.txt");
        File tempFile = new File("booking_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data[0].equals(bookingId)) { // find the correct booking
                    // update the status
                    data[8] = newStatus;
                    line = String.join(";", data);
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace original file with updated file
        if (!inputFile.delete()) {
            System.err.println("Could not delete original file");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Could not rename temp file");
        }
    }
    
    private void addToScheduleFile(String bookingId) {
        String lineToAdd = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("booking.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data[0].equals(bookingId)) {
                    String hallName = data[2];
                    String[] startParts = data[4].split(" ");
                    String[] endParts = data[5].split(" "); 

                    lineToAdd = String.format("Availability Slot;%s;%s;%s;%s;%s;null",
                            hallName,
                            startParts[0],// start date
                            startParts[1],// start time
                            endParts[0],// end date
                            endParts[1] //end time     
                    );
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
         if (lineToAdd != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Schedule.txt", true))) {
                writer.write(lineToAdd);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Booking not found for ID: " + bookingId);
        }
    }

    private void updateCustomerBalance(String customerId, double refundAmount) {
        String filePath = "customer.txt";
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data[0].equals(customerId)) {
                    // update balance
                    double currentBalance = Double.parseDouble(data[7]);
                    double newBalance = currentBalance + refundAmount;
                    data[7] = String.format("%.2f", newBalance);
                    line = String.join(";", data);

                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileManager.writeToFile(filePath, lines.toArray(new String[0]), false);
    }
    
    public void loadBookingsForCustomer(String customerId, JTable table, String status) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader("booking.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");

                String bookingId = data[0];
                String customerID = data[1];
                String hallType = data[3];
                String hallName = data[2];
                String startDateTime = data[4];
                String endDateTime = data[5];
                double amount = Double.parseDouble(data[6]);
                String bookingStatus = data[8];

                // validate with logged in customer
                if (customerID.equals(customerId)) {
                    LocalDate bookingDate = LocalDate.parse(startDateTime.split(" ")[0], formatter);

                    // if the booking date past current datetime and is also not cancelled status change the status to past
                    if (bookingDate.isBefore(currentDate)&& !bookingStatus.equals("cancelled")) {
                        bookingStatus = "past";
                    }

                    String[] startDateTimeParts = startDateTime.split(" ");
                    String[] endDateTimeParts = endDateTime.split(" ");

                    String bookingDateStr = startDateTimeParts[0] + " - " + endDateTimeParts[0];
                    String bookingTime = startDateTimeParts[1] + " - " + endDateTimeParts[1];
                    // upcoming and past have the same format
                    if (status.equals(bookingStatus)) {
                        if (bookingStatus.equals("upcoming") || bookingStatus.equals("past")) {
                            model.addRow(new Object[]{bookingId, hallType, hallName, bookingDateStr, bookingTime, String.format("%.2f", amount)});
                        } else if (bookingStatus.equals("cancelled")) {
                            double refund = amount - 10;
                            model.addRow(new Object[]{bookingId, hallType, hallName, bookingDateStr, bookingTime, String.format("%.2f", refund)});
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyBookings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tblCancelledBookings;
    private javax.swing.JTable tblPastBookings;
    private javax.swing.JTable tblUpcomingBookings;
    // End of variables declaration//GEN-END:variables
}
