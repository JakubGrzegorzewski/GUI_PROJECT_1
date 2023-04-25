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
        ArrayList<String> list = new ArrayList<>();
        list.add(name);
        list.add(surname);
        list.add(dateOfBirth.toString());
        list.add(department.getDepartmentID()+"");
        list.add(specialization);
       Log.write.create(Specialist.class, list);
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
