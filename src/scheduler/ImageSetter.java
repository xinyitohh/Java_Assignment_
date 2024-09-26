package scheduler;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yeo yu le
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageSetter implements SetImage{
    private void updateImageDisplay(JLabel label, int size) {
        ImageIcon originalIcon = (ImageIcon) label.getIcon();
        Image scaledImage = originalIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
 
        label.setIcon(scaledIcon);
        label.setMaximumSize(new java.awt.Dimension(size, size));
        label.setPreferredSize(new java.awt.Dimension(size, size));
    }
    
    public static void SetLabelMouseEnter(JLabel label){
        label.setOpaque(true);
        label.setBackground(new Color(125,17,209));
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
    }
    
    public static void SetLabelMouseExit(JLabel label, JPanel panel){
       Color panelColor = panel.getBackground();
        label.setBackground(panelColor);
    }

    @Override
    public void SetImageSize(JLabel...label){
        for (JLabel lbl:label){
            updateImageDisplay(lbl,image_size);
        }
    }
}
