package expression;

import java.util.Objects;

abstract class AbstractBinaryOperator implements Expression {
    protected Expression left;
    protected Expression right;

    protected abstract String getOperator();

    protected abstract int getPriority();

    public abstract int evaluate(int x);

    public AbstractBinaryOperator(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    private String getExpression(Expression expression, boolean isBracket) {
        return (isBracket ? "(" : "") + expression.toMiniString() + (isBracket ? ")" : "");
    }

    private boolean checkSimpleBrackets(Expression expression) {
        return expression instanceof AbstractBinaryOperator
                && ((AbstractBinaryOperator) expression).getPriority() < this.getPriority();
    }

    private boolean checkSpecialOperators(Expression expression) {
        return expression instanceof Subtract || expression instanceof Divide;
    }
    
    private boolean checkHardBrackets(Expression expression) {
        if (!(expression instanceof AbstractBinaryOperator)) {
            return false;
        }
        AbstractBinaryOperator expr = (AbstractBinaryOperator) expression;
        if (checkSpecialOperators(this)) {
            return this.getPriority() == expr.getPriority();
        }
        if (checkSpecialOperators(expression)) {
            return expression instanceof Divide && expr.getPriority() <= this.getPriority();
        }

        return false;
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder(getExpression(left, checkSimpleBrackets(left)))
                .append(getOperator())
                .append(getExpression(right, checkSimpleBrackets(right) || checkHardBrackets(right)));
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(").append(left).append(getOperator()).append(right).append(")");
        return sb.toString();
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
        return 4241 * left.hashCode() + 43 * Objects.hashCode(getOperator()) + right.hashCode() + 1 + 'S' + 'A' + 'V' + 'V' + 'A';
    }
}
