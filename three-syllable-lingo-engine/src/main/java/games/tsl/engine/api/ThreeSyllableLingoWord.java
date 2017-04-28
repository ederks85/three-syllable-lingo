package games.tsl.engine.api;

import games.tsl.engine.api.exception.ThreeSyllableLingoInvalidGuessException;

/**
 * Interface that defines the operations that can be performed on a three-syllable word that is being used in a Lingo game.
 *
 * @see ThreeSyllableLingoWordCharacter
 *
 * Created by Edwin on 21-8-2016.
 */
public interface ThreeSyllableLingoWord {

    /**
     * Get the status of how far the {@link ThreeSyllableWord} has been guessed. The array's length matches the length of the
     * word to be guessed. Array positions containing a {@link Character} represent correctly guessed locations where null
     * positions represent unguessed positions.
     *
     * @return A {@code ThreeSyllableLingoWordCharacter[]} representing the status of every single character in the word, never null.
     */
    ThreeSyllableLingoWordCharacter[] getGuessStatus();

    /**
     *
     *
     * @param input String that is meant to guess the three-syllable word being used.
     *
     * @return The result of the guess. The latest guess should return the same result as {@link #getGuessStatus()}
     *
     * @throws ThreeSyllableLingoInvalidGuessException when the input is invalid.
     */
    ThreeSyllableLingoWordCharacter[] guess(String input) throws ThreeSyllableLingoInvalidGuessException;
}