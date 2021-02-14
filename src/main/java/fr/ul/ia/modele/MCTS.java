package fr.ul.ia.modele;

import fr.ul.ia.engine.AIStrategy;
import fr.ul.ia.engine.State;
import fr.ul.ia.engine.Tree;

import java.util.List;
import java.util.Random;

public class MCTS implements AIStrategy {

    private Tree root;
    private static MCTS instance;

    private final int TIME = 3000;

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

        double max = Integer.MIN_VALUE;
        int indexMax = -1;
        List<Tree> childrens = root.getChildren();
        for (int i = 0; i < childrens.size();i++){
            if((double)childrens.get(i).getSumSimulation()/childrens.get(i).getNumberSimulation() > max){
                max = (double)childrens.get(i).getSumSimulation()/childrens.get(i).getNumberSimulation();
                indexMax = i;
            }
            System.out.println(childrens.get(i).getMoveFromPreviousState() + " nb sim:"+childrens.get(i).getNumberSimulation()+": "+((float)childrens.get(i).getSumSimulation()/childrens.get(i).getNumberSimulation())*100 + "% de vic");
        }



        return childrens.get(indexMax).getMoveFromPreviousState();
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
            int indexBValueMax = -1;
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
            tree.setExpanded(true);
        }
    }

    private static int simulate(Tree tree){
        Random rand = new Random();
        int r = 0;
        Tree tempTree = tree.getCopy();
        while(tempTree.getCurrentState().testEnd() == EndState.NOT_FINISHED){
            expand(tempTree);
            tempTree = tempTree.getChildren().get(rand.nextInt(tempTree.getChildren().size()));
        }
        r = tempTree.isMax() ? 0 : 1;
        return r;
    }

    private static void backPropagation(Tree tree,int r){
        tree.addSimulation(1,r);
        if(tree.getFather() != null)
            backPropagation(tree.getFather(),r);
    }


}
