/**
 * Created with IntelliJ IDEA.
 * User: Chen He
 * Date: 9/14/14
 * Time: 5:49 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.HashMap;
import java.util.HashSet;

public abstract class Node {

  int numCPU;
  int memoryInGB;
  boolean isCPUNode;
  String id;
  HashSet<String> poolofBlocks; //for data locality concern
  HashMap<Integer, Long> containers;
  float energyEff;

  public void init() {
    containers = new HashMap<Integer, Long>();
    for(int i=0; i<numCPU; i++) {
      containers.put(i, System.currentTimeMillis());
    }
  }

  public int getAvailCPU(Long timestamp) {
    int availCPU = 0;
    for(int i: containers.keySet()) {
      if(timestamp >= containers.get(i)) {
        availCPU++;
      }
    }
    return availCPU;
  }
  /**
   * get available CPU of a given Node
   * @return
   */
  public int getNumCPU() {
    return numCPU;
  }

  /**
   *
   * @param cpu
   */
  public void setNumCPU(int cpu) {
    if (cpu >= 0) {
      numCPU = cpu;
    }
  }

  /**
   * return Node ID of a Node
   * @return
   */
  public String getNodeId(){
    return id;
  }

  /**
   * Set Node ID for a instance
   * @param str
   */
  public void setNodeId(String str) {
    if (null != str) {
      id = str;
    }
  }

  /**
   * check whether this node has data locality for a given block
   * @param blockId
   * @return
   */
  public boolean isLocal(String blockId) {
    if (poolofBlocks.contains(blockId)) {
      return true;
    }
    return false;
  }

  /**
   * get node energy efficiency
   * @return
   */
  public float getEnergyEff() {
    return energyEff;
  }

  /**
   * set energy efficiency
   * @param eff
   */
  public void setEnergyEff(float eff) {
    if(eff >= 0) {
      this.energyEff = eff;
    }
  }

  /**
   * check whether is a cpu node
   */
  public boolean isCPUNode(){
    return isCPUNode;
  }
}