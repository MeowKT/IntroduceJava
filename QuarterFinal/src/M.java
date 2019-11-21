import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class M {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        AccurateMovement solver = new AccurateMovement();
        int testCount = in.nextInt();
        for (int test = 1; test <= testCount; test++) {
            solver.solve(test, in, out);
        }
        out.close();
    }
    static class AccurateMovement {
        void solve(int testNumber, Scanner in, PrintWriter out) {
            int n = in.nextInt();
            HashMap<Integer, Integer> mpRight = new HashMap<>();
            HashMap<Integer, Integer> mpLeft = new HashMap<>();

            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                mpRight.merge(a[i], 1, Integer::sum);
            }
            Integer ans = 0;
            for (int i = 0; i < n; i++) {
                mpRight.merge(a[i], - 1, Integer::sum);
                if (i > 0) {
                    mpLeft.merge(a[i - 1], 1, Integer::sum);
                }
                for (Map.Entry<Integer, Integer> w : mpLeft.entrySet()) {
                    ans += w.getValue() * mpRight.getOrDefault(2 * a[i] - w.getKey(), 0);
                }
            }
            out.println(ans);
        }
    }
}
