package encryptdecrypt;
import java.util.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String mode = "enc"; //default value
        String data = "";
        String encodedDecodedData = "";
        int key = 0;
        String fileNameIn = "";
        String fileNameOut = "";

        // collect all the command line arguments
        for(int i = 0; i < args.length; i=i+2) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i+1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i+1]);
                    break;
                case "-data":
                    data = args[i+1];
                    break;
                case "-in":
                    fileNameIn = args[i+1];
                    try {
                        data = readFileAsString(fileNameIn);
                    } catch(IOException e) {
                        System.out.println("Cannot read file: " + e.getMessage());
                    }
                    break;
                case "-out":
                    fileNameOut = args[i+1];
                    break;
                default:
                    System.out.println("Unsupported Commands");
                    break;
            }
        }

        if (mode.equals("enc"))
            encodedDecodedData = encodeDecode(data, key);
         else if (mode.equals("dec"))
            encodedDecodedData = encodeDecode(data, -key); //negative key un-shifts encrypted data

        // print to STDOUT if no filename is provided
        if(fileNameOut.equals("")) {
            System.out.println(encodedDecodedData);
        // otherwise write data to fileNameOut
        } else {
            try(FileWriter writer = new FileWriter(fileNameOut)) {
                writer.write(encodedDecodedData);
            } catch (IOException e) {
                System.out.printf("An exception has occured: %s", e.getMessage());
            }
        }

    }

    private static String encodeDecode(String line, int shiftAmount) {
        String s = "";
        int len = line.length();
        for (int x = 0; x < len; x++) {
            s += (char) (line.charAt(x) + shiftAmount);
        }
        return s;
    }

    private static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}