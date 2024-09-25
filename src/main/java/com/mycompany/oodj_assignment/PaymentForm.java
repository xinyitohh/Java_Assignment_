package com.mycompany.oodj_assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;


public class PaymentForm extends javax.swing.JFrame {
    private Payment payment;
    private static final SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy hh:mma");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mma");

    public PaymentForm(Payment payment) {
        this.payment = payment;
        initComponents();
        displayBookingDetails();
        formatDateAndTime(payment.getStartDate(), payment.getEndDate());
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblHallName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblHallType = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblBookingDate = new javax.swing.JLabel();
        lblBookingTime = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblPrice = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblHoursBooked = new javax.swing.JLabel();
        lblRate = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnConfirm = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lblPrice2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Payment");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(jLabel1)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lblHallName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHallName.setText("jLabel2");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Hall Name:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Hall Type:");

        lblHallType.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHallType.setText("jLabel9");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHallType, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblHallName, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(49, 49, 49))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblHallName))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblHallType))
                .addGap(25, 25, 25))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Booking Date(s):");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Time:");

        lblBookingDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBookingDate.setText("jLabel9");

        lblBookingTime.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBookingTime.setText("jLabel9");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblBookingTime))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblBookingDate)))
                .addGap(51, 51, 51))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblBookingDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblBookingTime))
                .addGap(18, 18, 18))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lblPrice.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPrice.setText("p");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Rate per hour:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Total Price:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Hours Booked:");

        lblHoursBooked.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHoursBooked.setText("jLabel9");

        lblRate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblRate.setText("jLabel9");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPrice))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRate))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblHoursBooked)))
                .addGap(54, 54, 54))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblHoursBooked))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblRate))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblPrice))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        btnConfirm.setBackground(new java.awt.Color(255, 204, 102));
        btnConfirm.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnConfirm.setText("Confirm Payment");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Total Payment");

        lblPrice2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPrice2.setForeground(new java.awt.Color(255, 204, 102));
        lblPrice2.setText("p");

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrice2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConfirm)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPrice2)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Customer customer = Session.getInstance().getCustomer();
        new CustomerDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        String hallName = payment.getHallName();
        String StartDate = payment.getStartDate();
        String EndDate = payment.getEndDate();
        double totalPrice = payment.getTotalPrice();
        double userBalance = Session.getInstance().getCustomer().getBalance();
        double amountNeeded = totalPrice - userBalance;

        if (userBalance >= totalPrice) {
            String bookingId = completePayment();
            removeBooking();
            new ReceiptForm(payment, bookingId).setVisible(true);
            this.dispose();
            
        } else {
            // insufficient balance
            int result = JOptionPane.showConfirmDialog(this, 
                    String.format("You need RM%.2f more. Do you want to top-up? You will be redirected", amountNeeded), 
                    "Insufficient Balance", 
                    JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                // redirect to topup form
                Customer customer = Session.getInstance().getCustomer();
                Topup topupForm = new Topup(customer);
                topupForm.setVisible(true);
                this.dispose();
            }
        }
        
    }//GEN-LAST:event_btnConfirmActionPerformed
    
    private String completePayment() {
        Customer customer = Session.getInstance().getCustomer();
        double totalPrice = payment.getTotalPrice();

        // update customer balance
        customer.setBalance(customer.getBalance() - totalPrice);
        Session.getInstance().setCustomer(customer);
        
        CustomerManager customerManager = new CustomerManager();
        customerManager.updateCustomerFile(customer);

        // generate booking id
        String bookingId = BookingIDIncrement.getNextBookingID();

        // get booking details
        String customerId = customer.getId();
        String hallName = payment.getHallName();
        String hallType = payment.getHallType();
        String bookingStartDate = payment.getStartDate();
        String bookingEndDate = payment.getEndDate();
        DecimalFormat df = new DecimalFormat("0.00");
        String paidAmount = df.format(totalPrice);
        String paymentDate = Utils.getCurrentDateTime();
        String status = "upcoming"; // set it as an upcoming booking

        String bookingDetails = String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s",
                bookingId, customerId, hallName, hallType, bookingStartDate, bookingEndDate, paidAmount, paymentDate, status);

        FileManager fileManager = new FileManager();
        fileManager.writeToFile("booking.txt", bookingDetails, true);

        JOptionPane.showMessageDialog(this, "Payment successful! Booking confirmed.");
        
        return bookingId;

    }
    private void formatDateAndTime(String startDateStr, String endDateStr) {
        try {
            Date startDate = inputFormat.parse(startDateStr);
            Date endDate = inputFormat.parse(endDateStr);

            lblBookingDate.setText(dateFormat.format(startDate) + " to " + dateFormat.format(endDate));
            lblBookingTime.setText(timeFormat.format(startDate) + " to " + timeFormat.format(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private void displayBookingDetails(){
        lblHallName.setText(payment.getHallName());
        lblHallType.setText(payment.getHallType());

        long totalHours = calculateHoursBooked(payment.getStartDate(), payment.getEndDate());
        lblHoursBooked.setText(String.valueOf(totalHours));

        // get rate for hall type
        double ratePerHour = getRateForHall(payment.getHallType());
        lblRate.setText(String.format("RM%.2f", ratePerHour));

        lblPrice.setText(String.format("RM%.2f", payment.getTotalPrice()));
        lblPrice2.setText(String.format("RM%.2f", payment.getTotalPrice()));
    }
    
    private long calculateHoursBooked(String startDateStr, String endDateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mma");
        LocalDateTime startDateTime = LocalDateTime.parse(startDateStr, dateTimeFormatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, dateTimeFormatter);

        // calculate number of days
        long daysBetween = ChronoUnit.DAYS.between(startDateTime.toLocalDate(), endDateTime.toLocalDate()) + 1;

        // calculate hours
        long hoursPerDay = ChronoUnit.HOURS.between(startDateTime.toLocalTime(), endDateTime.toLocalTime());

        // total hours
        long totalHours = daysBetween * hoursPerDay;

        return totalHours;
    }
    
    private void removeBooking() {
    String filePath = "Schedule.txt";
    List<String> lines = new ArrayList<>();
    String hallName = payment.getHallName();
    String paymentStartDateTime = payment.getStartDate();
    String paymentEndDateTime = payment.getEndDate();

    String[] startParts = paymentStartDateTime.split(" ");
    String startDate = startParts[0];
    String startTime = startParts[1];

    String[] endParts = paymentEndDateTime.split(" ");
    String endDate = endParts[0];
    String endTime = endParts[1];

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {          
            String[] parts = line.split(";");
            if (parts.length < 7) {
                continue; // skip if not as criteria
            }
            String currentHallName = parts[1];
            String currentStartDate = parts[2];
            String currentStartTime = parts[3];
            String currentEndDate = parts[4];
            String currentEndTime = parts[5];

            if (!(currentHallName.equals(hallName) &&
                  currentStartDate.equals(startDate) &&
                  currentStartTime.equals(startTime) &&
                  currentEndDate.equals(endDate) &&
                  currentEndTime.equals(endTime))) {
                lines.add(line);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    FileManager.writeToFile(filePath, lines.toArray(new String[0]), false);
    }



    private double getRateForHall(String hallType) {
    switch (hallType) {
        case "Auditorium":
            return 300;
        case "Banquet Hall":
            return 100;
        case "Meeting Room":
            return 50;
        default:
            return 0;
    }
    }

    
//    public static void main(String args[]) {
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PaymentForm(payment).setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblBookingDate;
    private javax.swing.JLabel lblBookingTime;
    private javax.swing.JLabel lblHallName;
    private javax.swing.JLabel lblHallType;
    private javax.swing.JLabel lblHoursBooked;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblPrice2;
    private javax.swing.JLabel lblRate;
    // End of variables declaration//GEN-END:variables
}
