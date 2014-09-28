import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: chenhe
 * Date: 9/14/14
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Cluster {

  int CPUNode;
  int GPUNode;
  HashMap<String, Node> cpuNodes;
  HashMap<String, Node> gpuNodes;
  int replica;
  int numOfBlocks;
  int size;

  public Cluster(int CPUNode, int GPUNode, int numBlocks, int replica) {
    this.CPUNode = CPUNode;
    this.GPUNode = GPUNode;
    this.numOfBlocks = numBlocks;
    this.replica = replica;
    this.size = CPUNode+GPUNode;

    cpuNodes = new HashMap<String, Node>();
    gpuNodes = new HashMap<String, Node>();

  }

  void addCPUNode(CPUNode cpuNode) {
    cpuNodes.put(cpuNodes.size() + "", cpuNode);
  }

  void addGPUNode(GPUNode gpuNode) {
    gpuNodes.put(gpuNodes.size()+"", gpuNode);
  }

  Node creatGeneralNode(int numOfCPU,
                         int numOfGPU, float energyEff,
                         String nodeId) {
    Node node;
    if(numOfGPU > 0) {
      node = new GPUNode(numOfCPU, numOfGPU);
    } else {
      node = new CPUNode(numOfCPU);
    }
    node.setNodeId(nodeId);
    node.setEnergyEff(energyEff);
    node.setNumCPU(numOfCPU);
    node.init();
    return node;
  }
  void init(){
    int i=0;
    ArrayList<Node> nodes = new ArrayList<Node>();
    while(i<CPUNode) {
      Node node = creatGeneralNode(4,0,0,""+i);
      nodes.add(node);
      cpuNodes.put(""+i, node);
      i++;
    }

    while(i<size) {
      Node node = creatGeneralNode(4,4,0,""+i);
      nodes.add(node);
      gpuNodes.put(""+i, node);
      i++;
    }

    initBlocks(nodes, this.numOfBlocks, replica);

  }
  void initBlocks(ArrayList<Node> nodes, int numofBlocks, int replica) {

    Random ran = new Random();
    for(int i=0;i<numofBlocks; i++) {
      for(int j=0;j<replica;j++) {
        Node node = nodes.get(ran.nextInt(nodes.size()));
        if(node.containsBlock(""+i)) {
          j--;
        } else {
          node.addBlock(""+i);
        }
      }
    }
  }

  public Node getNextAvailCPUNode() {
    Random ran = new Random();
    ArrayList<Node> availNodes = new ArrayList<Node>();

    for(Node node: cpuNodes.values()) {
      if (node.getAvailCPU(System.currentTimeMillis())>0) {
        availNodes.add(node);
      }
    }

    if(availNodes.size()==0){
      return null;
    } else {
      return availNodes.get(ran.nextInt(availNodes.size()));
    }
  }

  public Node getNextAvailGPUNode() {
    Random ran = new Random();
    ArrayList<Node> availNodes = new ArrayList<Node>();

    for(Node node: gpuNodes.values()) {
      if (node.getAvailCPU(System.currentTimeMillis())>0) {
        availNodes.add(node);
      }
    }

    if(availNodes.size()==0){
      return null;
    } else {
      return availNodes.get(ran.nextInt(availNodes.size()));
    }
  }

  public Node getNextAvailNode() {
    Random ran = new Random();
    ArrayList<Node> availNodes = new ArrayList<Node>();

    for(Node node: gpuNodes.values()) {
      if (node.getAvailCPU(System.currentTimeMillis())>0) {
        availNodes.add(node);
      }
    }

    for(Node node: cpuNodes.values()) {
      if (node.getAvailCPU(System.currentTimeMillis())>0) {
        availNodes.add(node);
      }
    }

    if(availNodes.size()==0){
      return null;
    } else {
      return availNodes.get(ran.nextInt(availNodes.size()));
    }
  }
}
