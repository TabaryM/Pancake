package fr.ul.ia.modele;

import fr.ul.ia.exception.IllegalBoardModificationException;

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

    /**
     * Set the value of the case (i,j) to the number of the player.
     * @param i line of the case
     * @param j column of the case
     * @param value number of the player
     * @throws IllegalBoardModificationException if there is token in the case (i,j) and the game try to change this token with another.
     */
    public void set(int i, int j, int value){
        if(board[i][j] != 0 && value != 0 && value != board[i][j]){
            throw new IllegalBoardModificationException("A player cannot replace an other's player token !");
        }
        if(value != 0){
            nbToken++;
        } else {
            if(board[i][j] != 0) {
                nbToken--;
            }
        }
        board[i][j] = value;
    }

    public int getNbToken(){
        return nbToken;
    }

    @Override
    public int compareTo(Object o) {
        if(this.equals(o)) return 0;
        return getNbToken() - ((Board) o).getNbToken();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = BOARD_HEIGHT -1  ; i >= 0 ; i--){
            for(int j = 0; j < BOARD_WIDTH; j++){
                sb.append(" ");
                switch (board[j][i]){
                    case 1:
                        sb.append('X');
                        break;
                    case 2:
                        sb.append('O');
                        break;
                    default:
                        sb.append('.');
                        break;
                }
                sb.append("|");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
