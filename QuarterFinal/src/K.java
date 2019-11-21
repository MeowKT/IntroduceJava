import java.io.*;
import java.util.*;

public class K {
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
    static class pair {
        int x, y;
        public pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        MyReader in = new MyReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        KingsChildren solver = new KingsChildren();
        solver.solve(1, in, out);
        out.close();
    }
    static class KingsChildren {
        final int N = 1000 + 13;
        char c[][] = new char[N][N];
        ArrayList<pair> pt = new ArrayList<>();
        int n, m;
        int x, y;
        int xx, xy, yx, yy;
        pair get(int l, int r) {
            int _l, _r;
            _l = 0;
            _r = m - 1;
            for (pair v : pt) {
                if (l <= v.x && v.x <= r) {
                    if (v.y >= y) {
                        _r = Math.min(_r, v.y - 1);
                    } else {
                        _l = Math.max(_l, v.y + 1);
                    }
                }
            }
            return new pair(_l, _r);
        }

        void fill (int ul, int ur, int hl, int hr, char w) {
            for (int i = ul; i <= ur; i++) {
                for (int j = hl; j <= hr; j++) {
                    if (c[i][j] == '.') {
                        c[i][j] = w;
                    }
                }
            }
        }

        void split(int ul, int ur, int hl, int hr) {
            for (int i = ul + 1; i < ur; i++) {
                for (int j = hl; j < hr; j++) {
                    if (c[i][j] == '.') {
                        c[i][j] = Character.toLowerCase(c[i - 1][j]);
                    }
                }
            }
            for (int i = ur - 2; i >= ul; i--) {
                for (int j = hl; j < hr; j++) {
                    if (c[i][j] == '.') {
                        c[i][j] = Character.toLowerCase(c[i + 1][j]);
                    }
                }
            }
            for (int i = ul; i < ur; i++) {
                for (int j = hl + 1; j < hr; j++) {
                    if (c[i][j] == '.') {
                        c[i][j] = Character.toLowerCase(c[i][j - 1]);
                    }
                }
            }
            for (int i = ul; i < ur; i++) {
                for (int j = hr - 2; j >= hl; j--) {
                    if (c[i][j] == '.') {
                        c[i][j] = Character.toLowerCase(c[i][j + 1]);
                    }
                }
            }
        }

        void relax(int l, int r) {
            pair pt = get(l, r);
            xx = l;
            xy = r + 1;
            yx = pt.x;
            yy = pt.y + 1;
            fill(l, r, pt.x, pt.y, 'a');
        }
        void solve(int testNumber, MyReader in, PrintWriter out) throws IOException {
            n = in.nextInt();
            m = in.nextInt();
            for (int i = 0; i < n; i++) {
                String s = in.nextWord();
                for (int j = 0; j < m; j++) {
                    c[i][j] = s.charAt(j);
                    if (Character.isLetter(c[i][j])) {
                        if (c[i][j] == 'A') {
                            x = i;
                            y = j;
                        } else {
                            pt.add(new pair(i, j));
                        }
                    }
                }
            }
            int ans = 0;
            int _l = x, _r = x;
            for (int l = 0; l <= x; l++) {
                for (int r = x; r < n; r++) {
                    pair tmp = get(l, r);
                    if (tmp.x > y || tmp.y < y) continue;
                    int cur = tmp.y - tmp.x + 1;
                    if (cur < 0) continue;
                    if (cur * (r - l + 1) > ans) {
                        ans = cur * (r - l + 1);
                        _l = l;
                        _r = r;
                    }
                }
            }
            relax(_l, _r);
            split(0, xx, 0, m);
            split(xy, n, 0, m);
            split(xx, xy, 0, yx);
            split(xx, xy, yy, m);
            for (int x = 0; x < n; x++, out.println())
                for (int y = 0; y < m; y++)
                    out.print(c[x][y]);
        }
    }
}