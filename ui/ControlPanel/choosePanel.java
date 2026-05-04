package ui.ControlPanel;

import gamelogic.clickToggle;
import java.awt.*;
import ui.ControlPanel.PanelComponents.creationSettings;
import ui.ControlPanel.PanelComponents.generationsLabel;
import ui.ControlPanel.PanelComponents.ruleFields;
import ui.helperComponent.warningDialog;
import ui.mainComponents.CreateCanvas;
import ui.mainComponents.mainFrame;

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

    private final mainFrame mFrame;
    private final clickToggle cToggle;
    private creationSettings mType;
    private ruleFields rField;
    private generationsLabel gLabel;

    
    

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
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 30));
        this.setBackground(Color.white);

        Label title = new Label(mFrame.getTitle());
        title.setFont(new Font("Arial", Font.BOLD, 20));
        


        Button confirmBtn = new Button("Create Canva");
        confirmBtn.setFont(new Font("Arial", Font.BOLD, 30));

        mType = new creationSettings();
        rField = new ruleFields();
        gLabel = new generationsLabel(0,0,0);

        this.add(title);
        this.add(confirmBtn);
        this.add(mType);
        this.add(rField);
        this.add(gLabel);





        confirmBtn.addActionListener(e ->
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
                    currentCanva.set_Running(false);
                    currentCanva.Start(); //DISABLES THE CANVA'S RUNNING LOOP 
                    mFrame.remove(currentCanva); //THEN DELETES IT
                }
                // CREATE A NEW ONE
                currentCanva = new CreateCanvas(setting.blocks, setting.chances, cToggle, Color.black,setting.rField1,setting.rField2,setting.rField3,gLabel);
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
                
                currentCanva.setGenerationtoZero();

                                

                gLabel.getGnerationsLabel().setText(String.format("Generations: %d", currentCanva.getGenerations()));


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
                new warningDialog().showWarning(mFrame, "Invalid inputs(Restarting all Inputs)");

                if(currentCanva != null)
                {
                    currentCanva.set_Running(false);
                    mFrame.remove(currentCanva);
                }
                mType.getchancesField().setText("50");
                mType.getblocksField().setText("100");
                rField.getrField1().setText("2");
                rField.getrField2().setText("3");
                rField.getrField3().setText("3");
                gLabel.getGnerationsLabel().setText("Generations: 0");
                gLabel.getAliveLabel().setText("Alive: 0");
                gLabel.getDeadLabel().setText("Dead: 0");

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
                    mType.getchancesField().setText(String.format("%d",s.chances));
                }
                if(s.blocks> 500 || s.blocks <2)
                {
                    new warningDialog().showWarning(mFrame,"Blocks must be 2-500 only!");
                    s.blocks = 100;
                    mType.getblocksField().setText(String.format("%d",s.blocks));
                }
                if(s.rField1 > 5 || s.rField1 <1)
                {
                    new warningDialog().showWarning(mFrame,"Rule Field 1 must be 1-5 only!");
                    s.rField1 = 2;
                    rField.getrField1().setText(String.format("%d",s.rField1));
                }
                if(s.rField2 > 5 || s.rField2 <1)
                {
                    new warningDialog().showWarning(mFrame,"Rule Field 2 must be 1-5 only!");
                    s.rField2 = 3;
                    rField.getrField2().setText(String.format("%d",s.rField2));
                }
                if(s.rField3 > 5 || s.rField3 <1)
                {
                    new warningDialog().showWarning(mFrame,"Rule Field 3 must be 1-5 only!");
                    s.rField3 = 3;
                    rField.getrField3().setText(String.format("%d",s.rField3));
                }
              

    }
}
