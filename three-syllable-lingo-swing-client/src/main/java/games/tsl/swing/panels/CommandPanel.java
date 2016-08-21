package games.tsl.swing.panels;

import games.tsl.swing.actions.WindowCloseAction;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Created by Edwin on 21-8-2016.
 */
public class CommandPanel extends JPanel {

    private static final Font FONT = new Font("Verdana", Font.BOLD, 30);

    private final Component leftGlue;
    private final Component betweenGlue;

    private final JTextField inputField;
    private final JButton quitButton;

    public CommandPanel() {
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        this.leftGlue = Box.createHorizontalGlue();
        this.add(leftGlue, Box.LEFT_ALIGNMENT);

        this.inputField = createInputField();
        this.add(this.inputField, Box.CENTER_ALIGNMENT);

        this.betweenGlue = Box.createHorizontalGlue();
        this.add(this.betweenGlue, Box.RIGHT_ALIGNMENT);

        this.quitButton = createQuitButton();
        this.add(this.quitButton, Box.RIGHT_ALIGNMENT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Define the QuitButton's dimensions
        final Dimension quitButtonsBounds = new Dimension(200, 50);
        this.quitButton.setPreferredSize(quitButtonsBounds);
        this.quitButton.setMaximumSize(quitButtonsBounds);

        // Define some calculation stuff and define the InputField dimensions with it
        final Rectangle currentWindowBounds = SwingUtilities.getRoot(this).getBounds();
        final int availableWidth = (Double.valueOf(currentWindowBounds.getWidth()).intValue() - 20);
        final int quitButtonWidth = (Double.valueOf(quitButtonsBounds.getWidth()).intValue());
        final int inputFieldWidth = (Double.valueOf(4 * quitButtonWidth)).intValue();

        this.inputField.setPreferredSize(new Dimension(inputFieldWidth, 50));
        this.inputField.setMaximumSize(new Dimension(inputFieldWidth, 50));

        // Redefine the space between the InputField and the QuitButton to center the InputField on the screen (is there a better way...?)
        final int betweenGlueWidth = Double.valueOf((availableWidth / 2) - (inputFieldWidth / 2) - quitButtonWidth).intValue();
        this.betweenGlue.setPreferredSize(new Dimension(betweenGlueWidth, Double.valueOf(this.betweenGlue.getPreferredSize().getHeight()).intValue()));
        this.betweenGlue.setMaximumSize(new Dimension(betweenGlueWidth, Double.valueOf(this.betweenGlue.getMaximumSize().getHeight()).intValue()));

        // Revalidate the panel
        this.revalidate();
        this.repaint();
    }

    private JTextField createInputField() {
        final JTextField textfield = new JTextField();
        textfield.setFont(FONT);
        textfield.setForeground(Color.BLUE);
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setLocation(5, 5);
        return textfield;
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