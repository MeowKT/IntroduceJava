import java.io.*;
import java.util.Scanner;

public class A {
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
            int n, a, b;
            a = in.nextInt();
            b = in.nextInt();
            n = in.nextInt();
            out.println(2 * ((n - a - 1) / (b - a)) + 1);
        }
    }
}