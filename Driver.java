import java.util.*;
import java.text.*;
public class Driver{ //how do i determine if I am getting close?
  public static void main(String[]args){
  int WIDTH = 1000;
  int HEIGHT = 1000;
  int REPEAT = 50;
  if(args.length > 2){
    WIDTH = Integer.parseInt(args[0]);
    HEIGHT = Integer.parseInt(args[1]);
    REPEAT = Integer.parseInt(args[2]);
  }
  double DENSITY = 0.0;
  System.out.print("WIDTH:  " + WIDTH + "  HEIGHT:  "+ HEIGHT + "  REPEAT:  "+ REPEAT);
  System.out.println("\nDENSITY  | TICKS");
  //repeat this test with different densities, REPEAT times.
  double densityRange=0.0;
  double densityChosen=0.0;
  int highest=0;
  while (densityRange<1.0){
    BurnTrees b = new BurnTrees(WIDTH,HEIGHT,densityRange);
    int burnTime = b.run();
    //System.out.println(burnTime+"m");
    if (burnTime>highest){
      highest=burnTime;
      densityChosen=densityRange;
    }
    densityRange+=0.1;
  }
  double highestBurntime=0;
  double RESULT=0;
  double TOTALD=0;
  DecimalFormat df_obj = new DecimalFormat("#.###");
  //System.out.println(densityChosen);
  DENSITY=densityChosen-0.1;
  while (DENSITY<1.0){ //AVERAGE THE 50 TESTS
    TOTALD=0;
  for (int i=0;i<REPEAT;i++){
    BurnTrees b = new BurnTrees(WIDTH,HEIGHT,DENSITY);
    int burnTime = b.run();
    TOTALD+=burnTime;
  }
    double burnTime=TOTALD/ ((double)REPEAT);
    if (burnTime>highestBurntime){
      highestBurntime=burnTime;
      RESULT=DENSITY;

    }
    System.out.println("  " + df_obj.format(DENSITY)+ " |  " + df_obj.format(burnTime));
    if (densityChosen-0.1<DENSITY&&DENSITY<densityChosen+0.1){
      DENSITY+=0.01;
    }
    else{
      DENSITY+=0.075;
    }
  }
  System.out.println("DENSITY with the largest burnTime: " + RESULT);
}
  //print all of the burnTime results (average the repeated tests into one result please)
  //Print in a table that includes the other important information for the experiment.
  //print out the DENSITY with the largest burnTime
}
