// imports libraries necessary to execute program
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.math.*;

public class physicsStuff extends JFrame
{
  // sets variables
  JButton buttonOK, buttonClear;
  JTextField velocity1, velocity2, acceleration, displacement, time;
  JLabel title, labelv1, labelv2, labelacc, labeldis, labeltime, labelimage;
  JLabel hlabelv1, hlabelv2, hlabelacc, hlabeldis, hlabeltime;
  JPanel panel;
  public double iv1, iv2, iacc, idis, itime; 
  public double calcv1, calcv2, calcacc, calcdis, calctime;
  public int errorCounter;
  public boolean error, cv1, cv2, cacc, cdis, ctime;
  // doubles that will be used if calculation requires quadratic equation
  public double a, b, c, solution1, solution2;
  
  // runs program
  public static void main (String [] args)
  {
    new physicsStuff();
  }
  
  public physicsStuff()
  {
    cv1 = true;
    cv2 = true;
    cacc = true;
    cdis = true;
    ctime = true;
    
    iv1 = 0;
    iv2 = 0;
    iacc = 0;
    idis = 0;
    itime = 0;
    
    // defines frame and panel
    panel = new JPanel();
    panel.setLayout(null);
    setTitle("Kinematics Calculator");
    setContentPane(panel);
    setVisible(true);
    setResizable(false);
    setSize(400,450);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // defines and locates textfields, buttons and labels and puts them into the correct layout
    title = new JLabel("Kinematics Calculator");
    title.setBounds(80,25,500,50);
    title.setFont(new Font("Serif", Font.PLAIN, 24));
    
    buttonOK = new JButton("OK");
    buttonOK.setBounds(50,360,100,25);
    buttonOK.addActionListener(new ButtonListener());
    buttonOK.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
    buttonClear = new JButton("CLEAR");
    buttonClear.setBounds(200,360,100,25);
    buttonClear.addActionListener(new ButtonListener());
    buttonClear.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
    velocity1 = new JTextField();
    velocity1.setBounds(225,100,100,25);
    velocity1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    hlabelv1 = new JLabel();
    hlabelv1.setBounds(225,100,100,25);
    hlabelv1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
    velocity2 = new JTextField();
    velocity2.setBounds(225,150,100,25);
    velocity2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    hlabelv2 = new JLabel();
    hlabelv2.setBounds(225,150,100,25);
    hlabelv2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
    acceleration = new JTextField();
    acceleration.setBounds(225,200,100,25);
    acceleration.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    hlabelacc = new JLabel();
    hlabelacc.setBounds(225,200,100,25);
    hlabelacc.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    displacement = new JTextField();
    displacement.setBounds(225,250,100,25);
    displacement.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    hlabeldis = new JLabel();
    hlabeldis.setBounds(225,250,100,25);
    hlabeldis.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
    time = new JTextField();
    time.setBounds(225,300,100,25);
    time.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    hlabeltime = new JLabel();
    hlabeltime.setBounds(225,300,100,25);
    hlabeltime.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
    labelv1 = new JLabel("Initial Velocity (m/s)");
    labelv1.setBounds(50,100,150,25);
    labelv2 = new JLabel("Final Velocity (m/s)");
    labelv2.setBounds(50,150,150,25);
    labelacc = new JLabel("Acceleration (m/s?)");
    labelacc.setBounds(50,200,150,25);
    labeldis = new JLabel("Displacement (m)");
    labeldis.setBounds(50,250,150,25);
    labeltime = new JLabel("Time (s)");
    labeltime.setBounds(50,300,150,25);
    
    // adds all elements to the panel
    panel.add(title);
    panel.add(buttonClear);
    panel.add(buttonOK);
    panel.add(velocity1);
    panel.add(hlabelv1);
    panel.add(velocity2);
    panel.add(hlabelv2);
    panel.add(acceleration);
    panel.add(hlabelacc);
    panel.add(displacement);
    panel.add(hlabeldis);
    panel.add(time);
    panel.add(hlabeltime);
    panel.add(labelv1);
    panel.add(labelv2);
    panel.add(labelacc);
    panel.add(labeldis);
    panel.add(labeltime);
    panel.add(new JLabel(new ImageIcon("images\\lines")));
    
  }
  
  public class ButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      if (e.getSource() == buttonOK)
      {
        
