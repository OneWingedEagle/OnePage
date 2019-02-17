package main;

import java.io.PrintStream;
import java.util.Random;



public class Distance
{
  public Distance() {}
  
  public static void main(String[] args)
  {}
  
  private int getInd(char s)
  {
    if (s == 'A') return 0;
    if (s == 'G') return 1;
    if (s == 'C') return 2;
    if (s == 'T') return 1;
    return -1;
  }
  


/*  private Mat prob(double lam, double t)
  {
    Mat P = new Mat(4, 4);
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++)
        if (i == j)  P.el[i][j] = (0.25D + 0.75D * Math.exp(-4.0D * lam * t)); else
         P.el[i][j] = (0.25D - 0.25D * Math.exp(-4.0D * lam * t));
    }
    return P;
  }*/
  


  public double getDistance(char[] s1, char[] s2)
  {
    double d = 0.0D;
    
    int L = s1.length;
    int n = 0;
    
    for (int i = 0; i < L; i++) {
      if (s1[i] != s2[i]) { n++;
      }
    }
    d = 1.0D * n / L;
    
    

    double dhat = -0.75D * Math.log(1.0D - 1.3333D * d);
    return dhat;
  }
}
