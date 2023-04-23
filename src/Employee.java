import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public abstract class Employee implements Comparable<Employee>{
    long employeeID = counter++;
    static long counter = 0;
    private String name;
    private String surname;
    private LocalDateTime dateOfBirth;
    private Department department;
    public boolean jobStatus = false;

    public static List<Employee> allEmployees = new ArrayList<>();

    Employee(
            String name,
            String surname,
            LocalDateTime dateOfBirth,
            Department department
    ){
        allEmployees.add(this);
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
    }

    public boolean setDepartment(Department department){
        if(this.department != null){
            System.out.println("The department is already set to: " + this.department);
            return false;
        }
        this.department = department;
        return true;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setSurname(String surname) {
        this.surname = surname;
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

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", department=" + department +
                '}';
    }
}
