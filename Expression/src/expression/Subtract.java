package expression;

public class Subtract extends AbstractBinaryOperator {

    public Subtract(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return " - ";
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    protected int calc(int x, int y) {
        return x - y;
    }

    @Override
    protected boolean checkSpecialOperator() {
        return true;
    }

}
