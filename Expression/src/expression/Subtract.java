package expression;

public class Subtract extends AbstractBinaryOperator {

    public Subtract(Expression a, Expression b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " - ";
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) - right.evaluate(x);
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
