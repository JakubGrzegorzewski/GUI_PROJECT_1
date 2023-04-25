import java.io.*;
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

static class write{
    public static void create(Class name, List<String> argumentList){
        try{
            Log.openWriteLog();
            writer.write(
                    name.getName() + ":" + " "
                    + argumentList.toString() +
                    "\n"
            );
            writer.close();
        }catch (IOException e){
            throw new RuntimeException();
        }

    }
}

public static class read{
    public static void all() throws IOException{
        Log.openReadLog();
        String line;
        int temp = reader.read();
        char character = (char) temp;
        while (temp != -1){
            line = "";
            while (character != '\n'){
                line = line.concat(Character.toString(character));
                temp = reader.read();
                character = (char) temp;
            }
            Log.read.line(line);
        }
    }
    public static void line(String line){

    }
}
}
