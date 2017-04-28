package games.tsl.engine.api;

/**
 * Defines a wrapper for a three-syllable word, providing the details of how the word is being split by syllable. This class doesn't verify if
 * the wrapped word is actually a three-syllable word. That should be verified by the provider of the word.
 *
 * Created by Edwin on 20-8-2016.
 */
public interface ThreeSyllableWord {

    /**
     * Get the location of the split between the first and second syllable in the word. E.g. if the word = "Lingo" where the first syllable is "Lin", the
     * returned location will be "3".
     *
     * @return The location in the word where the split between the first and second syllable occurs.
     */
    int getFirstSyllableSplitLocation();

    /**
     * Get the location of the split between the second and third syllable in the word. E.g. if the word = "Syllable" where the second syllable is "la", the
     * returned location will be "5".
     *
     * @return The location in the word where the split between the second and third syllable occurs.
     */
    int getSecondSyllableSplitLocation();

    /**
     * @return The exact word that is being wrapped. Never null.
     */
    String getCompleteWord();

    /**
     * @return The first character of the exact word that is being wrapped. Never null.
     */
    char getFirstCharacter();

    /**
     * The first syllable of the wrapped word, based on the first split location.
     *
     * @return The first syllable of the word. Never null.
     */
    String getFirstSyllable();

    /**
     * The second syllable of the wrapped word, based on the first and second split location.
     *
     * @return The second syllable of the word. Never null.
     */
    String getSecondSyllable();

    /**
     * The third syllable of the wrapped word, based on the second split location.
     *
     * @return The third syllable of the word. Never null.
     */
    String getThirdSyllable();
}