package nmk;

public interface Position {
    boolean isValid(Move move);
    Cell getCell(int r, int c);
    int rowСount();
    int columnCount();
}
