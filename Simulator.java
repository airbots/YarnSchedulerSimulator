/**
 * Created with IntelliJ IDEA.
 * User: chenhe
 * Date: 9/14/14
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 *
 * This is the main class to run this simulator
 */


public class Simulator {

  Cluster cluster;
  Scheduler scheduler;

  public Simulator(Cluster cluster, Scheduler scheduler) {
    this.cluster = cluster;
    this.scheduler = scheduler;
  }

  //work load generator code


  //create cluster and generate block distribution
  public Cluster createCluster(int CPUNode, int GPUNode, int numBlocks, int replica){
    Cluster cluster = new Cluster(CPUNode, GPUNode, numBlocks, replica);
    cluster.init();
    return cluster;
  }

  //scheduling policy
  public Scheduler createScheduler(int policy) {
    return Scheduler.createScheduler(policy);
  }

  public static void main (String[] args) {

  }
}
