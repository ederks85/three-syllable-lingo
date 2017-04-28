package games.tsl.engine.api;

import games.tsl.engine.api.exception.ThreeSyllableLingoGameException;

/**
 * Interface that represents a three-syllable Lingo game. This interface acts a facade that manages the status and all
 * possible operations on the game.
 *
 * Created by Edwin on 21-8-2016.
 */
public interface ThreeSyllableLingoGameEngine {

    /**
     * Starts a new game, randomly choosing a new three-syllable word before setting this one as the current word in play.
     *
     * @return The initial state of a three-syllable word to play the game with. Never null.
     *
     * @throws ThreeSyllableLingoGameException when starting a new game fails. The exception can contain a more detailed cause.
     */
    ThreeSyllableLingoWordCharacter[] startNewGame() throws ThreeSyllableLingoGameException;

    /**
     * This method processes  guess for the three-syllable word that is currently in play.
     *
     * @param input The input that is used to guess the three-syllable word in play.
     *
     * @return The result of the guess. Never null.
     *
     * @throws ThreeSyllableLingoGameException when processing the guess failed. The exception can contain specific details about why.
     */
    ThreeSyllableLingoWordCharacter[] guess(String input) throws ThreeSyllableLingoGameException;
}