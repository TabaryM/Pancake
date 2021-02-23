package fr.ul.ia.modele;

import fr.ul.ia.engine.AIStrategy;
import fr.ul.ia.engine.State;
import fr.ul.ia.engine.Tree;

import java.util.List;
import java.util.Random;

public class MCTS implements AIStrategy {

    private Tree root;
    private static MCTS instance;

    private final int TIME = 5000;

    public static MCTS getInstance() {
        if(instance == null)
            instance = new MCTS();
        return instance;
    }

    @Override
    public Move getNextMove(State state) {
        root = new MoveTree(null,state,true,null);

        long start = System.currentTimeMillis();

        while(System.currentTimeMillis() - start < TIME){
            Tree selected = select(root);
            backPropagation(selected,simulate(selected));
        }



        return chooseBestMove().getMoveFromPreviousState();

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

    private static int simulate(Tree tree){
        Random rand = new Random();
        int r = 0;
        Tree tempTree = tree.getCopy();
        while(tempTree.getCurrentState().testEnd() == EndState.NOT_FINISHED){
            expand(tempTree);
            List<Tree> childrens = tempTree.getChildren();
            Tree children = null;
            boolean winingMove = false;


            ///QUESTION 3 ///
            int i = 0;
            while(!winingMove && i < childrens.size()){
                children = childrens.get(i);

                winingMove = children.getCurrentState().testEnd() != EndState.NOT_FINISHED && !tempTree.isMax();

                i++;
            }
            /// QUESTION 3 ///

            if(winingMove){
                tempTree = children;
            } else {
                tempTree = tempTree.getChildren().get(rand.nextInt(tempTree.getChildren().size()));
            }



        }
        r = tempTree.isMax() ? 0 : 1;
        return r;
    }

    private static void backPropagation(Tree tree,int r){
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
            if((double)childrens.get(i).getSumSimulation()/childrens.get(i).getNumberSimulation() == 100.0){
                indexMax = i;
                max = Integer.MAX_VALUE;
            }
            if((double)childrens.get(i).getSumSimulation()/childrens.get(i).getNumberSimulation() > max){
                max = (double)childrens.get(i).getSumSimulation()/childrens.get(i).getNumberSimulation();
                indexMax = i;
            }
            sum += childrens.get(i).getNumberSimulation();

            System.out.println(childrens.get(i).getMoveFromPreviousState() + " nb sim:"+childrens.get(i).getNumberSimulation()+": "+((float)childrens.get(i).getSumSimulation()/childrens.get(i).getNumberSimulation())*100 + "% de vic");
        }

        System.out.println("total simulation:"+sum);



        return childrens.get(indexMax);

    }

}
