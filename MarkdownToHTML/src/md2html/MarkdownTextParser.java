package md2html;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MarkdownTextParser {
    private String s;
    private int openMark;
    private boolean isLink;
    private StringBuilder ans;
    private StringBuilder currentMark;
    private final static Map<String, String> elements = Map.of(
            "*", "em",
            "**", "strong",
            "_", "em",
            "__", "strong",
            "--", "s",
            "++", "u",
            "`", "code"
    );

    private final static Set<Character> item = Set.of(
            '*', '_', '-', '+', '`', '\\'
    );

    private final static Map<Character, String> code = Map.of(
            '<', "&lt;",
            '>', "&gt;",
            '&', "&amp;"
    );

    private HashMap <String, Integer> count = new HashMap<>();

    public MarkdownTextParser(String s) {
        this.s = s;
        openMark = 0;
        ans = new StringBuilder();
        currentMark = new StringBuilder();
    }

    private String nextItem(int ind) {
        StringBuilder tmp = new StringBuilder();
        char cur = s.charAt(ind);
        while (ind < s.length() && item.contains(s.charAt(ind)) && cur == s.charAt(ind)) {
            tmp.append(s.charAt(ind++));
        }
        return tmp.toString();
    }

    private boolean soloItem(int ind, String item) {
        return (ind + item.length() >= s.length() || Character.isWhitespace(s.charAt(ind + item.length())))
                && (ind == 0 || Character.isWhitespace(s.charAt(ind - 1)));
    }

    private String parseItem(int ind, String item) {
        if (!elements.containsKey(item) || soloItem(ind, item)) {
            return item;
        }
        int delta = count.computeIfAbsent(item, k -> 0) == 0 ? 1 : -1;
        count.merge(item, delta, Integer::sum);
        openMark += delta;
        return (delta > 0 ? "<" : "</") + elements.get(item) + ">";
    }

    private String getLink(int ind) {
        StringBuilder st = new StringBuilder();
        while (ind < s.length() && s.charAt(ind) != ')') {
            st.append(s.charAt(ind));
            ind++;
        }
        return st.toString();
    }

    public String toHTML() {
        for (int ind = 0; ind < s.length(); ind++) {
            if (openMark == 0) {
                ans.append(currentMark);
                currentMark.setLength(0);
            }
            if (s.charAt(ind) == '\\') {
                String next = nextItem(ind + 1);
                if (elements.containsKey(next)) {
                    currentMark.append(next);
                    ind += next.length();
                } else {
                    currentMark.append("\\");
                }
                continue;
            }
            if (s.charAt(ind) == '[') {
                ans.append(currentMark);
                currentMark.setLength(0);
                isLink = true;
                openMark++;
            } else if (s.charAt(ind) == ']' && isLink) {
                String link = getLink(ind + 2);
                ans.append("<a href='" + link + "'>" + currentMark + "</a>");
                ind = ind + 2 + link.length();
                openMark--;
                currentMark.setLength(0);
                isLink = false;
            } else {
                String item = nextItem(ind);
                if (item.length() > 0) {
                    currentMark.append(parseItem(ind, item));
                    ind += item.length() - 1;
                } else {
                    currentMark.append(code.getOrDefault(s.charAt(ind), s.charAt(ind) + ""));
                }
            }
        }
        if (currentMark.length() > 0) {
            ans.append(currentMark);
        }
        return ans.toString();
    }
}
