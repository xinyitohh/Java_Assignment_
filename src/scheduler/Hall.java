package scheduler;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Hall extends FileOperation implements DataValidation{
    private String HallType;
    protected String HallName;
    private String Capacity;
    private String Equipment;
    private ArrayList<Hall> hallData;
    private int index;

    public Hall() {
        
    } 
    
    public Hall(String HallType, String HallName, String Capacity, String Equipment) {
        this.HallType = HallType;
        this.HallName = HallName;
        this.Capacity = Capacity;
        this.Equipment = Equipment;
    }

    public String getHallType() {
        return HallType;
    }

    public String getHallName() {
        return HallName;
    }

    public String getCapacity() {
        return Capacity;
    }

    public String getEquipment() {
        return Equipment;
    }

    public void setHallType(String HallType) {
        this.HallType = HallType;
    }

    public void setHallName(String HallName) {
        this.HallName = HallName;
    }

    public void setCapacity(String Capacity) {
        this.Capacity = Capacity;
    }

    public void setEquipment(String Equipment) {
        this.Equipment = Equipment;
    }

    public void setHallData(ArrayList<Hall> hallData) {
        this.hallData = hallData;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    

    @Override
    public String toString() {
        return  HallType + ";" + HallName + ";" + Capacity + ";" + Equipment;
    }
    
    
    
    public void WriteHall(){
        ArrayList<String> hall = new ArrayList<>();
        String line = HallType + ";" + HallName + ";" + Capacity + ";" + Equipment;
        hall.add(line);
        System.out.println(hall.getLast());
        super.FileWriting("hall.txt",hall);  
    }
    
    public void LoadHallName(JComboBox cbox){
        try{
            ArrayList<Hall> hall =  FileOperation.ReadHall("hall.txt");
            for (Hall h: hall){
                cbox.addItem(h.getHallName());
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public boolean ValidateData() {
        boolean valid = true;
        StringBuilder message = new StringBuilder("");
        
        // Check Hall type select or not
        if (HallType == null || HallType.isEmpty()){
            message.append(" Hall Type, Capacity,");
        }
        
        // Check Hall Name entered or not
        if (HallName == null || HallName.isEmpty()){
            message.append(" Hall Name,");
        }
        
        // Check checkboxes select or not
       if (Equipment == null || Equipment.isEmpty()){
           message.append(" Equipment,");
       }
        
        // Warning the user
        if (message.length() > 0){
            message.setLength(message.length() - 1);
            JOptionPane.showMessageDialog(null, "Please complete the selection for" + message + ". Thank You", "Warning", 
                    JOptionPane.WARNING_MESSAGE);            
            valid = false;
        }        
        
        for (int i = 0; i < hallData.size(); i++){
            Hall h = hallData.get(i);
            if (i == index)continue;
                if ((this.HallName != null && HallName.equals(h.getHallName())) && !h.equals(this)){
                    JOptionPane.showMessageDialog(null, "The hall information already exists. "
                            + "Please try again", "Hall Info Duplication", JOptionPane.ERROR_MESSAGE);
                    valid = false;
                    break;
                }
        }        
        return valid;
    }
    
}
