import java.time.LocalDate;
import java.util.ArrayList;

public class User extends Employee {
    private final long userID = counter++;
    private static long counter = 0;
    String login;
    String password;
    String initials;

    User(
    String name,
    String surname,
    LocalDate dateOfBirth,
    Department department,
    String login,
    String password
    ) {
        super(
            name,
            surname,
            dateOfBirth,
            department
        );
        this.login = login;
        this.password = password;
        this.initials = name.charAt(0) + "" + surname.charAt(0);
    }
    boolean updateCredentials(String name, String surname){
        if(name.isBlank() || surname.isBlank()){
            if(!(name.isBlank() && surname.isBlank())){
                if(name.isBlank())
                    System.out.println("No name provided");
                if(name.isBlank())
                    System.out.println("No surname provided");
            }
            System.out.println("No name and surname provided");
            return false;
        }
        super.setSurname(surname);
        super.setName(name);
        this.initials = name.charAt(0) + "" + surname.charAt(0);
        return true;
    }

}