        // method will check for user input errors 
        checkError();
        
        // if there are no errors in the users input, the following will calculate the missing values
        // each variable in each kinematic formula has been isolated 
        if(error == false)
        {
          buttonOK.setEnabled(false);
          if (cv1 == false)
          {
            if (cv2 == false)
            {
              calcv1 = (idis - 0.5 * iacc * itime * itime) /itime;
            }
            else if(cacc == false)
            {
              calcv1 = 2* idis / itime - iv2;
            }
            else if(cdis == false)
            {
              calcv1 = (iacc * itime - iv2) * -1;
            }
            else if(ctime == false)
            {
              calcv1 = Math.sqrt(iv2 * iv2 - 2 * iacc * idis);
            }
            panel.remove(velocity1);
            hlabelv1.setText(Double.toString(calcv1));
          }
          
            
          if (cv2 == false)
          {
            if (cv1 == false)
            {
              calcv2 = (idis + 0.5 * iacc * itime * itime) / itime;
            }
            if(cacc == false)
            {
              calcv2 = 2 * idis / itime - iv1;
            }
            if(cdis == false)
            {
              calcv2 = iacc * itime + iv1;
            }
            if(ctime == false)
            {
              calcv2 = Math.sqrt(iv1 * iv1 + 2 * iacc * idis);
            }
            panel.remove(velocity2);
            hlabelv2.setText(Double.toString(calcv2));
          }
          
            
          if (cacc == false)
          {
            if (cv1 == false)
            {
              calcacc = (idis - iv2 * itime) / (-0.5 * itime * itime);
            }
            if(cv2 == false)
            {
              calcacc = (idis - iv1 * itime) / (0.5 * itime * itime); 
            }
            if(cdis == false)
            {
              calcacc = (iv2-iv1)/itime;
            }
            if(ctime == false)
            {
              calcacc = (iv2 * iv2 - iv1 * iv1) / (2 * idis);
            }
            panel.remove(acceleration);
            hlabelacc.setText(Double.toString(calcacc));
          }
          
            
          if (cdis == false)
          {
            if(cv1 == false)
            {
              calcdis = iv2 * itime - 0.5 * iacc * itime * itime;
            }
            if(cv2 == false)
            {
              calcdis = (iv1 * itime) + 0.5 * iacc * itime * itime;
            }
            if(cacc == false)
            {
              calcdis = 0.5 * itime * (iv2 + iv1);
            }
            if(ctime == false)
            {
              calcdis = (iv2 * iv2 - iv1 * iv1) / ( 2 * iacc);
            }
            panel.remove(displacement);
            hlabeldis.setText(Double.toString(calcdis));
          }
          
            
          if (ctime == false)
          {
            if(cv1 == false)
            {
              // requires quadratic formula
              a = -0.5 * iacc;
              b = iv1;
              c = -1 * idis;
              
              // yields two solutions 
              solution1 = (-1 * b + Math.sqrt(b*b - 4 * a * c))/ ( 2 * a);
              solution2 = (-1 * b - Math.sqrt(b*b - 4 * a * c))/ (2 * a);
              // whichever one is negative is dismissed because time cannot be negative
              if (solution1 > 0)
              {
                panel.remove(time);
                calctime = solution1;
                hlabeltime.setText(Double.toString(solution1));
              }
              if (solution2 > 0)
              {
                panel.remove(time);
                calctime = solution2;
                hlabeltime.setText(Double.toString(solution2));
              }
              // if both are positive both are the answer 
              if (solution2 > 0 && solution1 > 0)
              {
                panel.remove(time);
                hlabeltime.setText((Double.toString(solution1)) + " or " + (Double.toString(solution2)));
              }
              // if neither are positive there is no solution 
              if (solution2 < 0 && solution1 < 0)
              {
                panel.remove(time);
                hlabeltime.setText("No Solution");
              }
            }
            if(cv2 == false)
            {
              // requires quadratic formula
              a = 0.5 * iacc;
              b = iv2;
              c = -1 * idis;
              
              
              // yields two solutions 
              solution1 = (-1 * b + Math.sqrt(Math.abs((b*b - 4 * a * c)))/ ( 2 * a));
              solution2 = (-1 * b - Math.sqrt(b*b - 4 * a * c))/ (2 * a);
              
              // whichever one is negative is dismissed because time cannot be negative
              if (solution1 > 0)
              {
                panel.remove(time);
                calctime = solution1;
                hlabeltime.setText(Double.toString(calctime));
              }
              if (solution2 > 0)
              {
                panel.remove(time);
                calctime = solution2;
                hlabeltime.setText(Double.toString(calctime));
              }
              // if both are positive both are the answer 
              if (solution2 > 0 && solution1 > 0)
              {
                panel.remove(time);
                hlabeltime.setText((Double.toString(solution1)) + " or " + (Double.toString(solution2)));
              }
              // if neither are positive there is no solution 
              if (solution2 < 0 && solution1 < 0)
              {
                panel.remove(time);
                hlabeltime.setText("No Solution");
              }
       
            }
            
            if(cacc == false)
            {
              calctime = idis * 2 / (iv1+iv2);
              System.out.println(calctime);
              panel.remove(time);
              hlabeltime.setText(Double.toString(calctime));
            }
            if(cdis == false)
            {
              calctime = (iv2-iv1) / iacc;
              System.out.println(calctime);
              panel.remove(time);
              hlabeltime.setText(Double.toString(calctime));
            }
          }
          
        }
      }
      
