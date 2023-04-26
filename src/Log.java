import java.io.*;
import java.lang.reflect.Method;
import java.util.List;


public class Log {
    private static FileWriter fileWriter;
    private static BufferedWriter writer;
    private static int counter = 0;

    public static void reset() throws IOException {
        FileWriter file = new FileWriter("Log.txt");
        BufferedWriter reset = new BufferedWriter(file);
        reset.write("");
        reset.close();
    }

    static void openWriteLog(){
        try {
            if (counter++ == 0)
                Log.reset();
            Log.fileWriter = new FileWriter("Log.txt",true);
            Log.writer = new BufferedWriter(Log.fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static FileReader fileReader;
    static BufferedReader reader;

    static void openReadLog(){
        try {
            Log.fileReader = new FileReader("Log.txt");
            Log.reader = new BufferedReader(Log.fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

static class create {
    public static void classes(Class name, Object... arguments){
        try{
            Log.openWriteLog();
            String argumentList = "";
            for (Object obj:arguments) {
                argumentList = argumentList.concat(categorizeArgument(obj)) + ",";
            }
            writer.write(
                    name.getName() + ":" + " "
                    + argumentList +
                    "\n"
            );
            writer.close();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
    public static void methods(Object object, Method name, Object... arguments){
        try{
            Log.openWriteLog();
            String argumentList = "";
            for (Object obj:arguments) {
                argumentList = argumentList.concat(categorizeArgument(obj)) +
                ",";
            }
            writer.write(
                    "! " + object.getClass().getName() + ":" +
                    findClassID(object) + ":" + " " +
                    name.getName() + ":" + " " +
                            argumentList +
                            "\n"
            );
            writer.close();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
    private static Long findClassID(Object obj){
        if (obj.getClass() == Brigade.class)
            return ((Brigade) obj).getBrigadeID();
        else if (obj.getClass() == Department.class)
            return ((Department) obj).getDepartmentID();
        else if (obj.getClass() == Foreman.class)
            return ((Foreman) obj).getForemanID();
        else if (obj.getClass() == Job.class)
            return ((Job) obj).getJobID();
        else if (obj.getClass() == Order.class)
            return ((Order) obj).getOrderID();
        else if (obj.getClass() == Specialist.class)
            return ((Specialist) obj).getSpecialistID();
        else if (obj.getClass() == User.class)
            return ((User) obj).getUserID();
        return null;
    }
    private static String categorizeArgument(Object obj){
        if (obj.getClass() == String.class)
            return obj.getClass().getName() +
                    ":" + obj;
        if (obj.getClass() == null)
            return null +
                    ":" + "null";
        if (findClassID(obj)!=null)
            return obj.getClass().getName() +
                    ":" + findClassID(obj);
        return obj.getClass().getName() +
                    ":" + obj;

    }
    private static String categorizeArgument(List<Object> obj) {
        String argumentList = "[";
        for (Object element: obj) {
            argumentList = argumentList.concat(categorizeArgument(element)) + ",";
        }
        argumentList = argumentList.concat("]");
        return argumentList;
    }
}
}
