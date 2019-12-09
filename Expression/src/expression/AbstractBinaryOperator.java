package expression;

import java.util.Objects;

abstract class AbstractBinaryOperator extends AbstractExpression {
    private AbstractExpression left;
    private AbstractExpression right;

    protected abstract String getOperator();

    protected abstract int getPriority();

    protected abstract int calc(int x, int y);

    protected abstract boolean checkSpecialOperator();

    public AbstractBinaryOperator(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    public int evaluate(int x) {
        return calc(left.evaluate(x), right.evaluate(x));
    }
    public int evaluate(int x, int y, int z) {
        return calc(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    private String getExpression(Expression expression, boolean isBracket) {
        return (isBracket ? "(" : "") + expression.toMiniString() + (isBracket ? ")" : "");
    }

    private boolean checkSimpleBrackets(Expression expression) {
        return expression instanceof AbstractBinaryOperator
                && ((AbstractBinaryOperator) expression).getPriority() < this.getPriority();
    }

    private boolean checkHardBrackets(Expression expression) {
        if (expression instanceof AbstractBinaryOperator
                && ((AbstractBinaryOperator) expression).checkSpecialOperator()
                    && this.getPriority() == ((AbstractBinaryOperator) expression).getPriority()) {
            return true;
        }
        return this.checkSpecialOperator()
                && expression instanceof AbstractBinaryOperator
                && ((AbstractBinaryOperator) expression).getPriority() <= this.getPriority();
    }


    @Override
    public String toMiniString() {
        return getExpression(left, checkSimpleBrackets(left))
                + getOperator()
                + getExpression(right, checkSimpleBrackets(right) || checkHardBrackets(right));
    }

    @Override
    public String toString() {
        return "(" + left + getOperator() + right + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        AbstractBinaryOperator binaryOperator = (AbstractBinaryOperator) object;
        return this.left.equals(binaryOperator.left) && right.equals(binaryOperator.right);
    }

    @Override
    public int hashCode() {
        return 13 * left.hashCode() + 43 * Objects.hashCode(getOperator()) + 41 * right.hashCode();
    }
}
