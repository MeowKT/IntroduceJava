import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.math.*;

public class I {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        IdealPyramid solver = new IdealPyramid();
        solver.solve(1, in, out);
        out.close();
    }
    static class IdealPyramid {
        void solve(int testNumber, Scanner in, PrintWriter out) {
            int n = in.nextInt();
            int xl, xr, yl, yr;
            int x, y, h;
            xl = Integer.MAX_VALUE;
            yl = Integer.MAX_VALUE;
            xr = Integer.MIN_VALUE;
            yr = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                x = in.nextInt();
                y = in.nextInt();
                h = in.nextInt();
                xl = Math.min(x - h, xl);
                xr = Math.max(x + h, xr);
                yl = Math.min(y - h, yl);
                yr = Math.max(y + h, yr);
            }
            int ansH = (Math.max(xr - xl, yr - yl) + 1) / 2;
            out.println((xl + xr) / 2 + " " + (yl + yr) / 2 + " " + ansH);
        }
    }
}