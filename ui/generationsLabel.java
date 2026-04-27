package ui;

import java.awt.*;

public class generationsLabel extends Panel  {

    private Label aliveCount;
    private Label deadCount;
    private Label generationsCount;


    public generationsLabel() {
        this.setLayout(new FlowLayout());
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
    
    
    public void conwayGenerationLabel(int generations,int alive,int dead)
    {
        generationsCount = new Label(String.format("Generations: %d", generations));
        generationsCount.setFont(new Font("Arial",Font.BOLD,12));
        aliveCount = new Label(String.format("Alive: %d", alive));
        aliveCount.setFont(new Font("Arial",Font.BOLD,12));
        deadCount = new Label(String.format("Dead: %d", generations));
        deadCount.setFont(new Font("Arial",Font.BOLD,12));
        this.add(generationsCount);
        this.add(aliveCount);
        this.add(deadCount);


    }


}
