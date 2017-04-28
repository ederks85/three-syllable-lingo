package games.tsl.engine;

import games.tsl.engine.api.ThreeSyllableWord;
import games.tsl.engine.model.ImmutableThreeSyllableWord;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ThreeSyllableWordTest {

    private static final String ORIGINAL_WORD = "Syllable";

    private ThreeSyllableWord validThreeSyllableWord;

    @Before
    public void init() {
        this.validThreeSyllableWord = new ImmutableThreeSyllableWord(ORIGINAL_WORD, 3, 5);
    }

    @Test
    public void test_original_word_syllable_split() {
        assertEquals(3, this.validThreeSyllableWord.getFirstSyllableSplitLocation());
        assertEquals(5, this.validThreeSyllableWord.getSecondSyllableSplitLocation());

        assertEquals("Syl", this.validThreeSyllableWord.getFirstSyllable());
        assertEquals("la", this.validThreeSyllableWord.getSecondSyllable());
        assertEquals("ble", this.validThreeSyllableWord.getThirdSyllable());

        assertEquals(ORIGINAL_WORD, this.validThreeSyllableWord.getCompleteWord());
    }

    @Test
    public void test_first_character() {
        char firstCharacter = this.validThreeSyllableWord.getFirstCharacter();
        assertEquals(firstCharacter, this.validThreeSyllableWord.getCompleteWord().charAt(0));
    }

    @Test(expected = NullPointerException.class)
    public void test_null_original_word() {
        new ImmutableThreeSyllableWord(null, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_invalid_first_syllable_split_location_smaller_than_zero() {
        new ImmutableThreeSyllableWord(ORIGINAL_WORD, -1, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_invalid_first_syllable_split_location_greater_than_second_split_location() {
        new ImmutableThreeSyllableWord(ORIGINAL_WORD, 6, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_invalid_first_syllable_split_location_greater_than_word_length() {
        new ImmutableThreeSyllableWord(ORIGINAL_WORD, 9, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_invalid_second_syllable_split_location_smaller_than_zero() {
        new ImmutableThreeSyllableWord(ORIGINAL_WORD, 3, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_invalid_second_syllable_split_location_smaller_than_first_split_location() {
        new ImmutableThreeSyllableWord(ORIGINAL_WORD, 3, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_invalid_second_syllable_split_location_greater_than_word_length() {
        new ImmutableThreeSyllableWord(ORIGINAL_WORD, 3, 9);
    }
}