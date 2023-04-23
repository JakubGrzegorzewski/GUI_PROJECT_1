import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Order implements Runnable{
    long orderID = counter++;
    static long counter = 0;

    private LocalDateTime creationTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private PlanningStatus planningStatus;
    private JobStatus jobStatus;
    private Brigade assignedBrigade;

    private List<Job> assignedJobs = new ArrayList<>();
    public Map<Long, Object> allOrders = new HashMap<>();

    public enum PlanningStatus {
        PLANNED, UNPLANNED;
    }
    public enum JobStatus{
        CREATED, STARTED, ENDED;
    }

    Order(boolean planned){
        allOrders.put(orderID, this);
        if (planned)
            planningStatus = PlanningStatus.PLANNED;
        else
            planningStatus = PlanningStatus.UNPLANNED;
        this.creationTime = LocalDateTime.now();
        this.jobStatus = JobStatus.CREATED;
    }
    Order(boolean planned, Brigade brigade){
        new Order(planned);
        addBrigade(brigade);
    }
    Order(boolean planned, List<Job> jobs){
        new Order(planned);
        this.assignedJobs = jobs;
    }
    Order(boolean planned, List<Job> jobs, Brigade brigade){
        new Order(planned);
        addBrigade(brigade);
        this.assignedBrigade = brigade;
    }

    public boolean addBrigade(Brigade brigade){
        if(this.assignedBrigade != null)
            return false;
        this.assignedBrigade = brigade;
        this.assignedBrigade.getForeman().addOrder(this);
        return true;



    }
    public boolean addJob(Job job){
        if(this.assignedJobs != null || this.assignedJobs.contains(job))
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

    public void startOrder(){
        
    }

    @Override
    public void run() {
        this.startTime = LocalDateTime.now();
        this.jobStatus = JobStatus.STARTED;
        if(!this.assignedJobs.isEmpty() && this.assignedBrigade != null) {
            for (Job job: this.assignedJobs) {
                boolean allFree = false;
                while(!allFree){
                    allFree = true;
                    for (Employee employee:this.assignedBrigade.getEmployeeList())
                        if (!employee.jobStatus)
                            allFree = false;
                }
                this.assignedBrigade.setJobStatus(true);
                job.start();
                this.assignedBrigade.setJobStatus(false);
            }
        }
        this.jobStatus = JobStatus.ENDED;
        this.endTime = LocalDateTime.now();
    }
}
