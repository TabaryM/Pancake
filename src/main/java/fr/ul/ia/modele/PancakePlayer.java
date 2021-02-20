package fr.ul.ia.modele;

import fr.ul.ia.engine.Game;
import fr.ul.ia.engine.Player;

import java.util.Objects;

public abstract class PancakePlayer implements Player {
    private final Game game;
    private final String name;
    private final int num;

    public PancakePlayer(Game game, String name, int num) {
        this.game = game;
        this.name = name;
        this.num = num;
    }

    @Override
    public Board getBoard() {
        return game.getBoard();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNum() {
        return num;
    }

    protected Game getGame(){
        return game;
    }

    public static String getToken(int numPlayer){
        if(numPlayer == 1){
            return "\033[31mX\033[0m";
        } else if (numPlayer == 2){
            return "\033[33mO\033[0m";
        } else {
            return " ";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PancakePlayer that = (PancakePlayer) o;
        return num == that.num && Objects.equals(game, that.game) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, name, num);
    }
}
