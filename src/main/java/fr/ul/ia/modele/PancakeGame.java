package fr.ul.ia.modele;

import fr.ul.ia.engine.Game;
import fr.ul.ia.engine.Player;
import fr.ul.ia.engine.State;
import fr.ul.ia.exception.PancakeException;

public class PancakeGame implements Game {

    private PancakeState currentState;

    private Player[] players;
    public static final int nbDiscsToWin = 4;

    public PancakeGame() {
        currentState = PancakeState.getInitialState();
        init();
    }

    @Override
    public void init() {

        players = new Player[2];

        players[0] = new AIPlayer(this, MCTS.getInstance());
        //players[0] = new HumanPlayer(this, "Alice");
        //players[1] = new HumanPlayer(this, "Bob");
        players[1] = new AIPlayer(this, MCTS.getInstance());
    }

    @Override
    public void start() {
        while(!isFinished()){
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
                hasPlayed = false;
            }
        }
    }


    void displayState(){
        System.out.println("Turn: player "+ getCurrentPlayer());
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
        return players[currentState.getCurrentPlayer() -1];
    }

    public PancakeState getCurrentState() {
        return currentState;
    }
}
