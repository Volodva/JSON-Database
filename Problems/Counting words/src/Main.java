import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

class MapUtils {

    public static SortedMap<String, Integer> wordCount(String[] strings) {
        SortedMap<String, Integer> mapOfArr = new TreeMap<>();

        for (String word: strings) {
            if (mapOfArr.containsKey(word)){
                mapOfArr.replace(word, mapOfArr.get(word) + 1);
            } else {
                mapOfArr.put(word, 1);
            }
        }
        return mapOfArr;
    }

    public static void printMap(Map<String, Integer> map) {
        map.forEach((word, count) -> System.out.println(word + " : " + count));
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");
        MapUtils.printMap(MapUtils.wordCount(words));
    }
}