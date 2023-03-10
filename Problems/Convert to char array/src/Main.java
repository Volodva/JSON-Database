import java.io.CharArrayWriter;
import java.io.IOException;

class Converter {
    public static char[] convert(String[] words) throws IOException {
        CharArrayWriter stringWriter = new CharArrayWriter();
        for (String word : words) {
            stringWriter.write(word);
        }
        stringWriter.close();
        return stringWriter.toCharArray();
    }

}
