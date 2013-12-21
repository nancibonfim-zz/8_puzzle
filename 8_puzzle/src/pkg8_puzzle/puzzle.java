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
import busca.No;
import java.awt.BorderLayout;  
import java.awt.Dimension;  
import java.awt.GridLayout;  
  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
  
import java.awt.Robot;

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
    
    private JPanel centerPanel2;
    
    int[][] tabuleiro;
    int[][] pos;  
    int width, height;  
  
    
     public puzzle(List caminho) throws InterruptedException {  
        
        int labelIndex = 0;
        int x = 1;
        No aux = (No)caminho.get(caminho.size()-x);
        // No aux = (No)caminho.g
        tabuleiro = aux.getEstado().getTabuleiro();
        
         
        pos = new int[][] {  
                            {0, 1, 2},   
                            {3, 4, 5},   
                            {6, 7, 8}   
                             
                        };  
  
  
        centerPanel = new JPanel();  
        centerPanel.setLayout(new GridLayout(3, 4, 0, 0));  
  
        ImageIcon sid = new ImageIcon("icesid.jpg");  
        
  
        width = sid.getIconWidth();  
        height = sid.getIconHeight();  
  
  
        add(Box.createRigidArea(new Dimension(0, 5)), BorderLayout.NORTH);      
        add(centerPanel, BorderLayout.CENTER);  
  
        
        int aux2=0;
        
        for ( int i = 0; i < 3; i++) {  
            for ( int j = 0; j < 3; j++) {  
                
                if ( tabuleiro[i][j] == 0) {  
                    label = new JLabel(" ");  
                    centerPanel.add(label); 
                    labelIndex = aux2;
                } else {  
                    button = new JButton();  
                    button.addActionListener(this);  
                    centerPanel.add(button);  
                    String texto = ""+tabuleiro[i][j];
                    button.setText(texto);  
                } 
                
                aux2++;
            }  
        }  
   
        setSize(325, 275);  
        setTitle("Puzzle");  
        setResizable(false);  
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        setVisible(true);  
        
       //  Thread.sleep(300);
        int buttonIndex = 0;
        
         
        x++;
        aux = (No)caminho.get(caminho.size()-x);
        
        if(aux.getEstado().acao().equals("cima"))
            buttonIndex = labelIndex-3;
        if(aux.getEstado().acao().equals("baixo"))
            buttonIndex = labelIndex+3;
        if(aux.getEstado().acao().equals("direita"))
            buttonIndex = labelIndex+1;
        if(aux.getEstado().acao().equals("esquerda"))
            buttonIndex = labelIndex-1;
        
        while(aux != null){
           
           JButton button = (JButton)centerPanel.getComponent(buttonIndex);
           
           
           if((aux.getEstado().acao().equals("baixo")) || (aux.getEstado().acao().equals("direita"))) {
                centerPanel.remove(buttonIndex);  
                centerPanel.add(label, buttonIndex-1);  
                centerPanel.add(button,labelIndex);  
                centerPanel.validate(); 
           }else{
               centerPanel.remove(buttonIndex);  
                centerPanel.add(label, buttonIndex);  
                centerPanel.add(button,labelIndex);  
                centerPanel.validate(); 
           }    
           
           labelIndex = buttonIndex;
           
           Thread.sleep(500);
           x++;
           if(caminho.size()- x < 0){
               aux = null;
           }else{
               aux = (No)caminho.get(caminho.size()-x);
                if(aux.getEstado().acao().equals("cima") && aux != null)
                    buttonIndex = labelIndex-3;
                if(aux.getEstado().acao().equals("baixo") && aux != null)
                    buttonIndex = labelIndex+3;
                if(aux.getEstado().acao().equals("direita") && aux != null)
                    buttonIndex = labelIndex+1;
                if(aux.getEstado().acao().equals("esquerda") && aux != null)
                    buttonIndex = labelIndex-1;
           }
   
           
          
        }    
         
         
         
        
    }  
  
  
    
   
  
    public void actionPerformed(ActionEvent e) {  
       
    }  
}  