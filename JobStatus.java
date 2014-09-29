/**
 * Created with IntelliJ IDEA.
 * User: chenhe
 * Date: 9/28/14
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class JobStatus {

  String jobId;
  private State state;
  public enum State {
    SUBMITTED(1),
    MAP(2),
    REDUCE(3),
    SUCCESS(4),
    KILLED(5),
    FAILED(6),
    ERROR(7);

    int value;

    State(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }

  public JobStatus(String jobId, State state) {
    this.jobId = jobId;

  }

  public void setJobStatus(State status) {
    this.state = status;
  }

  public State getJobStatus() {
    return this.state;
  }

}
