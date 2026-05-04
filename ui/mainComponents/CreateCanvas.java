package ui.mainComponents;

import gamelogic.clickToggle;
import gamelogic.gameoflifeLogic;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Timer;
import java.util.TimerTask;
import ui.ControlPanel.PanelComponents.generationsLabel;
import ui.ControlPanel.PanelComponents.graphCanva;

public class CreateCanvas extends Canvas {

    private final int Blocks;
    private double Chance;

    private int rule1;
    private int rule2;
    private int rule3;

    private int generations = 0;
    private int alive = 0;
    private int dead = 0;

    private graphCanva gCanva;


    // TURNS the entire canvas to an image for smoother rendering
    private Image offscreen;
    private Graphics offg;
    private Timer timer;



    private boolean running = false;
    private Boolean[][] grid;


    gameoflifeLogic logic = new gameoflifeLogic();
    public generationsLabel gLabel;
    public CreateCanvas(int Blocks, double Chance, clickToggle cToggle, Color c,int rule1,int rule2,int rule3,generationsLabel gLabel)
    {
        this.gLabel = gLabel;
        this.Blocks = Blocks;
        this.Chance = Chance;
        this.rule1 = rule1;
        this.rule2 = rule2;
        this.rule3 = rule3;
        this.setBackground(c);
        gridInit();
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e)
                {
                    CreateCanvas canva = CreateCanvas.this;
                    Boolean toggle = cToggle.toggleCell(e.getX(), e.getY(),canva);
                    int squared = (int)(Math.pow((double)CreateCanvas.this.get_Blocks(),2));

                    if(toggle)
                    {
                        if(alive > squared)
                        {
                            alive = squared;
                            dead = 0;
                            }
                        }
                    else
                    {
                        if(dead > squared)
                        {
                            dead = squared;
                            alive = 0;
                        }
                    }
                     if(gCanva!= null)
                    {
                        gCanva.repaint();
                    }
                    gLabel.getDeadLabel().setText(String.format("Dead: %d", dead));
                    gLabel.getAliveLabel().setText(String.format("Alive: %d", alive));

                }});

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e)
                {
                    CreateCanvas canva = CreateCanvas.this;
                    Boolean toggle = cToggle.toggleCell(e.getX(), e.getY(), canva);
                    int squared = (int)(Math.pow((double)canva.get_Blocks(),2));
                    
                    if(toggle)
                    {
                        if(alive > squared)
                        {
                            alive = squared;
                            dead = 0;
                        }
                    }
                    else
                    {
                        if(dead > squared)
                        {
                            dead = squared;
                            alive = 0;
                        }
                    }
                    if(gCanva!= null)
                    {
                        gCanva.repaint();
                    }
                    gLabel.getDeadLabel().setText(String.format("Dead: %d", dead));
                    gLabel.getAliveLabel().setText(String.format("Alive: %d", alive));
                }
                });
        
        
    }

 
    public void setChance(double Chance)
    {
        this.Chance = Chance;
    }

    public boolean get_Running()
    {
        return this.running;
    }

    public void set_Running(boolean Running)
    {
        this.running = Running;
    }

    public int get_Blocks()
    {
        return this.Blocks;
    }

    public Boolean[][] get_grid()
    {
        return grid;
    }
       public void setR1(int Rule)
    {
        this.rule1 = Rule;
    }

       public void setR2(int Rule)
    {
        this.rule2 = Rule;
    }

       public void setR3(int Rule)
    {
        this.rule3 = Rule;
    }

    public int getR1()
    {
        return this.rule1;
    }
        public int getR2()
    {
        return this.rule2;
    }

        public int getR3()
    {
        return this.rule3;
    }

    public int getAlive()
    {
        return this.alive;
    }

    
    public int getGenerations()
    {
        return this.generations;
    }

    public int getDead()
    {
        return this.dead;
    }

    public void setGenerationtoZero()
    {
        this.generations = 0;
    }

    public void setAlive(int Alive)
    {
        this.alive = Alive;
    }
    
    public void setDead(int Dead)
    {
        this.dead = Dead;
    }

    public void setGraph(graphCanva gCanva)
    {
        this.gCanva = gCanva;
    }
    
    public final void gridInit()
    {
        

        grid = new Boolean[Blocks][Blocks];
        for (int i = 0; i < Blocks; i++)
        {
            for (int j = 0; j < Blocks; j++)
            {
                grid[i][j] = (int) (Math.random()*100) <= Chance;
                if(grid[i][j])
                {
                    this.alive++;
                }
                else
                {
                    this.dead++;
                }
            }
        }
        gLabel.getAliveLabel().setText(String.format("Alive: %d",alive));
        gLabel.getDeadLabel().setText(String.format("Dead: %d", dead));
    }

    @Override
    public void update(Graphics g)
    {
        paint(g);
    }

    @Override
    public void paint(Graphics g)
    {
        if (offscreen == null)
        {
            offscreen = createImage(getWidth(), getHeight());
            offg = offscreen.getGraphics();
        }



        offg.setColor(getBackground());
        offg.fillRect(0, 0, getWidth(), getHeight());

        int size = Math.min(getWidth(), getHeight()) / Blocks;
        int gridWidth = size * Blocks;
        int gridHeight = size * Blocks;
        int startX = (getWidth() - gridWidth) / 2;
        int startY = (getHeight() - gridHeight) / 2;
        this.alive = 0;
        this.dead = 0;
        for (int i = 0; i < Blocks; i++)
        {
            for (int j = 0; j < Blocks; j++)
            {
                if (grid[i][j])
                {
                    this.alive++;
                    offg.setColor(Color.WHITE);
                }
                else
                {
                    this.dead++;
                    offg.setColor(Color.BLACK);
                }
                offg.fillRect(startX + j * size, startY + i * size, size, size);
                if(i == Blocks/2 && j == Blocks/2)
                {
                    offg.setColor(Color.green);
                    offg.drawRect(startX + j*size,startY+ i*size , size, size);
                }
            }
        }

        g.drawImage(offscreen, 0, 0, this);
    }

    public void Start()
    {
        if (running)
        {
            if (timer != null)
            {
                timer.cancel();
                timer = null;
            }
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask()
            {
                @Override
                public void run()
                {
                    CreateCanvas canva = CreateCanvas.this;
                    graphCanva graphCanva = canva.gCanva;
                    if(graphCanva != null)
                    {
                        graphCanva.pushAliveinList(alive);


                        graphCanva.repaint();
                        
                    }
                    logic.updateGrid(canva,canva.getR1(),canva.getR2(),canva.getR3());
                    generations++;
                    gLabel.getGnerationsLabel().setText(String.format("Generations: %d", generations));
                    gLabel.getAliveLabel().setText(String.format("Alive: %d",alive));
                    gLabel.getDeadLabel().setText(String.format("Dead: %d", dead));

                    repaint();
                    
                }
            }, 0, 100);
        }
        else
        {
            if (timer != null)
            {
                timer.cancel();
                timer = null;
            }
        }
    }

    public void killAllCell()
    {
        this.dead = 0;
        for (int i = 0; i < Blocks; i++)
        {
            for (int j = 0; j < Blocks; j++)
            {
                grid[i][j] = false;
                this.dead++;

            }
        }
        repaint();
    }
}
