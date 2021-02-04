import fr.ul.ia.modele.Board;
import fr.ul.ia.modele.PancakeState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    Board board1, board2;

    @BeforeEach
    void setUp() {
        board1 = new Board();
        board2 = new Board();
    }

    @Test
    void compareToSgn(){
        board1.set(0, 1, 1);
        assertSame(board1.compareTo(board2), -board2.compareTo(board1)); // Dans un sens
        assertSame(board2.compareTo(board1), -board1.compareTo(board2)); // Puis dans l'autre
    }

}
