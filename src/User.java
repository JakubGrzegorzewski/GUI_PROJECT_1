import java.util.Date;

public class User extends Employees{
    String login;
    String password;
    String initials;

    User(
    String name,
    String surname,
    Date dateOfBirth,
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
