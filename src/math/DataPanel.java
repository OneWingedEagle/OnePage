package math;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class DataPanel
  implements ActionListener
{
  boolean initialized;
  int titleIndex;
  int xTitleIndex;
  int yTitleIndex;
  int xLowerIndex;
  int xUpperIndex;
  int xIntervalIndex;
  int yLowerIndex;
  int yUpperIndex;
  int yIntervalIndex;
  JFrame frame;
  JPanel panel;
  JLabel msg;
  String title;
  String xTitle;
  String yTitle;
  Double xLower;
  Double xUpper;
  Double xInterval;
  Double yLower;
  Double yUpper;
  Double yInterval;
  Point2D.Double[] points;
  int numberOfPoints;
  int numberOfPointsAllocated;
  JLabel[] paramLabels;
  JTextField[] paramFields;
  JTextField[] dataFields;
  
  DataPanel(JFrame newFrame)
  {
    initialized = false;
    numberOfPoints = (this.numberOfPointsAllocated = 0);
    titleIndex = 0;
    xTitleIndex = 1;
    xLowerIndex = 2;
    xUpperIndex = 3;
    xIntervalIndex = 4;
    yTitleIndex = 5;
    yLowerIndex = 6;
    yUpperIndex = 7;
    yIntervalIndex = 8;
    paramLabels = new JLabel[9];
    paramLabels[titleIndex] = new JLabel("Title");
    paramLabels[xTitleIndex] = new JLabel("X Axis Title");
    paramLabels[yTitleIndex] = new JLabel("Y Axis Title");
    paramLabels[xLowerIndex] = new JLabel("X lower bound");
    paramLabels[xUpperIndex] = new JLabel("X upper bound");
    paramLabels[xIntervalIndex] = new JLabel("X tick interval");
    paramLabels[yLowerIndex] = new JLabel("Y lower bound");
    paramLabels[yUpperIndex] = new JLabel("Y upper bound");
    paramLabels[yIntervalIndex] = new JLabel("Y tick interval");
    paramFields = new JTextField[9];
    paramFields[titleIndex] = new JTextField("Test Title");
    paramFields[xTitleIndex] = new JTextField("X");
    paramFields[yTitleIndex] = new JTextField("Y");
    paramFields[xLowerIndex] = new JTextField("0");
    paramFields[xUpperIndex] = new JTextField("10");
    paramFields[xIntervalIndex] = new JTextField("1");
    paramFields[yLowerIndex] = new JTextField("0");
    paramFields[yUpperIndex] = new JTextField("10");
    paramFields[yIntervalIndex] = new JTextField("1");
    frame = newFrame;
    panel = new JPanel(new FlowLayout());
    frame.getContentPane().add(panel, "West");
  }
  
  public void actionPerformed(ActionEvent e) {
    JFrame fileFrame = new JFrame();
    JPanel filePanel = new JPanel();
    JFileChooser fileChooser = new JFileChooser();
    fileFrame.getContentPane().add(filePanel);
    filePanel.add(fileChooser);
    fileChooser.setFileSelectionMode(0);
    int result = fileChooser.showOpenDialog(filePanel);
    if (result != 0) {
      msg = new JLabel("No file selected");
      panel.add(msg);
      return;
    }
    
    File datafile = fileChooser.getSelectedFile();
    initialized = readFile(datafile);
    panel.update(panel.getGraphics());
    frame.pack();
    frame.setVisible(true);
  }
  





  boolean readFile(File datafile)
  {
    int numAllocated = 10;
    int numRead = 0;
    int numDataPoints = 0;
    String[] dataStrings = new String[numAllocated];
    BufferedReader reader = null;
    try
    {
      reader = new BufferedReader(new FileReader(datafile));
      String text = null;
      while ((text = reader.readLine()) != null) {
        if (numRead >= numAllocated) {
          numAllocated *= 2;
          String[] temp = dataStrings;
          dataStrings = new String[numAllocated];
          System.arraycopy(temp, 0, dataStrings, 0, numRead);
        }
        
        dataStrings[numRead] = text;
        numRead++;
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    } catch (IOException e) {
      System.out.println("IO Exception");
    }
    try {
      if (reader != null)
        reader.close();
    } catch (IOException e) {
      System.out.println("IO Exception on close");
    }
    
    int thisCase = -2;
    int thisDataPoint = 0;
    for (int i = 0; i < numRead; i++) {
      String[] segments = dataStrings[i].split(" ");
      if (segments[0].equals("Title")) {
        thisCase = titleIndex;
      } else if (segments[0].equals("xTitle")) {
        thisCase = xTitleIndex;
      } else if (segments[0].equals("yTitle")) {
        thisCase = yTitleIndex;
      } else if (segments[0].equals("xLower")) {
        thisCase = xLowerIndex;
      } else if (segments[0].equals("xUpper")) {
        thisCase = xUpperIndex;
      } else if (segments[0].equals("xInterval")) {
        thisCase = xIntervalIndex;
      } else if (segments[0].equals("yLower")) {
        thisCase = yLowerIndex;
      } else if (segments[0].equals("yUpper")) {
        thisCase = yUpperIndex;
      } else if (segments[0].equals("yInterval")) {
        thisCase = yIntervalIndex;
      } else if (segments[0].equals("Data")) {
        thisCase = -1;
        numDataPoints = numRead - i - 1;
        dataFields = new JTextField[2 * numDataPoints];
        points = new Point2D.Double[numDataPoints];
      } else if (thisCase != -1) {
        thisCase = -2;
      }
      
      if ((thisCase >= 0) && (segments.length > 1)) {
        String temp = segments[1];
        for (int j = 2; j < segments.length; j++)
          temp = temp + " " + segments[j];
        paramFields[thisCase].setText(temp);
        thisCase = -2;
      } else if ((thisCase == -1) && (!segments[0].equals("Data")) && 
        (thisDataPoint < numDataPoints)) {
        dataFields[(2 * thisDataPoint)] = new JTextField(segments[0]);
        dataFields[(2 * thisDataPoint + 1)] = new JTextField(segments[1]);
        thisDataPoint++;
      }
    }
    

    frame.getContentPane().remove(panel);
    panel = new JPanel(new GridLayout(9 + numDataPoints, 2));
    for (int i = 0; i < 9; i++) {
      panel.add(paramLabels[i]);
      panel.add(paramFields[i]);
    }
    for (int i = 0; i < numDataPoints; i++) {
      panel.add(dataFields[(2 * i)]);
      panel.add(dataFields[(2 * i + 1)]);
    }
    frame.getContentPane().add(panel, "West");
    
    return true;
  }
  
  void refreshData()
  {
    if (!initialized) {
      return;
    }
    title = paramFields[titleIndex].getText();
    xTitle = paramFields[xTitleIndex].getText();
    yTitle = paramFields[yTitleIndex].getText();
    xLower = Double.parseDouble(paramFields[xLowerIndex].getText());
    xUpper = Double.parseDouble(paramFields[xUpperIndex].getText());
    xInterval = Double.parseDouble(paramFields[xIntervalIndex].getText());
    yLower = Double.parseDouble(paramFields[yLowerIndex].getText());
    yUpper = Double.parseDouble(paramFields[yUpperIndex].getText());
    yInterval = Double.parseDouble(paramFields[yIntervalIndex].getText());
    for (int i = 0; i < points.length; i++) {
      Double x = Double.valueOf(Double.parseDouble(dataFields[(2 * i)].getText()));
      Double y = Double.valueOf(Double.parseDouble(dataFields[(2 * i + 1)].getText()));
      points[i] = new Point2D.Double(x, y);
    }
  }
  
  boolean isInitialized() {
    return initialized;
  }
  
  String getTitle() {
    return title;
  }
  
  String getXTitle() {
    return xTitle;
  }
  
  String getYTitle() {
    return yTitle;
  }
  
  Double getXLower() {
    return xLower;
  }
  
  Double getXUpper() {
    return xUpper;
  }
  
  Double getXInterval() {
    return xInterval;
  }
  
  Double getYLower() {
    return yLower;
  }
  
  Double getYUpper() {
    return yUpper;
  }
  
  Double getYInterval() {
    return yInterval;
  }
  
  int getNumberOfPoints() {
    return points.length;
  }
  
  Point2D.Double getPoint(int i) {
    if (i < 0) {
      i = 0;
    } else if (i >= points.length) {
      i = points.length - 1;
    }
    return points[i];
  }
}
