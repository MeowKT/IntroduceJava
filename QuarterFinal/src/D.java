import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        DoublePalindrome solver = new DoublePalindrome();
        solver.solve(1, in, out);
        out.close();
    }
    static class DoublePalindrome {
        final long MOD = 998_244_353;
        final int N = 1000_000;
        long d[] = new long[N];
        long r[] = new long[N];
        long pow[] = new long[N];
        int[] ans = new int[N];

        List <Integer> divisors(int x) {
            List<Integer> div = new ArrayList<>();
            div.add(1);
            for (Integer i = 2; i * i <= x; i++) {
                if (x % i == 0) {
                    div.add(i);
                    if (i * i != x) {
                        div.add(x / i);
                    }
                }
            }
            return div;
        }
        void solve(int testNumber, Scanner in, PrintWriter out) {
            int n, k;
            n = in.nextInt();
            k = in.nextInt();
            pow[0] = 1;
            for (int i = 1; i < N; i++) {
                pow[i] = pow[i - 1] * k;
                pow[i] %= MOD;
            }
            for (int i = 1; i <= n; i++) {
                if (i % 2 == 0) {
                    r[i] = (long) (i / 2) * pow[i / 2] % MOD + (long) (i / 2) * pow[i / 2 + 1] % MOD;;
                    r[i] %= MOD;
                } else {
                    r[i] = (long) i * pow[(i + 1) / 2] % MOD;
                }
            }
            for (int i = 1; i <= n; i++) {
                d[i] = r[i];
                for (Integer l : divisors(i)) {
                    if (l < i) {
                        d[i] -= i / l * d[l] % MOD;
                        if (d[i] < 0) {
                            d[i] += MOD;
                        }
                        d[i] %= MOD;
                    }
                }
            }
            long sum = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = i; j <= n; j += i) {
                    ans[j] += d[i];
                    ans[j] %= MOD;
                }
                sum += ans[i];
                sum %= MOD;
            }
            out.println(sum);
        }
    }
}