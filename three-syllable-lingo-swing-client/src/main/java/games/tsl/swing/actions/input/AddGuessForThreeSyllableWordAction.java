package games.tsl.swing.actions.input;

import games.tsl.engine.api.ThreeSyllableLingoWordCharacter;
import games.tsl.engine.api.ThreeSyllableLingoWordCharacterGuessStatus;
import games.tsl.engine.api.exception.ThreeSyllableLingoInvalidGuessException;
import games.tsl.swing.actions.GameComponentManager;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Action that can be used by a user to input and process a guess for a three-syllable word.
 */
public class AddGuessForThreeSyllableWordAction extends AbstractAction {

    private final GameComponentManager gameComponentManager;

    public AddGuessForThreeSyllableWordAction(final GameComponentManager gameComponentManager) {
        Validate.notNull(gameComponentManager, "gameComponentManager is null");
        this.gameComponentManager = gameComponentManager;

        this.putValue(Action.NAME, "Guess");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddGuessForThreeSyllableWordAction.this.gameComponentManager.lockGUI();
        AddGuessForThreeSyllableWordAction.this.gameComponentManager.getCommandPanel().toggleInvalidInputState(false);

        final SwingWorker<ThreeSyllableLingoWordCharacter[], Void> guessWorker = new SwingWorker<ThreeSyllableLingoWordCharacter[], Void>() {
            @Override
            protected ThreeSyllableLingoWordCharacter[] doInBackground() throws Exception {
                final String guessInput = AddGuessForThreeSyllableWordAction.this.gameComponentManager.getCommandPanel().getCurrentInput();
                return AddGuessForThreeSyllableWordAction.this.gameComponentManager.getThreeSyllableLingoGameEngine().guess(guessInput);
            }

            @Override
            protected void done() {
                try {
                    final ThreeSyllableLingoWordCharacter[] guessResult = get();
                    AddGuessForThreeSyllableWordAction.this.gameComponentManager.getLingoGamePanel().addGuessForThreeSyllableWord(guessResult);

                    // Determine if the three-syllable word in use was guessed. If so, the game has ended.
                    if (validateThreeSyllableWordGuessed(guessResult)) {
                        final StartNewThreeSyllableWordGameAction startNewThreeSyllableWordGameAction = new StartNewThreeSyllableWordGameAction(
                                AddGuessForThreeSyllableWordAction.this.gameComponentManager,
                                false);
                        AddGuessForThreeSyllableWordAction.this.gameComponentManager.getCommandPanel().setInputButtonAction(startNewThreeSyllableWordGameAction);
                    }
                    AddGuessForThreeSyllableWordAction.this.gameComponentManager.unlockGUI();
                } catch (InterruptedException | ExecutionException e) {
                    final Throwable rootCause = ExceptionUtils.getRootCause(e);
                    if (rootCause instanceof ThreeSyllableLingoInvalidGuessException) {
                        AddGuessForThreeSyllableWordAction.this.gameComponentManager.unlockGUI();
                        AddGuessForThreeSyllableWordAction.this.gameComponentManager.getCommandPanel().toggleInvalidInputState(true);
                    } else {
                        e.printStackTrace();
                    }
                }
            }
        };
        guessWorker.execute();
    }

//    private static String threeSyllableWordCharacterArrayCharactersToString(final ThreeSyllableLingoWordCharacter[] tslwca) {
//        Validate.notNull(tslwca, "ThreeSyllableLingoWordCharacter Array is null");
//        return Arrays
//                .stream(tslwca)
//                .map(tslwc -> tslwc.getCharacter())
//                .collect(Collectors.toList())
//                    .stream()
//                    .map(character -> character.toString())
//                    .collect(Collectors.joining());
//    }

    private static boolean validateThreeSyllableWordGuessed(final ThreeSyllableLingoWordCharacter[] tslwca) {
        Validate.notNull(tslwca, "ThreeSyllableLingoWordCharacter Array is null");
        return Arrays
                .stream(tslwca)
                .map(tslwc -> tslwc.getStatus())
                .collect(Collectors.toList())
                    .stream()
                    .filter(tlswc -> tlswc == ThreeSyllableLingoWordCharacterGuessStatus.IN_PLACE)
                    .collect(Collectors.toList())
                    .size() == tslwca.length;
    }
}