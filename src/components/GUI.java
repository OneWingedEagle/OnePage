package components;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;






public class GUI
  extends JFrame
  implements ActionListener
{
  public JPanel convertPanel;
  public JPanel analysisPanel;
  public JTextArea textArea = new JTextArea();
  public JTextArea textArea2 = new JTextArea();
  public TextField tfFasFile;
  public TextField tfTextFile;
  public TextField tfTextFile2;
  public Button Browse1;
  
  public GUI() {
    JTabbedPane tbPanel = new JTabbedPane();
    
    convertPanel = new JPanel(new FlowLayout(0, 10, 10));
    convertPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    analysisPanel = new JPanel(new FlowLayout(0, 10, 10));
    
    setDefaultCloseOperation(3);
    setTitle("convert to onepage ident file ");
    setSize(850, 650);
    setLocation(10, 10);
    
    tbPanel.setFont(new Font("Arial", 1, 12));
    tbPanel.addTab("Converter", convertPanel);
    

    getContentPane().add(tbPanel);
    






    textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    textArea.setEditable(false);
    textArea.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createTitledBorder("Progress"), 
      BorderFactory.createEmptyBorder(10, 5, 5, 5)));
    scrollPane.setPreferredSize(new Dimension(800, 420));
    


    Label lbFasFile = new Label("input ", 4);
    Label lbOutFile = new Label("Output", 4);
    lbFasFile.setPreferredSize(new Dimension(80, 30));
    lbOutFile.setPreferredSize(new Dimension(80, 30));
    String inFile = System.getProperty("user.dir") + "\\outFile.txt";
    String outFile = System.getProperty("user.dir") + "\\ident.txt";
    tfFasFile = new TextField(inFile);
    tfFasFile.setPreferredSize(new Dimension(300, 30));
    tfTextFile = new TextField(outFile);
    tfTextFile.setPreferredSize(new Dimension(300, 30));
    
    Browse1 = new Button("Browse");
    Browse1.setPreferredSize(new Dimension(100, 30));
    Browse2 = new Button("Browse");
    Browse2.setPreferredSize(new Dimension(100, 30));
    
    Browse1.addActionListener(this);
    Browse2.addActionListener(this);
    

    JPanel leftPanel = new JPanel(new GridLayout(6, 1, 10, 10));
    leftPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 20));
    JPanel rightPanel = new JPanel(new FlowLayout(0, 5, 5));
    rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 5, 5, 0));
    
    convertPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    
    bRun = new Button("Convert");
    bRun.setBackground(Color.GREEN);
    bRun.setPreferredSize(new Dimension(100, 30));
    
    bRun.addActionListener(this);
    
    Label empty1 = new Label();
    empty1.setPreferredSize(new Dimension(50, 3));
    Label empty2 = new Label();
    empty2.setPreferredSize(new Dimension(50, 3));
    
    JPanel filesPanel1 = new JPanel(new FlowLayout(0, 10, 10));
    
    filesPanel1.add(lbFasFile);
    filesPanel1.add(tfFasFile);
    filesPanel1.add(Browse1);
    filesPanel1.add(empty1);
    filesPanel1.add(bRun);
    
    JPanel filesPanel2 = new JPanel(new FlowLayout(0, 10, 10));
    filesPanel2.add(lbOutFile);
    filesPanel2.add(tfTextFile);
    filesPanel2.add(Browse2);
    
    JPanel filesPanel = new JPanel(new GridLayout(2, 1, 10, 10));
    filesPanel.add(filesPanel1);
    filesPanel.add(filesPanel2);
    
    convertPanel.add(filesPanel);
    convertPanel.add(scrollPane);
    








    textArea2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    textArea2.setEditable(false);
    textArea2.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
    JScrollPane scrollPane2 = new JScrollPane(textArea2);
    scrollPane2.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createTitledBorder(""), 
      BorderFactory.createEmptyBorder(10, 5, 5, 5)));
    scrollPane2.setPreferredSize(new Dimension(800, 420));
    


    Label lbOutFile2 = new Label("Output (*.txt)", 4);
    lbOutFile2.setPreferredSize(new Dimension(80, 30));
    String outFile2 = System.getProperty("user.dir") + "\\table.txt";
    tfTextFile2 = new TextField(outFile);
    tfTextFile2.setPreferredSize(new Dimension(300, 30));
    
    Browse3 = new Button("Browse");
    Browse3.setPreferredSize(new Dimension(100, 30));
    
    Browse3.addActionListener(this);
    

    JPanel leftPanel2 = new JPanel(new GridLayout(6, 1, 10, 10));
    leftPanel2.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 20));
    JPanel rightPanel2 = new JPanel(new FlowLayout(0, 5, 5));
    rightPanel2.setBorder(BorderFactory.createEmptyBorder(50, 5, 5, 0));
    
    analysisPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    
    bAnalysis = new Button("Make Table");
    bAnalysis.setBackground(Color.GREEN);
    bAnalysis.setPreferredSize(new Dimension(100, 30));
    
    bAnalysis.addActionListener(this);
    
    Label empty3 = new Label();
    empty1.setPreferredSize(new Dimension(50, 3));
    Label empty4 = new Label();
    empty2.setPreferredSize(new Dimension(50, 3));
    


    JPanel filesPanel3 = new JPanel(new FlowLayout(0, 10, 10));
    filesPanel3.add(lbOutFile2);
    filesPanel3.add(tfTextFile2);
    filesPanel3.add(Browse3);
    filesPanel3.add(new Label());
    filesPanel3.add(new Label());
    filesPanel3.add(bAnalysis);
    


    analysisPanel.add(filesPanel3);
    analysisPanel.add(scrollPane2);
  }
  


  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == Browse1) {
      getFile(1);
    } else if (e.getSource() == Browse2) {
      getFile(2);
    } else if (e.getSource() == Browse3) {
      getFile(3);
    } else if (e.getSource() == bAnalysis) {
      makeTabel();
    } else if (e.getSource() == bRun)
    {

      String s = "";
      try {
        Scanner scr = new Scanner(new FileReader(tfFasFile.getText()));
        if (scr.hasNext()) {
          s = scr.next();
        }
      } catch (IOException ee) {
        ee.printStackTrace();
      }
      if (s.startsWith("Seq")) {
        convert();
      } else {
        convertNew();
      }
    }
  }
  

  public void getFile(int i)
  {
    FileDialog fd = new FileDialog(new Frame(), "Select bun  file", 0);
    fd.setVisible(true);
    fd.toFront();
    String Folder = fd.getDirectory();
    String File = fd.getFile();
    if ((Folder != null) && (File != null))
    {
      if (i == 1) {
        tfFasFile.setText(Folder + "\\" + File);
      }
      else if (i == 2) {
        tfTextFile.setText(Folder + "\\" + File);
      }
      else if (i == 3) {
        tfTextFile2.setText(Folder + "\\" + File);
      }
    }
  }
  


  public Button Browse2;
  

  public Button Browse3;
  
  public Button bRun;
  
  public Button bAnalysis;
  
  public void convert()
  {
    String infile = tfFasFile.getText();
    String outFile = tfTextFile.getText();
    Console.redirectOutput(textArea);
    
    try
    {
      PrintWriter pwTxt = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));
      BufferedReader br = new BufferedReader(new FileReader(infile));
      String line = "x";
      

      int I1 = 1000;int I = 0;
      String[] str = new String[I1];
      int j = 0;int k = 0;int L = 0;
      
      String regex = "[: ,=\\t]+";
      

      line = br.readLine();
      
      str[0] = line;
      str[1] = "abcd";
      str[2] = "abcd";
      str[3] = "abcd";
      
      br.readLine();
      br.readLine();
      
      for (int i = 4; i < I1; i++) {
        line = br.readLine();
        
        if (line == null) break;
        String[] sp = line.split(regex);
        if ((i > 2) && (sp[0].startsWith("Seq")))
        {
          br.readLine();
          br.readLine();
          line = br.readLine();
          sp = line.split(regex);
        }
        int kx = Integer.parseInt(sp[0]);
        if (kx < I) break;
        I = kx;
        



        str[i] = line;
      }
      


      for (int i = 4; i < I1; i++) {
        line = br.readLine();
        
        if ((line == null) || 
          (i >= I + 5)) break;
        String[] sp = line.split(regex);
        if (sp[0].startsWith("Seq"))
        {
          br.readLine();
          br.readLine();
          line = br.readLine();
          sp = line.split(regex);
        }
        
        str[i] = str[i].concat(" " + line);
      }
      

      System.out.println("identity");
      pwTxt.println("identity");
      System.out.println(I);
      pwTxt.println(I);
      
      System.out.print("\t");
      for (int i = 0; i < I; i++) {
        int n = i + 1;
        System.out.print(n + "\t");
        pwTxt.print(n + "\t");
      }
      System.out.println();
      pwTxt.println();
      
      for (int i = 5; i < I + 5; i++) {
        String[] spx = str[i].split(regex);
        for (int u = 0; u < spx.length; u++) {
          if (u == i - 4) {
            System.out.print("1.0\t");
            pwTxt.print("1.0\t");
          }
          else {
            System.out.print(spx[u] + "\t");
            pwTxt.print(spx[u] + "\t");
          }
        }
        System.out.println();
        pwTxt.println();
      }
      


      br.close();
      pwTxt.close();
      
      System.out.println("output file: " + outFile);
    }
    catch (IOException e)
    {
      System.err.println(infile + "  was not found.");
    }
  }
  

  public void convertNew()
  {
    String infile = tfFasFile.getText();
    String outFile = tfTextFile.getText();
    Console.redirectOutput(textArea);
    try
    {
      PrintWriter pwTxt = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));
      Scanner scr = new Scanner(new FileReader(infile));
      
      String line = "x";
      
      line = scr.next();
      
      int I = Integer.parseInt(line);
      
      String[] seqName = new String[I];
      double[][] val = new double[I][I];
      
      for (int i = 0; i < I; i++) {
        seqName[i] = scr.next();
        for (int j = 0; j < I; j++) {
          val[i][j] = (1.0D * Double.parseDouble(scr.next()));
        }
        scr.nextLine();
      }
      

      String[] strh = new String[2];
      
      strh[0] = "distance";
      strh[1] = Integer.toString(I);
      
      for (int i = 0; i < strh.length; i++) {
        System.out.println(strh[i]);
        pwTxt.println(strh[i]);
      }
      
      System.out.print("\t");
      for (int i = 0; i < I; i++) {
        int n = i + 1;
        System.out.print(n + "\t");
        pwTxt.print(n + "\t");
      }
      System.out.println();
      pwTxt.println();
      for (int i = 0; i < I; i++) {
        int n = i + 1;
        System.out.print(n + "\t");
        pwTxt.print(n + "\t");
        for (int j = 0; j < I; j++)
        {
          System.out.format("%10.8f\t", new Object[] { Double.valueOf(val[i][j]) });
          pwTxt.printf("%10.8f\t", new Object[] { Double.valueOf(val[i][j]) });
        }
        






        System.out.println(seqName[i]);
        pwTxt.println(seqName[i]);
      }
      

      pwTxt.close();
      
      scr.close();
      
      System.out.println("output file: " + outFile);

    }
    catch (IOException e)
    {
      System.err.println(infile + "  was not found.");
    }
  }
  




  public void writeMessageToFile(String file)
  {
    try
    {
      String alltext = textArea.getText();
      PrintWriter pwBun = new PrintWriter(new BufferedWriter(new FileWriter(file)));
      for (String line : alltext.split("\\n")) {
        pwBun.println(line);
      }
      pwBun.close();
    }
    catch (IOException localIOException) {}
  }
  
  public void makeTabel()
  {
    Console.redirectOutput(textArea2);
    int H = 100;
    int L = 100;
    double[] xx = new double[100];
    for (int i = 0; i < H; i++) {
      for (int j = 0; j < L; j++) {
        System.out.print(i + " " + j + "      ");
      }
      System.out.println();
    }
  }
}
