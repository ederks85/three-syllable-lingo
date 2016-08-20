package games.tsl.engine.model;

import games.tsl.engine.api.ThreeSyllableWord;
import org.apache.commons.lang3.Validate;

/**
 * Default implementation of {@link ThreeSyllableWord} that provided no internal synchronization.
 *
 * Created by Edwin on 20-8-2016.
 */
public class DefaultThreeSyllableWord implements ThreeSyllableWord {

    private final String originalWord;
    private final int firstSyllableSplitLocation;
    private final int secondSyllableSplitLocation;

    public DefaultThreeSyllableWord(final String originalWord, final int firstSyllableSplitLocation, final int secondSyllableSplitLocation) {
        Validate.notBlank(originalWord, "OriginalWord is blank");

        if (firstSyllableSplitLocation < 0 || firstSyllableSplitLocation >= secondSyllableSplitLocation || firstSyllableSplitLocation >= originalWord.length()) {
            throw new IllegalArgumentException("Invalid FirstSyllableSplitLocation: " + firstSyllableSplitLocation);
        }

        if (secondSyllableSplitLocation < 0 || secondSyllableSplitLocation <= firstSyllableSplitLocation || secondSyllableSplitLocation >= originalWord.length()) {
            throw new IllegalArgumentException("Invalid FirstSyllableSplitLocation: " + firstSyllableSplitLocation);
        }

        this.originalWord = originalWord;
        this.firstSyllableSplitLocation = firstSyllableSplitLocation;
        this.secondSyllableSplitLocation = secondSyllableSplitLocation;
    }

    @Override
    public int getFirstSyllableSplitLocation() {
        return this.firstSyllableSplitLocation;
    }

    @Override
    public int getSecondSyllableSplitLocation() {
        return this.secondSyllableSplitLocation;
    }

    @Override
    public String getCompleteWord() {
        return this.originalWord;
    }

    @Override
    public String getFirstSyllable() {
        return this.originalWord.substring(0, this.firstSyllableSplitLocation);
    }

    @Override
    public String getSecondSyllable() {
        return this.originalWord.substring(this.firstSyllableSplitLocation, this.secondSyllableSplitLocation);
    }

    @Override
    public String getThirdSyllable() {
        return this.originalWord.substring(this.secondSyllableSplitLocation, this.originalWord.length());
    }
}