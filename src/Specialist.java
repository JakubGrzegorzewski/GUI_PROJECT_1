import java.time.LocalDateTime;

public class Specialist extends Employee {
    long specialistID = counter++;
    static long counter = 0;
    String specialization;

    Specialist(
    String name,
    String surname,
    LocalDateTime dateOfBirth,
    Department department
    ) {
        super(
            name,
            surname,
            dateOfBirth,
            department
        );
    }

    @Override
    public String toString() {
        return super.toString() + "Specialist{" +
                "specialization='" + specialization + '\'' +
                '}';
    }
}
