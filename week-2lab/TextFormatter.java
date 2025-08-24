import java.util.*;

public class TextFormatter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the text to format:");
        String text = sc.nextLine();
        System.out.print("Enter the desired line width: ");
        int width = sc.nextInt();
        sc.close();

        System.out.println("\nOriginal Text:\n" + text);

        List<String> words = splitWords(text);

        long start1 = System.nanoTime();
        List<String> justified = justifyText(words, width);
        long end1 = System.nanoTime();

        System.out.println("\nLeft-Justified Text:");
        displayLines(justified);

        List<String> centered = centerAlign(words, width);
        System.out.println("\nCenter-Aligned Text:");
        displayLines(centered);

        long start2 = System.nanoTime();
        justifyUsingConcat(words, width);
        long end2 = System.nanoTime();

        System.out.println("\nPerformance Analysis:");
        System.out.println("Using StringBuilder : " + (end1 - start1) + " ns");
        System.out.println("Using Concatenation : " + (end2 - start2) + " ns");
    }

    public static List<String> splitWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (i > start) words.add(text.substring(start, i));
                start = i + 1;
            }
        }
        if (start < text.length()) words.add(text.substring(start));
        return words;
    }

    public static List<String> justifyText(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.size()) {
            int lineLen = words.get(i).length();
            int j = i + 1;
            while (j < words.size() && lineLen + 1 + words.get(j).length() <= width) {
                lineLen += 1 + words.get(j).length();
                j++;
            }
            int gaps = j - i - 1;
            StringBuilder sb = new StringBuilder();
            if (j == words.size() || gaps == 0) {
                for (int k = i; k < j; k++) {
                    sb.append(words.get(k));
                    if (k < j - 1) sb.append(" ");
                }
                while (sb.length() < width) sb.append(" ");
            } else {
                int totalSpaces = width - (lineLen - gaps);
                int space = totalSpaces / gaps;
                int extra = totalSpaces % gaps;
                for (int k = i; k < j; k++) {
                    sb.append(words.get(k));
                    if (k < j - 1) {
                        for (int s = 0; s < space; s++) sb.append(" ");
                        if (extra-- > 0) sb.append(" ");
                    }
                }
            }
            lines.add(sb.toString());
            i = j;
        }
        return lines;
    }

    public static List<String> centerAlign(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (sb.length() + word.length() + 1 > width) {
                lines.add(centerLine(sb.toString().trim(), width));
                sb = new StringBuilder();
            }
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) lines.add(centerLine(sb.toString().trim(), width));
        return lines;
    }

    public static String centerLine(String line, int width) {
        int padding = (width - line.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) sb.append(" ");
        sb.append(line);
        while (sb.length() < width) sb.append(" ");
        return sb.toString();
    }

    public static void justifyUsingConcat(List<String> words, int width) {
        String result = "";
        int i = 0;
        while (i < words.size()) {
            String line = words.get(i);
            int j = i + 1;
            while (j < words.size() && line.length() + 1 + words.get(j).length() <= width) {
                line += " " + words.get(j);
                j++;
            }
            result += line + "\n";
            i = j;
        }
    }

    public static void displayLines(List<String> lines) {
        int lineNum = 1;
        for (String line : lines) {
            System.out.printf("%2d | %s | (%d chars)\n", lineNum++, line, line.length());
        }
    }
}
