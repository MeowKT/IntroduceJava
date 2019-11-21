import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.math.*;

public class B {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        AccurateMovement solver = new AccurateMovement();
        solver.solve(1, in, out);
        out.close();
    }
    static class AccurateMovement {
        void solve(int testNumber, Scanner in, PrintWriter out) {
            int n = in.nextInt();
            final int a = -710 * 25000;
            for (int i = a; n > 0; i += 710, n--) {
                out.println(i);
            }
        }
    }
}
