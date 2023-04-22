import java.time.LocalDateTime;

public class User extends Employee {
    String login;
    String password;
    String initials;

    User(
    String name,
    String surname,
    LocalDateTime dateOfBirth,
    EmployeesDepartment department,
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
}
