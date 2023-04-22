import java.util.ArrayList;

public class EmployeesDepartment {
    String name;
    public ArrayList<Employees> employeeList = new ArrayList<Employees>();
    public static ArrayList<String> nameList = new ArrayList<String>();
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

    public ArrayList<Employees> getEmployeeList() {
        return employeeList;
    }
}
