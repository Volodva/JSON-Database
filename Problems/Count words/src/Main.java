import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int counter = 0;
        int charAsNumber = (int) reader.read();
        int prevChar = charAsNumber;
        charAsNumber =  reader.read();
        while (charAsNumber != -1) {
            if (charAsNumber == 32 && prevChar != 32) {
                counter++;
            }
            prevChar = charAsNumber;
            charAsNumber =  reader.read();

        }
        if (counter !=0) counter++;
        System.out.println(counter);
        reader.close();
    }
}