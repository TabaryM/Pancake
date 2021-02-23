package fr.ul.ia.modele;

import fr.ul.ia.engine.Game;
import fr.ul.ia.engine.Player;
import fr.ul.ia.engine.State;
import fr.ul.ia.exception.IllegalMoveException;
import fr.ul.ia.exception.UnknownPlayerException;

import java.util.ArrayList;
import java.util.List;

public class PancakeState implements State {

    public final static int PLAYER1 = 1;
    public final static int PLAYER2 = 2;

    private final Player[] players;
    private Player currentPlayer;

    private Board board;

    private PancakeState(Player[] players){
        this.players = players;
        board = new Board();
        currentPlayer = players[0];
    }

    private void nextPlayer(){
        currentPlayer = players[currentPlayer.getNum() %2];
    }

    public State getCopy(){
        PancakeState copy = new PancakeState(players);
        copy.setBoard(board);
        copy.setCurrentPlayer(currentPlayer);
        return copy;
    }

    @Override
    public void setBoard(Board board) {
        this.board = new Board(board);
    }

    @Override
    public void setCurrentPlayer(Player player){
        int i = 0;
        while (i < players.length - 1 && player.equals(players[i])) i++;
        if(i == players.length) throw new UnknownPlayerException("Unknown player "+player.toString());
        else currentPlayer = player;
    }

    public static PancakeState getInitialState(Game game){
        // TODO : essayer d'unifier avec getInitialState(Player[] players) qui est utilisé dans le PancakeGame
        Player[] players;
        players = new Player[2];

        players[0] = new AIPlayer(game, MCTS.getInstance(), "Arnold", 1);
//        players[0] = new HumanPlayer(game, "Alice", 1);
//        players[1] = new HumanPlayer(game, "Bob", 2);
        players[1] = new AIPlayer(game, MCTS.getInstance(), "BB-8", 2);
        return new PancakeState(players);
    }

    public static PancakeState getInitialState(Player[] players){
        // TODO : essayer d'unifier avec getInitialState(Game game) qui est utilisé dans les tests de PancakeState
        return new PancakeState(players);
    }

    @Override
    public void applyMove(Move move) throws IllegalMoveException {
        if(board.get(move.getColumn(),move.getRow()) == 0){
            board.set(move.getColumn(),move.getRow(), currentPlayer.getNum());
            nextPlayer();
        } else {
            throw new IllegalMoveException("Can't execute the move", move);
        }
    }

    @Override
    public EndState testEnd() {

        int nbMoves = 0;
        int counter;
        for (int i = 0; i < Board.BOARD_WIDTH; i++){
            for (int j = 0; j < Board.BOARD_HEIGHT; j++){
                if(board.get(i,j) != 0){
                    nbMoves++;

                    counter = 0;
                    while( counter < PancakeGame.nbDiscsToWin && i + counter < Board.BOARD_WIDTH && board.get(i+counter,j) == board.get(i,j))
                        counter++;
                    if( counter == PancakeGame.nbDiscsToWin)
                        return board.get(i,j) == PancakeState.PLAYER1 ? EndState.PLAYER1_WON : EndState.PLAYER2_WON;

                    counter = 0;
                    while( counter < PancakeGame.nbDiscsToWin && j + counter < Board.BOARD_HEIGHT && board.get(i,j+counter) == board.get(i,j))
                        counter++;
                    if( counter == PancakeGame.nbDiscsToWin)
                        return board.get(i,j) == PancakeState.PLAYER1 ? EndState.PLAYER1_WON : EndState.PLAYER2_WON;

                    counter = 0;
                    while( counter < PancakeGame.nbDiscsToWin && i + counter < Board.BOARD_WIDTH && j + counter < Board.BOARD_HEIGHT && board.get(i+ counter,j+counter) == board.get(i,j))
                        counter++;
                    if( counter == PancakeGame.nbDiscsToWin)
                        return board.get(i,j) == PancakeState.PLAYER1 ? EndState.PLAYER1_WON : EndState.PLAYER2_WON;

                    counter = 0;
                    while( counter < PancakeGame.nbDiscsToWin && i + counter < Board.BOARD_WIDTH && j - counter >= 0 && board.get(i+ counter,j-counter) == board.get(i,j))
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
    public String toString() {
        return "Board:\n" + board.toString();
    }

    @Override
    public List<Move> getAvailableMoves() {
        List<Move> avaibleMoves = new ArrayList<>();

        for(int i = 0; i < Board.BOARD_WIDTH; i++){
            int j = Board.BOARD_HEIGHT - 1;
            boolean found = false;
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
    public Board getCurrentBoard() {
        return board;
    }

    @Override
    public int compareTo(Object o) {
        return board.compareTo(((PancakeState)o).board);
    }


    public Player getCurrentPlayer(){
        return currentPlayer;
    }
}
