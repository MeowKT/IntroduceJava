package expression;

public class Divide extends AbstractBinaryOperator {

    public Divide(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return " / ";
    }

    @Override
    protected int getPriority() {
        return 2;
    }

    @Override
    protected int calc(int x, int y) {
        return x / y;
    }

    @Override
    protected boolean checkSpecialOperator() {
        return true;
    }

}
