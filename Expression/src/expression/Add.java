package expression;

public class Add extends AbstractBinaryOperator {
    @Override
    protected String getOperator() {
        return " + ";
    }

    public Add(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }


    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    protected int calc(int x, int y) {
        return x + y;
    }

    @Override
    protected boolean checkSpecialOperator() {
        return false;
    }
}
