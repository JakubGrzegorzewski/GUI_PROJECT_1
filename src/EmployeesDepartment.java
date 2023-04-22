import java.util.ArrayList;
import java.util.List;

public class EmployeesDepartment {
    String name;
    public List<Employee> employeeList = new ArrayList<Employee>();
    public static List<String> nameList = new ArrayList<String>();
    private EmployeesDepartment(String name){
        if(nameList.contains(name)){
            throw new NotUniqueNameException("This name is not unique");
        }
        nameList.add(name);
        this.name = name;
    }
    public static EmployeesDepartment createDepartment(String name){
        return new EmployeesDepartment(name);
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }
}
