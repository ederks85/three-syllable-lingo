package games.tsl.engine.api;

/**
 * Interface that defines a character in a {@link ThreeSyllableLingoWord}.
 *
 * Created by Edwin on 23-8-2016.
 */
public interface ThreeSyllableLingoWordCharacter {

    /**
     * Get the status of the character in a three-syllable word.
     *
     * @see #getCharacter()
     *
     * @return The {@link ThreeSyllableLingoWordCharacterGuessStatus}, never null.
     */
    ThreeSyllableLingoWordCharacterGuessStatus getStatus();

    /**
     * Get the {@link char} that is associated with the status.
     *
     * @see #getStatus()
     *
     * @return The {@link char}.
     */
    char getCharacter();
}