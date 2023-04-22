import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Foreman extends User{
    List<Brigade> brigadeList = new ArrayList<Brigade>();
    List<Order> orderList = new ArrayList<Order>();

    Foreman(
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
            department,
            login,
            password
        );
    }
    public List<Brigade> getBrigadeList(){
        return this.brigadeList;
    }
    public List<Order> getOrderList(){
        return this.orderList;
    }
}
