package games.tsl.engine;

import games.tsl.engine.api.ThreeSyllableLingoWord;
import games.tsl.engine.api.ThreeSyllableLingoWordCharacter;
import games.tsl.engine.api.ThreeSyllableLingoWordCharacterGuessStatus;
import games.tsl.engine.api.ThreeSyllableWord;
import games.tsl.engine.api.ThreeSyllableWordFactory;
import games.tsl.engine.api.exception.ThreeSyllableLingoInvalidGuessException;
import games.tsl.engine.model.ImmutableThreeSyllableWord;
import games.tsl.engine.model.ThreadSafeThreeSyllableLingoWord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ThreeSyllableLingoWordTest {

    private final String GENERATED_THREE_SYLLABLE_WORD = "syllable";
    private final String VALID_GUESS_INPUT = "sollatom";
    private final String INVALID_GUESS_INPUT = "invalidsyllable";

    @Mock
    private ThreeSyllableWordFactory threeSyllableWordFactory;

    private ThreeSyllableWord threeSyllableWord;

    private ThreeSyllableLingoWord threeSyllableLingoWord;

    @Before
    public void init() throws IOException {
        when(this.threeSyllableWordFactory.createRandomThreeSyllableWord()).thenReturn(new ImmutableThreeSyllableWord(GENERATED_THREE_SYLLABLE_WORD, 3, 5));

        this.threeSyllableWord = this.threeSyllableWordFactory.createRandomThreeSyllableWord();
        this.threeSyllableLingoWord = new ThreadSafeThreeSyllableLingoWord(threeSyllableWord);
    }

    @Test
    public void test_three_syllable_lingo_word_initial_guess_status() {
        final ThreeSyllableLingoWordCharacter[] guessStatus = threeSyllableLingoWord.getGuessStatus();

        assertNotNull("ThreeSyllableLingoWord GuessStatus is null", guessStatus);
        assertEquals(threeSyllableWord.getCompleteWord().length(), guessStatus.length);

        for (int i=0; i < guessStatus.length; i++) {
            if (guessStatus[i] != null) {
                assertEquals(guessStatus[i].getCharacter(), threeSyllableWord.getCompleteWord().charAt(i));
            }
        }
    }

    @Test
    public void test_three_syllable_lingo_word_guess_status_after_proper_guess() throws ThreeSyllableLingoInvalidGuessException {
        final ThreeSyllableLingoWordCharacter[] mostRecentGuessStatus = this.threeSyllableLingoWord.guess(VALID_GUESS_INPUT);

        assertNotNull(mostRecentGuessStatus);
        assertEquals(mostRecentGuessStatus.length, this.threeSyllableWord.getCompleteWord().length());

        assertEquals(ThreeSyllableLingoWordCharacterGuessStatus.IN_PLACE, mostRecentGuessStatus[0].getStatus());
        assertEquals(GENERATED_THREE_SYLLABLE_WORD.charAt(0), mostRecentGuessStatus[0].getCharacter());
        assertEquals(VALID_GUESS_INPUT.charAt(0), mostRecentGuessStatus[0].getCharacter());

        assertEquals(ThreeSyllableLingoWordCharacterGuessStatus.HIDDEN, mostRecentGuessStatus[1].getStatus());
        assertNotEquals(GENERATED_THREE_SYLLABLE_WORD.charAt(1), mostRecentGuessStatus[1].getCharacter());
        assertEquals(VALID_GUESS_INPUT.charAt(1), mostRecentGuessStatus[1].getCharacter());

        assertEquals(ThreeSyllableLingoWordCharacterGuessStatus.IN_PLACE, mostRecentGuessStatus[2].getStatus());
        assertEquals(GENERATED_THREE_SYLLABLE_WORD.charAt(2), mostRecentGuessStatus[2].getCharacter());
        assertEquals(VALID_GUESS_INPUT.charAt(2), mostRecentGuessStatus[2].getCharacter());

        assertEquals(ThreeSyllableLingoWordCharacterGuessStatus.IN_PLACE, mostRecentGuessStatus[3].getStatus());
        assertEquals(GENERATED_THREE_SYLLABLE_WORD.charAt(3), mostRecentGuessStatus[3].getCharacter());
        assertEquals(VALID_GUESS_INPUT.charAt(3), mostRecentGuessStatus[3].getCharacter());

        assertEquals(ThreeSyllableLingoWordCharacterGuessStatus.IN_PLACE, mostRecentGuessStatus[4].getStatus());
        assertEquals(GENERATED_THREE_SYLLABLE_WORD.charAt(4), mostRecentGuessStatus[4].getCharacter());
        assertEquals(VALID_GUESS_INPUT.charAt(4), mostRecentGuessStatus[4].getCharacter());

        assertEquals(ThreeSyllableLingoWordCharacterGuessStatus.HIDDEN, mostRecentGuessStatus[5].getStatus());
        assertNotEquals(GENERATED_THREE_SYLLABLE_WORD.charAt(5), mostRecentGuessStatus[5].getCharacter());
        assertEquals(VALID_GUESS_INPUT.charAt(5), mostRecentGuessStatus[5].getCharacter());

        assertEquals(ThreeSyllableLingoWordCharacterGuessStatus.HIDDEN, mostRecentGuessStatus[6].getStatus());
        assertNotEquals(GENERATED_THREE_SYLLABLE_WORD.charAt(6), mostRecentGuessStatus[6].getCharacter());
        assertEquals(VALID_GUESS_INPUT.charAt(6), mostRecentGuessStatus[6].getCharacter());

        assertEquals(ThreeSyllableLingoWordCharacterGuessStatus.HIDDEN, mostRecentGuessStatus[7].getStatus());
        assertNotEquals(GENERATED_THREE_SYLLABLE_WORD.charAt(7), mostRecentGuessStatus[7].getCharacter());
        assertEquals(VALID_GUESS_INPUT.charAt(7), mostRecentGuessStatus[7].getCharacter());
    }

    @Test(expected = ThreeSyllableLingoInvalidGuessException.class)
    public void test_three_syllable_lingo_word_guess_status_improper_guess_word_invalid_size() throws ThreeSyllableLingoInvalidGuessException {
        this.threeSyllableLingoWord.guess(INVALID_GUESS_INPUT);
    }
}