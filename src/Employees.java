import java.util.ArrayList;
import java.util.Date;

public abstract class Employees implements Comparable<Employees>{
    private String name;
    private String surname;
    private Date dateOfBirth;
    private EmployeesDepartment department;

    public static ArrayList<Employees> allEmployees = new ArrayList<Employees>();
    Employees(
            String name,
            String surname,
            Date dateOfBirth,
            EmployeesDepartment department
    ){
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
    }

    @Override
    public int compareTo(Employees comperedEmployee) {
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
