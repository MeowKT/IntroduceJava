package md2html;

public class MarkdownParagraphParser {
    private String s;

    public MarkdownParagraphParser(String s) {
        this.s = s;
    }

    public String toHTML() {
        return ("<p>" + new MarkdownTextParser(s).toHTML() + "</p>");
    }
}
