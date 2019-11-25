package nmk;

public class ProxyBoard implements Position {
    private Position positionBoard;

    public ProxyBoard(Position positionBoard) {
        this.positionBoard = positionBoard;
    }

    @Override
    public boolean isValid(Move move) {
        return positionBoard.isValid(move);
    }

    @Override
    public Cell getCell(int r, int c) {
        return positionBoard.getCell(r, c);
    }

    @Override
    public int rowСount() {
        return positionBoard.rowСount();
    }

    @Override
    public int columnCount() {
        return positionBoard.columnCount();
    }

    public String toString() {
        return positionBoard.toString();
    }
}
