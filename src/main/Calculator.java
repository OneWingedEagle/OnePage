package main;

import java.io.PrintStream;




public class Calculator
{
  public Calculator() {}
  
  public static void main(String[] args)
  {
    double x = 1.0D;
    System.out.println("Gamma(" + x + ") = " + gamma(x));
    System.out.println("log Gamma(" + x + ") = " + logGamma(x));
    
    double X = 1.0D;
    int k = 6;
    
    System.out.println("Gamma(" + x + ") = " + gamma(X));
    System.out.println("incGamma(" + x + ") = " + incGamma(X, k));
    System.out.println("probChi2( " + X + ", " + k + ") = " + probChi2(X, k));
  }
  

  static double logGamma(double x)
  {
    double tmp = (x - 0.5D) * Math.log(x + 4.5D) - (x + 4.5D);
    double ser = 1.0D + 76.18009173D / (x + 0.0D) - 86.50532033D / (x + 1.0D) + 
      24.01409822D / (x + 2.0D) - 1.231739516D / (x + 3.0D) + 
      0.00120858003D / (x + 4.0D) - 5.36382E-6D / (x + 5.0D);
    return tmp + Math.log(ser * Math.sqrt(6.283185307179586D));
  }
  
  static double gamma(double x) {
    return Math.exp(logGamma(x));
  }
  
  private static double incGamma(double x, double k)
  {
    double M = 1.0D;
    double tt = 1.0D;
    for (int i = 1; i < 100000; i++) {
      tt *= x / (k + i);
      if (tt < 1.0E-8D) break;
      M += tt;
    }
    double gin = M * Math.pow(x, k) * Math.exp(-x) / k;
    
    return gin;
  }
  
  static double probChi2(double X, int k)
  {
    if (X == 0.0D) return 1.0D;
    return 1.0D - incGamma(0.5D * X, 0.5D * k) / gamma(0.5D * k);
  }
}
