package games.tsl.swing.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;

/**
 * Created by Edwin on 21-8-2016.
 */
public class BannerPanel extends JPanel {

    private final JLabel bannerLabel;

    public BannerPanel() {
        this.setBackground(Color.YELLOW);

        this.bannerLabel = new JLabel("Three Syllable Lingo");
        this.add(this.bannerLabel);
    }
}