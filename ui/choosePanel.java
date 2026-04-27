package ui;

import gamelogic.clickToggle;
import java.awt.*;
import java.awt.event.ActionEvent;

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

        this.add(title);
        this.add(confirmBtn);
        this.add(popupMenu);

        mType = new modeUI();
        this.add(mType);
        mType.ConwayLabel(menuItem1.getLabel());

        confirmBtn.addActionListener(e ->
        {
            popupMenu.show(confirmBtn, this.getX() / 2, this.getY() / 2);
        });

        menuItem1.addActionListener((ActionEvent e) ->
        {
            try {
                int chance = Integer.parseInt(mType.getchancesField().getText().trim());
                int blocks = Integer.parseInt(mType.getblocksField().getText().trim());
                if(chance > 100 || chance < 0)
                {
                    new warningDialog().showWarning(mFrame,"Chance must be 0-100 only!");
                    chance = 50;
                }
                if(blocks > 500 || blocks <2)
                {
                    new warningDialog().showWarning(mFrame,"Blocks must be 2-500 only!");
                    blocks = 100;
                }
                if (currentCanva != null)
                {
                    mFrame.remove(currentCanva);
                }

                currentCanva = new CreateCanvas(blocks, chance, cToggle, Color.black);
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
                    sButton.setBackground(Color.green);
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
}
