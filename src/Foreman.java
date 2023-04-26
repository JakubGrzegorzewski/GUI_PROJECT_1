import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Foreman extends User{
    private final long foremanID = counter++;
    private static long counter = 0;
    private final List<Brigade> brigadeList = new ArrayList<>();
    private final List<Order> orderList = new ArrayList<>();

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
        Log.create.classes(this.getClass(), name, surname, dateOfBirth, department, login, password);
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
        try{
            Log.create.methods(this, Foreman.class.getDeclaredMethod("addBrigade", Brigade.class), brigade);
        }catch (NoSuchMethodException e){e.printStackTrace();}
        return true;
    }
    public boolean addOrder(Order order){
        if(this.orderList.contains(order))
            return false;
        this.orderList.add(order);
        try{
            Log.create.methods(this, Foreman.class.getDeclaredMethod("addOrder", Order.class), order);
        }catch (NoSuchMethodException e){e.printStackTrace();}
        return true;

    }
    public long getForemanID() {
        return this.foremanID;
    }



    @Override
    public String toString() {
        return super.toString() + "["+ this.getForemanID() + "]";
//                " list of orders:" + this.getOrderList().toString();
    }
}
