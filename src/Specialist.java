import java.time.LocalDate;
import java.util.ArrayList;

public class Specialist extends Employee {
    private final long specialistID = counter++;
    private static long counter = 0;
    String specialization;

    Specialist(
    String name,
    String surname,
    LocalDate dateOfBirth,
    Department department,
    String specialization
    ) {
        super(
            name,
            surname,
            dateOfBirth,
            department
        );
       this.specialization = specialization;
       Log.create.classes(this.getClass(), name,surname,dateOfBirth,department,specialization);

    }


    @Override
    public String toString() {
        return super.toString() + "["+ this.specialistID + "]" +
                " Specialization:" + this.specialization;
    }

    public long getSpecialistID() {
        return this.specialistID;
    }
}
