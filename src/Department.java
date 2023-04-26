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
    }
    public static Department createDepartment(String name){
        Department department = new Department(name);
        try{
            Log.create.methods(department, Department.class.getDeclaredMethod("createDepartment", String.class), name);
        }catch (NoSuchMethodException e){e.printStackTrace();}
        return department;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public long getDepartmentID() {
        return this.departmentID;
    }

    @Override
    public String toString() {
        return "["+ this.departmentID + "]" +
                " name:" + name;
    }

}