      // completely resets all variables
      if(e.getSource() == buttonClear)
      {
        // reenables confirmation button
        buttonOK.setEnabled(true);
        // adds text fields back to the panel
        panel.add(velocity1);
        panel.add(velocity2);
        panel.add(acceleration);
        panel.add(displacement);
        panel.add(time);
        // resets booleans and error counter
        cv1 = true;
        cv2 = true;
        cacc = true;
        cdis = true;
        ctime = true;
        errorCounter = 0;
        // resets values in text fields 
        velocity1.setText("");
        velocity2.setText("");
        acceleration.setText("");
        displacement.setText("");
        time.setText("");
        // resets values in hidden JLabels
        hlabelv1.setText("");
        hlabelv2.setText("");
        hlabelacc.setText("");
        hlabeldis.setText("");
        hlabeltime.setText("");
      }
    }
  }
  
  // the following method will check for user input errors 
  // if an error is found a boolean will be set to true and will not allow the calculation to occur
  
  
  
  public void checkError()
  {
    // one field should be empty to fulfill calculations
    // counter will add up number of fields that are empty and will run an error if there is more than one field empty or if all fields are full
    // otherwise the textfield is converted to a double for calculations 
    errorCounter = 0;
    error = false;
    
    if (velocity1.getText().length() == 0)
    {
      errorCounter ++;
      cv1 = false;
    }
    else
    {
      iv1 = Double.parseDouble(velocity1.getText()); 
    }
 
    
    if (velocity2.getText().length() == 0)
    {
      errorCounter ++;
      cv2 = false;
    }
    else 
    {
      iv2 = Double.parseDouble(velocity2.getText());  
    }
     
    
    if (acceleration.getText().length() == 0)
    {
      errorCounter ++;
      cacc = false;
    }
    else 
    {
      iacc = Double.parseDouble(acceleration.getText()); 
    }
    
    
    if (displacement.getText().length() == 0)
    {
      errorCounter ++;
      cdis = false;
    }
    else 
    {
      idis = Double.parseDouble(displacement.getText()); 
    }
    
      
    if (time.getText().length() == 0)
    {
      errorCounter ++;
      ctime = false;
    }
    else
    { 
      itime = Double.parseDouble(time.getText()); 
    }
    System.out.println("Error Counter: " + errorCounter);
    
    // will run if user should input in more fields 
 
    if(errorCounter > 2)
    {
      JOptionPane.showMessageDialog(null,"Please enter in three or four fields","Message Dialog",JOptionPane.ERROR_MESSAGE);
      error = true;
    }
    
    // will run if user has inputted all fields 
    if(errorCounter == 0)
    {
      JOptionPane.showMessageDialog(null,"Please leave one or two fields blank","Message Dialog",JOptionPane.ERROR_MESSAGE);
      error = true;
    }
    
    //if time is a negative value an error will be caught
    if (itime < 0)
    {
      JOptionPane.showMessageDialog(null,"Time cannot be negative!","Message Dialog",JOptionPane.ERROR_MESSAGE);
      error = true;
    }
    
  }
}
// Created by Cathy Le 
