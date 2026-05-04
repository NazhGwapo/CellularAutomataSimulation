package ui.ControlPanel.PanelComponents;

import java.awt.*;

public class generationsLabel extends Panel  {

    private final Label aliveCount;
    private final Label deadCount;
    private final Label generationsCount;


    public generationsLabel(int generations,int alive,int dead) {
        this.setLayout(new FlowLayout());
        
        generationsCount = new Label(String.format("Generations: %d", generations));
        generationsCount.setFont(new Font("Arial",Font.BOLD,12));
        aliveCount = new Label(String.format("Alive: %d", alive));
        aliveCount.setFont(new Font("Arial",Font.BOLD,12));
        deadCount = new Label(String.format("Dead: %d", dead));
        deadCount.setFont(new Font("Arial",Font.BOLD,12));
        this.add(generationsCount);
        this.add(aliveCount);
        this.add(deadCount);

    }

    public Label getGnerationsLabel()
    {
        return this.generationsCount;
    }
    
    public Label getAliveLabel()
    {
        return this.aliveCount;
    }
    
    public Label getDeadLabel()
    {
        return this.deadCount;
    }
    
    
    


}
