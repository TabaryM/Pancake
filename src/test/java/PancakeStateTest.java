import fr.ul.ia.engine.Player;
import fr.ul.ia.modele.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;


public class PancakeStateTest {

    private PancakeState state;

    @BeforeEach
    void setUp() {

        PancakeGame game = new PancakeGame();
        state = PancakeState.getInitialState(game);
    }

    // compareTo : testé via compareTo de board

    @Test
    void availableMoves(){
        List<Move> moves = state.getAvailableMoves();

        assertEquals(Board.BOARD_WIDTH,moves.size());
    }

    @Test
    void testEndRight1() {

        state.applyMove(new Move(0,0));
        state.applyMove(new Move(1,0));

        state.applyMove(new Move(1,1));
        state.applyMove(new Move(2,0));

        state.applyMove(new Move(2,2));
        state.applyMove(new Move(3,0));

        state.applyMove(new Move(3,3));
        state.applyMove(new Move(4,0));

        assertEquals(EndState.PLAYER1_WON,state.testEnd());
    }

    @Test
    void testEndRight2() {

        state.applyMove(new Move(0,0));
        state.applyMove(new Move(1,0));

        state.applyMove(new Move(0,1));
        state.applyMove(new Move(1,1));

        state.applyMove(new Move(0,2));
        state.applyMove(new Move(1,2));

        state.applyMove(new Move(2,0));
        state.applyMove(new Move(1,3));

        assertEquals(EndState.PLAYER2_WON,state.testEnd());
    }

    @Test
    void testEndRight3() {

        state.applyMove(new Move(0,0));
        state.applyMove(new Move(0,1));

        state.applyMove(new Move(1,0));
        state.applyMove(new Move(1,1));

        state.applyMove(new Move(2,0));
        state.applyMove(new Move(2,1));

        state.applyMove(new Move(3,0));
        state.applyMove(new Move(3,1));

        assertEquals(EndState.PLAYER1_WON,state.testEnd());
    }

    @Test
    void testEndRight4() {

        state.applyMove(new Move(6,0));
        state.applyMove(new Move(6,1));

        state.applyMove(new Move(5,1));
        state.applyMove(new Move(5,2));

        state.applyMove(new Move(4,2));
        state.applyMove(new Move(4,3));

        state.applyMove(new Move(3,2));
        state.applyMove(new Move(3,4));

        assertEquals(EndState.PLAYER2_WON,state.testEnd());
    }
}
