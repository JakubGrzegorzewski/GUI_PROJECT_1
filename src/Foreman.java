import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Foreman extends User{
    long foremanID = counter++;
    static long counter = 0;
    private List<Brigade> brigadeList = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();

    Foreman(
    String name,
    String surname,
    LocalDateTime dateOfBirth,
    Department department,
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
    public boolean addBrigade(Brigade brigade){
        if(this.brigadeList.contains(brigade))
            return false;
        this.brigadeList.add(brigade);
        return true;
    }
    public boolean addOrder(Order order){
        if(this.orderList.contains(order))
            return false;
        this.orderList.add(order);
        return true;

    }
}
