package nmk;

import java.util.Arrays;
import java.util.Map;

public class NMKBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;
    private final int k;
    private int used;

    public NMKBoard(final int n, final int m, final int k) {
        if (n < 0 || m < 0 || k <= 0) {
            throw new IllegalArgumentException("Not a positive size of board or K");
        }
        this.cells = new Cell[n][m];
        this.k = k;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        used = 0;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        used++;

        int[] dx = {0, 1, 1, -1};
        int[] dy = {1, 0, 1, 1};
        for (int it = 0; it < 4; it++) {
            if (checkWin(move.getRow(), move.getColumn(), dx[it], dy[it])) {
                return Result.WIN;
            }
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return used == cells.length * cells[0].length ? Result.DRAW :  Result.UNKNOWN;
    }

    private boolean checkWin(int row, int column, int dx, int dy) {
        return getCount(row, column, dx, dy) + getCount(row, column, -dx, -dy) - 1 >= k;
    }

    private int getCount(int row, int column, int dx, int dy) {
        int count = 0;
        for (int x = row, y = column; 0 <= x && x < cells.length && 0 <= y && y < cells[0].length && getCell(x, y) == turn; x += dx, y += dy) {
            count++;
        }
        return count;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < cells.length
                && 0 <= move.getColumn() && move.getColumn() < cells[0].length
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public int rowСount() {
        return cells.length;
    }

    @Override
    public int columnCount() {
        return cells[0].length;
    }

    @Override
    public String toString() {
        final int len = Math.max(String.valueOf(cells[0].length).length(), String.valueOf(cells.length).length());
        final StringBuilder sb = new StringBuilder(" ".repeat(len + 1));
        final String Digit = "%" + len + "d";
        final String Char = "%" + len + "c";

        for (int c = 0; c < cells[0].length; c++) {
            sb.append(String.format(Digit, c + 1)).append(" ");
        }
        for (int r = 0; r < cells.length; r++) {
            sb.append("\n");
            sb.append(String.format(Digit, r + 1)).append(" ");
            for (int c = 0; c < cells[0].length; c++) {
                sb.append(String.format(Char, SYMBOLS.get(cells[r][c]))).append(" ");
            }
        }

        return sb.toString();
    }
}
