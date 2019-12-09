package expression;

public class Multiply extends AbstractBinaryOperator {

    public Multiply(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return " * ";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    protected int calc(int x, int y) {
        return x * y;
    }

    @Override
    protected boolean checkSpecialOperator() {
        return false;
    }


}
