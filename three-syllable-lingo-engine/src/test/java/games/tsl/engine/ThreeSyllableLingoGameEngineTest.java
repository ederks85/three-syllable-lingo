package games.tsl.engine;

import games.tsl.engine.api.ThreeSyllableLingoGameEngine;
import games.tsl.engine.api.ThreeSyllableLingoWordCharacter;
import games.tsl.engine.api.ThreeSyllableLingoWordCharacterGuessStatus;
import games.tsl.engine.api.ThreeSyllableWordFactory;
import games.tsl.engine.api.exception.ThreeSyllableLingoGameException;
import games.tsl.engine.api.exception.ThreeSyllableLingoInvalidGameStateException;
import games.tsl.engine.api.exception.ThreeSyllableLingoInvalidGuessException;
import games.tsl.engine.model.CSVThreeSyllableWordFactory;
import games.tsl.engine.model.ImmutableThreeSyllableWord;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@PrepareForTest({
        LocalThreeSyllableLingoGameEngine.class,
        ThreeSyllableWordFactory.class})
@RunWith(PowerMockRunner.class)
public class ThreeSyllableLingoGameEngineTest {

    private final String GENERATED_THREE_SYLLABLE_WORD = "syllable";
    private final String VALID_GUESS_INPUT = "sollatom";
    private final String INVALID_GUESS_INPUT = "invalidsyllable";

    @Mock
    private CSVThreeSyllableWordFactory threeSyllableWordFactory;

    @Rule
    private ExpectedException expectedException = ExpectedException.none();

    private ThreeSyllableLingoGameEngine threeSyllableLingoGameEngine;

    @Before
    public void init() throws Exception {
        whenNew(CSVThreeSyllableWordFactory.class).withNoArguments().thenReturn(threeSyllableWordFactory);
        when(threeSyllableWordFactory.createRandomThreeSyllableWord()).thenReturn(new ImmutableThreeSyllableWord(GENERATED_THREE_SYLLABLE_WORD, 3, 5));

        this.threeSyllableLingoGameEngine = new LocalThreeSyllableLingoGameEngine();
    }

    @Test
    public void test_initialize_three_syllable_lingo_game() throws ThreeSyllableLingoGameException {
        final ThreeSyllableLingoWordCharacter[] initialGuessStatus = threeSyllableLingoGameEngine.startNewGame();

        assertNotNull("ThreeSyllableLingoGameEngine InitialGuessStatus is null", initialGuessStatus);

        for (int i=0; i < initialGuessStatus.length; i++) {
            if (i == 0) {
                assertNotNull("ThreeSyllableLingoWord InitialGuessStatus character at " + i + " is null", initialGuessStatus[i]);
                assertEquals(ThreeSyllableLingoWordCharacterGuessStatus.IN_PLACE, initialGuessStatus[i].getStatus());
            } else {
                assertNotNull("ThreeSyllableLingoWord InitialGuessStatus character at " + i + " is null", initialGuessStatus[i]);
                assertEquals(ThreeSyllableLingoWordCharacterGuessStatus.HIDDEN, initialGuessStatus[i].getStatus());
            }
        }
    }

    @Test
    public void test_guess_current_three_syllable_lingo_without_game_started() throws ThreeSyllableLingoGameException {
        expectedException.expectCause(isA(ThreeSyllableLingoInvalidGameStateException.class));

        final ThreeSyllableLingoWordCharacter[] guessResult = this.threeSyllableLingoGameEngine.guess(VALID_GUESS_INPUT);

        assertNotNull(guessResult);
        assertEquals(VALID_GUESS_INPUT.length(), guessResult.length);
    }

    @Test
    public void test_guess_current_three_syllable_lingo_valid_input() throws ThreeSyllableLingoGameException {
        this.threeSyllableLingoGameEngine.startNewGame();
        final ThreeSyllableLingoWordCharacter[] guessResult = this.threeSyllableLingoGameEngine.guess(VALID_GUESS_INPUT);

        assertNotNull(guessResult);
        assertEquals(VALID_GUESS_INPUT.length(), guessResult.length);
    }

    @Test
    public void test_guess_current_three_syllable_lingo_invalid_input() throws ThreeSyllableLingoGameException {
        expectedException.expectCause(isA(ThreeSyllableLingoInvalidGuessException.class));

        this.threeSyllableLingoGameEngine.startNewGame();
        this.threeSyllableLingoGameEngine.guess(INVALID_GUESS_INPUT);
    }
}