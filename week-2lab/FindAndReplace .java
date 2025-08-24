import java.util.Scanner;

public class FindAndReplace {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter main text: ");
        String text = sc.nextLine();
        System.out.print("Enter substring to find: ");
        String find = sc.nextLine();
        System.out.print("Enter replacement substring: ");
        String replace = sc.nextLine();
        String manualResult = manualReplace(text, find, replace);
        String builtInResult = text.replace(find, replace);
        System.out.println("Manual Replace Result: " + manualResult);
        System.out.println("Built-in Replace Result: " + builtInResult);
        System.out.println("Are both results same? " + manualResult.equals(builtInResult));
        sc.close();
    }
    public static String manualReplace(String text, String find, String replace) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            if (i <= text.length() - find.length() && text.substring(i, i + find.length()).equals(find)) {
                result.append(replace);
                i += find.length();
            } else {
                result.append(text.charAt(i));
                i++;
            }
        }
        return result.toString();
    }
}
