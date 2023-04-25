import java.util.ArrayList;
import java.util.List;

public class Department {
    private final long departmentID = counter++;
    private static long counter = 0;
    String name;
    public List<Employee> employeeList = new ArrayList<>();
    public static List<String> nameList = new ArrayList<>();
    private Department(String name){
        if(nameList.contains(name)){
            throw new NotUniqueNameException("This name is not unique");
        }
        nameList.add(name);
        this.name = name;
        ArrayList<String> list = new ArrayList<>();
        list.add(this.name);
        Log.write.create(Department.class, list);
    }
    public static Department createDepartment(String name){
        return new Department(name);
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }
    public boolean addEmployee(Employee employee){
        if(employeeList.contains(employee)) {
            System.out.println("Employee " + employee + " is already added");
            return false;
        }
        employeeList.add(employee);
        return true;
    }

    public long getDepartmentID() {
        return this.departmentID;
    }

    @Override
    public String toString() {
        return "["+ this.departmentID + "]" +
                " name" + name;
    }

}
