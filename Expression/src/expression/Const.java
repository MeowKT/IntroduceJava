package expression;

import java.util.Objects;

public class Const implements Expression {
    private int val;

    public Const(int val) {
        this.val = val;
    }

    @Override
    public int evaluate(int x) {
        return val;
    }

    @Override
    public String toString() {
        return Integer.toString(val);
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
        Const aConst = (Const) object;
        return val == aConst.val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val) + 'S' + 'A' + 'V' + 'V' + 'A';
    }
}
