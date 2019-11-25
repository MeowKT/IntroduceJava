package md2html;

public class MarkdownHeaderParser {
    private String s;
    private int Number;

    public MarkdownHeaderParser(String s, int number) {
        this.s = s;
        Number = number;
    }

    public String toHTML() {
        return ("<h" + Number + ">" + new MarkdownTextParser(s.substring(Number + 1)).toHTML() + "</h" + Number + ">");
    }
}
