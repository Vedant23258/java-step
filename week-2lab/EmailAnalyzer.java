import java.util.*;

public class EmailAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of emails: ");
        int n = sc.nextInt();
        sc.nextLine();
        String[] emails = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter email " + (i + 1) + ": ");
            emails[i] = sc.nextLine();
        }

        analyzeEmails(emails);
        sc.close();
    }

    public static boolean isValid(String email) {
        int at = email.indexOf('@');
        int lastAt = email.lastIndexOf('@');
        int dot = email.indexOf('.', at);
        if (at == -1 || at != lastAt || dot == -1) return false;
        String username = email.substring(0, at);
        String domain = email.substring(at + 1);
        return !username.isEmpty() && !domain.isEmpty();
    }

    public static void extractAndDisplay(String email) {
        if (!isValid(email)) {
            System.out.printf("%-25s %-15s %-20s %-15s %-10s %-10s\n",
                    email, "-", "-", "-", "-", "Invalid");
            return;
        }
        int at = email.indexOf('@');
        String username = email.substring(0, at);
        String domain = email.substring(at + 1);
        int dot = domain.lastIndexOf('.');
        String domainName = domain.substring(0, dot);
        String extension = domain.substring(dot + 1);
        System.out.printf("%-25s %-15s %-20s %-15s %-10s %-10s\n",
                email, username, domain, domainName, extension, "Valid");
    }

    public static void analyzeEmails(String[] emails) {
        int validCount = 0, invalidCount = 0, totalUserLen = 0;
        Map<String, Integer> domainCount = new HashMap<>();

        System.out.printf("%-25s %-15s %-20s %-15s %-10s %-10s\n",
                "Email", "Username", "Domain", "Domain Name", "Ext", "Status");
        System.out.println("-------------------------------------------------------------------------------------------");

        for (String email : emails) {
            if (isValid(email)) {
                validCount++;
                int at = email.indexOf('@');
                String username = email.substring(0, at);
                totalUserLen += username.length();
                String domain = email.substring(at + 1);
                domainCount.put(domain, domainCount.getOrDefault(domain, 0) + 1);
            } else {
                invalidCount++;
            }
            extractAndDisplay(email);
        }

        String mostCommonDomain = "-";
        int max = 0;
        for (Map.Entry<String, Integer> entry : domainCount.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                mostCommonDomain = entry.getKey();
            }
        }

        double avgUserLen = validCount > 0 ? (double) totalUserLen / validCount : 0;

        System.out.println("\nAnalysis:");
        System.out.println("Total Valid Emails   : " + validCount);
        System.out.println("Total Invalid Emails : " + invalidCount);
        System.out.println("Most Common Domain   : " + mostCommonDomain);
        System.out.println("Average Username Len : " + avgUserLen);
    }
}
