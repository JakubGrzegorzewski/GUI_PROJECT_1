import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Foreman extends User{
    private final long foremanID = counter++;
    private static long counter = 0;
    private List<Brigade> brigadeList = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();

    Foreman(
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
            department,
            login,
            password
        );
        ArrayList<String> list = new ArrayList<>();
        list.add(name);
        list.add(surname);
        list.add(dateOfBirth.toString());
        list.add(department.getDepartmentID()+"");
        list.add(login);
        list.add(password);
        Log.write.create(Foreman.class,list);
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

    public long getForemanID() {
        return this.foremanID;
    }

    @Override
    public String toString() {
        return super.toString() + "["+ this.foremanID + "]" +
                " list of brigades:" + this.brigadeList +
                " list of orders:" + this.orderList;
    }
}
