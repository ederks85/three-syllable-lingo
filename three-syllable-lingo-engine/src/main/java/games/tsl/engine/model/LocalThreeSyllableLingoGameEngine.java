package games.tsl.engine.model;

import games.tsl.engine.api.ThreeSyllableLingoGameEngine;
import games.tsl.engine.api.ThreeSyllableLingoWord;
import games.tsl.engine.api.ThreeSyllableWordFactory;
import games.tsl.engine.api.exception.ThreeSyllableLingoGameException;

import java.io.IOException;
import java.io.Serializable;

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
     * @throws ThreeSyllableLingoGameException
     */
    public LocalThreeSyllableLingoGameEngine() throws ThreeSyllableLingoGameException {
        try {
            this.threeSyllableWordFactory = new CSVThreeSyllableWordFactory();
            this.threeSyllableLingoWord = new DefaultThreeSyllableLingoWord(this.threeSyllableWordFactory.createRandomThreeSyllableWord());
        } catch (IOException e) {
            throw new ThreeSyllableLingoGameException("Exception while creating " + LocalThreeSyllableLingoGameEngine.class, e);
        }
    }

    @Override
    public Character[] startNewGame() {
        return this.threeSyllableLingoWord.getGuessStatus();
    }
}