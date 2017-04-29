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
    }

    @Override
    public ThreeSyllableLingoWordCharacter[] getGuessStatus() {
        String mostRecentGuess = this.guesses.peek();
        if (mostRecentGuess == null) {
            return getInitialGuessStatus();
        } else {
            return getGuessStatusAfterGuess();
        }
    }

    private ThreeSyllableLingoWordCharacter[] getInitialGuessStatus() {
        final String completeThreeSyllableWordAsString = this.threeSyllableWord.getCompleteWord();

        final ThreeSyllableLingoWordCharacter[] guessStatus = new ThreeSyllableLingoWordCharacter[completeThreeSyllableWordAsString.length()];

        for (int i=0; i < completeThreeSyllableWordAsString.length(); i++) {
            final ThreeSyllableLingoWordCharacterGuessStatus status;
            if (i == 0 && completeThreeSyllableWordAsString.charAt(i) == completeThreeSyllableWordAsString.charAt(i)) {
                status = ThreeSyllableLingoWordCharacterGuessStatus.IN_PLACE;
            } else {
                status = ThreeSyllableLingoWordCharacterGuessStatus.HIDDEN;
            }
            guessStatus[i] = new ImmutableThreeSyllableLingoWordCharacter(status, completeThreeSyllableWordAsString.charAt(i));
        }

        this.guesses.push(completeThreeSyllableWordAsString);

        return guessStatus;
    }

    private ThreeSyllableLingoWordCharacter[] getGuessStatusAfterGuess() {
        final String completeThreeSyllableWordAsString = this.threeSyllableWord.getCompleteWord();
        final ThreeSyllableLingoWordCharacter[] guessStatus = new ThreeSyllableLingoWordCharacter[completeThreeSyllableWordAsString.length()];

        final String mostRecentGuess = this.guesses.peek();
        for (int i=0; i < completeThreeSyllableWordAsString.length(); i++) {
            final ThreeSyllableLingoWordCharacterGuessStatus status;
            if (mostRecentGuess.charAt(i) == completeThreeSyllableWordAsString.charAt(i)) {
                status = ThreeSyllableLingoWordCharacterGuessStatus.IN_PLACE;
            } else {
                status = ThreeSyllableLingoWordCharacterGuessStatus.HIDDEN;
            }
            guessStatus[i] = new ImmutableThreeSyllableLingoWordCharacter(status, mostRecentGuess.charAt(i));
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