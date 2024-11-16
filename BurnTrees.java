import java.util.*;
public class BurnTrees{
  private int[][]map;
  private int ticks;
  private Frontier front;
  private static final int TREE = 2;
  private static final int FIRE = 1;
  private static final int ASH = 3;
  private static final int SPACE = 0;


  /*Determine if the simulation is still burning
   *@return false if any fires are still burning, true otherwise
   */
  public boolean done(){ // when frontier is empty
    //YOU MUST IMPLEMENT THIS METHOD
    //(BEFORE WRITING ANY CODE READ ALL OF THE CODE AND SEE HOW IT FITS TOGETHER)
    //HINT: do not check the board for fire which is an n^2 operation
    //placeholder for compilation purposes
    return front.size()==0;
  }

  /*This is the core of the simulation. All of the logic for advancing to the next round goes here.
   *All existing fires spread new fires, and turn to ash
   *new fires should remain fire, and not spread.
   */
  public void tick(){
    ticks++;//leave this here.
    //YOU MUST IMPLEMENT THE REST OF THIS METHOD
    //(BEFORE WRITING ANY CODE READ ALL OF THE CODE AND SEE HOW IT FITS TOGETHER)
      int[] portion;
      for(int i=0;i<front.size();i++){
        portion= front.remove();
        int first= portion[0];
        int second= portion[1];
        map[first][second]=ASH;
        if (first>0 && map[first-1][second]==TREE){
          map[first-1][second]=FIRE;
          portion= new int[]{first-1,second};
          front.add(portion);
        }
        if (second>0 && map[first][second-1]==TREE){
          map[first][second-1]=FIRE;
          portion= new int[]{first,second-1};
          front.add(portion);
        }
        if (first<map.length-1 && map[first+1][second]==TREE){
          map[first+1][second]=FIRE;
          portion= new int[]{first+1,second};
          front.add(portion);
        }
        if (second<map[0].length-1 && map[first][second+1]==TREE){
          map[first][second+1]=FIRE;
          portion= new int[]{first,second+1};
          front.add(portion);
        }
      }
  }
  /*int [][] base = new int[map.length][map[0].length];
    for (int i=0;i<map.length;i++){
      for (int j=0;j<map[0].length;j++){
        base[i][j]=map[i][j];
      }
    }
    for (int row=0;row<map.length;row++){
      for (int col=0;col<map[0].length;col++){
        if (map[row][col]==FIRE){
          base[row][col]=ASH;
          if (row-1>0&&base[row-1][col]==TREE){
            base[row-1][col]=FIRE;
          }
          if (row+1<map.length&&base[row+1][col]==TREE){
            base[row+1][col]=FIRE;
          }
          if (col+1<map[0].length&&base[row][col+1]==TREE){
            base[row][col+1]=FIRE;
          }
          if (col-1>0&&base[row][col-1]==TREE){
            base[row][col-1]=FIRE;
          }
        }
      }
    }
    map=base;
  }

  /***********************YOU MIGHT UPDATE THIS**************************/

  /*Initialize the simulation.
   *If you add more instance variables you can add more here,
   *otherwise it is complete
   */
  public BurnTrees(int width,int height, double density){
    map = new int[height][width];
    //map= new int[height][width];
    front= new Frontier();
    for(int r=0; r<map.length; r++ ){
      for(int c=0; c<map[r].length; c++ ){
        if(Math.random() < density){
           map[r][c]=TREE;
           //map[r][c]=TREE;
         }
       }
     }

     start();//set the left column on fire.
  }


  /*
   *Sets the trees in the left column of the forest on fire
   */
  public void start(){
    //If you add more instance variables you can add more here,
    //otherwise it is complete.
    for(int i = 0; i < map.length; i++){
      if(map[i][0]==TREE){
        map[i][0]=FIRE;
        front.add(new int[]{i, 0});
      }
    }
  }



    public static void main(String[]args){
      int WIDTH = 20;
      int HEIGHT = 20;
      int DELAY = 200;
      double DENSITY = .7;
      if(args.length > 1){
        WIDTH = Integer.parseInt(args[0]);
        HEIGHT = Integer.parseInt(args[1]);
        DENSITY = Double.parseDouble(args[2]);
      }
      if(args.length > 3){
        DELAY = Integer.parseInt(args[3]);
      }
      BurnTrees b = new BurnTrees(WIDTH,HEIGHT,DENSITY);


      //int ans = b.animate(DELAY);//animate all screens
      int ans = b.run();//runtime
      System.out.println(ans);//print the final answer

      //int ans = b.outputAll();//print all screens one after another
      //System.out.println(ans);//print the final answer
    }




  /***********************DO NOT UPDATE THINGS BELOW HERE**************************/

  /*DO NOT UPDATE THIS
   *PLEASE READ SO YOU SEE HOW THE SIMULATION IS SUPPOSED TO WORK!!!
   */
  public int run(){
    while(!done()){
      tick();
    }
    return getTicks();
  }


  /*DO NOT UPDATE THIS*/
  public int getTicks(){
    return ticks;
  }

  /*DO NOT UPDATE THIS*/
  public String toString(){
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < map.length; i++) {
      for (int c = 0; c < map[i].length; c++) {
        if(map[i][c]==SPACE)
          builder.append(" ");
        else if(map[i][c]==TREE)
          builder.append("@");
        else if(map[i][c]==FIRE)
          builder.append("w");
        else if(map[i][c]==ASH)
          builder.append(".");
      }
      builder.append("\n");
    }
    return builder.toString();
  }

  /*DO NOT UPDATE THIS*/
  public String toStringColor(){
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < map.length; i++) {
      for (int c = 0; c < map[i].length; c++) {
        if(map[i][c]==0)
          builder.append(" ");
        else if(map[i][c]==2)
          builder.append(Text.color(Text.GREEN)+"@");
        else if(map[i][c]==1)
          builder.append(Text.color(Text.RED)+"w");
        else if(map[i][c]==3)
          builder.append(Text.color(Text.DARK)+".");
      }
      builder.append("\n"+Text.RESET);
    }
    return builder.toString()+ticks+"\n";
  }

  /*DO NOT UPDATE THIS*/
  public int animate(int delay) {
    System.out.print(Text.CLEAR_SCREEN);
    System.out.println(Text.go(1,1)+toStringColor());
    Text.wait(delay);
    while(!done()){
      tick();
      System.out.println(Text.go(1,1)+toStringColor());
      Text.wait(delay);
    }
    return getTicks();
  }

  /*DO NOT UPDATE THIS*/
  public int outputAll(){
    System.out.println(toString());
    while(!done()){
      tick();
      System.out.println(toString());
    }
    return getTicks();
  }


}
