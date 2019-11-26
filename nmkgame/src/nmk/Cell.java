package nmk;

import java.util.Map;

public enum Cell {
    X, O, A, B, C, D, E;

    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.A, '-',
            Cell.B, '|',
            Cell.C, 'C',
            Cell.D, 'D',
            Cell.E, '.'
    );

    @Override
    public String toString() {
        return SYMBOLS.get(this).toString();
    }


}
