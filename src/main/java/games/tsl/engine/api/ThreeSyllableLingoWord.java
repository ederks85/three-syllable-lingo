package games.tsl.engine.api;

/**
 * Interface that defines the operations that can be performed on a three-syllable word that is being used in a Lingo game.
 *
 * Created by Edwin on 21-8-2016.
 */
public interface ThreeSyllableLingoWord {

    /**
     * Get the status of how far the {@link ThreeSyllableWord} has been guessed. The array's length matches the length of the
     * word to be guessed. Array positions containing a {@link Character} represent correctly guessed locations where null
     * positions represent unguessed positions.
     *
     * @return A {@link Character[]} representing the status, never null.
     */
    Character[] getGuessStatus();
}