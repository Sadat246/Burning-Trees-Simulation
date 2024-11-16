import java.util.*;
public class Frontier{
  private ArrayDeque<int[]> frontier;
  private int size;
  public Frontier(){
    frontier= new ArrayDeque<int[]>();
    size=0;
  }
  public int size(){
    return size;
  }
  public void add(int[]location){
    frontier.addLast(location);
    size++;
  }
  public int[] remove(){
    size--;
    return frontier.removeFirst();
  }
}
