package fr.ul.ia.modele;

import fr.ul.ia.engine.AIStrategy;
import fr.ul.ia.engine.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStrategy implements AIStrategy {

    private static RandomStrategy instance;

    private Random r = new Random();


    public static RandomStrategy getInstance(){
        if(instance == null)
            instance = new RandomStrategy();
        return instance;
    }

    private RandomStrategy(){
    }


    @Override
    public Move getNextMove(State state) {

        List<Move> listMov = state.getAvailableMoves();


        return listMov.get(r.nextInt(listMov.size()));
    }
}
