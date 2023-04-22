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
        return this.brigadeList;
    }
    public ArrayList<Order> getOrderList(){
        return this.orderList;
    }
}
