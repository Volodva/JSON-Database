import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> word1 = new ArrayList<>(List.of(sc.nextLine().toLowerCase().split("")));
        List<String> word2 = new ArrayList<>(List.of(sc.nextLine().toLowerCase().split("")));

        Map<String, Integer> map = new HashMap<>();
        for (String s : word1) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        for (String s : word2) {
            if (map.containsKey(s) && word1.contains(s)) {
                map.put(s, map.get(s) - 1);

            } else if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        Integer result = 0;
        for (Integer integer : map.values()) {
            result += Math.abs(integer);
        }

        System.out.println(result);
    }
}