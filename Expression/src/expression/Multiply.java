package expression;

public class Multiply extends AbstractBinaryOperator {

    public Multiply(Expression a, Expression b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " * ";
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) * right.evaluate(x);
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
