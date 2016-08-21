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
        final Character[] guessStatus = threeSyllableLingoWord.getGuessStatus();

        assertNotNull("ThreeSyllableLingoWord GuessStatus is null", guessStatus);
        assertEquals(threeSyllableWord.getCompleteWord().length(), guessStatus.length);

        for (int i=0; i < guessStatus.length; i++) {
            if (guessStatus[i] != null) {
                assertEquals(guessStatus[i], Character.valueOf(threeSyllableWord.getCompleteWord().charAt(i)));
            }
        }
    }
}