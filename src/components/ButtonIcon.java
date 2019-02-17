package components;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

public class ButtonIcon extends JButton
{
  public Font tfFont = new Font("Times New Roman", 1, 12);
  
  public ButtonIcon() {
    setFont(tfFont);
    setFont(tfFont);
    setPreferredSize(new Dimension(30, 30));
  }
  
  public ButtonIcon(String st, int alignment) {
    setText(st);
    setHorizontalAlignment(alignment);
    setFont(tfFont);
    setPreferredSize(new Dimension(100, 30));
  }
  
  public ButtonIcon(String str) {
    setText(str);
    setFont(tfFont);
    setPreferredSize(new Dimension(100, 30));
  }
}
