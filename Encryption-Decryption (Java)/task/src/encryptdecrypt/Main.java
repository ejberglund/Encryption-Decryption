package encryptdecrypt;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        String mode = "";
        String data = "";
        int key = 0;

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
                default:
                    mode = "enc";
            }
        }

        if (mode.equals("enc"))
            encode(data, key);
         else if (mode.equals("dec"))
            encode(data, -key);

    }

    private static void encode(String line, int shiftAmount) {
        String s = "";
        int len = line.length();
        for (int x = 0; x < len; x++) {
            s += (char) (line.charAt(x) + shiftAmount);
        }
        System.out.println(s);
    }
}