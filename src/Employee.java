import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public abstract class Employee implements Comparable<Employee>{
    private final long employeeID = counter++;
    private static long counter = 0;
    private String name;
    private String surname;
    private final LocalDate dateOfBirth;
    private final Department department;
    private boolean jobStatus = false;

    public static List<Employee> allEmployees = new ArrayList<>();

    Employee(
            String name,
            String surname,
            LocalDate dateOfBirth,
            Department department
    ){
        allEmployees.add(this);
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.department = department;

    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
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
        return "["+ this.employeeID + "]" +
                " name:" + this.name +
                " surname:" + this.surname +
                " dateOfBirth:" + this.dateOfBirth +
                " department:" + this.department;
    }


    public long getEmployeeID() {
        return this.employeeID;
    }
    public boolean getJobStatus() {
        return jobStatus;
    }
    public void setJobStatus(boolean jobStatus) {
        this.jobStatus = jobStatus;
        try{
            Log.create.methods(this, Employee.class.getDeclaredMethod("setJobStatus", boolean.class), jobStatus);
        }catch (NoSuchMethodException e){e.printStackTrace();}
    }
}
