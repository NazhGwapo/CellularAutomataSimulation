package ui;

import gamelogic.clickToggle;
import java.awt.*;

public class buttonPanels extends Panel {

    private CreateCanvas canva;
    private Button StartButton;
    private Button ToggleButton;
    private Button KillAllButton;

    private generationsLabel gLabel;


    public buttonPanels(clickToggle cToggle)
    {
        this.setLayout(new GridLayout(3, 1));
        ToggleButton = new Button("Toggle");
        StartButton  = new Button("Start");
        KillAllButton = new Button("Kill All?");

        StartButton.setBackground(Color.gray);
        StartButton.setFont(new Font("Arial", Font.BOLD, 15));
        ToggleButton.setFont(new Font("Arial", Font.BOLD, 15));
        KillAllButton.setFont(new Font("Arial", Font.BOLD, 15));

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
            gLabel.getAliveLabel().setText(String.format("alive: %d",0));
            gLabel.getDeadLabel().setText(String.format("dead: %d", canva.getDead()));
        });

        this.add(ToggleButton);
        this.add(StartButton);
        this.add(KillAllButton);
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
