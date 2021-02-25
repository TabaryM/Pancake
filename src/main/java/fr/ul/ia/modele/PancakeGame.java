package fr.ul.ia.modele;

import fr.ul.ia.engine.Game;
import fr.ul.ia.engine.Player;
import fr.ul.ia.exception.IllegalMoveException;

public class PancakeGame implements Game {

    private PancakeState currentState;

    private Player[] players;
    public static final int nbDiscsToWin = 4;

    public PancakeGame() {
        init();
    }

    @Override
    public void init() {

        players = new Player[2];

//        players[0] = new AIPlayer(this, MCTS.getInstance(), "Arnold", 1);
        players[0] = new HumanPlayer(this, "Alice", 1);
        //players[1] = new HumanPlayer(this, "Bob", 2);
        players[1] = new AIPlayer(this, MCTS.getInstance(), "BB-8", 2);
        currentState = PancakeState.getInitialState(players);
    }

    @Override
    public void start() {
        while(!isFinished()){
            System.out.println("Turn: player "+ getCurrentPlayer().toString());
            displayState();
            evolve();
        }
        displayState();
        displayEnd();
    }

    @Override
    public boolean isFinished() {
        return currentState.testEnd() != EndState.NOT_FINISHED;
    }

    @Override
    public void evolve() {
        boolean hasPlayed = false;
        while(!hasPlayed) {
            try{
                Move move = getCurrentPlayer().play();
                currentState.applyMove(move);
                hasPlayed = true;
            } catch (IllegalMoveException e) {
                System.out.println(e.getMessage());
                hasPlayed = false;
            }
        }
    }

    void displayState(){
        System.out.println(currentState.toString() + " 0| 1| 2| 3| 4| 5| 6|");
        System.out.println(currentState.testEnd());
    }

    void displayEnd(){
        String endState = "";
        switch (currentState.testEnd()){
            case DRAW:
                endState = "Draw";
                break;
            case PLAYER1_WON:
                endState = "Player 1 won";
                break;
            case PLAYER2_WON:
                endState = "Player 2 won";
                break;
            case NOT_FINISHED:
                endState = "The game is not finished";
                break;
        }
        System.out.println(endState);
    }

    @Override
    public Board getBoard() {
        return currentState.getCurrentBoard();
    }

    private Player getCurrentPlayer(){
        return currentState.getCurrentPlayer();
    }

    public PancakeState getCurrentState() {
        return currentState;
    }
}
