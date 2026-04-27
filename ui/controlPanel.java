package ui;

import gamelogic.clickToggle;
import java.awt.*;

public class controlPanel extends Panel {

    public controlPanel(int Width, int Length, mainFrame mFrame, clickToggle cToggle)
    {
        this.setPreferredSize(new Dimension((int)(Width * 0.3), Length));
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(0, 1));
        buttonPanels bPanel = new buttonPanels(cToggle);

        choosePanel cPanel = new choosePanel(mFrame, cToggle,bPanel);
        cPanel.setCanvasReadyListener(canvas -> {
            bPanel.setCanvas(canvas);
        });

        this.add(cPanel);
        this.add(bPanel);
    }
}
