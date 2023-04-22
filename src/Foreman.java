import java.util.ArrayList;
import java.util.Date;

public class Foreman extends User{
    ArrayList<Brigade> brigadeList = new ArrayList<Brigade>();
    ArrayList<Order> orderList = new ArrayList<Order>();

    Foreman(
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
            department,
            login,
            password
        );
    }
    public ArrayList<Brigade> getBrigadeList(){
        System.out.println(this.brigadeList.toString());
        return this.brigadeList;
    }
}
