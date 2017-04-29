package games.tsl.swing.panels;

import games.tsl.swing.actions.gui.WindowCloseAction;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CommandPanel extends JPanel {

    private static final Font FONT = new Font("Verdana", Font.BOLD, 30);

    private final Component leftGlue;
    private final Component betweenInputStrut;
    private final Component betweenGlue;

    private final JTextField inputField;
    private final Border inputFieldDefaultBorder;
    private final Border inputFieldInvalidInputBorder;

    private final JButton inputButton;
    private final JButton quitButton;

    public CommandPanel() {
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        this.leftGlue = Box.createHorizontalGlue();
        this.add(leftGlue, Box.LEFT_ALIGNMENT);


        this.inputField = createInputField();
        this.inputFieldDefaultBorder = this.inputField.getBorder();
        this.inputFieldInvalidInputBorder = BorderFactory.createLineBorder(Color.RED, 2);

        final Dimension inputFieldBounds = new Dimension(800, 50);
        this.inputField.setPreferredSize(inputFieldBounds);
        this.inputField.setMaximumSize(inputFieldBounds);

        this.add(this.inputField, Box.CENTER_ALIGNMENT);


        this.betweenInputStrut = Box.createHorizontalStrut(5);
        this.add(this.betweenInputStrut, Box.CENTER_ALIGNMENT);


        this.inputButton = createInputButton();

        final Dimension inputButtonBounds = new Dimension(200, 50);
        this.inputButton.setPreferredSize(inputButtonBounds);
        this.inputButton.setMaximumSize(inputButtonBounds);

        this.add(this.inputButton, Box.CENTER_ALIGNMENT);


        this.betweenGlue = Box.createHorizontalGlue();
        this.add(this.betweenGlue, Box.RIGHT_ALIGNMENT);


        this.quitButton = createQuitButton();

        final Dimension quitButtonBounds = new Dimension(200, 50);
        this.quitButton.setPreferredSize(quitButtonBounds);
        this.quitButton.setMaximumSize(quitButtonBounds);

        this.add(this.quitButton, Box.RIGHT_ALIGNMENT);
    }

    public String getCurrentInput() {
        return this.inputField.getText();
    }

    public void setInputButtonAction(final Action action) {
        this.inputButton.setAction(action);
    }

    public void lockGUI() {
        this.inputField.setEnabled(false);
        this.inputField.setBackground(Color.GRAY);

        this.inputButton.setEnabled(false);
        this.inputButton.setBackground(Color.GRAY);
    }

    public void unlockGUI() {
        this.inputField.setEnabled(true);
        this.inputField.setBackground(Color.WHITE);

        this.inputButton.setEnabled(true);
        this.inputButton.setBackground(Color.WHITE);
    }

    public void toggleInvalidInputState(final boolean toggle) {
        if (toggle) {
            this.inputField.setBorder(this.inputFieldInvalidInputBorder);
        } else {
            this.inputField.setBorder(this.inputFieldDefaultBorder);
        }
    }

    private JTextField createInputField() {
        final JTextField textfield = new JTextField();
        textfield.setFont(FONT);
        textfield.setForeground(Color.BLUE);
        textfield.setHorizontalAlignment(JTextField.CENTER);
        return textfield;
    }

    private JButton createInputButton() {
        final Font font = new Font("Verdana", Font.BOLD, 30);

        final JButton button = new JButton();
        button.setFont(font);
        button.setForeground(Color.BLUE);
        button.setBackground(Color.WHITE);

        return button;
    }

    private JButton createQuitButton() {
        final Font font = new Font("Verdana", Font.BOLD, 30);

        final JButton button = new JButton();
        button.setFont(font);
        button.setForeground(Color.BLUE);
        button.setAction(new WindowCloseAction());
        button.setBackground(Color.WHITE);

        return button;
    }
}