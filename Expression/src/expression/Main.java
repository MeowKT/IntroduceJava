package expression;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        Expression expression = new Add(new Const(1), new Subtract(new Variable("x") ,new Variable("x")));
        //System.out.println(expression.evaluate(scanner.nextInt()));
        System.out.println(expression.toMiniString());
    }
}
