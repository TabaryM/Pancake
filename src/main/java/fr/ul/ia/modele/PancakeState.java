package fr.ul.ia.modele;

import fr.ul.ia.engine.State;
import fr.ul.ia.exception.IllegalMoveException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PancakeState implements State {


    private int player;

    private Board board;

    private PancakeState(){
        board = new Board();
        player = 1;
    }

    private void nextPlayer(){
        player = player%2 + 1;
    }

    public PancakeState(PancakeState pancakeState){
        board = new Board(pancakeState.board);
        player = pancakeState.player;
    }

    public static PancakeState getInitialState(){
        return new PancakeState();
    }

    @Override
    public void applyMove(Move move) throws IllegalMoveException {
        if(board.get(move.getColumn(),move.getRow()) == 0){
            board.set(move.getColumn(),move.getRow(),player);
            nextPlayer();
        } else {
            throw new IllegalMoveException("Can't execute the move");
        }
    }

    @Override
    public EndState testEnd() {
        return null;
    }

    @Override
    public List<Move> getAvailableMoves() {
        List<Move> avaibleMoves = new ArrayList<>();

        for(int i = 0; i < Board.BOARD_WIDTH; i++){
            int j = Board.BOARD_HEIGHT - 1;
            Boolean found = false;
            while( j > 0 && !found){
                if(board.get(i,j) == 0 && board.get(i,j-1) != 0){
                    found = true;
                } else {
                    j--;
                }
            }
            if(j == 1){
                if(board.get(i,0) == 0) {
                    found = true;
                    j = 0;
                }
            }
            if(found){
                avaibleMoves.add(new Move(i,j));
            }
        }
        return avaibleMoves;
    }

    /**
     * Must respect that : https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        return board.compareTo(((PancakeState)o).board);
    }

}
