import java.util.ArrayList;
import java.util.List;

public class Brigade {
    long brigadeID = counter++;
    static long counter = 0;
    private String name;
    private Foreman foreman;
    private List<Employee> employeeList = new ArrayList<>();
    private boolean jobStatus = false;

    public void setJobStatus(boolean jobStatus) {
        for (Employee employee: employeeList)
            employee.jobStatus = jobStatus;
        this.jobStatus = jobStatus;
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
    }
    public void addEmployee(List<Employee> employees){
        for (Employee employee:employees)
            addEmployee(employee);

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean setForeman(Foreman foreman) {
        this.foreman = foreman;
        foreman.addBrigade(this);
        return true;
    }

    public Foreman getForeman() {
        return foreman;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }
}
