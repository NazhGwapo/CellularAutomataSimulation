package ui.ControlPanel;

import gamelogic.clickToggle;
import ui.ControlPanel.PanelComponents.AliveGraph;
import ui.ControlPanel.PanelComponents.generationsLabel;
import ui.helperComponent.warningDialog;
import ui.mainComponents.CreateCanvas;
import ui.mainComponents.mainFrame;

import java.awt.*;

public class buttonPanels extends Panel {

    private CreateCanvas canva;
    private Button StartButton;
    private Button ToggleButton;
    private Button KillAllButton;
    private Button showGraphButton;
    
    private generationsLabel gLabel;
    private AliveGraph aGraph;


    public buttonPanels(clickToggle cToggle,mainFrame mFrame)
    {
        this.setLayout(new GridLayout(4, 1));
        ToggleButton = new Button("Toggle");
        StartButton  = new Button("Start");
        KillAllButton = new Button("Kill All?");
        showGraphButton = new Button("Show Graph");

        StartButton.setBackground(Color.gray);
        StartButton.setFont(new Font("Arial", Font.BOLD, 15));
        ToggleButton.setFont(new Font("Arial", Font.BOLD, 15));
        KillAllButton.setFont(new Font("Arial", Font.BOLD, 15));
        showGraphButton.setFont(new Font("Arial", Font.BOLD, 15));

        ToggleButton.setLabel("Toggle: Alive");
        ToggleButton.setBackground(Color.green);

        ToggleButton.addActionListener(e ->
        {
            if (!cToggle.get_Toggle())
            {
                ToggleButton.setLabel("Toggle: Alive");
                ToggleButton.setBackground(Color.green);
                cToggle.set_Toggle(true);
            }
            else
            {
                ToggleButton.setLabel("Toggle: Dead");
                ToggleButton.setBackground(Color.red);
                cToggle.set_Toggle(false);
            }
        });

        StartButton.addActionListener(e ->
        {
            if (canva == null) return;

            if (canva.get_Running())
            {
                canva.set_Running(false);
                canva.Start();
                StartButton.setLabel("Start");
                StartButton.setBackground(Color.gray);
                gLabel.getGnerationsLabel().setText(String.format("Generations: %d", canva.getGenerations()));
                gLabel.getAliveLabel().setText(String.format("Alive: %d", canva.getAlive()));
                gLabel.getDeadLabel().setText(String.format("Dead : %d", canva.getDead()));

            }
            else
            {
                canva.set_Running(true);
                canva.Start();
                StartButton.setLabel("Stop");
                StartButton.setBackground(Color.red);
            }
        });

        KillAllButton.addActionListener(e ->
        {
            if (canva == null) return;

            if (canva.get_Running())
            {
                canva.set_Running(false);
                canva.Start();
                StartButton.setLabel("Start");
                StartButton.setBackground(Color.gray);
               
                
            }
            canva.killAllCell();
            gLabel.getAliveLabel().setText(String.format("Alive: %d",0));
            gLabel.getDeadLabel().setText(String.format("Dead: %d", canva.getDead()));
        });

        showGraphButton.addActionListener(e->
            {
                if (canva == null)
                {
                    new warningDialog().showWarning(mFrame,"You have not made a canva yet");
                    return;
                }
                if(aGraph!= null && aGraph.getRunning())
                {
                    
                    new warningDialog().showWarning(mFrame,"You have made a graph already");
                    
                }
                else
                {
                    aGraph = new AliveGraph();
                    aGraph.showGraph(mFrame);
                    this.canva.setGraph(aGraph.getgraphCanva());
                }

            });

        this.add(ToggleButton);
        this.add(StartButton);
        this.add(KillAllButton);
        this.add(showGraphButton);
    }

    public void setCanvas(CreateCanvas canva)
    {
        this.canva = canva;
        this.gLabel = canva.gLabel;
    }

    public Button getStartButton()
    {
        return  StartButton;
    }
}
