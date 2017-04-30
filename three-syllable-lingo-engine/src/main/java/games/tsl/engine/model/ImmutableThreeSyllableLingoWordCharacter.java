package games.tsl.engine.model;

import games.tsl.engine.api.ThreeSyllableLingoWordCharacter;
import games.tsl.engine.api.ThreeSyllableLingoWordCharacterGuessStatus;
import org.apache.commons.lang3.Validate;

/**
 * Immutable implementation of {@link ThreeSyllableLingoWordCharacter};
 *
 * Created by Edwin on 23-8-2016.
 */
public class ImmutableThreeSyllableLingoWordCharacter implements ThreeSyllableLingoWordCharacter {

    private final ThreeSyllableLingoWordCharacterGuessStatus status;
    private final char character;
    private final int syllableNumber;

    /**
     * @param status The status of {@link #character} in the three-syllable lingo word
     * @param character The character in question.
     * @param syllableNumber The syllable number this character resided in the three-syllable word
     *
     * @throws NullPointerException when {@link #status} is null
     */
    ImmutableThreeSyllableLingoWordCharacter(
            final ThreeSyllableLingoWordCharacterGuessStatus status,
            final char character,
            final int syllableNumber) {
        Validate.notNull(status);

        this.status = status;
        this.character = character;
        this.syllableNumber = syllableNumber;
    }

    @Override
    public ThreeSyllableLingoWordCharacterGuessStatus getStatus() {
        return this.status;
    }

    @Override
    public char getCharacter() {
        return this.character;
    }

    @Override
    public int getSyllableNumber() {
        return this.syllableNumber;
    }
}