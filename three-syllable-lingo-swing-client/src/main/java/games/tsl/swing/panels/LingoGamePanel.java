package games.tsl.swing.panels;

import games.tsl.engine.api.ThreeSyllableLingoWordCharacter;
import games.tsl.swing.renderers.ThreeSyllableLingoListCellRenderer;

import javax.swing.*;
import java.awt.*;

public class LingoGamePanel extends JPanel {

    private final JList<ThreeSyllableLingoWordCharacter[]> threeSyllableLingoWordsList;
    private final DefaultListModel<ThreeSyllableLingoWordCharacter[]> listModel;

    public LingoGamePanel() {
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        this.listModel = new DefaultListModel<>();

        this.threeSyllableLingoWordsList = new JList<>();
        this.threeSyllableLingoWordsList.setEnabled(false);
        this.threeSyllableLingoWordsList.setModel(listModel);
        this.threeSyllableLingoWordsList.setCellRenderer(new ThreeSyllableLingoListCellRenderer());

        this.add(this.threeSyllableLingoWordsList);
    }

    public void addGuessForThreeSyllableWord(final ThreeSyllableLingoWordCharacter[] guess) {
        this.listModel.addElement(guess);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        final int listHeight = (getHeight() - 20);

        this.threeSyllableLingoWordsList.setPreferredSize(new Dimension(800, listHeight));
    }
}