package fr.ul.ia.modele;

import fr.ul.ia.Main;
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
        StringBuilder stringBuilder = new StringBuilder();
        if(numPlayer == 1){
            if(Main.haveEC) {
                stringBuilder.append(Main.beginEC).append("31").append(Main.endEC);
            }
            stringBuilder.append('X');
            if(Main.haveEC) {
                stringBuilder.append(Main.beginEC).append("0").append(Main.endEC);
            }
        } else if (numPlayer == 2){
            if(Main.haveEC) {
                stringBuilder.append(Main.beginEC).append("33").append(Main.endEC);
            }
            stringBuilder.append('0');
            if(Main.haveEC) {
                stringBuilder.append(Main.beginEC).append("0").append(Main.endEC);
            }
        } else {
            stringBuilder.append(' ');
        }
        return stringBuilder.toString();
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
