package games.tsl.swing.renderers;

import games.tsl.engine.api.ThreeSyllableLingoWordCharacter;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

/**
 * Renderer for the list that displays the word guesses in a three-syllable-lingo game.
 *
 * Created by Edwin on 24-8-2016.
 */
public class ThreeSyllableLingoListCellRenderer extends JPanel implements ListCellRenderer<ThreeSyllableLingoWordCharacter[]> {

    private boolean rendered;

    public ThreeSyllableLingoListCellRenderer() {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        this.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.setAlignmentY(Component.CENTER_ALIGNMENT);

        this.setBackground(Color.LIGHT_GRAY.brighter());

        this.setMinimumSize(new Dimension(800, 70));
        this.setPreferredSize(new Dimension(800, 70));
        this.setMaximumSize(new Dimension(800, 70));

        this.rendered = false;

        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends ThreeSyllableLingoWordCharacter[]> list,
            ThreeSyllableLingoWordCharacter[] tslwcs,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        if (!this.rendered) {
            for (ThreeSyllableLingoWordCharacter tslwc : tslwcs) {
                final JLabel characterLabel = new JLabel();

                final Font labelFont = characterLabel.getFont();
                characterLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 40));

                characterLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

                characterLabel.setMinimumSize(new Dimension(60, 60));
                characterLabel.setPreferredSize(new Dimension(60, 60));
                characterLabel.setMaximumSize(new Dimension(60, 60));

                characterLabel.setHorizontalAlignment(SwingConstants.CENTER);
                characterLabel.setVerticalAlignment(SwingConstants.CENTER);

                characterLabel.setOpaque(true);

                switch (tslwc.getStatus()) {
                    case IN_PLACE: {
                        characterLabel.setText(String.valueOf(tslwc.getCharacter()).toUpperCase(Locale.ROOT));
                        characterLabel.setForeground(Color.WHITE);
                        characterLabel.setBackground(Color.BLUE);
                        break;
                    }
                    case HIDDEN: {
                        characterLabel.setText(".");
                        characterLabel.setForeground(Color.BLUE);
                        characterLabel.setBackground(Color.WHITE);
                        break;
                    }
                    default: {
                        characterLabel.setText("?");
                        characterLabel.setForeground(Color.WHITE);
                        characterLabel.setBackground(Color.BLACK);
                        break;
                    }
                }

                this.add(characterLabel);
            }
            this.rendered = true;
        }
        return this;
    }
}