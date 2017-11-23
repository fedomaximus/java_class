import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Calculus {

    // using Map
    public Map<Character, Integer> byUsingMap_CharsInString(String ContentintheFile) {
        Map<Character, Integer> charMap = new HashMap<Character, Integer>();
        if (ContentintheFile != null) {
            for (Character charic : ContentintheFile.toCharArray()) {
                Integer count = charMap.get(charic);
                int newCount = (count == null ? 1 : count + 1);
                charMap.put(charic, newCount);
            }
        }
        return charMap;
    }



    public void byUsing_MapAndIterator(String ContentintheFile) {
        char array[] = ContentintheFile.toLowerCase().toCharArray();
        Map<Character, Integer> map = new HashMap<Character, Integer>();

        for (int i = 0; i < ContentintheFile.length(); ++i) {
            char singlechar = ContentintheFile.charAt(i);
            int newCount = (map.get(singlechar) != null ? map.get(singlechar) : 0);
            newCount++;
            // если надо, то проверяем является ли символ буквой
            if (Character.isLetter(singlechar)) {
                if (map.containsKey(singlechar)) {
                    map.put(singlechar, newCount);
                } else {
                    map.put(singlechar, 1);
                }
            }
        }
        for (Iterator<Character> it = map.keySet().iterator(); it.hasNext(); ) {
            Character key = it.next();
            System.out.println(key + " = " + map.get(key));
        }


    }


    //Using simple array
    public  void byUsingArray_CalculateCharsNum_andPrinttoConsole(String ContentintheFile) {
        int[] arr = new int[255];
        for (int i = 0; i < ContentintheFile.length(); i++) {
            //Для извлечения символов по индексу в классе String определен метод
            // char charAt(int index). Он принимает индекс, по которому надо получить символов,
            // и возвращает извлеченный символ
            arr[ContentintheFile.charAt(i)]++;
        }
        for (int i = 0; i < 255; i++) {
            if (arr[i] > 0) {
                System.out.println((char) i + " matches " + arr[i] + " times");
            }
        }
    }


}
