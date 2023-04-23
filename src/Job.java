import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Job extends Thread{
    long jobID = counter++;
    static long counter = 0;
    public enum JobType{
        GENERAL, ASSEMBLY, DISASSEMBLY, REPLACEMENT;
    }
    private JobType jobType;
    private int time;
    private boolean completed = false;
    private String description;
    private Collection<Job> waitToEnd = new ArrayList<>();
    public static Map<Long, Object> allJobs = new HashMap<>();

    Job(JobType jobType, int time, String description){
        allJobs.put(this.jobID, this);
        this.jobType = jobType;
        this.time = time;
        this.description = description;

        System.out.println(jobType.toString() + " " + time + " " + description);
    }

    @Override
    public void run() {
        boolean flag = false;
        while (!flag){
            flag = true;
            for (Job job:waitToEnd) {
                if(!job.completed)
                    flag = false;
            }
        }

        try {
            this.wait(this.time* 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.completed = true;
    }

    public static Object getObject(int id){
        return allJobs.get(id);
    }
}
