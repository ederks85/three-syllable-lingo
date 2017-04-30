package games.tsl.engine;

import games.tsl.engine.api.ThreeSyllableLingoGameEngine;
import games.tsl.engine.api.ThreeSyllableLingoWord;
import games.tsl.engine.api.ThreeSyllableLingoWordCharacter;
import games.tsl.engine.api.ThreeSyllableWordFactory;
import games.tsl.engine.api.exception.ThreeSyllableLingoGameException;
import games.tsl.engine.api.exception.ThreeSyllableLingoInvalidGameStateException;
import games.tsl.engine.api.exception.ThreeSyllableLingoInvalidGuessException;
import games.tsl.engine.model.CSVThreeSyllableWordFactory;
import games.tsl.engine.model.ThreadSafeThreeSyllableLingoWord;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Implementation of {@link ThreeSyllableLingoGameEngine} that manages the game completely in a local VM.
 *
 * Created by Edwin on 21-8-2016.
 */
public class LocalThreeSyllableLingoGameEngine implements ThreeSyllableLingoGameEngine {

    private final ThreeSyllableWordFactory threeSyllableWordFactory;
    private final AtomicReference<ThreeSyllableLingoWord> threeSyllableLingoWord;

    /**
     * Create a new {@link LocalThreeSyllableLingoGameEngine} that uses the default way of retrieving the available three-syllable
     * words to use in the game.
     *
     * @throws ThreeSyllableLingoGameException when creating the game engine fails.
     */
    public LocalThreeSyllableLingoGameEngine() throws ThreeSyllableLingoGameException {
        try {
            this.threeSyllableWordFactory = new CSVThreeSyllableWordFactory();
            this.threeSyllableLingoWord = new AtomicReference<>(null);
        } catch (IOException e) {
            throw new ThreeSyllableLingoGameException("Exception while creating " + LocalThreeSyllableLingoGameEngine.class, e);
        }
    }

    @Override
    public ThreeSyllableLingoWordCharacter[] startNewGame() throws ThreeSyllableLingoGameException {
        final ThreeSyllableLingoWord newThreeSyllableLongoWord = new ThreadSafeThreeSyllableLingoWord(this.threeSyllableWordFactory.createRandomThreeSyllableWord());
        this.threeSyllableLingoWord.set(newThreeSyllableLongoWord);
        return this.threeSyllableLingoWord.get().getGuessStatus();
    }

    @Override
    public ThreeSyllableLingoWordCharacter[] guess(String input) throws ThreeSyllableLingoGameException {
        try {
            if (this.threeSyllableLingoWord.get() == null) {
                throw new ThreeSyllableLingoInvalidGameStateException(ThreeSyllableLingoInvalidGameStateException.Reason.CURRENTLY_NO_WORD_IN_PLAY);
            } else {
                return this.threeSyllableLingoWord.get().guess(input);
            }
        } catch (ThreeSyllableLingoInvalidGuessException | ThreeSyllableLingoInvalidGameStateException e) {
            throw new ThreeSyllableLingoGameException("Processing guess input for three-syllable word failed.", e);
        }
    }
}