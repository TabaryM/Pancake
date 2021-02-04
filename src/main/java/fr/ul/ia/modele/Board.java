package fr.ul.ia.modele;

import java.util.Arrays;

public class Board implements Comparable{

    public static final int BOARD_WIDTH  = 7;
    public static final int BOARD_HEIGHT = 6;
    private final int[][] board = new int[BOARD_WIDTH][BOARD_HEIGHT];
    private int nbToken;

    public Board(){
        nbToken = 0;
    }

    public Board(Board board){
        for(int i = 0; i < BOARD_WIDTH; i++){
            System.arraycopy(board.board[i], 0, this.board[i], 0, BOARD_HEIGHT);
        }
        this.nbToken = board.nbToken;
    }

    public int get(int i, int j) {
        return board[i][j];
    }

    public void set(int i, int j, int value){
        board[i][j] = value;
        // TODO : vérifier par rapport a l'ancienne valeur
        if(value != 0){
            nbToken++;
        } else {
            nbToken--;
        }
    }

    public int getNbToken(){
        return nbToken;
    }

    @Override
    public int compareTo(Object o) {
        if(this.equals(o)) return 0;
        Board compare = (Board) o;
        return getNbToken() - compare.getNbToken();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return Arrays.equals(board, board1.board);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }
}
