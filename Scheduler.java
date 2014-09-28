import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: chenhe
 * Date: 9/24/14
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Scheduler {
  ConcurrentHashMap<Job.JobScheduleInfo, Job> jobQueue =
    new ConcurrentHashMap<Job.JobScheduleInfo, Job>();

  public static Scheduler createScheduler(int policy) {
    if(policy == 0) {
      return new FifoScheduler();
    } else {
      return new EnergyScheduler();
    }
  }

  public void submitJob(Job job) {
    job.jobScheduleInfo.setSubmitTime(System.currentTimeMillis());
    jobQueue.put(job.jobScheduleInfo, job);
  }

  public void scheduleJob() {}


}
