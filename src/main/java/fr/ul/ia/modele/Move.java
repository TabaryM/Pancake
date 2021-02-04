package fr.ul.ia.modele;

public class Move {
    private final int column;
    private final int row;

    public Move(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
