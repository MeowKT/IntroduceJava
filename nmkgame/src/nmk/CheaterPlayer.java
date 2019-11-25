package nmk;

public class CheaterPlayer implements Player {
    @Override
    public Move move(final Position position, final Cell cell) {
        Board board = (Board) position; // you're dead
        board.makeMove(new Move(1, 1, cell));
        for (int r = 0; r < position.rowСount(); r++) {
            for (int c = 0; c < position.columnCount(); c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
