package fr.ul.ia.modele;

import fr.ul.ia.engine.State;
import fr.ul.ia.exception.IllegalMoveException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PancakeState implements State {

    public final static int PLAYER1 = 1;
    public final static int PLAYER2 = 2;

    private int player;
    private int currentPlayer;
    private final Board board;

    private PancakeState(){
        board = new Board();
        currentPlayer = 1;
    }

    private void nextPlayer(){
        currentPlayer = currentPlayer %2 + 1;
    }

    public PancakeState(PancakeState pancakeState){
        board = new Board(pancakeState.board);
        currentPlayer = pancakeState.currentPlayer;
    }

    public static PancakeState getInitialState(){
        return new PancakeState();
    }

    @Override
    public void applyMove(Move move) throws IllegalMoveException {
        if(board.get(move.getColumn(),move.getRow()) == 0){
            board.set(move.getColumn(),move.getRow(), currentPlayer);
            nextPlayer();
        } else {
            throw new IllegalMoveException("Can't execute the move");
        }
    }

    @Override
    public EndState testEnd() {

        int nbMoves = 0;
        int counter;
        for (int i = 0; i < Board.BOARD_WIDTH; i ++){
            for (int j = 0; j < Board.BOARD_HEIGHT; j++){
                if(board.get(i,j) != 0){
                    nbMoves++;

                    counter = 0;
                    while( counter < PancakeGame.nbDiscsToWin && i + counter < Board.BOARD_WIDTH && board.get(i+counter,j) == board.get(i,j))
                        counter++;
                    if( counter == PancakeGame.nbDiscsToWin)
                        return board.get(i,j) == PancakeState.PLAYER1 ? EndState.PLAYER1_WON : EndState.PLAYER2_WON;

                    counter = 0;
                    while( counter < PancakeGame.nbDiscsToWin && j + counter < Board.BOARD_HEIGHT && board.get(i,+counter) == board.get(i,j))
                        counter++;
                    if( counter == PancakeGame.nbDiscsToWin)
                        return board.get(i,j) == PancakeState.PLAYER1 ? EndState.PLAYER1_WON : EndState.PLAYER2_WON;

                    counter = 0;
                    while( counter < PancakeGame.nbDiscsToWin && i + counter < Board.BOARD_WIDTH && j + counter < Board.BOARD_HEIGHT && board.get(i+ counter,j+counter) == board.get(i,j))
                        counter++;
                    if( counter == PancakeGame.nbDiscsToWin)
                        return board.get(i,j) == PancakeState.PLAYER1 ? EndState.PLAYER1_WON : EndState.PLAYER2_WON;

                    counter = 0;
                    while( counter < PancakeGame.nbDiscsToWin && i + counter < Board.BOARD_WIDTH && j - counter < Board.BOARD_HEIGHT && board.get(i+ counter,j-counter) == board.get(i,j))
                        counter++;
                    if( counter == PancakeGame.nbDiscsToWin)
                        return board.get(i,j) == PancakeState.PLAYER1 ? EndState.PLAYER1_WON : EndState.PLAYER2_WON;
                }
            }
        }

        if(Board.BOARD_HEIGHT * Board.BOARD_WIDTH == nbMoves)
            return EndState.DRAW;

        return EndState.NOT_FINISHED;
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
            if(j == 0){
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

    @Override
    public int compareTo(Object o) {
        return board.compareTo(((PancakeState)o).board);
    }

}
