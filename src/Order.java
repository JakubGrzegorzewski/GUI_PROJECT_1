import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Order implements Runnable{
    private int orderID;
    private int counterID = 0;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    PlanningStatus planningStatus;
    JobStatus jobStatus;
    private Brigade assignedBrigade;

    private List<Job> assignedJobs = new ArrayList<>();
    public Map<Integer, Object> allOrders = new HashMap<>();

    public enum PlanningStatus {
        PLANNED, UNPLANNED;
    }
    public enum JobStatus{
        CREATED, STARTED, ENDED;
    }

    Order(boolean planned){
        allOrders.put(counterID, this);
        this.orderID = counterID++;
        if (planned)
            planningStatus = PlanningStatus.PLANNED;
        else
            planningStatus = PlanningStatus.UNPLANNED;
        this.startTime = LocalDateTime.now();
        this.jobStatus = JobStatus.CREATED;
    }
    Order(boolean planned, Brigade brigade){
        new Order(planned);
        this.assignedBrigade = brigade;
    }
    Order(boolean planned, List<Job> jobs){
        new Order(planned);
        this.assignedJobs = jobs;
    }
    Order(boolean planned, List<Job> jobs, Brigade brigade){
        new Order(planned);
        this.assignedJobs = jobs;
        this.assignedBrigade = brigade;
    }

    public void addBrigade(Brigade brigade){
        this.assignedBrigade = brigade;

    }
    public boolean addJob(Job job){
        if(this.assignedBrigade == null)
            return false;
        this.assignedJobs.add(job);
        return true;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public PlanningStatus getPlanningStatus() {
        return planningStatus;
    }

    @Override
    public void run() {
        this.jobStatus = JobStatus.STARTED;
        if(!this.assignedJobs.isEmpty()) {
            for (Job job: this.assignedJobs) {

            }
        }
        this.jobStatus = JobStatus.ENDED;
        this.endTime = LocalDateTime.now();
    }
}
