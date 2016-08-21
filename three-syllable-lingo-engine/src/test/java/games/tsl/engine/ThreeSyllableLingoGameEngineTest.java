package games.tsl.engine;

import games.tsl.engine.api.ThreeSyllableLingoGameEngine;
import games.tsl.engine.api.exception.ThreeSyllableLingoGameException;
import games.tsl.engine.model.LocalThreeSyllableLingoGameEngine;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Edwin on 21-8-2016.
 */
public class ThreeSyllableLingoGameEngineTest {

    @Test
    public void test_initialize_three_syllable_lingo_game() throws ThreeSyllableLingoGameException {
        final ThreeSyllableLingoGameEngine threeSyllableLingoGameEngine = new LocalThreeSyllableLingoGameEngine();
        final Character[] initialGuessStatus = threeSyllableLingoGameEngine.startNewGame();

        assertNotNull("ThreeSyllableLingoGameEngine InitialGuessStatus is null", initialGuessStatus);

        for (int i=0; i < initialGuessStatus.length; i++) {
            if (i == 0) {
                assertNotNull("ThreeSyllableLingoWord InitialGuessStatus first character is null", initialGuessStatus[i]);
            } else {
                assertNull(initialGuessStatus[i]);
            }
        }
    }
}