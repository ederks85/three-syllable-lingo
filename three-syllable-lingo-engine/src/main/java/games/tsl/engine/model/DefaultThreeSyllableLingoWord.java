package games.tsl.engine.model;

import games.tsl.engine.api.ThreeSyllableLingoWord;
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
    public Character[] getGuessStatus() {
        final String completeThreeSyllableWordAsString = this.threeSyllableWord.getCompleteWord();
        final Character[] guessStatus = new Character[completeThreeSyllableWordAsString.length()];

        guessStatus[0] = completeThreeSyllableWordAsString.charAt(0);

        return guessStatus;
    }
}