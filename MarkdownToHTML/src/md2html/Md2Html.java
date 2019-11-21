package md2html;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Md2Html {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid input files");
            return;
        }

        List<String> paragraphs = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(args[0]), "UTF-8"));
            try {
                while (true) {
                    String s;
                    StringBuilder tmp = new StringBuilder();
                    while (true) {
                        s = reader.readLine();
                        if (s == null || s.isEmpty()) {
                            break;
                        }
                        tmp.append(s).append('\n');
                    }
                    if (tmp.length() > 0) {
                        paragraphs.add(tmp.deleteCharAt(tmp.length() - 1).toString());
                    }
                    if (s == null) {
                        break;
                    }
                }
            } finally {
                reader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("Input file error: " + e.getMessage());
            return;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(args[1]), "UTF-8"));
            try {
                writer.write(new MarkdownParser(paragraphs).toHTML());
            } finally {
                writer.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Not access to create file: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("Output file error: " + e.getMessage());
            return;
        }
    }
}
