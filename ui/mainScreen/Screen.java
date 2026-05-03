package ui.mainScreen;

import gamelogic.clickToggle;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ui.ControlPanel.controlPanel;
import ui.mainComponents.mainFrame;

public class Screen {

    public void CreateScreen(String Title, int Width, int Length)
    {
        mainFrame mFrame = new mainFrame(Width, Length, Title);
        mFrame.setLayout(new BorderLayout());

        clickToggle cToggle = new clickToggle();
        controlPanel ctrlPanel = new controlPanel(Width, Length, mFrame, cToggle);

        mFrame.add(ctrlPanel, BorderLayout.WEST);
        mFrame.setVisible(true);

        mFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                mFrame.dispose();
            }
        });
    }
}
