import java.io.*;
import java.util.*;
import static java.util.stream.Collectors.toList;


/*
 * This class processes an arbitrary text file.
 * It has methods that do not adhere to clean coding practices.
 * Refactor this class.
 */
public class DirtyFunctions {

    private static List<String> data = new ArrayList<>();

    public static void main(String[] args) {
        // parse arguments
        boolean transform = false;
        String filename = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-t") && i + 1 < args.length) {
                transform = Boolean.parseBoolean(args[i + 1]);
            } else if (args[i].equals("-f") && i + 1 < args.length) {
                filename = args[i + 1];
            }
        }
        if (filename.equals("")){
            throw new IllegalArgumentException("You must supply a file name");
        }
        processFile(filename, transform);
        displayData("data", "start", "data", "end");
    }

    private static void processFile(String filename, boolean transform) {
        // Read
        File file = new File(filename);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (transform) {
                    line = line.toUpperCase();
                }
                data.add(line);
            }
            if (transform) { 
                data.add("END OF TRANSFORMED DATA"); 
            } else {
                data.add("END OF DATA");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // Remove short lines
        Iterator<String> iterator = data.iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.length() < 5) {
                iterator.remove();
            }
        }
    }

    private static void displayData(String headerFirstWord, String headerSecondWord, String footerFirstWord, String footerSecondWord) {
        data = data.stream().map(String::toLowerCase).collect(toList());
        System.out.println(headerFirstWord + " " + headerSecondWord);
        for (String line : data) {
            System.out.println(line);
        }
        System.out.println(footerFirstWord + " " + footerSecondWord);
    }
}