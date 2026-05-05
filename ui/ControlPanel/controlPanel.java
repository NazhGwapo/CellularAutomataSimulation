    package ui.ControlPanel;

    import gamelogic.clickToggle;
    import java.awt.*;
import ui.ControlPanel.choosePanel.CanvasReadyListener;
import ui.mainComponents.CreateCanvas;
import ui.mainComponents.mainFrame;

    public class controlPanel extends Panel {

        public controlPanel(int Width, int Length, mainFrame mFrame, clickToggle cToggle)
        {
            this.setPreferredSize(new Dimension((int)(Width * 0.3), Length));
            this.setBackground(Color.WHITE);
            this.setLayout(new GridLayout(0, 1));
            buttonPanels bPanel = new buttonPanels(cToggle,mFrame);

            choosePanel cPanel = new choosePanel(mFrame, cToggle,bPanel);
             cPanel.setCanvasReadyListener(new CanvasReadyListener() 
             {
                 @Override
                public void onCanvasReady(CreateCanvas canvas) {
                    bPanel.setCanvas(canvas);
                }
            });   
            
            this.add(cPanel);
            this.add(bPanel);
        }
    }
