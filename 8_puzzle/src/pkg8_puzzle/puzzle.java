/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg8_puzzle;

/**
 *
 * @author Diogo
 */
import java.awt.BorderLayout;  
import java.awt.Dimension;  
import java.awt.GridLayout;  
import java.awt.Image;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.image.CropImageFilter;  
import java.awt.image.FilteredImageSource;  
  
import javax.swing.Box;  
import javax.swing.ImageIcon;  
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JPanel;  
  
  
public class puzzle extends JFrame implements ActionListener {  
  
    private JPanel centerPanel;  
    private JButton button;  
    private JLabel label;  
     
    int[][] pos;  
    int width, height;  
  
    public puzzle() {  
  
        pos = new int[][] {  
                            {0, 1, 2},   
                            {3, 4, 5},   
                            {6, 7, 8}   
                             
                        };  
  
  
        centerPanel = new JPanel();  
        centerPanel.setLayout(new GridLayout(4, 4, 0, 0));  
  
        ImageIcon sid = new ImageIcon("icesid.jpg");  
        
  
        width = sid.getIconWidth();  
        height = sid.getIconHeight();  
  
  
        add(Box.createRigidArea(new Dimension(0, 5)), BorderLayout.NORTH);      
        add(centerPanel, BorderLayout.CENTER);  
  
  
        for ( int i = 0; i < 3; i++) {  
            for ( int j = 0; j < 3; j++) {  
                if ( j == 2 && i == 2) {  
                    label = new JLabel(" ");  
                    centerPanel.add(label);  
                } else {  
                    button = new JButton();  
                    button.addActionListener(this);  
                    centerPanel.add(button);  
//                    image = createImage(new FilteredImageSource(source.getSource(),  
//                        new CropImageFilter(j*width/3, i*height/4,   
//                            (width/3)+1, height/4)));  
                    button.setText("teste");  
                }  
            }  
        }  
  
        setSize(325, 275);  
        setTitle("Puzzle");  
        setResizable(false);  
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        setVisible(true);  
    }  
  
  
    public static void main(String[] args) {  
  
        new puzzle();  
  
    }  
  
    public void actionPerformed(ActionEvent e) {  
        JButton button = (JButton) e.getSource();  
        Dimension size = button.getSize();  
  
        int labelX = label.getX();  
        int labelY = label.getY();  
        int buttonX = button.getX();  
        int buttonY = button.getY();  
        int buttonPosX = buttonX / size.width;  
        int buttonPosY = buttonY / size.height;  
        int buttonIndex = pos[buttonPosY][buttonPosX];  
  
  
  
        if (labelX == buttonX && (labelY - buttonY) == size.height ) {  
  
             int labelIndex = buttonIndex + 3;  
  
             centerPanel.remove(buttonIndex);  
             centerPanel.add(label, buttonIndex);  
             centerPanel.add(button,labelIndex);  
             centerPanel.validate();  
        }  
  
        if (labelX == buttonX && (labelY - buttonY) == -size.height ) {  
  
             int labelIndex = buttonIndex - 3;  
             centerPanel.remove(labelIndex);  
             centerPanel.add(button,labelIndex);  
             centerPanel.add(label, buttonIndex);  
             centerPanel.validate();  
        }  
  
        if (labelY == buttonY && (labelX - buttonX) == size.width ) {  
  
             int labelIndex = buttonIndex + 1;  
  
             centerPanel.remove(buttonIndex);  
             centerPanel.add(label, buttonIndex);  
             centerPanel.add(button,labelIndex);  
             centerPanel.validate();  
        }  
  
        if (labelY == buttonY && (labelX - buttonX) == -size.width ) {  
  
             int labelIndex = buttonIndex - 1;  
  
             centerPanel.remove(buttonIndex);  
             centerPanel.add(label, labelIndex);  
             centerPanel.add(button,labelIndex);  
             centerPanel.validate();  
        }  
    }  
}  