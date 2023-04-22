import java.time.LocalDateTime;

public class Specialist extends Employee {
    String tekst;

    Specialist(
    String name,
    String surname,
    LocalDateTime dateOfBirth,
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
