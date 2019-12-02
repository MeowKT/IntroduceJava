package expression;

public class Add extends AbstractBinaryOperator {
    @Override
    protected String getOperator() {
        return " + ";
    }

    public Add(Expression a, Expression b) {
        super(a, b);
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) + right.evaluate(x);
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
