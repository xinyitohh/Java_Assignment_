package scheduler;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author yeo yu le
 */

import javax.swing.UIManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.*;
import java.text.ParseException;
import javax.swing.border.TitledBorder;
import java.util.Date;
import java.util.Calendar;
import java.util.Collections;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Hall_Availability extends javax.swing.JFrame implements EditListener{
    /**
     * Creates new form Hall_Availability
     */
    JButton button;
    ArrayList<Schedule> schedule;
    ArrayList<JPanel> panelObj = new ArrayList<>();
    ArrayList<JLabel> labelObj = new ArrayList<>();
    private DataValidation validater;
    private SetImage imageSetter = new ImageSetter();

    
    @SuppressWarnings("empty-statement")
    public Hall_Availability() {
        initComponents();

        imageSetter.SetImageSize(lblHall4,lblSchedule4);
        System.out.println(lblHall4.getHorizontalTextPosition());
        System.out.println(lblSchedule4.getHorizontalTextPosition());
        
        Schedule s = new Schedule();
        s.showTime(cboStartHour, txtAmPm1);
        s.showTime(cboEndHour, txtAmPm2);
        
        Date today = new Date();
        dteStart.setDate(today);
        dteEnd.setDate(today);        
        
        cboScheduleType.setSelectedIndex(0);
        
        Hall h = new Hall();
        h.LoadHallName(cboHallName);
        h.LoadHallName(cboFilterHallName);
                           
        setupLayout();
    }
    
    private void setupLayout() {
        pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.Y_AXIS));

        try {
            schedule = FileOperation.ReadSchedule("Schedule.txt");
        } catch (IOException ex) {
            System.out.println(ex);
        }

        // Initially render without filtering
        filterSchedule("", -1); // Render all without filtering
    }

    private void filterSchedule(String hallName, int month) {
        pnlButton.removeAll();

        ArrayList<Date> dateList = CompareDate();
        SimpleDateFormat dformat = new SimpleDateFormat("dd-MM-yyyy");

        for (Date d : dateList) {
            // Remove the time in date for better comparison
            LocalDate today = LocalDate.now();
            LocalDate dateInList = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            
            // Filter the data before todat only
            if (!dateInList.isBefore(today)) { 
                Calendar cal = Calendar.getInstance();
                cal.setTime(d);
                int scheduleMonth = cal.get(Calendar.MONTH);

                if (month != -1 && scheduleMonth != month) continue;

                JLabel dateLabel = new JLabel();
                dateLabel.setFont(new Font("Arial", Font.BOLD, 15));
                dateLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 10, 0));
                pnlButton.add(dateLabel);

                // Loop through all hall names in the combo box
                for (int i = 0; i < cboHallName.getItemCount(); i++) {
                    String currentHallName = cboHallName.getItemAt(i);

                    if (!hallName.isEmpty() && !currentHallName.equals(hallName)) continue;

                    ArrayList<String> button_text = new ArrayList<>();
                    ArrayList<Integer> position = new ArrayList<>();

                    for (int j = 0; j < schedule.size(); j++) {
                        Schedule line = schedule.get(j);

                        try {
                            Date start = dformat.parse(line.getStart_Date());
                            Date end = dformat.parse(line.getEnd_Date());

                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(start);

                            while (!calendar.getTime().after(end)) {
                                if (d.equals(calendar.getTime()) && line.getHall_Name().equals(currentHallName)) {
                                    button_text.add(line.getStart_Time() + " - " + line.getEnd_Time());
                                    position.add(j);
                                }
                                calendar.add(Calendar.DATE, 1);
                            }
                        } catch (ParseException ex) {
                            System.out.println(ex);
                        }
                    }

                    if (!button_text.isEmpty()) {
                        JPanel panel = new JPanel();
                        panel.setBorder(BorderFactory.createTitledBorder(currentHallName));
                        panel.setBackground(new Color(255, 102, 102));
                        createButton(button_text, panel, position);
                        pnlButton.add(panel);
                        if (dateLabel.getText().equals("")){
                            dateLabel.setText(dformat.format(d));
                        }
                    }    
                }
                
            }
        }

        pnlButton.revalidate();
        pnlButton.repaint();
    }
    
    private void createButton(ArrayList<String> button_info, JPanel panel, ArrayList<Integer> position) {
        panel.setLayout(new GridBagLayout());  // Layout for buttons in the panel
        GridBagConstraints gbc = new GridBagConstraints();

        int fixedButtonHeight = 30;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15);  // Set padding for buttons

        // Loop through the button info to create buttons dynamically
        for (int i = 0; i < button_info.size(); i++) {
            JButton button = new JButton(button_info.get(i));
            String type = schedule.get(position.get(i)).getSchedule_Type();
            if (type.equals("Maintenance")){
                button.setBackground(new Color(204,255,204));
            }
            else if(type.equals("Reserved")){
                button.setBackground(new Color(153,102,255));
            }
            
            Dimension preferredSize = button.getPreferredSize();
            int adjustedWidth = Math.max(100, preferredSize.width + 20);

            button.setPreferredSize(new Dimension(adjustedWidth, fixedButtonHeight));

            gbc.gridx = i % 3;  // Arrange buttons in 3 columns
            gbc.gridy = i / 3;

            panel.add(button, gbc);

            Hall_Availability_Operation new_hall = new Hall_Availability_Operation(position.get(i),this);
            // Add action listener for the buttons
            button.addActionListener(e -> new_hall.setVisible(true));
            System.out.println("Button created" + button_info.get(i));
        }
    }

    private ArrayList<Date> CompareDate() {
        ArrayList<Date> dateList = new ArrayList<>();

        if (schedule != null) {
            for (int i = 0; i < schedule.size(); i++) {
                Schedule line = schedule.get(i);

                try {
                    SimpleDateFormat dformat = new SimpleDateFormat("dd-MM-yyyy");

                    Date start = dformat.parse(line.getStart_Date());
                    Date end = dformat.parse(line.getEnd_Date());

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(start);

                    // Add all dates from start to end
                    while (!calendar.getTime().after(end)) {
                        Date current = calendar.getTime();

                        if (!dateList.contains(current)) {
                            dateList.add(current);
                        }
                        calendar.add(Calendar.DATE, 1);
                    }
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
            }
        }
        Collections.sort(dateList);  // Sort the date list in ascending order
        return dateList;
    }

    public void EditView(){
        setupLayout();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNav4 = new javax.swing.JPanel();
        lblHall4 = new javax.swing.JLabel();
        lblSchedule4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblRemark = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtfRemarks = new javax.swing.JTextArea();
        pnlStart = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboStartHour = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        spnStartMinutes = new javax.swing.JSpinner();
        txtAmPm1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboEndHour = new javax.swing.JComboBox<>();
        spnEndMinutes = new javax.swing.JSpinner();
        txtAmPm2 = new javax.swing.JTextField();
        dteStart = new com.toedter.calendar.JDateChooser();
        dteEnd = new com.toedter.calendar.JDateChooser();
        btnAdd = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cboHallName = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cboScheduleType = new javax.swing.JComboBox<>();
        scrSchedule = new javax.swing.JScrollPane();
        pnlButton = new javax.swing.JPanel();
        btnReset = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cboFilterHallName = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        btnFilter = new javax.swing.JButton();
        btnFilterReset = new javax.swing.JButton();
        mthMonth = new com.toedter.calendar.JMonthChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlNav4.setBackground(new java.awt.Color(37, 5, 62));

        lblHall4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHall4.setForeground(new java.awt.Color(255, 255, 255));
        lblHall4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHall4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scheduler/icons8-hall-96.png"))); // NOI18N
        lblHall4.setText("Hall Manager");
        lblHall4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblHall4.setPreferredSize(new java.awt.Dimension(69, 48));
        lblHall4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblHall4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHall4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHall4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHall4MouseExited(evt);
            }
        });

        lblSchedule4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSchedule4.setForeground(new java.awt.Color(255, 255, 255));
        lblSchedule4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSchedule4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scheduler/schedule_icon.png"))); // NOI18N
        lblSchedule4.setText("Schedule");
        lblSchedule4.setToolTipText("");
        lblSchedule4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblSchedule4.setMaximumSize(new java.awt.Dimension(62, 60));
        lblSchedule4.setMinimumSize(new java.awt.Dimension(62, 60));
        lblSchedule4.setPreferredSize(new java.awt.Dimension(62, 60));
        lblSchedule4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        lblRemark.setText("Remarks");

        txtfRemarks.setColumns(20);
        txtfRemarks.setRows(5);
        jScrollPane1.setViewportView(txtfRemarks);

        pnlStart.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Start Date & Time"));

        jLabel1.setText("Start Date");

        cboStartHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8", "9", "10", "11", "12", "1", "2", "3", "4", "5", "6" }));
        cboStartHour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboStartHourActionPerformed(evt);
            }
        });

        jLabel2.setText("Start Time");

        spnStartMinutes.setModel(new javax.swing.SpinnerNumberModel(00, 00,59 , 1)
        );
        spnStartMinutes.setEditor(new javax.swing.JSpinner.NumberEditor(spnStartMinutes, "00"));

        txtAmPm1.setEditable(false);

        jLabel4.setText("End Date");

        jLabel5.setText("End Time");

        cboEndHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8", "9", "10", "11", "12", "1", "2", "3", "4", "5", "6" }));
        cboEndHour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEndHourActionPerformed(evt);
            }
        });

        spnEndMinutes.setModel(new javax.swing.SpinnerNumberModel(00, 00,59 , 1)
        );
        spnEndMinutes.setEditor(new javax.swing.JSpinner.NumberEditor(spnEndMinutes, "00"));

        txtAmPm2.setEditable(false);

        dteStart.setDateFormatString("dd-MM-yyyy");

        dteEnd.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout pnlStartLayout = new javax.swing.GroupLayout(pnlStart);
        pnlStart.setLayout(pnlStartLayout);
        pnlStartLayout.setHorizontalGroup(
            pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStartLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStartLayout.createSequentialGroup()
                        .addGroup(pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlStartLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(cboStartHour, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnStartMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAmPm1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlStartLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(dteStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(dteEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlStartLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(22, 22, 22)
                        .addComponent(cboEndHour, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnEndMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAmPm2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        pnlStartLayout.setVerticalGroup(
            pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStartLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStartLayout.createSequentialGroup()
                        .addGroup(pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel4))
                            .addComponent(dteStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(spnStartMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAmPm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboStartHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dteEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(spnEndMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAmPm2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboEndHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Hall Name");

        jLabel8.setText("Schedule Type");

        cboScheduleType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Availability Slot", "Maintenance", "Reserved" }));
        cboScheduleType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboScheduleTypeActionPerformed(evt);
            }
        });

        scrSchedule.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrSchedule.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        pnlButton.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout pnlButtonLayout = new javax.swing.GroupLayout(pnlButton);
        pnlButton.setLayout(pnlButtonLayout);
        pnlButtonLayout.setHorizontalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
        );
        pnlButtonLayout.setVerticalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 557, Short.MAX_VALUE)
        );

        scrSchedule.setViewportView(pnlButton);

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filter", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(153, 102, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 17, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        jLabel7.setText("Hall Name");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Maintenance");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Reserved");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Availability Slot");

        jLabel11.setText("Month");

        btnFilter.setText("Filter");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        btnFilterReset.setText("Reset");
        btnFilterReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(cboFilterHallName, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mthMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addGap(75, 75, 75)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(55, 55, 55)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFilterReset, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mthMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(cboFilterHallName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10))
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFilterReset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(18, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(cboScheduleType, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel6)
                        .addGap(38, 38, 38)
                        .addComponent(cboHallName, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRemark)
                            .addComponent(pnlStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrSchedule)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cboScheduleType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cboHallName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addComponent(pnlStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblRemark)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(scrSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlNav4Layout = new javax.swing.GroupLayout(pnlNav4);
        pnlNav4.setLayout(pnlNav4Layout);
        pnlNav4Layout.setHorizontalGroup(
            pnlNav4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNav4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNav4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHall4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSchedule4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pnlNav4Layout.setVerticalGroup(
            pnlNav4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNav4Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(lblHall4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(lblSchedule4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlNav4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNav4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFilterResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterResetActionPerformed
        setupLayout();
    }//GEN-LAST:event_btnFilterResetActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        String name = cboFilterHallName.getSelectedItem().toString();
        int month = mthMonth.getMonth();

        filterSchedule(name, month);
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        Date today = new Date();
        cboScheduleType.setSelectedIndex(0);
        cboHallName.setSelectedIndex(0);
        dteStart.setDate(today);
        dteEnd.setDate(today);
        cboStartHour.setSelectedIndex(0);
        spnStartMinutes.setValue(00);
        cboEndHour.setSelectedIndex(0);
        spnEndMinutes.setValue(00);
    }//GEN-LAST:event_btnResetActionPerformed

    private void cboScheduleTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboScheduleTypeActionPerformed
        txtfRemarks.setText(null);
        if (cboScheduleType.getSelectedItem().toString().equals("Availability Slot")){
            txtfRemarks.setVisible(false);
        }
        else{
            txtfRemarks.setVisible(true);
        }
    }//GEN-LAST:event_cboScheduleTypeActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // Convert components' value to respective data type
        String type = cboScheduleType.getSelectedItem().toString();

        String hallName = cboHallName.getSelectedItem().toString();

        String start_hour = cboStartHour.getSelectedItem().toString();
        String start_minutes = String.format("%02d",spnStartMinutes.getValue());
        String time1 = txtAmPm1.getText();

        String end_hour = cboEndHour.getSelectedItem().toString();
        String end_minutes = String.format("%02d", spnEndMinutes.getValue());
        String time2 = txtAmPm2.getText();

        String start_time = start_hour + ":" + start_minutes + time1;
        String end_time = end_hour + ":" + end_minutes + time2;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String start_date = dateFormat.format(dteStart.getDate());
        String end_date = dateFormat.format(dteEnd.getDate());
        String remark = txtfRemarks.getText();
        if (remark.equals("")){remark = null;}

        //
        // validate the data

        validater = new Schedule(type,hallName,start_date,start_time,end_date,end_time,remark);

        if (validater.ValidateData()){
            Schedule s = new Schedule(type, hallName,start_date,start_time,end_date,end_time,remark);
            s.WriteSchedule();

            for (JPanel p:panelObj){pnlButton.remove(p);}
            for (JLabel label:labelObj){pnlButton.remove(label);}
            setupLayout();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void cboEndHourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEndHourActionPerformed
        Schedule s = new Schedule();
        s.showTime(cboEndHour, txtAmPm2);
    }//GEN-LAST:event_cboEndHourActionPerformed

    private void cboStartHourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboStartHourActionPerformed
        Schedule s = new Schedule();
        s.showTime(cboStartHour, txtAmPm1);
    }//GEN-LAST:event_cboStartHourActionPerformed

    private void lblHall4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHall4MouseClicked
        Hall_Manager hall = new Hall_Manager();
        hall.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblHall4MouseClicked

    private void lblHall4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHall4MouseEntered
        ImageSetter.SetLabelMouseEnter(lblHall4);
    }//GEN-LAST:event_lblHall4MouseEntered

    private void lblHall4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHall4MouseExited
        ImageSetter.SetLabelMouseExit(lblHall4,pnlNav4);
    }//GEN-LAST:event_lblHall4MouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Hall_Availability().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnFilterReset;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cboEndHour;
    private javax.swing.JComboBox<String> cboFilterHallName;
    private javax.swing.JComboBox<String> cboHallName;
    private javax.swing.JComboBox<String> cboScheduleType;
    private javax.swing.JComboBox<String> cboStartHour;
    private com.toedter.calendar.JDateChooser dteEnd;
    private com.toedter.calendar.JDateChooser dteStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHall4;
    private javax.swing.JLabel lblRemark;
    private javax.swing.JLabel lblSchedule4;
    private com.toedter.calendar.JMonthChooser mthMonth;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlNav4;
    private javax.swing.JPanel pnlStart;
    private javax.swing.JScrollPane scrSchedule;
    private javax.swing.JSpinner spnEndMinutes;
    private javax.swing.JSpinner spnStartMinutes;
    private javax.swing.JTextField txtAmPm1;
    private javax.swing.JTextField txtAmPm2;
    private javax.swing.JTextArea txtfRemarks;
    // End of variables declaration//GEN-END:variables

    private void add(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
