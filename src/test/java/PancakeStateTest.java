import fr.ul.ia.modele.Move;
import fr.ul.ia.modele.Board;
import fr.ul.ia.modele.PancakeState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;


public class PancakeStateTest {

    private PancakeState state;

    @BeforeEach
    void setUp() {
        state = PancakeState.getInitialState();
    }

    // compareTo : testé via compareTo de board

    @Test
    void availableMoves(){
        List<Move> moves = state.getAvailableMoves();

        assertEquals(Board.BOARD_WIDTH,moves.size());
    }

}
