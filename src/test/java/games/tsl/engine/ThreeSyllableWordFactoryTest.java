package games.tsl.engine;

import games.tsl.engine.api.ThreeSyllableWord;
import games.tsl.engine.api.ThreeSyllableWordFactory;
import static org.junit.Assert.assertNotNull;

import games.tsl.engine.model.CSVThreeSyllableWordFactory;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Edwin on 21-8-2016.
 */
public class ThreeSyllableWordFactoryTest {

    private static final String INVALID_CSV_FILE_NAME = "three-syllable-lingo-words.unexisting.csv";

    @Test
    public void test_csv_factory_create_random_three_syllable_word_not_null() throws IOException {
        final ThreeSyllableWordFactory threeSyllableWordFactory = new CSVThreeSyllableWordFactory();
        final ThreeSyllableWord threeSyllableWord = threeSyllableWordFactory.createRandomThreeSyllableWord();

        assertNotNull("ThreeSyllableWord is null", threeSyllableWord);
    }

    @Test(expected = NullPointerException.class)
    public void test_csv_factory_path_string_null() throws IOException {
        final ThreeSyllableWordFactory threeSyllableWordFactory = new CSVThreeSyllableWordFactory(null);
        final ThreeSyllableWord threeSyllableWord = threeSyllableWordFactory.createRandomThreeSyllableWord();

        assertNotNull("ThreeSyllableWord is null", threeSyllableWord);
    }

    @Test(expected = NullPointerException.class)
    public void test_csv_factory_path_string_invalid() throws IOException {
        final ThreeSyllableWordFactory threeSyllableWordFactory = new CSVThreeSyllableWordFactory(INVALID_CSV_FILE_NAME);
        final ThreeSyllableWord threeSyllableWord = threeSyllableWordFactory.createRandomThreeSyllableWord();

        assertNotNull("ThreeSyllableWord is null", threeSyllableWord);
    }
}