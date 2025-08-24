 import java.util.Scanner;
 public class WordsAndLengths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a line of text: ");
        String input = scanner.nextLine();
        String[] words = splitWords(input);
        String[][] wordsWithLengths = getWordsWithLengths(words);
        System.out.println("Word\tLength");
        for (int i = 0; i < wordsWithLengths.length; i++) {
            System.out.println(wordsWithLengths[i][0] + "\t" + 
Integer.parseInt(wordsWithLengths[i][1]));
        }
    }
    public static String[] splitWords(String text) {
        int length = getStringLength(text);
        int spaceCount = 0;
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') spaceCount++;
        }
        int[] spaceIndexes = new int[spaceCount];
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                spaceIndexes[index++] = i;
            }
        }
        String[] words = new String[spaceCount + 1];
        int start = 0;
        for (int i = 0; i < spaceCount; i++) {
            words[i] = substring(text, start, spaceIndexes[i]);
            start = spaceIndexes[i] + 1;
        }
        words[spaceCount] = substring(text, start, length);
        return words;
    }
    public static int getStringLength(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count);
                count++;
            }
        } catch (Exception e) {
            return count;
        }
    }
    public static String substring(String str, int start, int end) {
        String result = "";
        for (int i = start; i < end; i++) {
            result += str.charAt(i);
        }
        return result;
    }
    public static String[][] getWordsWithLengths(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i];
            result[i][1] = String.valueOf(getStringLength(words[i]));
        }
        return result;
    }
}