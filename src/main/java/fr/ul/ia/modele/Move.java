package fr.ul.ia.modele;

public class Move {
    private final int line;
    private final int row;

    public Move(int line, int row) {
        this.line = line;
        this.row = row;
    }

    public int getLine() {
        return line;
    }

    public int getRow() {
        return row;
    }
}
