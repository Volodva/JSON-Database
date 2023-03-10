import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstWord = scanner.nextLine();
        String secondWord = scanner.nextLine();

        char[] firstArr = firstWord.toLowerCase().toCharArray();
        char[] secondArr = secondWord.toLowerCase().toCharArray();

        Map<Character,Integer> firstMap = new HashMap<>();
        Map<Character,Integer> secondMap = new HashMap<>();

        for (char ch: firstArr) {
            if (firstMap.containsKey(ch)){
                firstMap.replace(ch, firstMap.get(ch) + 1);
            } else {
                firstMap.put(ch, 1);
            }
        }

        for (char ch: secondArr) {
            if (secondMap.containsKey(ch)){
                secondMap.replace(ch, secondMap.get(ch) + 1);
            } else {
                secondMap.put(ch, 1);
            }
        }

        if (Objects.equals(firstMap, secondMap)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

    }
}