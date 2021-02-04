import fr.ul.ia.exception.IllegalBoardModificationException;
import fr.ul.ia.modele.Board;
import fr.ul.ia.modele.PancakeState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    Board board1, board2, board3;

    @BeforeEach
    void setUp() {
        board1 = new Board();
        board2 = new Board();
        board3 = new Board();
    }

    // Les trois tests ci-dessous ne sont absolument pas exhaustifs, mais je ne pense même pas qu'ils soient nécessaires

    @Test
    void compareToSgn(){
        board1.set(0, 1, 1);
        assertSame(sgn(board1.compareTo(board2)), - sgn(board2.compareTo(board1))); // Dans un sens
        assertSame(sgn(board2.compareTo(board1)), - sgn(board1.compareTo(board2))); // Puis dans l'autre
    }

    @Test
    void compareToTransitif(){
        int comp1_2, comp2_3, comp1_3;
        board2.set(0, 1, 1);
        board3.set(0, 1, 1);
        board3.set(0, 2, 2);
        comp1_2 = board1.compareTo(board2);
        comp1_3 = board1.compareTo(board3);
        comp2_3 = board2.compareTo(board3);

        if(comp1_2 > 0 && comp2_3 > 0){
            assertTrue(comp1_3 > 0);
        }
    }

    @Test
    void compareToTransitifSgn(){
        int comp1_2, comp2_3, comp1_3;
        board2.set(0, 1, 1);
        board3.set(0, 1, 1);
        board3.set(0, 2, 2);
        comp1_2 = board1.compareTo(board2);
        comp1_3 = board1.compareTo(board3);
        comp2_3 = board2.compareTo(board3);
        if(comp1_2 == 0){
            assertEquals(comp1_3, comp2_3);
        }
    }

    int sgn(int i){
        return Integer.compare(i, 0);
    }


    @Test
    void setValueAtException(){
        board1.set(0,0,1);
        assertThrows(IllegalBoardModificationException.class, ()-> board1.set(0, 0,2));
        assertDoesNotThrow(()-> board1.set(0, 0,1));
        assertDoesNotThrow(()-> board1.set(0, 0,0));
    }

}
