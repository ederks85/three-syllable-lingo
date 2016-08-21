package games.tsl.engine.model;

import games.tsl.engine.api.ThreeSyllableWord;
import games.tsl.engine.api.ThreeSyllableWordFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

/**
 * {@link ThreeSyllableWordFactory} that retrieves available three-syllable words from a command-separated-values (CSV) file.
 *
 * Created by Edwin on 21-8-2016.
 */
public class CSVThreeSyllableWordFactory implements ThreeSyllableWordFactory {

    public enum CSVThreeSyllableWordFactoryHeaders {WORD_NAME, FIRST_SYLLABLE_SPLIT_LOCATION, SECOND_SYLLABLE_SPLIT_LOCATION}

    private static final String THREE_SYLLABLE_WORD_CSV_FILE_NAME = "three-syllable-lingo-words.csv";

    private final Random random;
    private final List<CSVRecord> threeSyllableWordsCollection;

    /**
     * Creates a new {@link CSVThreeSyllableWordFactory} that looks up the words in a CSV file "three-syllable-lingo-words.csv" on the root of the classpath.
     */
    public CSVThreeSyllableWordFactory() throws IOException {
        this(THREE_SYLLABLE_WORD_CSV_FILE_NAME);
    }

    /**
     * Creates a new {@link CSVThreeSyllableWordFactory} that looks up the words in a CSV file with the provided name on the classpath.
     */
    public CSVThreeSyllableWordFactory(final String threeSyllableWordsCsvFileName) throws IOException {
        Validate.notNull(threeSyllableWordsCsvFileName, "ThreeSyllableWordsCsvFileName is null");

        this.random = new Random();

        final Reader threeSyllableWordsFileReader = new InputStreamReader(this.getClass().getResourceAsStream("/" + threeSyllableWordsCsvFileName), CharEncoding.UTF_8);
        this.threeSyllableWordsCollection = CSVFormat.RFC4180
                                            .withHeader(
                                                CSVThreeSyllableWordFactoryHeaders.WORD_NAME.toString(),
                                                CSVThreeSyllableWordFactoryHeaders.FIRST_SYLLABLE_SPLIT_LOCATION.toString(),
                                                CSVThreeSyllableWordFactoryHeaders.SECOND_SYLLABLE_SPLIT_LOCATION.toString())
                                            .withSkipHeaderRecord()
                                            .parse(threeSyllableWordsFileReader)
                                            .getRecords();
    }

    @Override
    public ThreeSyllableWord createRandomThreeSyllableWord() {
        final int randomNumber = this.random.nextInt(this.threeSyllableWordsCollection.size());

        final CSVRecord csvRecord = this.threeSyllableWordsCollection.get(randomNumber);

        return new DefaultThreeSyllableWord(
                csvRecord.get(CSVThreeSyllableWordFactoryHeaders.WORD_NAME),
                Integer.parseInt(csvRecord.get(CSVThreeSyllableWordFactoryHeaders.FIRST_SYLLABLE_SPLIT_LOCATION)),
                Integer.parseInt(csvRecord.get(CSVThreeSyllableWordFactoryHeaders.SECOND_SYLLABLE_SPLIT_LOCATION))
        );
    }
}
