package games.tsl.swing.actions.input;

import games.tsl.engine.api.ThreeSyllableLingoWordCharacter;
import games.tsl.engine.api.exception.ThreeSyllableLingoInvalidGuessException;
import games.tsl.swing.actions.GameComponentManager;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutionException;

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
}