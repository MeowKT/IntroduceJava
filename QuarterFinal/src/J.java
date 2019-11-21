import java.io.*;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.math.*;

public class J {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        JustTheLastDigit solver = new JustTheLastDigit();
        solver.solve(1, in, out);
        out.close();
    }
    static class JustTheLastDigit {
        void solve(int testNumber, Scanner in, PrintWriter out) {
            int n = in.nextInt();
            int[][] c = new int[n][n];
            for (int i = 0; i < n; i++) {
                String s = in.next();
                for (int j = 0; j < n; j++) {
                    c[i][j] = Integer.parseInt(s.charAt(j) + "");
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (c[i][j] > 0) {
                        for (int k = j + 1; k < n; k++) {
                            c[i][k] -= c[j][k];
                            if (c[i][k] < 0)
                                c[i][k] += 10;
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(c[i][j]);
                }
                System.out.println();
            }
        }
    }
}
