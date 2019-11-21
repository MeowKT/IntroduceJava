import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.math.*;

public class H {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        HighLoadDatabase solver = new HighLoadDatabase();
        solver.solve(1, in, out);
        out.close();
    }
    static class HighLoadDatabase {
        final int N = 1000_000 + 100;
        int[] pr = new int[N];
        public int sum(int l, int r) {
            return pr[r] - (l - 1 >= 0 ? pr[l - 1] : 0);
        }
        void solve(int testNumber, Scanner in, PrintWriter out) {
            int n = in.nextInt();
            int maxim = -1;
            for (int i = 0; i < n; i++) {
                pr[i] = in.nextInt();
                maxim = Math.max(maxim, pr[i]);
                if (i > 0) {
                    pr[i] += pr[i - 1];
                }
            }
            int q = in.nextInt();
            HashMap<Integer, Integer> mp = new HashMap<>();
            for (int it= 0; it < q; it++) {
                int t = in.nextInt();
                if (t < maxim) {
                    out.println("Impossible");
                    continue;
                }
                if (mp.containsKey(t)) {
                    out.println(mp.get(t));
                    continue;
                }
                int i = 0;
                int cnt = 0;
                while (i < n) {
                    int l = i;
                    int r = n;
                    while (r - l > 1) {
                        int m = (r + l) / 2;
                        if (sum(i, m) <= t) {
                            l = m;
                        } else {
                            r = m;
                        }
                    }
                    i = r;
                    cnt++;
                }
                out.println(cnt);
                mp.put(t, cnt);
            }
        }
    }
}
