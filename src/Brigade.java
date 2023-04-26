import java.util.ArrayList;
import java.util.List;

public class Brigade {
    private final long brigadeID = counter++;
    private static long counter = 0;
    private String name;
    private Foreman foreman;
    private final List<Employee> employeeList = new ArrayList<>();
    private boolean jobStatus = false;

    Brigade(){
        Log.create.classes(this.getClass());
    }

    public void setJobStatus(boolean jobStatus) {
        for (Employee employee: employeeList)
            employee.setJobStatus(jobStatus);
        this.jobStatus = jobStatus;
        try{
        Log.create.methods(this, Brigade.class.getDeclaredMethod("setJobStatus", boolean.class), jobStatus);
        }catch (NoSuchMethodException e){e.printStackTrace();}
    }

    public void addEmployee(Employee employee){
        if (!employeeList.contains(employee)){
            if(employee.getClass() == Foreman.class)
                employeeList.add(employee);
            else if(employee.getClass() == User.class)
                return;
            else
                employeeList.add(employee);

        }
        try{
            Log.create.methods(this, Brigade.class.getDeclaredMethod("addEmployee", Employee.class), employee);
        }catch (NoSuchMethodException e){e.printStackTrace();}
    }
    public void addEmployee(List<Employee> employees){
        for (Employee employee:employees)
            addEmployee(employee);
        try{
            Log.create.methods(this, Brigade.class.getDeclaredMethod("addEmployee", List.class), employees);
        }catch (NoSuchMethodException e){e.printStackTrace();}

    }

    public void setName(String name) {
        this.name = name;
        try{
            Log.create.methods(this, Brigade.class.getDeclaredMethod("setJobStatus", boolean.class), jobStatus);
        }catch (NoSuchMethodException e){e.printStackTrace();}
    }

    public String getName() {
        return name;
    }

    public boolean setForeman(Foreman foreman) {
        this.foreman = foreman;
        foreman.addBrigade(this);
        try{
            Log.create.methods(this, Brigade.class.getDeclaredMethod("setForeman", Foreman.class), foreman);
        }catch (NoSuchMethodException e){e.printStackTrace();}
        return true;
    }

    public Foreman getForeman() {
        return foreman;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public boolean getJobStatus() {
        return jobStatus;
    }


    public long getBrigadeID() {
        return this.brigadeID;
    }
    @Override
    public String toString() {
        if (this.foreman!=null)
            return "["+ this.getBrigadeID() + "]" +
                    " name:" + this.getName() +
                    " foreman:" + this.getForeman().toString() +
                    " all employees:" + this.getEmployeeList().toString() +
                    " at work:" + this.getJobStatus();
        else
            return "["+ this.getBrigadeID() + "]" +
                    " name:" + this.getName() +
                    " foreman:" + null +
                    " all employees:" + this.getEmployeeList().toString() +
                    " at work:" + this.getJobStatus();
    }
}
