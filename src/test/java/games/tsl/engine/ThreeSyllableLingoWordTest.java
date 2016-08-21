package games.tsl.engine;

import games.tsl.engine.api.ThreeSyllableLingoWord;
import games.tsl.engine.api.ThreeSyllableWord;
import games.tsl.engine.api.ThreeSyllableWordFactory;
import games.tsl.engine.model.CSVThreeSyllableWordFactory;
import games.tsl.engine.model.DefaultThreeSyllableLingoWord;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Edwin on 21-8-2016.
 */
public class ThreeSyllableLingoWordTest {

    private ThreeSyllableWordFactory threeSyllableWordFactory;

    @Before
    public void init() throws IOException {
        this.threeSyllableWordFactory = new CSVThreeSyllableWordFactory();
    }

    @Test(expected = NullPointerException.class)
    public void test_three_syllable_lingo_word_input_null() {
        final ThreeSyllableLingoWord threeSyllableLingoWord = new DefaultThreeSyllableLingoWord(null);
    }

    @Test
    public void test_three_syllable_lingo_word_input_valid() {
        final ThreeSyllableWord threeSyllableWord = this.threeSyllableWordFactory.createRandomThreeSyllableWord();
        final ThreeSyllableLingoWord threeSyllableLingoWord = new DefaultThreeSyllableLingoWord(threeSyllableWord);
    }

    @Test
    public void test_three_syllable_lingo_word_initial_guess_status() {
        final ThreeSyllableWord threeSyllableWord = this.threeSyllableWordFactory.createRandomThreeSyllableWord();
        final ThreeSyllableLingoWord threeSyllableLingoWord = new DefaultThreeSyllableLingoWord(threeSyllableWord);
        final Character[] initialGuessStatus = threeSyllableLingoWord.getGuessStatus();

        assertNotNull("ThreeSyllableLingoWord InitialGuessStatus is null", initialGuessStatus);
        assertEquals(threeSyllableWord.getCompleteWord().length(), initialGuessStatus.length);

        for (int i=0; i < initialGuessStatus.length; i++) {
            if (i == 0) {
                assertNotNull("ThreeSyllableLingoWord InitialGuessStatus first character is null", initialGuessStatus[i]);
                assertEquals(initialGuessStatus[i], Character.valueOf(threeSyllableWord.getCompleteWord().charAt(i)));
                System.out.print(initialGuessStatus[i]);
            } else {
                assertNull(initialGuessStatus[i]);
                System.out.print(initialGuessStatus[i]);
            }
        }
    }
}