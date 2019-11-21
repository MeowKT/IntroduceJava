import java.io.*;
import java.util.*;
import java.math.*;

public class C {
    static class Vertex {
        final int x, y;
        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(final Object o) {
            final Vertex x = (Vertex) o;
            return this.x == x.x && this.y == x.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }

        public String toString() {
            return y + " " + x;
        }
    }
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        CrossStitch solver = new CrossStitch();
        solver.solve(1, in, out);
        out.close();
    }
    static class CrossStitch {
        final int N = 200;
        int n, m;
        char c[][] = new char[N][N];
        Map <Vertex, List<Vertex>> good = new HashMap<>();
        Map <Vertex, List<Vertex>> bad = new HashMap<>();
        List <Vertex> ans = new ArrayList<>();

        void solve(int testNumber, Scanner in, PrintWriter out) throws IOException {
            m = in.nextInt();
            n = in.nextInt();
            in.nextLine();
            Vertex v = null;
            for (int i = 0; i < n; i++) {
                String s = in.nextLine();
                for (int j = 0; j < m; j++) {
                    c[i][j] = s.charAt(j);
                    if (c[i][j] == 'X') {
                        add(good, new Vertex(i, j), new Vertex(i + 1, j + 1));
                        add(good, new Vertex(i, j + 1), new Vertex(i + 1, j));
                        add(bad, new Vertex(i, j), new Vertex(i + 1, j));
                        add(bad, new Vertex(i, j + 1), new Vertex(i + 1, j + 1));
                        if (v == null) {
                            v = new Vertex(i, j);
                        }
                    }
                }
            }
            go(v, good,1);
            out.println(ans.size() - 1);
            for (Vertex x : ans) {
                out.println(x);
            }
        }
        void go(Vertex v, Map<Vertex, List<Vertex>> cur, int t) {
            List<Vertex> vertices = cur.get(v);
            while (!vertices.isEmpty()) {
                Vertex to = vertices.get(vertices.size() - 1);
                vertices.remove(vertices.size() - 1);
                cur.get(to).remove(v);
                go(to, (t == 1 ? bad : good), t ^ 1);
                ans.add(v);
            }
        }
        void add(Map<Vertex, List<Vertex>> mp, Vertex v, Vertex to) {
            mp.computeIfAbsent(v, k->new ArrayList<>()).add(to);
            mp.computeIfAbsent(to, k->new ArrayList<>()).add(v);
        }
    }
    static class MyReader {
        BufferedInputStream in;

        final int bufSize = 1 << 16;
        final byte b[] = new byte[bufSize];

        MyReader(InputStream in) {
            this.in = new BufferedInputStream(in, bufSize);
        }

        int nextInt() throws IOException {
            int c;
            while ((c = nextChar()) <= 32);
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
            while ((c = nextChar()) <= 32 && c != -1);
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
            while ((ch = nextChar()) <= 32 && ch != -1);
            return ch;
        }
    }
}
