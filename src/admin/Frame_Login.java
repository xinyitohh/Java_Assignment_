package admin;

import scheduler.*;
import customer.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.io.*;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JFileChooser;
import javax.swing.SwingWorker;

public class Frame_Login extends javax.swing.JFrame implements DataValidator_admin{

    private Component frame;
    private File profilePictureFile;
    private final javax.swing.ButtonGroup genderButtonGroup;

    public Frame_Login() {
        initComponents();
        genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(radMale);
        genderButtonGroup.add(radFemale);
        ImageIcon originalIcon = (ImageIcon) lblLogo.getIcon();
        Image scaledImage = originalIcon.getImage().getScaledInstance(700, 370, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
 
        lblLogo.setIcon(scaledIcon);

    }
    @SuppressWarnings("unchecked")
    
    String verificationCode;
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnScheduler = new javax.swing.JButton();
        btnUser = new javax.swing.JButton();
        jTabbedPane = new javax.swing.JTabbedPane();
        pLogin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tboxUserID = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        lblForget = new javax.swing.JLabel();
        tboxPass = new javax.swing.JPasswordField();
        lblLogo = new javax.swing.JLabel();
        pRegister = new javax.swing.JPanel();
        txtConfirmPss = new javax.swing.JPasswordField();
        btnSubmit = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnUpload = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        radMale = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        radFemale = new javax.swing.JRadioButton();
        txtPss = new javax.swing.JPasswordField();
        pForget = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tboxUserID1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tboxEmail = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblSend = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pVerification = new javax.swing.JPanel();
        tboxVerify = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnVerify = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        pReset = new javax.swing.JPanel();
        tboxUserID2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tboxReset = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(108, 91, 123));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(37, 5, 62));

        btnScheduler.setBackground(new java.awt.Color(89, 14, 147));
        btnScheduler.setForeground(new java.awt.Color(255, 255, 255));
        btnScheduler.setText("Login");
        btnScheduler.setBorder(null);
        btnScheduler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSchedulerActionPerformed(evt);
            }
        });

        btnUser.setBackground(new java.awt.Color(89, 14, 147));
        btnUser.setForeground(new java.awt.Color(255, 255, 255));
        btnUser.setText("Register");
        btnUser.setBorder(null);
        btnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnUser, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnScheduler, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(btnScheduler, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUser, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 630));

        pLogin.setBackground(new java.awt.Color(108, 91, 123));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("User ID");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");

        tboxUserID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblForget.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblForget.setForeground(new java.awt.Color(255, 255, 255));
        lblForget.setText("<html><u>Forget Password?</u></html>");
        lblForget.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblForgetMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblForgetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblForgetMouseExited(evt);
            }
        });

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logo_home.png"))); // NOI18N
        lblLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pLoginLayout = new javax.swing.GroupLayout(pLogin);
        pLogin.setLayout(pLoginLayout);
        pLoginLayout.setHorizontalGroup(
            pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pLoginLayout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(pLoginLayout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addGroup(pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pLoginLayout.createSequentialGroup()
                        .addGroup(pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tboxUserID)
                            .addComponent(tboxPass, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblForget, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pLoginLayout.setVerticalGroup(
            pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pLoginLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tboxUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tboxPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblForget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnLogin)
                .addContainerGap(138, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("tab2", pLogin);

        pRegister.setBackground(new java.awt.Color(108, 91, 123));

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Phone Number");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Profile Picture");

        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Gender");

        radMale.setBackground(new java.awt.Color(108, 91, 123));
        radMale.setForeground(new java.awt.Color(255, 255, 255));
        radMale.setText("Male");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Registration");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Name");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Email");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Password");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Confirm Password");

        radFemale.setBackground(new java.awt.Color(108, 91, 123));
        radFemale.setForeground(new java.awt.Color(255, 255, 255));
        radFemale.setText("Female");

        javax.swing.GroupLayout pRegisterLayout = new javax.swing.GroupLayout(pRegister);
        pRegister.setLayout(pRegisterLayout);
        pRegisterLayout.setHorizontalGroup(
            pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 870, Short.MAX_VALUE)
            .addGroup(pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pRegisterLayout.createSequentialGroup()
                    .addGap(232, 232, 232)
                    .addGroup(pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11)
                        .addGroup(pRegisterLayout.createSequentialGroup()
                            .addGap(198, 198, 198)
                            .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pRegisterLayout.createSequentialGroup()
                            .addGap(41, 41, 41)
                            .addGroup(pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pRegisterLayout.createSequentialGroup()
                                    .addGroup(pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel14))
                                    .addGap(33, 33, 33)
                                    .addGroup(pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtConfirmPss, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPss, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(pRegisterLayout.createSequentialGroup()
                                    .addGroup(pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel10))
                                    .addGap(33, 33, 33)
                                    .addGroup(pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pRegisterLayout.createSequentialGroup()
                                            .addComponent(radMale, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(radFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addContainerGap(240, Short.MAX_VALUE)))
        );
        pRegisterLayout.setVerticalGroup(
            pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 635, Short.MAX_VALUE)
            .addGroup(pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pRegisterLayout.createSequentialGroup()
                    .addGap(102, 102, 102)
                    .addComponent(jLabel11)
                    .addGap(39, 39, 39)
                    .addGroup(pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(radMale)
                        .addComponent(radFemale))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpload)
                        .addComponent(jLabel9))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txtConfirmPss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(103, 103, 103)))
        );

        jTabbedPane.addTab("tab1", pRegister);

        pForget.setBackground(new java.awt.Color(108, 91, 123));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password Reset");

        tboxUserID1.setEditable(false);
        tboxUserID1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Email");

        tboxEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblSend.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblSend.setForeground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("User ID");

        javax.swing.GroupLayout pForgetLayout = new javax.swing.GroupLayout(pForget);
        pForget.setLayout(pForgetLayout);
        pForgetLayout.setHorizontalGroup(
            pForgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pForgetLayout.createSequentialGroup()
                .addGroup(pForgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pForgetLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pForgetLayout.createSequentialGroup()
                        .addGap(297, 297, 297)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pForgetLayout.createSequentialGroup()
                        .addGap(312, 312, 312)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(497, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pForgetLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pForgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pForgetLayout.createSequentialGroup()
                        .addComponent(lblSend, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(381, 381, 381))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pForgetLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(338, 338, 338))))
            .addGroup(pForgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pForgetLayout.createSequentialGroup()
                    .addGap(394, 394, 394)
                    .addGroup(pForgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pForgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tboxUserID1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tboxEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(310, Short.MAX_VALUE)))
        );
        pForgetLayout.setVerticalGroup(
            pForgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pForgetLayout.createSequentialGroup()
                .addContainerGap(184, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(41, 41, 41)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(20, 20, 20)
                .addComponent(lblSend, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(201, 201, 201)
                .addComponent(btnBack)
                .addGap(41, 41, 41))
            .addGroup(pForgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pForgetLayout.createSequentialGroup()
                    .addGap(257, 257, 257)
                    .addComponent(tboxUserID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(tboxEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(34, 34, 34)
                    .addComponent(btnSend)
                    .addContainerGap(257, Short.MAX_VALUE)))
        );

        jTabbedPane.addTab("tab3", pForget);

        pVerification.setBackground(new java.awt.Color(108, 91, 123));

        tboxVerify.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Verification Code");

        btnVerify.setText("Verify");
        btnVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Password Reset");

        javax.swing.GroupLayout pVerificationLayout = new javax.swing.GroupLayout(pVerification);
        pVerification.setLayout(pVerificationLayout);
        pVerificationLayout.setHorizontalGroup(
            pVerificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pVerificationLayout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(488, 488, 488))
            .addGroup(pVerificationLayout.createSequentialGroup()
                .addGap(369, 369, 369)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pVerificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pVerificationLayout.createSequentialGroup()
                    .addContainerGap(453, Short.MAX_VALUE)
                    .addGroup(pVerificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tboxVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(300, 300, 300)))
        );
        pVerificationLayout.setVerticalGroup(
            pVerificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pVerificationLayout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(jLabel17)
                .addGap(40, 40, 40)
                .addComponent(jLabel7)
                .addContainerGap(335, Short.MAX_VALUE))
            .addGroup(pVerificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pVerificationLayout.createSequentialGroup()
                    .addGap(276, 276, 276)
                    .addComponent(tboxVerify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(34, 34, 34)
                    .addComponent(btnVerify)
                    .addContainerGap(276, Short.MAX_VALUE)))
        );

        jTabbedPane.addTab("tab5", pVerification);

        pReset.setBackground(new java.awt.Color(108, 91, 123));

        tboxUserID2.setEditable(false);
        tboxUserID2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("User ID");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("New Password");

        tboxReset.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Password Reset");

        javax.swing.GroupLayout pResetLayout = new javax.swing.GroupLayout(pReset);
        pReset.setLayout(pResetLayout);
        pResetLayout.setHorizontalGroup(
            pResetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pResetLayout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(jLabel6)
                .addContainerGap(454, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pResetLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(322, 322, 322))
            .addGroup(pResetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pResetLayout.createSequentialGroup()
                    .addGap(300, 300, 300)
                    .addGroup(pResetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pResetLayout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(58, 58, 58)
                            .addGroup(pResetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tboxUserID2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tboxReset, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(270, Short.MAX_VALUE)))
        );
        pResetLayout.setVerticalGroup(
            pResetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pResetLayout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jLabel18)
                .addGap(87, 87, 87)
                .addComponent(jLabel6)
                .addContainerGap(317, Short.MAX_VALUE))
            .addGroup(pResetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pResetLayout.createSequentialGroup()
                    .addGap(256, 256, 256)
                    .addGroup(pResetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(tboxUserID2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(tboxReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(34, 34, 34)
                    .addComponent(btnReset)
                    .addContainerGap(257, Short.MAX_VALUE)))
        );

        jTabbedPane.addTab("tab4", pReset);

        getContentPane().add(jTabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, -40, 870, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public boolean validateInput(){
        // name
        if (txtName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name cannot be empty.");
            return false;
        }

        // email
        String email = txtEmail.getText().trim();
        if (email.isEmpty() || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            JOptionPane.showMessageDialog(this, "Invalid email format.");
            return false;
        }

        // phone
        String phone = txtPhone.getText().trim();
        if (phone.isEmpty() || !phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Phone number must be 10 digits.");
            return false;
        }

        // gender
        if (!radMale.isSelected() && !radFemale.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please select a gender.");
            return false;
        }

        // password
        String password = new String(txtPss.getPassword());
        String confirmPassword = new String(txtConfirmPss.getPassword());
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password fields cannot be empty.");
            return false;
        }

        // password match
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.");
            return false;
        }
        
        return true;
    };
    
    private void btnSchedulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSchedulerActionPerformed
        jTabbedPane.setSelectedIndex(0);
    }//GEN-LAST:event_btnSchedulerActionPerformed

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed
        jTabbedPane.setSelectedIndex(1);
    }//GEN-LAST:event_btnUserActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String name = tboxUserID.getText();
        String pass = new String(tboxPass.getPassword());
        User tempUser = null;
        if (name.startsWith("S")){
            tempUser = new Scheduler(name,pass);
        }else if (name.startsWith("C")){
            tempUser = new Customer(name,pass);
        }else if (name.startsWith("A")){
            tempUser = new Admin(name,pass);
        }else if (name.startsWith("M")){
            tempUser = new Manager(name,pass);
        }
        
        if (tempUser != null){
            User currentUser = tempUser.login();
            if (currentUser != null){
                if (currentUser.getStatus().equals("active")){
                    System.out.println("Login successful: "+currentUser.getUserId());
                    System.out.println(currentUser);
                    User.setLoggedInUser(currentUser);

                    JFrame mainFrame = getFrameForUser(currentUser);
                    if (mainFrame != null){
                        mainFrame.setVisible(true);
                        this.dispose();
                    }
                }else{
                    JOptionPane.showMessageDialog(this,"User blocked","Login Error",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(this,"Invalid User ID / Password","Login Error",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this,"Invalid User ID / Password","Login Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        String enteredUserId = tboxUserID1.getText();
        String enteredEmail = tboxEmail.getText();

        User tempUser = null;
        if (enteredUserId.startsWith("S")) {
            tempUser = new Scheduler(enteredUserId, "");
        } else if (enteredUserId.startsWith("C")) {
            tempUser = new Customer(enteredUserId, "");
        } else if (enteredUserId.startsWith("A")) {
            tempUser = new Admin(enteredUserId, "");
        } else if (enteredUserId.startsWith("M")) {
            tempUser = new Manager(enteredUserId, "");
        }


        if (tempUser != null && tempUser.verifyEmail(enteredEmail)) {
            lblSend.setText("Sending...");
            btnSend.setEnabled(false);

            // Perform the email sending in a background thread
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {

                    verificationCode = String.format("%06d", new java.util.Random().nextInt(999999));

                    Email email = new Email();
                    email.sendVerificationCode(enteredEmail, verificationCode);

                    return null;
                }

                @Override
                protected void done() {
                    JOptionPane.showMessageDialog(null, "Verification code sent to " + enteredEmail, "Verification Sent", JOptionPane.INFORMATION_MESSAGE);
                    jTabbedPane.setSelectedIndex(3);
                    lblSend.setText("");
                    btnSend.setEnabled(true);
                    tboxUserID1.setText("");
                }
            };

            // Start the background task
            worker.execute();
            
        } else {
            JOptionPane.showMessageDialog(this, "User ID and email do not match", "Verification Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnSendActionPerformed

    private void lblForgetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblForgetMouseClicked
        tboxUserID1.setText(tboxUserID.getText());
        tboxUserID2.setText(tboxUserID.getText());
        jTabbedPane.setSelectedIndex(2);
        
    }//GEN-LAST:event_lblForgetMouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        jTabbedPane.setSelectedIndex(0);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        String fileName = "";
        if (tboxUserID2.getText().startsWith("S")){
            fileName = "scheduler.txt";
        }else if(tboxUserID2.getText().startsWith("A")){
            fileName = "admin.txt";
        }else if(tboxUserID2.getText().startsWith("C")){
            fileName = "customer.txt";
        }else if(tboxUserID2.getText().startsWith("M")){
            fileName = "manager.txt";
        }
        
        ArrayList<User> userList = FileOperations.readFromFile(fileName);
        for (User user : userList) {
            if (user.getUserId().equals(tboxUserID2.getText())) {
                user.setPassword(tboxReset.getText());
                break;
            }
        }
        FileOperations.writeToFile(userList,fileName);
        JOptionPane.showMessageDialog(this, "Password reset successfully", "Reset", JOptionPane.INFORMATION_MESSAGE);
        jTabbedPane.setSelectedIndex(0);
        tboxPass.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyActionPerformed
        if (tboxVerify.getText().equals(verificationCode)){
            JOptionPane.showMessageDialog(this, "Verified Successfully.", "Verification Verified", JOptionPane.INFORMATION_MESSAGE);
            jTabbedPane.setSelectedIndex(4);
        }else{
            JOptionPane.showMessageDialog(this, "Verification code does not match.", "Verification Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnVerifyActionPerformed

    private void lblForgetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblForgetMouseEntered
        lblForget.setForeground(Color.lightGray);
        lblForget.setFont(lblForget.getFont().deriveFont(Font.ITALIC | Font.BOLD));
        lblForget.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblForgetMouseEntered

    private void lblForgetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblForgetMouseExited
        lblForget.setForeground(Color.WHITE);
        lblForget.setFont(lblForget.getFont().deriveFont(Font.ITALIC ));
        lblForget.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblForgetMouseExited

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed

        registerUser();

    }//GEN-LAST:event_btnSubmitActionPerformed


    
    private void registerUser() {
        try{
            String name = txtName.getText();
            String email = txtEmail.getText();
            String password = new String(txtPss.getPassword());
            String confirmPassword = new String(txtConfirmPss.getPassword());
            String phone = txtPhone.getText();
            String gender = radMale.isSelected()? "Male" : (radFemale.isSelected()? "Female" : "");
            String pfpFilename = profilePictureFile != null ? profilePictureFile.getName() : "images/default.jpg";

            if (!validateInput())return;

            String customerId = UserIDIncrement.getNextCustomerID();
            if (!pfpFilename.equals("images/default.jpg") && profilePictureFile != null) {
                String newPfpFilename = "images/" + customerId + ".jpg";

                File newFile = new File(newPfpFilename);
                profilePictureFile.renameTo(newFile);

                pfpFilename = newPfpFilename;
            }
        User customer = new Customer(customerId, password, name, gender, email, phone, pfpFilename);
        ArrayList<User> customerList = FileOperations.readFromFile("customer.txt");
        customerList.add(customer);
        FileOperations.writeToFile(customerList,"customer.txt");

        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtPss.setText("");
        txtConfirmPss.setText("");
        radMale.setSelected(false);
        radFemale.setSelected(false);
        JOptionPane.showMessageDialog(this, "Registraion Successful!\nUser ID: "+customerId, "Registration", JOptionPane.INFORMATION_MESSAGE);
        jTabbedPane.setSelectedIndex(0);
        
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(frame, "An error occurred during registration: " + ex.getMessage());
        }
    }
    
    
    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            File directory = new File("images");
            if (!directory.exists()) {
                directory.mkdirs(); 
            }

            File destinationFile = new File(directory, selectedFile.getName());

            try {
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(selectedFile));
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destinationFile))) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = bis.read(buffer)) != -1) {
                        bos.write(buffer, 0, bytesRead);
                    }
                }
                profilePictureFile = destinationFile; 
                System.out.println("Profile picture saved to: " + destinationFile.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("An error occurred while saving the file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("File selection canceled.");
        }
        
        
    }//GEN-LAST:event_btnUploadActionPerformed

    int mouseClick = 0;
    private void lblLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoMouseClicked
        mouseClick++;
        System.out.println(mouseClick);
        if (mouseClick >= 3){
            User admin = new Admin("A1001","a","a","a","a","a","images/A1001.jpg","active");
            User.setLoggedInUser(admin);
                    
                    
                    JFrame mainFrame = getFrameForUser(admin);
                    if (mainFrame != null){
                        mainFrame.setVisible(true);
                        this.dispose();
                    }
        }
    }//GEN-LAST:event_lblLogoMouseClicked

    
    private JFrame getFrameForUser(User user) {
        if (user instanceof Scheduler) {
            return new scheduler.Scheduler_Home();
        } else if (user instanceof Admin) {
            return new Frame_Admin();
        } else if (user instanceof Manager) {
            return new Manager.Frame_Main();
        } else if (user instanceof Customer) {
            return new customer.CustomerDashboard();
        }
        return null;
    }
    
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
            java.util.logging.Logger.getLogger(Frame_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
            try {
            // Set the look and feel to Windows
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame_Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnScheduler;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnUpload;
    private javax.swing.JButton btnUser;
    private javax.swing.JButton btnVerify;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JLabel lblForget;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblSend;
    private javax.swing.JPanel pForget;
    private javax.swing.JPanel pLogin;
    private javax.swing.JPanel pRegister;
    private javax.swing.JPanel pReset;
    private javax.swing.JPanel pVerification;
    private javax.swing.JRadioButton radFemale;
    private javax.swing.JRadioButton radMale;
    private javax.swing.JTextField tboxEmail;
    private javax.swing.JPasswordField tboxPass;
    private javax.swing.JTextField tboxReset;
    private javax.swing.JTextField tboxUserID;
    private javax.swing.JTextField tboxUserID1;
    private javax.swing.JTextField tboxUserID2;
    private javax.swing.JTextField tboxVerify;
    private javax.swing.JPasswordField txtConfirmPss;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JPasswordField txtPss;
    // End of variables declaration//GEN-END:variables
}
