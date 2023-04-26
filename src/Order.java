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
    public static Map<Long, Object> allOrders = new HashMap<>();

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
        Order.addOrderToAllOrders(this);
        if (planned)
            this.setPlanningStatus(PlanningStatus.PLANNED);
        else
            this.setPlanningStatus(PlanningStatus.UNPLANNED);
        this.setCreationTime();
        this.setJobStatus(JobStatus.CREATED);

        ArrayList<String> list = new ArrayList<>();
        list.add(Boolean.toString(planned));

        if (jobs != null) {
            this.setAssignedJobs(jobs);
            list.add(jobs.toString());
        }
        else
            list.add("null");

        if (brigade != null) {
            this.setBrigade(brigade);
            list.add(""+brigade.getBrigadeID());
        }
        else
            list.add("null");
        Log.write.create(Order.class, list);
    }

    public boolean addJob(Job job){
        if(this.getAssignedJobs() == null || this.getAssignedJobs().contains(job))
            return false;
        this.assignedJobs.add(job);
        return true;
    }

    public void startOrder(){
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        this.setStartTime();
        this.setJobStatus(JobStatus.STARTED);
        if(!this.getAssignedJobs().isEmpty() && this.getAssignedJobs() != null) {
            for (Job job: this.getAssignedJobs()) {
                boolean allFree = false;
                while(!allFree){
                    allFree = true;
                    if (!this.getAssignedBrigade().getJobStatus())
                        for (Employee employee:this.getAssignedBrigade().getEmployeeList())
                            if (!employee.getJobStatus()) {
                                allFree = false;
                                break;
                            }
                }
                this.getAssignedBrigade().setJobStatus(true);
                job.start();
                this.getAssignedBrigade().setJobStatus(false);
            }
        }
        this.setJobStatus(JobStatus.ENDED);
        this.setEndTime();
    }
    @Override
    public String toString() {
        return "["+ this.getOrderID() + "]" +
                " planing status:" + this.getPlanningStatus().toString() +
                " job status:" + this.getJobStatus() +
                " brigade:" + this.getAssignedBrigade().toString() +
                " created at:" + this.getCreationTime() +
                " stated at:" + this.getStartTime() +
                " ended at:" + this.getEndTime();

    }

    public long getOrderID() {
        return orderID;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    private void setCreationTime() {
        if (this.creationTime != null)
            this.creationTime = LocalDateTime.now();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    private void setStartTime() {
        if (this.startTime != null)
            this.startTime = LocalDateTime.now();    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    private void setEndTime() {
        if (this.endTime != null)
            this.endTime = LocalDateTime.now();    }

    public PlanningStatus getPlanningStatus() {
        return planningStatus;
    }

    private void setPlanningStatus(PlanningStatus planningStatus) {
        this.planningStatus = planningStatus;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    private void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Brigade getAssignedBrigade() {
        return assignedBrigade;
    }

    public void setBrigade(Brigade brigade){
        if(this.assignedBrigade != null)
            return;
        this.assignedBrigade = brigade;
        this.assignedBrigade.getForeman().addOrder(this);
    }

    public List<Job> getAssignedJobs() {
        return assignedJobs;
    }

    public void setAssignedJobs(List<Job> assignedJobs) {
        this.assignedJobs = assignedJobs;
    }

    public static void addOrderToAllOrders(Order order){
        Order.allOrders.put(order.getOrderID(), order);
    }

    public static Map<Long, Object> getAllOrders() {
        return allOrders;
    }
}
