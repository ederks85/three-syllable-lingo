package games.tsl.engine.model;

import games.tsl.engine.api.ThreeSyllableLingoWord;
import games.tsl.engine.api.ThreeSyllableLingoWordCharacter;
import games.tsl.engine.api.ThreeSyllableLingoWordCharacterGuessStatus;
import games.tsl.engine.api.ThreeSyllableWord;
import games.tsl.engine.api.exception.ThreeSyllableLingoInvalidGuessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Thread-unsafe implementation of {@link ThreeSyllableLingoWord}.
 *
 * Created by Edwin on 21-8-2016.
 */
public class ThreadSafeThreeSyllableLingoWord implements ThreeSyllableLingoWord {

    private final ThreeSyllableWord threeSyllableWord;
    private final BlockingDeque<String> guesses;

    public ThreadSafeThreeSyllableLingoWord(final ThreeSyllableWord threeSyllableWord) {
        Validate.notNull(threeSyllableWord, "ThreeSyllableWord is null");

        this.threeSyllableWord = threeSyllableWord;
        this.guesses = new LinkedBlockingDeque<>();

        // Set initial guess status: the first character
        this.guesses.push(String.valueOf(this.threeSyllableWord.getFirstCharacter()));
    }

    @Override
    public ThreeSyllableLingoWordCharacter[] getGuessStatus() {
        final String completeThreeSyllableWordAsString = this.threeSyllableWord.getCompleteWord();
        final String mostRecentGuess = this.guesses.peek();

        final ThreeSyllableLingoWordCharacter[] guessStatus = new ThreeSyllableLingoWordCharacter[completeThreeSyllableWordAsString.length()];

        for (int i=0; i < completeThreeSyllableWordAsString.length(); i++) {
            final ThreeSyllableLingoWordCharacterGuessStatus status;
            if (mostRecentGuess.length() > i && mostRecentGuess.charAt(i) == completeThreeSyllableWordAsString.charAt(i)) {
                status = ThreeSyllableLingoWordCharacterGuessStatus.IN_PLACE;
            } else {
                status = ThreeSyllableLingoWordCharacterGuessStatus.HIDDEN;
            }
            guessStatus[i] = new ImmutableThreeSyllableLingoWordCharacter(status, completeThreeSyllableWordAsString.charAt(i));
        }

        return guessStatus;
    }

    @Override
    public ThreeSyllableLingoWordCharacter[] guess(String input) throws ThreeSyllableLingoInvalidGuessException {
        if (StringUtils.isEmpty(input) || input.length() != this.threeSyllableWord.getCompleteWord().length()) {
            throw new ThreeSyllableLingoInvalidGuessException();
        }

        this.guesses.push(input);
        return this.getGuessStatus();
    }
}