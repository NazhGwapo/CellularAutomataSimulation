package ui;

import gamelogic.clickToggle;
import java.awt.*;
import java.awt.event.ActionEvent;

class Settings
{
    int chances;
    int blocks;
    int rField1;
    int rField2;
    int rField3;

}

public class choosePanel extends Panel {

    private CreateCanvas currentCanva;

    private mainFrame mFrame;
    private clickToggle cToggle;
    private modeUI mType;
    

    public interface CanvasReadyListener {
        void onCanvasReady(CreateCanvas canvas);
    }

    private CanvasReadyListener canvasReadyListener;

    public void setCanvasReadyListener(CanvasReadyListener listener)
    {
        this.canvasReadyListener = listener;
    }

    public choosePanel(mainFrame mFrame, clickToggle cToggle,buttonPanels bPanel)
    {
        this.mFrame = mFrame;
        this.cToggle = cToggle;

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 130, 40));
        this.setBackground(Color.white);

        Label title = new Label("Choose Cellular Automata Simulation");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        

        PopupMenu popupMenu = new PopupMenu();
        MenuItem menuItem1 = new MenuItem("Conway's game of life");
        MenuItem menuItem2 = new MenuItem("Elementary Cellular Automata");
        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);

        Button confirmBtn = new Button("Choose");
        confirmBtn.setFont(new Font("Arial", Font.BOLD, 30));

        mType = new modeUI();
        ruleFields rField = new ruleFields();

        this.add(title);
        this.add(confirmBtn);
        this.add(popupMenu);
        this.add(mType);
        this.add(rField);
        rField.conwayField();
        mType.ConwayLabel(menuItem1.getLabel());





        confirmBtn.addActionListener(e ->
        {
            popupMenu.show(confirmBtn, this.getX() / 2, this.getY() / 2);
        });

        menuItem1.addActionListener((ActionEvent e) ->
        {
            try {
                Settings setting = new Settings();
                setting.chances = Integer.parseInt(mType.getchancesField().getText().trim());
                setting.blocks= Integer.parseInt(mType.getblocksField().getText().trim());
                setting.rField1 = Integer.parseInt(rField.getrField1().getText().trim());
                setting.rField2 = Integer.parseInt(rField.getrField2().getText().trim());
                setting.rField3 = Integer.parseInt(rField.getrField3().getText().trim());
                FieldsChecking(setting);

                if (currentCanva != null)
                {
                    mFrame.remove(currentCanva);
                }

                currentCanva = new CreateCanvas(setting.blocks, setting.chances, cToggle, Color.black,setting.rField1,setting.rField2,setting.rField3);
                mFrame.add(currentCanva, BorderLayout.CENTER);
                mFrame.validate();
                mFrame.repaint();

                if (canvasReadyListener != null)
                {
                    canvasReadyListener.onCanvasReady(currentCanva);
                }

                Button sButton = bPanel.getStartButton();
                
                if(sButton.getLabel().equals("Stop"))
                {
                    sButton.setLabel("Start");
                    sButton.setBackground(Color.gray);
                }

                TextField chancesField =  mType.getchancesField();
                Label chancesLabel =  mType.getchancesText();
                TextField blocksField = mType.getblocksField();
                Label blocksText = mType.getblocksText();

                    mType.remove(blocksField);
                    mType.remove(blocksText);
                    mType.add(chancesLabel);
                    mType.add(chancesField);
                    mType.add(blocksText);
                    mType.add(blocksField);
            } catch (NumberFormatException ex) {
                System.err.println("Invalid input: " + ex.getMessage());
            }
        });

        menuItem2.addActionListener(e ->
        {
            try
            {
                TextField chancesField =  mType.getchancesField();
                Label chancesLabel =  mType.getchancesText();
                TextField blocksField = mType.getblocksField();
                Label blocksText = mType.getblocksText();

                if(mType.isAncestorOf(chancesField) && mType.isAncestorOf(chancesLabel))
                {
                    mType.remove(blocksField);
                    mType.remove(blocksText);
                    mType.remove(chancesField);
                    mType.remove(chancesLabel);
                    mType.add(blocksText);
                    mType.add(blocksField);

                }

            }
            catch(NumberFormatException ex)
            {

            }
        });
    }

    public CreateCanvas getCurrentCanva()
    {
        return this.currentCanva;
    }

    private void FieldsChecking(Settings s)
    {
         if(s.chances > 100 || s.chances < 0)
                {
                    new warningDialog().showWarning(mFrame,"Chance must be 0-100 only!");
                    s.chances = 50;
                }
                if(s.blocks> 500 || s.blocks <2)
                {
                    new warningDialog().showWarning(mFrame,"Blocks must be 2-500 only!");
                    s.blocks = 100;
                }
                if(s.rField1 > 5 || s.rField1 <1)
                {
                    new warningDialog().showWarning(mFrame,"Rule Field 1 must be 1-5 only!");
                    s.rField1 = 2;
                }
                if(s.rField2 > 5 || s.rField2 <1)
                {
                    new warningDialog().showWarning(mFrame,"Rule Field 2 must be 1-5 only!");
                    s.rField2 = 3;
                }
                if(s.rField3 > 5 || s.rField3 <1)
                {
                    new warningDialog().showWarning(mFrame,"Rule Field 3 must be 1-5 only!");
                    s.rField3 = 3;
                }
              

    }
}
