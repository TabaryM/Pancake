package fr.ul.ia.modele;

import fr.ul.ia.Main;
import fr.ul.ia.engine.AIStrategy;
import fr.ul.ia.engine.State;
import fr.ul.ia.engine.Tree;

import java.util.List;
import java.util.Random;

public class MCTS implements AIStrategy {

    private Tree root;
    private static MCTS instance;

    private final int TIME = 5000;
    private final int nbSimul = 10;

    public static MCTS getInstance() {
        if(instance == null)
            instance = new MCTS();
        return instance;
    }

    @Override
    public Move getNextMove(State state) {
        root = new MoveTree(null,state,true,null);

        long start = System.currentTimeMillis();
        expand(root);
        Tree winningMove = preCheckMove();

        if(winningMove != null)
            return winningMove.getMoveFromPreviousState();

        while(System.currentTimeMillis() - start < TIME){
            Tree selected = select(root);
            backPropagation(selected,simulate(selected));
        }

        /*
        int i = 0;
        while(i < nbSimul){
            Tree selected = select(root);
            backPropagation(selected,simulate(selected));
            i++;
        }
         */

        return chooseBestMove().getMoveFromPreviousState();

    }

    @Override
    public String getName() {
        return "MCTS";
    }

    private static Tree select(Tree tree){
        Tree tempTree = tree;
        Tree selected = null;

        if(tempTree.getCurrentState().testEnd() != EndState.NOT_FINISHED)
            selected = tempTree;

        while (selected == null){
            if(!tempTree.isExpanded()){
                expand(tempTree);
            }
            double bValueMax = Integer.MIN_VALUE;
            int indexBValueMax = 0;
            boolean childrensAllExpanded = true;
            Tree firstNotExpanded = null;

            List<Tree> childrens = tempTree.getChildren();
            Tree children;
            for (int i = 0;i < childrens.size(); i++){
                children = childrens.get(i);
                if(children.isExpanded()){
                    if(children.getBValue() > bValueMax){
                        bValueMax = children.getBValue();
                        indexBValueMax = i;
                    }
                } else {
                    childrensAllExpanded = false;
                    firstNotExpanded = children;
                    expand(children);
                    break;
                }
            }

            selected = childrensAllExpanded ? select(childrens.get(indexBValueMax)) : firstNotExpanded;
        }

        return selected;
    }

    private static void expand(Tree tree) {
        for (Move m :tree.getCurrentState().getAvailableMoves()) {
            State copy = tree.getCurrentState().getCopy();
            copy.applyMove(m);
            Tree children = new MoveTree(m,copy,!tree.isMax(),tree);
            tree.addChildren(children);
        }
        tree.setExpanded(true);
    }

    private static float simulate(Tree tree){
        Random rand = new Random();
        float r = 0;
        Tree tempTree = tree.getCopy();
        int a = 0;
        while(tempTree.getCurrentState().testEnd() == EndState.NOT_FINISHED){
            expand(tempTree);
            List<Tree> childrens = tempTree.getChildren();
            Tree children = null;
            boolean winingMove = false;


            ///QUESTION 3 ///
            int i = 0;
            while(!winingMove && i < childrens.size() && tempTree.isMax()){
                children = childrens.get(i);

                winingMove = children.getCurrentState().testEnd() != EndState.NOT_FINISHED;

                i++;
            }
            /// QUESTION 3 ///

            if(winingMove){
                tempTree = children;
            } else {
                tempTree = tempTree.getChildren().get(rand.nextInt(tempTree.getChildren().size()));
            }
            a++;
        }
        r = tempTree.isMax() ? 0 : 1;
        r = tempTree.getCurrentState().testEnd() == EndState.DRAW ? 0.5f : r;

        return r;
    }

    private static void backPropagation(Tree tree,float r){
        tree.addSimulation(1,r);
        if(tree.getFather() != null)
            backPropagation(tree.getFather(),r);
    }

    private Tree chooseBestMove(){
        double max = Integer.MIN_VALUE;
        int indexMax = -1;
        int sum = 0;
        List<Tree> childrens = root.getChildren();
        for (int i = 0; i < childrens.size();i++){
            if((double)childrens.get(i).getSumSimulation()/childrens.get(i).getNumberSimulation() > max){
                max = (double)childrens.get(i).getSumSimulation()/childrens.get(i).getNumberSimulation();
                indexMax = i;
            }
            sum += childrens.get(i).getNumberSimulation();

        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("total simulation : ").append(sum).append("\n");
        for(int i = 0; i < childrens.size();i++) {
            if(i == indexMax){
                if(Main.haveEC){
                    stringBuilder.append(Main.beginEC);
                    stringBuilder.append("32");
                    stringBuilder.append(Main.endEC);
                }
            }
            stringBuilder.append(childrens.get(i).getMoveFromPreviousState());
            stringBuilder.append(" nb sim:").append(childrens.get(i).getNumberSimulation());
            stringBuilder.append(": ").append(((float) childrens.get(i).getSumSimulation() / childrens.get(i).getNumberSimulation()) * 100);
            stringBuilder.append("% de vic");
            if(Main.haveEC){
                stringBuilder.append(Main.beginEC);
                stringBuilder.append("0");
                stringBuilder.append(Main.endEC);
            }
            if(i == indexMax){
                stringBuilder.append("\t<--");
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());



        return childrens.get(indexMax);
    }

    private Tree preCheckMove(){
        boolean winningMove = false;
        List<Tree> childrens = root.getChildren();
        int i = 0;
        while (i < childrens.size() && !winningMove){
            winningMove = childrens.get(i).getCurrentState().testEnd() != EndState.NOT_FINISHED;
            i++;
        }

        return winningMove ? childrens.get(i - 1):null;
    }

}
