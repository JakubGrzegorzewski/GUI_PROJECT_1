import java.util.Date;

public class Specialist extends Employees{
    String tekst;

    Specialist(
    String name,
    String surname,
    Date dateOfBirth,
    EmployeesDepartment department
    ) {
        super(
            name,
            surname,
            dateOfBirth,
            department
        );
    }
}
