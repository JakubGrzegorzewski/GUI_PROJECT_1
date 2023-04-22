import java.util.ArrayList;

public class Brigade {
    String name;
    Foreman foreman;
    ArrayList<Employee> employeeList = new ArrayList<Employee>();
    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }
    public void addEmployee(ArrayList employees){
        employeeList.addAll(employees);
    }
}
