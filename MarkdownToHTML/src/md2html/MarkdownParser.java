package md2html;

import java.util.List;

public class MarkdownParser {
    private List<String> list;

    public MarkdownParser(List<String> list) {
        this.list = list;
    }

    public String toHTML() {
        StringBuilder ans = new StringBuilder();
        for (String cur : list) {
            int cnt = headerNumber(cur);
            String tag = cnt > 0 ? "h" + cnt : "p";
            if (cnt > 0) {
                ans.append("<h").append(cnt).append(">").append(new MarkdownTextParser(cur.substring(cnt + 1)).toHTML()).append("</h").append(cnt).append(">");
            } else {
                ans.append("<p>").append(new MarkdownTextParser(cur).toHTML()).append("</p>");
            }
            ans.append(System.lineSeparator());
        }
        return ans.toString();
    }

    private static int headerNumber(String s) {
        int ind = 0;
        while (ind < s.length() && s.charAt(ind) == '#') {
            ind++;
        }
        if (ind < s.length() && Character.isWhitespace(s.charAt(ind))) {
            return ind;
        }
        return 0;
    }
}
