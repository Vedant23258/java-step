import java.util.Scanner;

public class StringPerformance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of iterations: ");
        int n = sc.nextInt();
        Result concatRes = testConcat(n);
        Result builderRes = testBuilder(n);
        Result bufferRes = testBuffer(n);
        System.out.printf("%-15s %-20s %-20s\n", "Method", "Time (ms)", "Final Length");
        System.out.printf("%-15s %-20d %-20d\n", "Concat", concatRes.time, concatRes.length);
        System.out.printf("%-15s %-20d %-20d\n", "StringBuilder", builderRes.time, builderRes.length);
        System.out.printf("%-15s %-20d %-20d\n", "StringBuffer", bufferRes.time, bufferRes.length);
        sc.close();
    }
    static class Result {
        long time;
        int length;
        Result(long time, int length) {
            this.time = time;
            this.length = length;
        }
    }
    public static Result testConcat(int n) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) {
            s += "a";
        }
        long end = System.currentTimeMillis();
        return new Result(end - start, s.length());
    }
    public static Result testBuilder(int n) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
        long end = System.currentTimeMillis();
        return new Result(end - start, sb.length());
    }
    public static Result testBuffer(int n) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
        long end = System.currentTimeMillis();
        return new Result(end - start, sb.length());
    }
}
