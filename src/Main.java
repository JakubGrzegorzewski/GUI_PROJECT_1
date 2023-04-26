import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Brigade brigade = new Brigade();
        brigade.setName("Marzec 1");

        Department department = Department.createDepartment("Depar1");

        User user = new User("Jakub","Czanel", LocalDate.of(2030,9,4),department,"Test","pass");
        Specialist specialist = new Specialist("Jakub","Czanel", LocalDate.of(2030,9,4),department,"Book");
        Foreman foreman = new Foreman("Jakub", "Grzegorzewski", LocalDate.now(), department, "Login", "Haslo");
//        department.addEmployee(`user`);
        brigade.addEmployee(specialist);
        brigade.setForeman(foreman);
        Job job1 = new Job(Job.Type.ASSEMBLY, 1, "Job1");
        Job job2 = new Job(Job.Type.ASSEMBLY, 1, "Job2");
        Job job3 = new Job(Job.Type.ASSEMBLY, 1, "Job3");
        Job job4 = new Job(Job.Type.ASSEMBLY, 1, "Job4");
        Job job5 = new Job(Job.Type.ASSEMBLY, 1, "Job5");
        Job job6 = new Job(Job.Type.ASSEMBLY, 1, "Job6");
        Job job7 = new Job(Job.Type.ASSEMBLY, 1, "Job7");
        job2.addJobToWait(job1);
        job3.addJobToWait(job2);
        job4.addJobToWait(job2);
        job5.addJobToWait(job2);
        job6.addJobToWait(job3);
        job7.addJobToWait(job6);
        job7.addJobToWait(job5);
        job7.addJobToWait(job4);

        ArrayList<Job> jobArrayList = new ArrayList<>();
        jobArrayList.add(job1);
        jobArrayList.add(job2);
        jobArrayList.add(job3);
        jobArrayList.add(job4);

        Order order = new Order(true, jobArrayList, brigade);
        order.addJob(job5);
        order.addJob(job6);
        order.addJob(job7);
        order.startOrder();
    }
}