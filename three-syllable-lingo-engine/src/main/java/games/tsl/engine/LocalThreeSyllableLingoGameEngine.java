package games.tsl.engine;

import games.tsl.engine.api.ThreeSyllableLingoGameEngine;
import games.tsl.engine.api.ThreeSyllableLingoWord;
import games.tsl.engine.api.ThreeSyllableLingoWordCharacter;
import games.tsl.engine.api.ThreeSyllableWordFactory;
import games.tsl.engine.api.exception.ThreeSyllableLingoGameException;
import games.tsl.engine.api.exception.ThreeSyllableLingoInvalidGuessException;
import games.tsl.engine.model.CSVThreeSyllableWordFactory;
import games.tsl.engine.model.ThreadSafeThreeSyllableLingoWord;

import java.io.IOException;

/**
 * Implementation of {@link ThreeSyllableLingoGameEngine} that manages the game completely in a local VM.
 *
 * Created by Edwin on 21-8-2016.
 */
public class LocalThreeSyllableLingoGameEngine implements ThreeSyllableLingoGameEngine {

    private final ThreeSyllableWordFactory threeSyllableWordFactory;
    private final ThreeSyllableLingoWord threeSyllableLingoWord;

    /**
     * Create a new {@link LocalThreeSyllableLingoGameEngine} that uses the default way of retrieving the available three-syllable
     * words to use in the game.
     *
     * @throws ThreeSyllableLingoGameException when creating the game engine fails.
     */
    public LocalThreeSyllableLingoGameEngine() throws ThreeSyllableLingoGameException {
        try {
            this.threeSyllableWordFactory = new CSVThreeSyllableWordFactory();
            this.threeSyllableLingoWord = new ThreadSafeThreeSyllableLingoWord(this.threeSyllableWordFactory.createRandomThreeSyllableWord());
        } catch (IOException e) {
            throw new ThreeSyllableLingoGameException("Exception while creating " + LocalThreeSyllableLingoGameEngine.class, e);
        }
    }

    @Override
    public ThreeSyllableLingoWordCharacter[] startNewGame() throws ThreeSyllableLingoGameException {
        return this.threeSyllableLingoWord.getGuessStatus();
    }

    @Override
    public ThreeSyllableLingoWordCharacter[] guess(String input) throws ThreeSyllableLingoGameException {
        try {
            return threeSyllableLingoWord.guess(input);
        } catch (ThreeSyllableLingoInvalidGuessException e) {
            throw new ThreeSyllableLingoGameException("Processing guess input for three-syllable word failed.", e);
        }
    }
}