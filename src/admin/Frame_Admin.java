package admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class Frame_Admin extends javax.swing.JFrame implements DataValidator_admin{

    ArrayList<User> schedulerList = FileOperations.readFromFile("scheduler.txt");
    ArrayList<User> allUsers = FileOperations.loadAllUsers();
    private ArrayList<String[]> allBookings = new ArrayList<>();
    private String picture = null;
    private User currentUser = User.getLoggedInUser();
    Admin admin = (Admin) currentUser;
    
    
    public Frame_Admin() {
        initComponents(); 
        System.out.println("Current user:\n"+ currentUser);
        tboxID.setEnabled(false);
        tboxID.setText(UserIDIncrement.getCurrentSchedulerID());
        initialImage(lblPicture, tboxID.getText());       
        updateTable(tScheduler,schedulerList);
        tScheduler.setDefaultRenderer(Object.class, new AlternatingRowRenderer());
        tUser.setDefaultRenderer(Object.class, new AlternatingRowRenderer()); 
        User.setProfile(lblProfileID, lblProfilePic, currentUser);

        
        jTabbedPane.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            int selectedIndex = jTabbedPane.getSelectedIndex();
            if (selectedIndex == 1) {
                System.out.println("Tab 2 is selected.");
                initialImage(lblPicture1,tboxID.getText());
    
                updateTable(tUser,allUsers);
                System.out.println(allUsers);
                disableInput();
            }
            if (selectedIndex == 2) {
                System.out.println("Tab 3 is selected.");
                updateTable_Booking();
                tBooking.setDefaultRenderer(Object.class, new AlternatingRowRenderer());
            }
        }
        
    });
        //combobox user id load tab1
        for (User user:schedulerList){
            cboxUserID.addItem(user.getUserId());
        }
        
        //combobox user id load tab2
        for (User user:allUsers){
            cboxUserID_u.addItem(user.getUserId());
        }
        

    }
    
    @Override
    public boolean validateInput(){

        if (tboxName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name cannot be empty.");
            return false;
        }

        String email = tboxEmail.getText().trim();
        if (email.isEmpty() || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            JOptionPane.showMessageDialog(this, "Invalid email format.");
            return false;
        }

        String phone = tboxPhone.getText().trim();
        if (phone.isEmpty() || !phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Phone number must be 10 digits.");
            return false;
        }

        if (!rBtnMale.isSelected() && !rBtnFemale.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please select a gender.");
            return false;
        }

        String password = tboxPass.getText();
        if (password.isEmpty()){
            JOptionPane.showMessageDialog(this, "Password fields cannot be empty.");
            return false;
        }

        return true;
    }
    
    private void updateTable(JTable table, ArrayList<User> users) {
        updateTable(table, users, false, null, null, null); // No filters
    }
    
    private void updateTable(JTable table, ArrayList<User> users, boolean filter, String id, String status, String role) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (User user : users) {
            if (filter) {
                // Filter by ID
                if (!id.isEmpty() && !user.getUserId().equals(id)) continue;
                // Filter by status
                if (!status.isEmpty() && !user.getStatus().equals(status)) continue;
                // Filter by role
                if (!role.isEmpty()) {
                    switch (role) {
                        case "scheduler":
                            if (!user.getUserId().startsWith("S")) continue;
                            break;
                        case "customer":
                            if (!user.getUserId().startsWith("C")) continue;
                            break;
                        case "admin":
                            if (!user.getUserId().startsWith("A")) continue;
                            break;
                        case "manager":
                            if (!user.getUserId().startsWith("M")) continue;
                            break;
                    }
                }
            }
            // Add the user to the table
            model.addRow(new Object[]{
                user.getUserId(),
                user.getPassword(),
                user.getName(),
                user.getGender(),
                user.getEmail(),
                user.getPhone(),
                user.getStatus(), 
                user.getPicture()
            });
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

        jPanel1 = new javax.swing.JPanel();
        btnScheduler = new javax.swing.JButton();
        btnUser = new javax.swing.JButton();
        btnBooking = new javax.swing.JButton();
        lblProfilePic = new javax.swing.JLabel();
        lblProfileID = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jTabbedPane = new javax.swing.JTabbedPane();
        Panel_Scheduler = new javax.swing.JPanel();
        pInfo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tboxID = new javax.swing.JTextField();
        tboxPass = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tboxName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tboxEmail = new javax.swing.JTextField();
        tboxPhone = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        rBtnMale = new javax.swing.JRadioButton();
        rBtnFemale = new javax.swing.JRadioButton();
        btnUpdate = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        pPicture = new javax.swing.JPanel();
        lblPicture = new javax.swing.JLabel();
        btnImage = new javax.swing.JButton();
        btnImgDelete = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        tScheduler = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cboxUserID = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        btnFilter1_s = new javax.swing.JButton();
        cboxStatus = new javax.swing.JComboBox<>();
        btnFilter2_s = new javax.swing.JButton();
        btnReset_s = new javax.swing.JButton();
        Panel_User = new javax.swing.JPanel();
        pInfo1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tboxID1 = new javax.swing.JTextField();
        tboxPass1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tboxName1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tboxEmail1 = new javax.swing.JTextField();
        tboxPhone1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        rBtnMale1 = new javax.swing.JRadioButton();
        rBtnFemale1 = new javax.swing.JRadioButton();
        btnUnblock = new javax.swing.JButton();
        btnBlock = new javax.swing.JButton();
        btnDelete1 = new javax.swing.JButton();
        btnClear1 = new javax.swing.JButton();
        pPicture1 = new javax.swing.JPanel();
        lblPicture1 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        scrollPane1 = new javax.swing.JScrollPane();
        tUser = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        cboxUserID_u = new javax.swing.JComboBox<>();
        btnFilter1_u = new javax.swing.JButton();
        cboxStatus_u = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        btnFilter2_u = new javax.swing.JButton();
        btnReset_u = new javax.swing.JButton();
        cboxRole_u = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        btnFilter3_u = new javax.swing.JButton();
        Panel_Booking = new javax.swing.JPanel();
        scrollPane2 = new javax.swing.JScrollPane();
        tBooking = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cboxUp = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        btnFilter1_b = new javax.swing.JButton();
        cboxHall = new javax.swing.JComboBox<>();
        btnFilter2_b = new javax.swing.JButton();
        btnReset_b = new javax.swing.JButton();
        pInfo2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        tboxHallName = new javax.swing.JTextField();
        tboxHallType = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        tboxSDate = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        tboxEDate = new javax.swing.JTextField();
        tboxETime = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        tboxSTime = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        tboxBID = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        tboxCID = new javax.swing.JTextField();
        tboxAmount = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(37, 5, 62));

        btnScheduler.setBackground(new java.awt.Color(89, 14, 147));
        btnScheduler.setForeground(new java.awt.Color(255, 255, 255));
        btnScheduler.setText("Scheduler");
        btnScheduler.setBorder(null);
        btnScheduler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSchedulerActionPerformed(evt);
            }
        });

        btnUser.setBackground(new java.awt.Color(89, 14, 147));
        btnUser.setForeground(new java.awt.Color(255, 255, 255));
        btnUser.setText("User");
        btnUser.setBorder(null);
        btnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionPerformed(evt);
            }
        });

        btnBooking.setBackground(new java.awt.Color(89, 14, 147));
        btnBooking.setForeground(new java.awt.Color(255, 255, 255));
        btnBooking.setText("Booking");
        btnBooking.setBorder(null);
        btnBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookingActionPerformed(evt);
            }
        });

        lblProfilePic.setIcon(new javax.swing.ImageIcon("C:\\Users\\U S E R\\Desktop\\emopou.jpg")); // NOI18N
        lblProfilePic.setText("images/default.jpg");
        lblProfilePic.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblProfilePic.setMaximumSize(new java.awt.Dimension(85, 85));
        lblProfilePic.setPreferredSize(new java.awt.Dimension(85, 85));

        lblProfileID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblProfileID.setForeground(new java.awt.Color(255, 255, 255));
        lblProfileID.setText("User ID");

        btnLogout.setBackground(new java.awt.Color(89, 14, 147));
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.setBorder(null);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblProfilePic, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnBooking, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(btnUser, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(btnScheduler, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblProfileID, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblProfilePic, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lblProfileID)
                .addGap(11, 11, 11)
                .addComponent(btnScheduler, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUser, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 320, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 630));

        jTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneStateChanged(evt);
            }
        });

        Panel_Scheduler.setBackground(java.awt.SystemColor.control);

        pInfo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("User ID");

        tboxPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tboxPassActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Password");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Name");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Gender");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Email");

        tboxEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tboxEmailActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Phone");

        rBtnMale.setBackground(new java.awt.Color(255, 255, 255));
        rBtnMale.setText("Male");
        rBtnMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnMaleActionPerformed(evt);
            }
        });

        rBtnFemale.setBackground(new java.awt.Color(255, 255, 255));
        rBtnFemale.setText("Female");
        rBtnFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnFemaleActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        lblPicture.setIcon(new javax.swing.ImageIcon("C:\\Users\\U S E R\\Desktop\\emopou.jpg")); // NOI18N
        lblPicture.setText("images/default.jpg");
        lblPicture.setMaximumSize(new java.awt.Dimension(85, 85));
        lblPicture.setPreferredSize(new java.awt.Dimension(85, 85));

        javax.swing.GroupLayout pPictureLayout = new javax.swing.GroupLayout(pPicture);
        pPicture.setLayout(pPictureLayout);
        pPictureLayout.setHorizontalGroup(
            pPictureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPictureLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pPictureLayout.setVerticalGroup(
            pPictureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPictureLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnImage.setText("Image");
        btnImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImageActionPerformed(evt);
            }
        });

        btnImgDelete.setText("Delete");
        btnImgDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImgDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pInfoLayout = new javax.swing.GroupLayout(pInfo);
        pInfo.setLayout(pInfoLayout);
        pInfoLayout.setHorizontalGroup(
            pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInfoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pInfoLayout.createSequentialGroup()
                        .addComponent(pPicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnImage, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImgDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pInfoLayout.createSequentialGroup()
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pInfoLayout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pInfoLayout.createSequentialGroup()
                        .addComponent(rBtnMale)
                        .addGap(18, 18, 18)
                        .addComponent(rBtnFemale))
                    .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tboxPhone)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tboxEmail)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tboxName)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tboxPass)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tboxID, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        pInfoLayout.setVerticalGroup(
            pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInfoLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pPicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pInfoLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnImgDelete)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(7, 7, 7)
                .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rBtnMale)
                    .addComponent(rBtnFemale))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnClear))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        scrollPane.setBackground(new java.awt.Color(255, 255, 255));
        scrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scrollPaneMouseClicked(evt);
            }
        });

        tScheduler.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tScheduler.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Name", "Password", "Gender", "Email", "Phone", "Status", "Picture"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tScheduler.setRowHeight(24);
        tScheduler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tSchedulerMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(tScheduler);
        if (tScheduler.getColumnModel().getColumnCount() > 0) {
            tScheduler.getColumnModel().getColumn(7).setMinWidth(0);
            tScheduler.getColumnModel().getColumn(7).setPreferredWidth(0);
            tScheduler.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("User ID");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Status");

        btnFilter1_s.setText("Filter");
        btnFilter1_s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilter1_sActionPerformed(evt);
            }
        });

        cboxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "active", "blocked" }));

        btnFilter2_s.setText("Filter");
        btnFilter2_s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilter2_sActionPerformed(evt);
            }
        });

        btnReset_s.setText("Reset");
        btnReset_s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset_sActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cboxUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFilter1_s, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cboxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFilter2_s, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnReset_s, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(224, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboxUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter1_s)
                    .addComponent(cboxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter2_s)
                    .addComponent(btnReset_s))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Panel_SchedulerLayout = new javax.swing.GroupLayout(Panel_Scheduler);
        Panel_Scheduler.setLayout(Panel_SchedulerLayout);
        Panel_SchedulerLayout.setHorizontalGroup(
            Panel_SchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_SchedulerLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(pInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(Panel_SchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        Panel_SchedulerLayout.setVerticalGroup(
            Panel_SchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_SchedulerLayout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(Panel_SchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_SchedulerLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );

        jTabbedPane.addTab("tab1", Panel_Scheduler);

        pInfo1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("User ID");

        tboxPass1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tboxPass1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Password");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Name");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Gender");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Email");

        tboxEmail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tboxEmail1ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Phone");

        rBtnMale1.setBackground(new java.awt.Color(255, 255, 255));
        rBtnMale1.setText("Male");
        rBtnMale1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnMale1ActionPerformed(evt);
            }
        });

        rBtnFemale1.setBackground(new java.awt.Color(255, 255, 255));
        rBtnFemale1.setText("Female");
        rBtnFemale1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnFemale1ActionPerformed(evt);
            }
        });

        btnUnblock.setText("Unblock");
        btnUnblock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUnblockMouseClicked(evt);
            }
        });
        btnUnblock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnblockActionPerformed(evt);
            }
        });

        btnBlock.setText("Block");
        btnBlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBlockActionPerformed(evt);
            }
        });

        btnDelete1.setText("Delete");
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });

        btnClear1.setText("Clear");
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        lblPicture1.setIcon(new javax.swing.ImageIcon("C:\\Users\\U S E R\\Desktop\\emopou.jpg")); // NOI18N
        lblPicture1.setText("images/default.jpg");
        lblPicture1.setMaximumSize(new java.awt.Dimension(85, 85));
        lblPicture1.setPreferredSize(new java.awt.Dimension(85, 85));

        javax.swing.GroupLayout pPicture1Layout = new javax.swing.GroupLayout(pPicture1);
        pPicture1.setLayout(pPicture1Layout);
        pPicture1Layout.setHorizontalGroup(
            pPicture1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPicture1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPicture1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pPicture1Layout.setVerticalGroup(
            pPicture1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPicture1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPicture1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblStatus.setText("ㅤ");
        lblStatus.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Status");

        javax.swing.GroupLayout pInfo1Layout = new javax.swing.GroupLayout(pInfo1);
        pInfo1.setLayout(pInfo1Layout);
        pInfo1Layout.setHorizontalGroup(
            pInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInfo1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pInfo1Layout.createSequentialGroup()
                        .addComponent(pPicture1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pInfo1Layout.createSequentialGroup()
                        .addComponent(btnDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pInfo1Layout.createSequentialGroup()
                        .addComponent(btnBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUnblock, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pInfo1Layout.createSequentialGroup()
                        .addComponent(rBtnMale1)
                        .addGap(18, 18, 18)
                        .addComponent(rBtnFemale1))
                    .addGroup(pInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tboxPhone1)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tboxEmail1)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tboxName1)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tboxPass1)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tboxID1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        pInfo1Layout.setVerticalGroup(
            pInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInfo1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pPicture1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pInfo1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblStatus)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxPass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addGap(7, 7, 7)
                .addGroup(pInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rBtnMale1)
                    .addComponent(rBtnFemale1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxPhone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBlock)
                    .addComponent(btnUnblock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete1)
                    .addComponent(btnClear1))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        scrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        scrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scrollPane1MouseClicked(evt);
            }
        });

        tUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Password", "Name", "Gender", "Email", "Phone", "Status", "Picture"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tUser.setRowHeight(24);
        tUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tUserMouseClicked(evt);
            }
        });
        scrollPane1.setViewportView(tUser);
        if (tUser.getColumnModel().getColumnCount() > 0) {
            tUser.getColumnModel().getColumn(7).setMinWidth(0);
            tUser.getColumnModel().getColumn(7).setPreferredWidth(0);
            tUser.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("User ID");

        btnFilter1_u.setText("Filter");
        btnFilter1_u.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilter1_uActionPerformed(evt);
            }
        });

        cboxStatus_u.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "active", "blocked" }));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Status");

        btnFilter2_u.setText("Filter");
        btnFilter2_u.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilter2_uActionPerformed(evt);
            }
        });

        btnReset_u.setText("Reset");
        btnReset_u.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset_uActionPerformed(evt);
            }
        });

        cboxRole_u.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "scheduler", "customer", "admin", "manager" }));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Role");

        btnFilter3_u.setText("Filter");
        btnFilter3_u.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilter3_uActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cboxUserID_u, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFilter1_u, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cboxStatus_u, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFilter2_u, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(217, 217, 217))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cboxRole_u, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFilter3_u, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReset_u, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboxRole_u, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFilter3_u)
                            .addComponent(btnReset_u)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboxUserID_u, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFilter1_u)
                            .addComponent(cboxStatus_u, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFilter2_u))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Panel_UserLayout = new javax.swing.GroupLayout(Panel_User);
        Panel_User.setLayout(Panel_UserLayout);
        Panel_UserLayout.setHorizontalGroup(
            Panel_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_UserLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(pInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(Panel_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        Panel_UserLayout.setVerticalGroup(
            Panel_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_UserLayout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(Panel_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pInfo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_UserLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );

        jTabbedPane.addTab("tab2", Panel_User);

        scrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        scrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scrollPane2MouseClicked(evt);
            }
        });

        tBooking.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tBooking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking ID", "Customer ID", "Hall Name", "Hall Type", "Start Date", "Start Time", "End Date", "End Time", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tBooking.setRowHeight(24);
        tBooking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tBookingMouseClicked(evt);
            }
        });
        scrollPane2.setViewportView(tBooking);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Bookings");

        cboxUp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "upcoming", "past" }));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Hall Type");

        btnFilter1_b.setText("Filter");
        btnFilter1_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilter1_bActionPerformed(evt);
            }
        });

        cboxHall.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Auditorium", "Banquet Hall", "Meeting Room" }));
        cboxHall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxHallActionPerformed(evt);
            }
        });

        btnFilter2_b.setText("Filter");
        btnFilter2_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilter2_bActionPerformed(evt);
            }
        });

        btnReset_b.setText("Reset");
        btnReset_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset_bActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cboxUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFilter1_b, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cboxHall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFilter2_b, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnReset_b, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(224, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboxUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter1_b)
                    .addComponent(cboxHall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter2_b)
                    .addComponent(btnReset_b))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pInfo2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Hall Name");

        tboxHallName.setEnabled(false);

        tboxHallType.setEnabled(false);
        tboxHallType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tboxHallTypeActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Hall Type");

        tboxSDate.setEnabled(false);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Start Date");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Start Time");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("End Date");

        tboxEDate.setEnabled(false);
        tboxEDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tboxEDateActionPerformed(evt);
            }
        });

        tboxETime.setEnabled(false);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("End Time");

        tboxSTime.setEnabled(false);

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("Booking ID");

        tboxBID.setEnabled(false);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Customer ID");

        tboxCID.setEnabled(false);

        tboxAmount.setEnabled(false);

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("Amount");

        javax.swing.GroupLayout pInfo2Layout = new javax.swing.GroupLayout(pInfo2);
        pInfo2.setLayout(pInfo2Layout);
        pInfo2Layout.setHorizontalGroup(
            pInfo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInfo2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pInfo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pInfo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tboxBID, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tboxCID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pInfo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tboxAmount, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tboxETime, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tboxEDate, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tboxSDate, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tboxHallType, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tboxHallName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tboxSTime, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        pInfo2Layout.setVerticalGroup(
            pInfo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInfo2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel28)
                .addGap(15, 15, 15)
                .addComponent(tboxBID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxCID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxHallName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxHallType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxSDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxSTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxEDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxETime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tboxAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Panel_BookingLayout = new javax.swing.GroupLayout(Panel_Booking);
        Panel_Booking.setLayout(Panel_BookingLayout);
        Panel_BookingLayout.setHorizontalGroup(
            Panel_BookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_BookingLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(pInfo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(Panel_BookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        Panel_BookingLayout.setVerticalGroup(
            Panel_BookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_BookingLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(Panel_BookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pInfo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Panel_BookingLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );

        jTabbedPane.addTab("tab3", Panel_Booking);

        getContentPane().add(jTabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, -40, 1100, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tboxPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tboxPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tboxPassActionPerformed

    private void tboxEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tboxEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tboxEmailActionPerformed

    private void rBtnMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnMaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rBtnMaleActionPerformed

    private void rBtnFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnFemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rBtnFemaleActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if(!validateInput())return;
        String selectedRow = tboxID.getText();
        String password = tboxPass.getText();
        String name = tboxName.getText();
        String gender = rBtnMale.isSelected() ? "M" : "F";
        String email = tboxEmail.getText();
        String phone = tboxPhone.getText();
        
        admin.updateScheduler(selectedRow, password, name, gender, email, phone);
        schedulerList = FileOperations.readFromFile("scheduler.txt");
        updateTable(tScheduler,schedulerList);
        clearInput();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(!validateInput())return;
        String id = tboxID.getText();
        String password = tboxPass.getText();
        String name = tboxName.getText();
        String gender = rBtnMale.isSelected() ? "M" : "F";
        String email = tboxEmail.getText();
        String phone = tboxPhone.getText();
        if (picture == null){
            picture = "images/default.jpg";
        }

        admin.addScheduler(id, password, name, gender, email, phone, picture);
        schedulerList = FileOperations.readFromFile("scheduler.txt");
        updateTable(tScheduler, schedulerList);
        clearInput();
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String selectedRow = tboxID.getText();
        admin.deleteScheduler(selectedRow);
        schedulerList = FileOperations.readFromFile("scheduler.txt");
        updateTable(tScheduler, schedulerList);
        clearInput();
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearInput();
    }//GEN-LAST:event_btnClearActionPerformed

    private void clearInput(){
        tboxID.setText(UserIDIncrement.getCurrentSchedulerID());
        tboxPass.setText("");
        tboxName.setText("");
        rBtnMale.setSelected(false);
        rBtnFemale.setSelected(false);
        rBtnMale.setEnabled(true);
        rBtnFemale.setEnabled(true);
        tboxEmail.setText("");
        tboxPhone.setText("");
        lblPicture.setText("images/default.jpg");
        updateImageDisplay(lblPicture,"images/default.jpg",85);
        updateTable(tScheduler,schedulerList);
    }
    
    private void tSchedulerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tSchedulerMouseClicked
                int row = tScheduler.getSelectedRow();
                rBtnMale.setEnabled(false);
                rBtnFemale.setEnabled(false);
                DefaultTableModel model = (DefaultTableModel)tScheduler.getModel();
                tboxID.setText(model.getValueAt(row, 0).toString());
                tboxPass.setText(model.getValueAt(row, 1).toString());
                tboxName.setText(model.getValueAt(row, 2).toString());
                String gender = model.getValueAt(row, 3).toString();
                if (gender.equals("M")) {
                        rBtnMale.setSelected(true);
                    } else {
                        rBtnFemale.setSelected(true);
                    }
                tboxEmail.setText(model.getValueAt(row, 4).toString());
                tboxPhone.setText(model.getValueAt(row, 5).toString());
                lblPicture.setText(model.getValueAt(row, 7).toString());
                
                updateImageDisplay(lblPicture,model.getValueAt(row, 7).toString(),85);

    }//GEN-LAST:event_tSchedulerMouseClicked

    private void scrollPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrollPaneMouseClicked

    }//GEN-LAST:event_scrollPaneMouseClicked

    private void btnImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImageActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                String projectPath = System.getProperty("user.dir"); // current running application directory
                String fileName = selectedFile.getName(); 
                String fileExtension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                File destFile = new File(projectPath + "/images", tboxID.getText()+ fileExtension); // destination file path

                // Copy the file to the destination
                Files.copy(selectedFile.toPath(), destFile.toPath());

                // Construct the relative path
                String relativePath = "images/" + tboxID.getText()+ fileExtension;
                JOptionPane.showMessageDialog(null, "Image saved to: " + relativePath);
                System.out.println("Relative Path: " + relativePath);
                
                lblPicture.setText(relativePath);
                picture = relativePath;
                
                for (User user : schedulerList){
                    if (user.getUserId().equals(tboxID.getText())){
                        user.setPicture(relativePath);
                        break;
                    }
                }
                
                FileOperations.writeToFile(schedulerList, "scheduler.txt");
                updateTable(tScheduler,schedulerList);
                
                updateImageDisplay(lblPicture,relativePath,85);

            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to save the image.");
            }
        }
    }//GEN-LAST:event_btnImageActionPerformed

    private void btnImgDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImgDeleteActionPerformed

        String projectPath = System.getProperty("user.dir"); // current running application directory
        File fileToDelete = new File(projectPath, lblPicture.getText()); // get image path to delete
        
        // Check if the file exists and then delete it
        if (!lblPicture.getText().equals("images/default.jpg") && fileToDelete.exists()) {
            if (fileToDelete.delete()) {
                JOptionPane.showMessageDialog(null, "Image deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete the image.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Failed to delete the image.");
        }
        
        String selectedRow = tboxID.getText();
        for (User user : schedulerList){
            if (user.getUserId().equals(selectedRow)){
                user.setPicture("images/default.jpg");
                break;
            }
        }
        updateTable(tScheduler,schedulerList);
        FileOperations.writeToFile(schedulerList, "scheduler.txt");
        lblPicture.setText("images/default.jpg");
        updateImageDisplay(lblPicture,"images/default.jpg",85);

    }//GEN-LAST:event_btnImgDeleteActionPerformed

    private void tboxPass1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tboxPass1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tboxPass1ActionPerformed

    private void tboxEmail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tboxEmail1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tboxEmail1ActionPerformed

    private void rBtnMale1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnMale1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rBtnMale1ActionPerformed

    private void rBtnFemale1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnFemale1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rBtnFemale1ActionPerformed

    private void btnUnblockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnblockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUnblockActionPerformed

    private void btnBlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlockActionPerformed
        if (lblStatus.getText().equals("Blocked")){
            JOptionPane.showMessageDialog(null, "User is already blocked.");
        }
        String selectedRow = tboxID1.getText();
        modifyUser(selectedRow,"update",tUser,"blocked");
        schedulerList.clear();
        schedulerList = FileOperations.readFromFile("scheduler.txt");
        updateTable(tScheduler,schedulerList); //update scheduler table too
        btnBlock.setEnabled(false);
        btnUnblock.setEnabled(true);
    }//GEN-LAST:event_btnBlockActionPerformed

    public void modifyUser(String userId, String operation, JTable table) {
        modifyUser(userId, operation, table, null); // delete
    }
    
    private void modifyUser(String userId, String operation, JTable table,String status) {
        String fileName = getFileNameFromId(userId);
        ArrayList<User> userList = new ArrayList<>();
        userList = FileOperations.readFromFile(fileName); 
        boolean userFound = false;

        for (User user : userList) {
            if (user.getUserId().equals(userId)) {
                if (operation.equals("update")) {
                    user.setStatus(status);
                    lblStatus.setText(status.equals("blocked") ? "Blocked" : "Active");
                } else if (operation.equals("delete")) {
                    File imageFile = new File(user.getPicture());
                    if (!imageFile.getName().equals("default.jpg")) {
                        if (imageFile.exists() && imageFile.isFile()) {
                            if (imageFile.delete()) {
                                System.out.println("Deleted image file: " + imageFile.getPath());
                            } else {
                                System.out.println("Failed to delete image file: " + imageFile.getPath());
                            }
                        } else {
                        System.out.println("Image file not found: " + imageFile.getPath());
                        }
                    }
                    userList.remove(user);
                }
                userFound = true;
                break; 
            }
        }

        if (!userFound) {
            JOptionPane.showMessageDialog(null, "User not found.");
            return;
        }

        FileOperations.writeToFile(userList, fileName);
        // reload all users and update the table
        allUsers.clear();
        allUsers = FileOperations.loadAllUsers();
        updateTable(table, allUsers);
    }

    private String getFileNameFromId(String userId) {
        String userType = getUserTypeFromId(userId);

        switch (userType) {
            case "admin":
                return "admin.txt";
            case "scheduler":
                return "scheduler.txt";
            case "customer":
                return "customer.txt";
            case "manager":
                return "manager.txt";
            default:
                return null;
        }
    }
    
    private String getUserTypeFromId(String userId) {
        if (userId.startsWith("A")) {
            return "admin";
        } else if (userId.startsWith("S")) {
            return "scheduler";
        } else if (userId.startsWith("C")) {
            return "customer";
        } else if (userId.startsWith("M")) {
            return "manager";
        }
        return "unknown";
    }
    
    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        String selectedRow = tboxID1.getText();
        modifyUser(selectedRow,"delete",tUser);
        clearInput2();
    }//GEN-LAST:event_btnDelete1ActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        clearInput2();
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void clearInput2(){
        lblStatus.setText("ㅤ");
        tboxID1.setText("");
        tboxPass1.setText("");
        tboxName1.setText("");
        rBtnMale1.setSelected(false);
        rBtnFemale1.setSelected(false);
        rBtnMale1.setEnabled(true);
        rBtnFemale1.setEnabled(true);
        tboxEmail1.setText("");
        tboxPhone1.setText("");
        lblPicture1.setText("images/default.jpg");
        updateImageDisplay(lblPicture1,"images/default.jpg",85);
        updateTable(tUser,allUsers);
        btnBlock.setEnabled(false);
        btnUnblock.setEnabled(true);
    }
    
    private void tUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tUserMouseClicked
        int row = tUser.getSelectedRow();
                rBtnMale1.setEnabled(false);
                rBtnFemale1.setEnabled(false);
                DefaultTableModel model = (DefaultTableModel)tUser.getModel();
                tboxID1.setText(model.getValueAt(row, 0).toString());
                tboxPass1.setText(model.getValueAt(row, 1).toString());
                tboxName1.setText(model.getValueAt(row, 2).toString());
                String gender = model.getValueAt(row, 3).toString();
                if (gender.equals("M")) {
                        rBtnMale1.setSelected(true);
                    } else {
                        rBtnFemale1.setSelected(true);
                    }
                tboxEmail1.setText(model.getValueAt(row, 4).toString());
                tboxPhone1.setText(model.getValueAt(row, 5).toString());
                lblPicture1.setText(model.getValueAt(row, 7).toString());
                String status = model.getValueAt(row,6).toString();
                lblStatus.setText(status.equals("active")?"Active":"Blocked");
                if (status.equals("blocked")){
                    btnBlock.setEnabled(false);
                    btnUnblock.setEnabled(true);
                }else if (status.equals("active")){
                    btnUnblock.setEnabled(false);
                    btnBlock.setEnabled(true);
                }
                
                updateImageDisplay(lblPicture1,model.getValueAt(row, 7).toString(),85);
    }//GEN-LAST:event_tUserMouseClicked

    private void scrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_scrollPane1MouseClicked

    private void btnSchedulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSchedulerActionPerformed
        jTabbedPane.setSelectedIndex(0);
    }//GEN-LAST:event_btnSchedulerActionPerformed

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed
        jTabbedPane.setSelectedIndex(1);
    }//GEN-LAST:event_btnUserActionPerformed

    private void jTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneStateChanged

    }//GEN-LAST:event_jTabbedPaneStateChanged

    private void btnUnblockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUnblockMouseClicked
         if (lblStatus.getText().equals("Active")){
            JOptionPane.showMessageDialog(null, "User is already active.");
        }
        String selectedRow = tboxID1.getText();
        modifyUser(selectedRow,"update",tUser,"active");
        btnBlock.setEnabled(true);
        btnUnblock.setEnabled(false);
        schedulerList.clear();
        schedulerList = FileOperations.readFromFile("scheduler.txt");
        updateTable(tScheduler,schedulerList);
    }//GEN-LAST:event_btnUnblockMouseClicked

    private void btnBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookingActionPerformed
        jTabbedPane.setSelectedIndex(2);
    }//GEN-LAST:event_btnBookingActionPerformed

    private void btnFilter1_sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilter1_sActionPerformed
        updateTable(tScheduler,schedulerList,true,cboxUserID.getSelectedItem().toString(),"","");
    }//GEN-LAST:event_btnFilter1_sActionPerformed

    private void btnFilter1_uActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilter1_uActionPerformed
        updateTable(tUser,allUsers,true,cboxUserID_u.getSelectedItem().toString(),"","");
    }//GEN-LAST:event_btnFilter1_uActionPerformed

    private void btnFilter2_sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilter2_sActionPerformed
        updateTable(tScheduler,schedulerList,true,"",cboxStatus.getSelectedItem().toString(),"");
    }//GEN-LAST:event_btnFilter2_sActionPerformed

    private void btnReset_sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset_sActionPerformed
        updateTable(tScheduler,schedulerList);
    }//GEN-LAST:event_btnReset_sActionPerformed

    private void btnFilter2_uActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilter2_uActionPerformed
        updateTable(tUser,allUsers,true,"",cboxStatus_u.getSelectedItem().toString(),"");
    }//GEN-LAST:event_btnFilter2_uActionPerformed

    private void btnReset_uActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset_uActionPerformed
        updateTable(tUser,allUsers);
    }//GEN-LAST:event_btnReset_uActionPerformed

    private void btnFilter3_uActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilter3_uActionPerformed
        updateTable(tUser,allUsers,true,"","",cboxRole_u.getSelectedItem().toString());
    }//GEN-LAST:event_btnFilter3_uActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        currentUser.logout();
        System.out.println(currentUser);
        System.out.println(User.getLoggedInUser());
        System.out.println("Successfully Logged out.");
        this.dispose();
        Frame_Login login = new Frame_Login();
        login.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void tBookingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tBookingMouseClicked
        int row = tBooking.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)tBooking.getModel();
        tboxBID.setText(model.getValueAt(row, 0).toString());
        tboxCID.setText(model.getValueAt(row, 1).toString());
        tboxHallName.setText(model.getValueAt(row, 2).toString());
        tboxHallType.setText(model.getValueAt(row, 3).toString());
        tboxSDate.setText(model.getValueAt(row, 4).toString());
        tboxSTime.setText(model.getValueAt(row, 5).toString());
        tboxEDate.setText(model.getValueAt(row, 6).toString());
        tboxETime.setText(model.getValueAt(row, 7).toString());
        tboxAmount.setText(model.getValueAt(row, 8).toString());
    }//GEN-LAST:event_tBookingMouseClicked

    private void scrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_scrollPane2MouseClicked

    private void btnFilter1_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilter1_bActionPerformed

        boolean status = cboxUp.getSelectedItem().toString().equals("upcoming");
        updateTable_Booking(status,null);
    }//GEN-LAST:event_btnFilter1_bActionPerformed

    private void btnFilter2_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilter2_bActionPerformed
        updateTable_Booking(null, (String) cboxHall.getSelectedItem());
    }//GEN-LAST:event_btnFilter2_bActionPerformed

    private void btnReset_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset_bActionPerformed
        updateTable_Booking();
        tboxBID.setText("");
        tboxCID.setText("");
        tboxHallName.setText("");
        tboxHallType.setText("");
        tboxSDate.setText("");
        tboxSTime.setText("");
        tboxEDate.setText("");
        tboxETime.setText("");
        tboxAmount.setText("");
    }//GEN-LAST:event_btnReset_bActionPerformed

    private void tboxHallTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tboxHallTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tboxHallTypeActionPerformed

    private void tboxEDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tboxEDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tboxEDateActionPerformed

    private void cboxHallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxHallActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxHallActionPerformed

    private void updateImageDisplay(JLabel label,String relativePath, int size) {
        label.setText(relativePath);

        ImageIcon originalIcon = new ImageIcon(relativePath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        label.setIcon(scaledIcon);
        label.setMaximumSize(new java.awt.Dimension(size, size));
        label.setPreferredSize(new java.awt.Dimension(size, size));
    }
    
    public void initialImage(JLabel label, String id){
        String currentID = id;
        String path1 = "images/" + currentID + ".jpg";
        String path2 = "images/" + currentID + ".png";
        File file1 = new File(path1);
        File file2 = new File(path2);
        if (file1.exists()){
            updateImageDisplay(label,path1,85);
        }else if (file2.exists()){
            updateImageDisplay(label,path2,85);
        }else{
            updateImageDisplay(label,"images/default.jpg",85);
        }
    }
    
    
    
    private void disableInput(){
        tboxID1.setEnabled(false);
        tboxPass1.setEnabled(false);
        tboxName1.setEnabled(false);
        rBtnMale1.setEnabled(false);
        rBtnFemale1.setEnabled(false);
        rBtnMale1.setEnabled(false);
        rBtnFemale1.setEnabled(false);
        tboxEmail1.setEnabled(false);
        tboxPhone1.setEnabled(false);
    }

    
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
            java.util.logging.Logger.getLogger(Frame_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            // Set the look and feel to Windows
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame_Admin().setVisible(true);
            }
        });
    }
    
    class AlternatingRowRenderer extends DefaultTableCellRenderer {
        private final Color green1 = new Color(247, 255, 244, 255); 
        private final Color green2 = new Color(0, 204, 0, 255);     
        private final Color yellow1 = new Color(254, 255, 241, 255); 

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            String columnName = table.getColumnModel().getColumn(column).getHeaderValue().toString();
            cell.setForeground(Color.BLACK);

            if (!isSelected) {  
                if (row % 2 == 0) {
                    cell.setBackground(green1);
                } else {
                    cell.setBackground(yellow1);
                }
            }

            if ("Status".equals(columnName) && value != null) {
                String status = value.toString();
                if (status.equalsIgnoreCase("active")) {
                    cell.setForeground(green2); 
                } else if (status.equalsIgnoreCase("blocked")) {
                    cell.setForeground(Color.RED); 
                } else {
                    cell.setForeground(Color.BLACK);  
                }
            }


            if ("Start Date".equals(columnName) && value != null) {
                String date = value.toString();
                if (LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(LocalDate.now())) {
                    cell.setForeground(green2);  
                } else if (LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isBefore(LocalDate.now())) {
                    cell.setForeground(Color.RED);  
                } else {
                    cell.setForeground(Color.BLACK);  
                }
            }

            if (isSelected) {
                cell.setBackground(table.getSelectionBackground());
                cell.setForeground(table.getSelectionForeground());
            }

            return cell;
        }
    }
        
   

    private void updateTable_Booking() {
        updateTable_Booking(null, null); // No filter
    }

    private void updateTable_Booking(Boolean isUpcoming, String hallType) {
        DefaultTableModel model = (DefaultTableModel) tBooking.getModel();
        model.setRowCount(0);  
        allBookings.clear(); 

        try (BufferedReader reader = new BufferedReader(new FileReader("booking.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 7) {
                    System.out.println("Skipping line due to insufficient data: " + line);
                    continue; 
                }
                String bID = parts[0];
                String cID = parts[1];
                String hName = parts[2];
                String hType = parts[3];
                String sDateTime = parts[4];
                String eDateTime = parts[5];
                String amount = parts[6];
                String sDate = sDateTime.split(" ")[0];
                String sTime = sDateTime.split(" ")[1];
                String eDate = eDateTime.split(" ")[0];
                String eTime = eDateTime.split(" ")[1];

                LocalDate startDate = LocalDate.parse(sDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                LocalDate today = LocalDate.now();

                boolean matchesHallType = hallType == null || hType.equals(hallType);
                boolean matchesDate = isUpcoming == null || (isUpcoming ? 
                    !startDate.isBefore(today) : 
                    startDate.isBefore(today)); 


                if (matchesHallType && matchesDate) {
                    String[] row = {bID, cID, hName, hType, sDate, sTime, eDate, eTime, amount};
                    model.addRow(row);
                    allBookings.add(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_Booking;
    private javax.swing.JPanel Panel_Scheduler;
    private javax.swing.JPanel Panel_User;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBlock;
    private javax.swing.JButton btnBooking;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnFilter1_b;
    private javax.swing.JButton btnFilter1_s;
    private javax.swing.JButton btnFilter1_u;
    private javax.swing.JButton btnFilter2_b;
    private javax.swing.JButton btnFilter2_s;
    private javax.swing.JButton btnFilter2_u;
    private javax.swing.JButton btnFilter3_u;
    private javax.swing.JButton btnImage;
    private javax.swing.JButton btnImgDelete;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnReset_b;
    private javax.swing.JButton btnReset_s;
    private javax.swing.JButton btnReset_u;
    private javax.swing.JButton btnScheduler;
    private javax.swing.JButton btnUnblock;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUser;
    private javax.swing.JComboBox<String> cboxHall;
    private javax.swing.JComboBox<String> cboxRole_u;
    private javax.swing.JComboBox<String> cboxStatus;
    private javax.swing.JComboBox<String> cboxStatus_u;
    private javax.swing.JComboBox<String> cboxUp;
    private javax.swing.JComboBox<String> cboxUserID;
    private javax.swing.JComboBox<String> cboxUserID_u;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JLabel lblPicture;
    private javax.swing.JLabel lblPicture1;
    private javax.swing.JLabel lblProfileID;
    private javax.swing.JLabel lblProfilePic;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPanel pInfo;
    private javax.swing.JPanel pInfo1;
    private javax.swing.JPanel pInfo2;
    private javax.swing.JPanel pPicture;
    private javax.swing.JPanel pPicture1;
    private javax.swing.JRadioButton rBtnFemale;
    private javax.swing.JRadioButton rBtnFemale1;
    private javax.swing.JRadioButton rBtnMale;
    private javax.swing.JRadioButton rBtnMale1;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JScrollPane scrollPane2;
    private javax.swing.JTable tBooking;
    private javax.swing.JTable tScheduler;
    private javax.swing.JTable tUser;
    private javax.swing.JTextField tboxAmount;
    private javax.swing.JTextField tboxBID;
    private javax.swing.JTextField tboxCID;
    private javax.swing.JTextField tboxEDate;
    private javax.swing.JTextField tboxETime;
    private javax.swing.JTextField tboxEmail;
    private javax.swing.JTextField tboxEmail1;
    private javax.swing.JTextField tboxHallName;
    private javax.swing.JTextField tboxHallType;
    private javax.swing.JTextField tboxID;
    private javax.swing.JTextField tboxID1;
    private javax.swing.JTextField tboxName;
    private javax.swing.JTextField tboxName1;
    private javax.swing.JTextField tboxPass;
    private javax.swing.JTextField tboxPass1;
    private javax.swing.JTextField tboxPhone;
    private javax.swing.JTextField tboxPhone1;
    private javax.swing.JTextField tboxSDate;
    private javax.swing.JTextField tboxSTime;
    // End of variables declaration//GEN-END:variables
}
