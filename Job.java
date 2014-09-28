import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: Chen He
 * Date: 9/14/14
 * Time: 5:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Job {

  long startTime;
  long finishTime;
  int mapTask;
  int redTask;
  int mapTaskTimeOnCPU;
  int mapTaskTimeOnGPU;
  int redTaskTimeOnGPU;
  int redTaskTimeOnCPU;
  JobScheduleInfo jobScheduleInfo;

  HashSet<Integer> requestedBlocks;

  public Job(int m, int r, long submitTime, HashSet<Integer> block){
    if(m>0) mapTask = m;
    if(r>0) redTask = r;
    requestedBlocks = block;
    jobScheduleInfo = new JobScheduleInfo();
    jobScheduleInfo.setSubmitTime(submitTime);
  }

  public int getMapTask() {
    return mapTask;
  }

  public int getRedTask() {
    return redTask;
  }

  public void setFinishTime(long finish) {
    if(finish > jobScheduleInfo.submitTime){
      finishTime = finish;
    }
  }

  public void setMapOnCPU(int period) {
    if(period < 0) {
      throw new IllegalArgumentException("MapTask running On CPU can not be negative!");
    } else {
      this.mapTaskTimeOnCPU = period;
    }
  }

  public void setRedOnCPU(int period) {
    if(period < 0) {
      throw new IllegalArgumentException("RedTask running On CPU can not be negative!");
    } else {
      this.redTaskTimeOnCPU = period;
    }
  }

  public void setMapOnGPU(int period) {
    if(period < 0) {
      throw new IllegalArgumentException("MapTask running On GPU can not be negative!");
    } else {
      this.mapTaskTimeOnGPU = period;
    }
  }

  public void setRedOnGPU(int period) {
    if(period < 0) {
      throw new IllegalArgumentException("RedTask running On GPU can not be negative!");
    } else {
      this.redTaskTimeOnGPU = period;
    }
  }

  class JobScheduleInfo {
    long submitTime;
    double energyEffOnGPUNode;
    double energyEffOnCPUNode;
    String JobId;

    public JobScheduleInfo() {
      this.JobId = "job_" + System.currentTimeMillis();
    }

    public void setSubmitTime(long start) {
      if(start>=submitTime) startTime = start;
    }

    public void energyEffOnGPUNode(double energy) {
      energyEffOnGPUNode = energy;
    }

    public void energyEffOnCPUNode(double energy) {
      energyEffOnCPUNode = energy;
    }




  }
}
