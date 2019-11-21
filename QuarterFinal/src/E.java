import java.io.*;
import java.util.*;
import java.math.*;

public class E {
    static class MyReader {
        BufferedInputStream in;

        final int bufSize = 1 << 16;
        final byte b[] = new byte[bufSize];

        MyReader(InputStream in) {
            this.in = new BufferedInputStream(in, bufSize);
        }

        int nextInt() throws IOException {
            int c;
            while ((c = nextChar()) <= 32)
                ;
            int x = 0, sign = 1;
            if (c == '-') {
                sign = -1;
                c = nextChar();
            }
            while (c >= '0') {
                x = x * 10 + (c - '0');
                c = nextChar();
            }
            return x * sign;
        }

        StringBuilder _buf = new StringBuilder();
        String nextWord() throws IOException {
            int c;
            _buf.setLength(0);
            while ((c = nextChar()) <= 32 && c != -1)
                ;
            if (c == -1)
                return null;
            while (c > 32) {
                _buf.append((char)c);
                c = nextChar();
            }
            return _buf.toString();
        }

        int bn = bufSize, k = bufSize;

        int nextChar() throws IOException {
            if (bn == k) {
                k = in.read(b, 0, bufSize);
                bn = 0;
            }
            return bn >= k ? -1 : b[bn++];
        }

        int nextNotSpace() throws IOException {
            int ch;
            while ((ch = nextChar()) <= 32 && ch != -1)
                ;
            return ch;
        }
    }
    static class IntList {

        private int size;
        private int[] a;

        public IntList() {
            size = 0;
            a = new int[1];
        }

        public void add(int x) {
            if (size == a.length)
                a = Arrays.copyOf(a, 2 * size);
            a[size++] = x;
        }

        public int get(int i) {
            return a[i];
        }

        public int length() {
            return size;
        }
    }
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        MyReader in = new MyReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Equidistant solver = new Equidistant();
        solver.solve(1, in, out);
        out.close();
    }
    static class Equidistant {
        final int N = 200000 + 13;
        final int INF = 1_000_000_000 + 7;
        IntList[] g = new IntList[N];
        int d[] = new int[N];
        int p[] = new int[N];

        void dfs(int v, int pr) {
            for (int j = 0; j < g[v].length(); j++) {
                int to = g[v].get(j);
                if (to == pr) continue;
                d[to] = d[v] + 1;
                p[to] = v;
                dfs(to, v);
            }
        }
        void solve(int testNumber, MyReader in, PrintWriter out) throws IOException {
            int n, m;
            n = in.nextInt();
            m = in.nextInt();
            for (int i = 0; i < n; i++)
                g[i] = new IntList();
            for (int i = 0; i < n - 1; i++) {
                int v, to;
                v = in.nextInt();
                to = in.nextInt();
                v--;
                to--;
                g[v].add(to);
                g[to].add(v);
            }
            for (int i = 0; i < n; i++) {
                d[i] = INF;
            }
            IntList q = new IntList();
            for (int i = 0; i < m; i++) {
                int v;
                v = in.nextInt();
                v--;
                q.add(v);
            }
            d[q.get(0)] = 0;
            dfs(q.get(0), -1);
            int maxim = -1;
            int v = -1;
            for (int i = 0; i < q.length(); i++) {
                int j = q.get(i);
                if (d[j] > maxim) {
                    maxim = d[j];
                    v = j;
                }
            }
            if (maxim % 2 != 0) {
                out.println("NO");
                return;
            }
            for (int i = 0; i < maxim / 2; i++) {
                v = p[v];
            }
            d[v] = 0;
            dfs(v, -1);
            int minim = -1;
            for (int i = 0; i < q.length(); i++) {
                int x = q.get(i);
                if (minim == -1) {
                    minim = d[x];
                }
                if (minim != d[x]) {
                    out.println("NO");
                    return;
                }
            }
            out.println("YES\n" + (v + 1));
        }
    }
}
