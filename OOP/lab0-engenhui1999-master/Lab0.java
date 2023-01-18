import java.util.Scanner;
import java.util.Random;

/**
 * CS2030S Lab 0: Estimating Pi with Monte Carlo
 * Semester 2, 2021/22
 *
 * <p>This program takes in two command line arguments: the 
 * number of points and a random seed.  It runs the
 * Monte Carlo simulation with the given argument and print
 * out the estimated pi value.
 *
 * @author XXX 
 */

class Lab0 {
  private Circle c = new Circle(new Point(0.5, 0.5), 0.5);

  public double estimatePi(int numOfPoints, int seed) {
    Point p;
    double n = 0;
    Random rng = new Random(seed);

    for (int i = 0; i < numOfPoints; i++) {
      p = new RandomPoint(0, 1, 0, 1);
      
      if(c.contains(p)){
        n++;
      }
      System.out.println("i: " + i + "| p: " + p + " | contained? " + c.contains(p));
    }
    System.out.println("n: " + n);
    double pi = n * 4 / numOfPoints;    
    //System.out.println("Pi: " + pi + " | n: " + n);

    return pi;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    System.out.println("Num of points: ");
    int numOfPoints = sc.nextInt();
    System.out.println("Seed no: ");
    int seed = sc.nextInt();
    
    Lab0 ans = new Lab0();
    System.out.println(String.format("%.6f", ans.estimatePi(numOfPoints, seed)));

    sc.close();
  }
}
