import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: Chen He
 * Date: 9/14/14
 * Time: 5:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Job {
  long submitTime;
  long startTime;
  long finishTime;
  int mapTask;
  int redTask;
  HashSet<Integer> requestedBlocks;

  public Job(int m, int r, long submitTime, HashSet<Integer> block){
    if(m>0) mapTask = m;
    if(r>0) redTask = r;
    this.submitTime = submitTime;
    requestedBlocks = block;
  }

  public void setStartTime(long start) {
    if(start>=submitTime) startTime = start;
  }

  public int getMapTask() {
    return mapTask;
  }

  public int getRedTask() {
    return redTask;
  }

  public void setFinishTime(long finish) {
    if(finish > submitTime){
      finishTime = finish;
    }
  }


}
