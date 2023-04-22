import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public abstract class Employee implements Comparable<Employee>{
    private String name;
    private String surname;
    private LocalDateTime dateOfBirth;
    private EmployeesDepartment department;

    public static List<Employee> allEmployees = new ArrayList<Employee>();
    Employee(
            String name,
            String surname,
            LocalDateTime dateOfBirth,
            EmployeesDepartment department
    ){
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
    }

    @Override
    public int compareTo(Employee comperedEmployee) {
        int dateComparison = this.dateOfBirth.compareTo(comperedEmployee.dateOfBirth);
        int surnameComparison = this.surname.compareTo(comperedEmployee.surname);
        int nameComparison = this.name.compareTo(comperedEmployee.name);
        if(nameComparison != 0)
            return dateComparison;
        else if (surnameComparison != 0)
            return surnameComparison;
        else return dateComparison;
    }
}
