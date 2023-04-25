import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Job extends Thread{
    private final long jobID = counter++;
    private static long counter = 0;
    public enum Type {
        GENERAL, ASSEMBLY, DISASSEMBLY, REPLACEMENT;
    }
    private Type type;
    private int time;
    private boolean completed = false;
    private String description;
    private Collection<Job> waitToEnd = new ArrayList<>();
    public static Map<Long, Object> allJobs = new HashMap<>();

    Job(Type type, int time, String description){
        allJobs.put(this.jobID, this);
        this.type = type;
        this.time = time;
        this.description = description;

    }

    @Override
    public void run() {
        boolean flag = false;
        while (!flag){
            flag = true;
            for (Job job:waitToEnd) {
                if (!job.completed) {
                    flag = false;
                    break;
                }
            }
        }

        try {
            this.wait(this.time* 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.completed = true;
    }

    public long getJobID() {
        return this.jobID;
    }

    public static Object getObject(int id){
        return allJobs.get(id);
    }

    @Override
    public String toString() {
        return "["+ this.jobID + "]" +
                " Is completed:" + this.completed +
                " Type:" + this.type.toString() +
                " Description:" + this.description;
    }
}
