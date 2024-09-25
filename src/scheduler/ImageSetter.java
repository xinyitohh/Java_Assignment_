package scheduler;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yeo yu le
 */
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageSetter implements SetImage{
        private void updateImageDisplay(JLabel label, int size) {
        ImageIcon originalIcon = (ImageIcon) label.getIcon();
        Image scaledImage = originalIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
 
        label.setIcon(scaledIcon);
        label.setMaximumSize(new java.awt.Dimension(size, size));
        label.setPreferredSize(new java.awt.Dimension(size, size));
    }

    @Override
    public void SetImageSize(int size, JLabel...label){
        for (JLabel lbl:label){
            updateImageDisplay(lbl,size);
        }
    }
}
