import java.util.ArrayList;
import java.util.List;

public class Brigade {
    String name;
    Foreman foreman;
    List<Employee> employeeList = new ArrayList<Employee>();
    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }
    public void addEmployee(List employees){
        employeeList.addAll(employees);
    }
}
