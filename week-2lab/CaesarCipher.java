import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter shift value: ");
        int shift = sc.nextInt();

        String encrypted = encrypt(text, shift);
        String decrypted = decrypt(encrypted, shift);

        System.out.println("\nOriginal Text with ASCII values:");
        displayAscii(text);

        System.out.println("\nEncrypted Text with ASCII values:");
        displayAscii(encrypted);

        System.out.println("\nDecrypted Text with ASCII values:");
        displayAscii(decrypted);

        System.out.println("\nValidation: " + text.equals(decrypted));
        sc.close();
    }

    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                char ch = (char) ((c - 'A' + shift) % 26 + 'A');
                result.append(ch);
            } else if (Character.isLowerCase(c)) {
                char ch = (char) ((c - 'a' + shift) % 26 + 'a');
                result.append(ch);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    public static void displayAscii(String text) {
        for (char c : text.toCharArray()) {
            System.out.println(c + " -> " + (int) c);
        }
    }
}
