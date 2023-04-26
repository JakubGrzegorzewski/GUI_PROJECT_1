import java.time.LocalDate;

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
        this.initials = String.valueOf(name.charAt(0)).concat(String.valueOf(surname.charAt(0)));
        Log.create.classes(this.getClass(), name,surname,dateOfBirth,department,login,password);

    }

    @Override
    public void setName(String name) {
        super.setName(name);
        this.updateCredentials(name, getSurname());
    }

    @Override
    public void setSurname(String surname) {
        super.setSurname(surname);
        this.updateCredentials(getName(),surname);
    }
    private boolean updateCredentials(String name, String surname){
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
        this.initials = String.valueOf(name.charAt(0)).concat(String.valueOf(surname.charAt(0)));
        try{
            Log.create.methods(this, User.class.getDeclaredMethod("updateCredentials", String.class, String.class),name, surname);
        }catch (NoSuchMethodException e){e.printStackTrace();}
        return true;
    }

    public long getUserID() {
        return this.userID;
    }

    @Override
    public String toString() {
        return super.toString() + "["+ this.userID + "]" +
                " Initials:" + this.initials +
                " Login:" + this.login +
                " Password:" + this.password;
    }
}
