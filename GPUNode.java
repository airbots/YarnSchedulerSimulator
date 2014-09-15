

/**
 * Created with IntelliJ IDEA.
 * User: chenhe
 * Date: 9/14/14
 * Time: 10:28 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.HashMap;

public class GPUNode extends Node {

  int numGPU;
  HashMap<Integer, Integer> gpu2cpu;

  public GPUNode(int numCPU, int numGPU) {
    gpu2cpu = new HashMap<Integer, Integer>();
    this.numCPU = numCPU;
    this.numGPU = numGPU;
  }

  @Override
  public void init() {
    super.init();


  }
}
