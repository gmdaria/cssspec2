/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author gmdaria
 */




import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public final class Main extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;    
    private static ArrayList names = new ArrayList();
    private static JFrame frame;  
    private static JButton start = new JButton("Start");
    private static JLabel[] label;
    private static JLabel winLabel = new JLabel();  //winner
    
    private static JTextField display = new JTextField();
    private static JButton one =new JButton("1");
    private static JButton two =new JButton("2");
    private static JButton three =new JButton("3");
    private static JButton four =new JButton("4");
    private static JButton five=new JButton("5");
    private static JButton six =new JButton("6");
    private static JButton seven =new JButton("7");
    private static JButton eight =new JButton("8");
    private static JButton nine =new JButton("9");
    private static JButton zero =new JButton("0");
    private static JButton add =new JButton("+");
    private static JButton subtract =new JButton("-");
    private static JButton multiply =new JButton("*");
    private static JButton divide =new JButton("/");
    
    
    
    
    
    
    
    
    
    public Main() throws FileNotFoundException{
      frame = new JFrame("ACM Raffle Draw");  
      frame.setSize(1027, 768);
      frame.setLayout(null);
      frame.setLocationRelativeTo(null);    //center
      display.setBounds(0,60,240,20);
      one.setBounds(0,120,60,20);
      two.setBounds(60,120,60,20);
      three.setBounds(120,120,60,20);
      seven.setBounds(0,80,60,20);
      eight.setBounds(60,80,60,20);
      nine.setBounds(120,80,60,20);
      four.setBounds(0,100,60,20);
      five.setBounds(60,100,60,20);
      six.setBounds(120,100,60,20);
      seven.setBounds(0,80,60,20);
      eight.setBounds(60,80,60,20);
      nine.setBounds(120,80,60,20);
      zero.setBounds(180,120,60,20);
      add.setBounds(270,80,60,20);
      subtract.setBounds(210,100,60,20);        
      /*multiply.setBounds(,,60,20);*/
      divide.setBounds(180,80,60,20); 
      start.setBounds(0, 0, 70, 20);
      start.setLayout(null);
      start.addActionListener(this);//for something to happen when start is clicked
      frame.add(start);
      frame.add(display);
      frame.add(one);
      frame.add(two);
      frame.add(three);
      frame.add(four);
      frame.add(five);
      frame.add(six);
      frame.add(seven);
      frame.add(eight);
      frame.add(nine);
      frame.add(zero);
      frame.add(add);
      frame.add(subtract);
      frame.add(multiply);
      frame.add(divide);
      
      
      
      
      frame.setResizable(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Scanner in = new Scanner(new FileReader("input.txt"));//read from input.txt
      while(in.hasNext()){
          names.add(in.nextLine()); //add input.txt contents to ArrayList names
      }
      Collections.shuffle(names);   //shuffle names
      label = new JLabel[names.size()];
      frame.setVisible(true);   //show frame
    }//end Main

    public void actionPerformed (ActionEvent e) { 
        if(e.getSource()==start){   //if start button was clicked
            new Candidates().start();   //run Candidates thread
            start.setEnabled(false);    //disable start button (para di abuso :) )
        }
    }//end action performed()
         
    public static void main(String[] args) throws java.io.IOException{
            Main gui = new Main();
            gui.frame.setVisible(true);
    }
    
    public class Candidates extends Thread{
        public void run(){
            String win;
            int i=0;
            frame.remove(winLabel); //if there is a winner, remove it (refresh)
            winLabel=new JLabel(names.get(0).toString());   //get first name in shuffled ArrayList names
            int randomX=(int)(Math.random()*1024);
            int randomY=(int)(Math.random()*700);
            winLabel.setFont(new Font("Arial", Font.PLAIN, 25));
            winLabel.setBounds(randomX, randomY, 290, 20);  //set winLabel location
            winLabel.setLayout(null);
            frame.add(winLabel);
            
            for(i=0; i<names.size(); i++){  //add all the names in random locations
                label[i]=new JLabel(names.get(i).toString());   //put name into a JLabel
                label[i].setFont(new Font("Arial", Font.PLAIN, 25));
                randomX=(int)(Math.random()*1024);
                randomY=(int)(Math.random()*768);
                label[i].setBounds(randomX, randomY, 290, 20);
                label[i].setLayout(null);
                label[i].setVisible(false); //initially hide the JLabels
                frame.add(label[i]);
            }
            //make the JLabels visible with gradually increasing speed
            for(i=names.size()-1; i>=0; i--)
                try {Thread.sleep(i/20);    //dynamic delay, based on the number of names
                    label[i].setVisible(true);
                } catch (InterruptedException ex) {}
            //make the JLabels disappear with gradually decreasing speed (dramatic ;) )    
            for(i=0; i<names.size(); i++)
                try {Thread.sleep(i/2);
                    label[i].setVisible(false);
                } catch (InterruptedException ex) {}
            
            //horizontally center the winner
            if(winLabel.getX()>512)
                for(int x=winLabel.getX(); x>512; x--)
                    try {Thread.sleep(10);
                        winLabel.setLocation(x, winLabel.getY());
                    } catch (InterruptedException ex) {}
                    
            else
                for(int x=winLabel.getX(); x<512; x++)
                    try {Thread.sleep(10);
                        winLabel.setLocation(x, winLabel.getY());
                    } catch (InterruptedException ex) {}
            //vertically center the winner
            if(winLabel.getY()>384)
                for(int y=winLabel.getY(); y>384; y--)
                    try {Thread.sleep(10);
                        winLabel.setLocation(winLabel.getX(), y);
                    } catch (InterruptedException ex) {}
                    
            else
                for(int y=winLabel.getY(); y<384; y++)
                    try {Thread.sleep(10);
                        winLabel.setLocation(winLabel.getX(), y);
                    } catch (InterruptedException ex) {}
            start.setEnabled(true);
            names.remove(0);    //remove winner from ArrayList names so that he/she will not win again
        }
    }//end Candidates class
}

