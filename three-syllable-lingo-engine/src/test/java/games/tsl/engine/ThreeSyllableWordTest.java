package games.tsl.engine;

import static org.junit.Assert.assertEquals;

import games.tsl.engine.api.ThreeSyllableWord;
import games.tsl.engine.model.ImmutableThreeSyllableWord;
import org.junit.Test;

/**
 * Created by Edwin on 20-8-2016.
 */
public class ThreeSyllableWordTest {

    private static final String ORIGINAL_WORD = "Syllable";

    @Test
    public void test_original_word_syllable_split() {
        final ThreeSyllableWord threeSyllableWord = new ImmutableThreeSyllableWord(ORIGINAL_WORD, 3, 5);

        assertEquals(3, threeSyllableWord.getFirstSyllableSplitLocation());
        assertEquals(5, threeSyllableWord.getSecondSyllableSplitLocation());

        assertEquals("Syl", threeSyllableWord.getFirstSyllable());
        assertEquals("la", threeSyllableWord.getSecondSyllable());
        assertEquals("ble", threeSyllableWord.getThirdSyllable());

        assertEquals(ORIGINAL_WORD, threeSyllableWord.getCompleteWord());
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