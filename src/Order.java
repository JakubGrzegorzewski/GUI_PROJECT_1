import java.time.LocalDateTime;
import java.util.*;

public class Order implements Runnable{
    private final long orderID = counter++;
    private static long counter = 0;

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

    Order(boolean planned) {
        new Order(planned, null, null);

    }
    Order(boolean planned, Brigade brigade){
        new Order(planned, null, brigade);



    }
    Order(boolean planned, List<Job> jobs){
        new Order(planned, jobs, null);
    }
    Order(boolean planned, List<Job> jobs, Brigade brigade){
        this.allOrders.put(orderID, this);
        if (planned)
            this.planningStatus = PlanningStatus.PLANNED;
        else
            this.planningStatus = PlanningStatus.UNPLANNED;
        this.creationTime = LocalDateTime.now();
        this.jobStatus = JobStatus.CREATED;

        ArrayList<String> list = new ArrayList<>();
        list.add(Boolean.toString(planned));

        if (jobs != null) {
            this.assignedJobs = jobs;
            list.add(jobs.toString());
        }
        else
            list.add("null");

        if (brigade != null) {
            addBrigade(brigade);
            list.add(""+brigade.getBrigadeID());
        }
        else
            list.add("null");
        Log.write.create(Order.class, list);
    }

    public boolean addBrigade(Brigade brigade){
        if(this.assignedBrigade != null)
            return false;
        this.assignedBrigade = brigade;
        this.assignedBrigade.getForeman().addOrder(this);
        return true;
    }



    public boolean addJob(Job job){
        if(this.assignedJobs == null)
            return false;
        if (this.assignedJobs.contains(job))
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
        //create this
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
                    if (!this.assignedBrigade.getJobStatus())
                        for (Employee employee:this.assignedBrigade.getEmployeeList())
                            if (!employee.getJobStatus()) {
                                allFree = false;
                                break;
                            }
                }
                this.assignedBrigade.setJobStatus(true);
                job.start();
                this.assignedBrigade.setJobStatus(false);
            }
        }
        this.jobStatus = JobStatus.ENDED;
        this.endTime = LocalDateTime.now();
    }
    public long getOrderID() {
        return this.orderID;
    }
    @Override
    public String toString() {
        return "["+ this.orderID + "]" +
                " planing status:" + this.planningStatus.toString() +
                " job status:" + this.jobStatus +
                " brigade:" + this.assignedBrigade.toString() +
                " created at:" + this.creationTime +
                " stated at:" + this.startTime +
                " ended at:" + this.endTime;

    }
}
