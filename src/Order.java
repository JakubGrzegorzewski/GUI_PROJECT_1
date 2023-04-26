import java.time.LocalDateTime;
import java.util.*;

public class Order implements Runnable{
    private final long orderID = counter++;
    private static long counter = 0;

    private LocalDateTime creationTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private PlanningStatus planningStatus;
    private OrderStatus orderStatus;
    private Brigade assignedBrigade;

    private final List<Job> assignedJobs = new ArrayList<>();
    public static Map<Long, Object> allOrders = new HashMap<>();

    public enum PlanningStatus {
        PLANNED, UNPLANNED
    }
    public enum OrderStatus {
        CREATED, STARTED, ENDED
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
        this.setOrderStatus(OrderStatus.CREATED);
        if (jobs != null)
            this.addJob(jobs);
        if (brigade != null)
            this.addBrigade(brigade);
        Log.create.classes(this.getClass(),planned, jobs, brigade);

    }

    private void addJob(List<Job> jobs) {
        for (Job job:jobs) {
            addJob(job);
        }
    }
    public boolean addJob(Job job) {
        if(this.getAssignedJobs() == null || this.getAssignedJobs().contains(job))
            return false;
        this.assignedJobs.add(job);
        try{
            Log.create.methods(this, Order.class.getDeclaredMethod("addJob", Job.class),job);
        }catch (NoSuchMethodException e){e.printStackTrace();}
        return true;
    }

    public boolean addBrigade(Brigade brigade){
        if(this.assignedBrigade != null)
            return false;
        this.assignedBrigade = brigade;
        this.assignedBrigade.getForeman().addOrder(this);
        try{
            Log.create.methods(this, Order.class.getDeclaredMethod("addBrigade", Brigade.class),brigade);
        }catch (NoSuchMethodException e){e.printStackTrace();}
        return true;
    }

    public void startOrder(){
        Thread thread = new Thread(this);
        thread.start();
        try{
            Log.create.methods(this, Order.class.getDeclaredMethod("startOrder"));
        }catch (NoSuchMethodException e){e.printStackTrace();}
    }

    @Override
    public void run() {
        this.setStartTime();
        this.setOrderStatus(OrderStatus.STARTED);
        if(!this.getAssignedJobs().isEmpty() && this.getAssignedJobs() != null) {
            for (Job job: this.getAssignedJobs()) {
                boolean allFree = false;
                while(!allFree){
                    allFree = true;
                    if (!this.getAssignedBrigade().getJobStatus())
                        for (Employee employee:this.getAssignedBrigade().getEmployeeList())
                            if (employee.getJobStatus()) {
                                allFree = false;
                                break;
                            }
                }
                this.getAssignedBrigade().setJobStatus(true);
                job.start();
                this.getAssignedBrigade().setJobStatus(false);
            }
        }
        this.setOrderStatus(OrderStatus.ENDED);
        this.setEndTime();
    }
    @Override
    public String toString() {
        return "["+ this.getOrderID() + "]" +
                " planing status:" + this.getPlanningStatus().toString() +
                " job status:" + this.getOrderStatus() +
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    private void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Brigade getAssignedBrigade() {
        return assignedBrigade;
    }

    public List<Job> getAssignedJobs() {
        return assignedJobs;
    }

    private static void addOrderToAllOrders(Order order){
        Order.allOrders.put(order.getOrderID(), order);
    }

    public static Map<Long, Object> getAllOrders() {
        return allOrders;
    }
}
