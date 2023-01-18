import java.util.Random;

// TODO

class RandomPoint extends Point { 
  private double x;
  private double y;
  private Random rng = new Random(); //not sure why Random(1) doesnt work

  public RandomPoint (double minX, double maxX, double minY, double maxY) {
    super(0,0);
    this.x = randomNumber(minX,maxX);
    this.y = randomNumber(minY,maxY);
  }  

  public void setSeed(int seedNo){
    rng = new Random(seedNo);
  }

  public double randomNumber(double low, double high) {  
    double rngNum = rng.nextDouble() * (high - low) + low;    
    System.out.println("rng: " + rng);
    return rngNum;
  }

  public double getX() {
    return this.x;
  }

  public double getY(){
    return this.y;
  }
  
  @Override
  public String toString() {
    return String.format("(" + this.x + ", " + this.y + ")");
  }
}
