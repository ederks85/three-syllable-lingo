package games.tsl.engine.model;

import games.tsl.engine.api.ThreeSyllableLingoWord;
import games.tsl.engine.api.ThreeSyllableLingoWordCharacter;
import games.tsl.engine.api.ThreeSyllableLingoWordCharacterGuessStatus;
import games.tsl.engine.api.ThreeSyllableWord;
import org.apache.commons.lang3.Validate;

/**
 * Thread-unsafe implementation of {@link ThreeSyllableLingoWord}.
 *
 * Created by Edwin on 21-8-2016.
 */
public class DefaultThreeSyllableLingoWord implements ThreeSyllableLingoWord {

    private final ThreeSyllableWord threeSyllableWord;

    public DefaultThreeSyllableLingoWord(final ThreeSyllableWord threeSyllableWord) {
        Validate.notNull(threeSyllableWord, "ThreeSyllableWord is null");

        this.threeSyllableWord = threeSyllableWord;
    }

    @Override
    public ThreeSyllableLingoWordCharacter[] getGuessStatus() {
        final String completeThreeSyllableWordAsString = this.threeSyllableWord.getCompleteWord();
        final ThreeSyllableLingoWordCharacter[] guessStatus = new ThreeSyllableLingoWordCharacter[completeThreeSyllableWordAsString.length()];

        for (int i=0; i < completeThreeSyllableWordAsString.length(); i++) {
            final ThreeSyllableLingoWordCharacterGuessStatus status;
            if (i == 0) {
                status = ThreeSyllableLingoWordCharacterGuessStatus.IN_PLACE;
            } else {
                status = ThreeSyllableLingoWordCharacterGuessStatus.HIDDEN;
            }
            guessStatus[i] = new ImmutableThreeSyllableLingoWordCharacter(status, completeThreeSyllableWordAsString.charAt(i));

        }

        return guessStatus;
    }
}