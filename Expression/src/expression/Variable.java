package expression;

import java.util.Objects;

public class Variable implements Expression {
    private String x;

    public Variable(String x) {
        this.x = x;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toString() {
        return x;
    }

    @Override
    public String toMiniString() {
        return this.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Variable variable = (Variable) object;
        return Objects.equals(x, variable.x);
    }

    @Override
    public int hashCode() {
        return 313 * Objects.hash(x) + 'S' + 'A' + 'V' + 'V' + 'A';
    }
}
