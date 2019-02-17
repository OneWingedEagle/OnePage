package main;

import components.GUI;





public class Main
{
  public Main() {}
  
  public static void main(String[] args)
  {
    int i = 7;
    int j = 8;
    int k = i + j;
    int p = i * k;
    
    GUI gui = new GUI();
    gui.setVisible(true);
  }
}
