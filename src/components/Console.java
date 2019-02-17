package components;

import java.io.PipedOutputStream;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Console implements Runnable
{
  JTextArea displayPane;
  java.io.BufferedReader reader;
  
  private Console(JTextArea displayPane, PipedOutputStream pos)
  {
    this.displayPane = displayPane;
    
    try
    {
      java.io.PipedInputStream pis = new java.io.PipedInputStream(pos);
      reader = new java.io.BufferedReader(new java.io.InputStreamReader(pis));
    }
    catch (java.io.IOException localIOException) {}
  }
  
  public void run()
  {
    String line = null;
    
    try
    {
      while ((line = reader.readLine()) != null)
      {
        displayPane.append(line + "\n");
        displayPane.setCaretPosition(displayPane.getDocument().getLength());
      }
    }
    catch (java.io.IOException localIOException) {}
  }
  




  public static void redirectOutput(JTextArea displayPane)
  {
    redirectOut(displayPane);
    redirectErr(displayPane);
  }
  
  public static void redirectOut(JTextArea displayPane)
  {
    PipedOutputStream pos = new PipedOutputStream();
    System.setOut(new PrintStream(pos, true));
    
    Console console = new Console(displayPane, pos);
    new Thread(console).start();
  }
  
  public static void redirectErr(JTextArea displayPane)
  {
    PipedOutputStream pos = new PipedOutputStream();
    System.setErr(new PrintStream(pos, true));
    
    Console console = new Console(displayPane, pos);
    new Thread(console).start();
  }
  
  public static void main(String[] args)
  {
    JTextArea textArea = new JTextArea();
    javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
    
    JFrame frame = new JFrame("Progress");
    frame.setDefaultCloseOperation(3);
    frame.getContentPane().add(scrollPane);
    frame.setSize(200, 100);
    frame.setVisible(true);
    
    redirectOutput(textArea);
    int i = 0;
    
    javax.swing.Timer timer = new javax.swing.Timer(1000, new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent e)
      {
        System.out.println(new java.util.Date().toString());
        System.err.println(System.currentTimeMillis());
      }
    });
    timer.start();
  }
}
