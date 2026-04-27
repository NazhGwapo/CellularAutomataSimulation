package ui;

import gamelogic.clickToggle;
import gamelogic.gameoflifeLogic;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Timer;
import java.util.TimerTask;

public class CreateCanvas extends Canvas {

    private final int Blocks;
    private double Chance;

    // TURNS the entire canvas to an image for smoother rendering
    private Image offscreen;
    private Graphics offg;
    private Timer timer;

    private boolean running = false;
    private Boolean[][] grid;

    gameoflifeLogic logic = new gameoflifeLogic();

    public CreateCanvas(int Blocks, double Chance, clickToggle cToggle, Color c)
    {
        this.Blocks = Blocks;
        this.Chance = (Chance >= 0 && Chance <= 100) ? Chance / 100.0 : 0.5;
        this.setBackground(c);
        gridInit();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                cToggle.toggleCell(e.getX(), e.getY(), CreateCanvas.this);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e)
            {
                cToggle.toggleCell(e.getX(), e.getY(), CreateCanvas.this);
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

    public final void gridInit()
    {
        if (Chance < 0 || Chance > 1)
            Chance = 0.5;

        grid = new Boolean[Blocks][Blocks];
        for (int i = 0; i < Blocks; i++)
        {
            for (int j = 0; j < Blocks; j++)
            {
                grid[i][j] = Math.random() <= Chance;
            }
        }
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

        for (int i = 0; i < Blocks; i++)
        {
            for (int j = 0; j < Blocks; j++)
            {
                if (grid[i][j])
                    offg.setColor(Color.WHITE);
                else
                    offg.setColor(Color.BLACK);

                offg.fillRect(startX + j * size, startY + i * size, size, size);
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
                    logic.updateGrid(CreateCanvas.this);
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
        for (int i = 0; i < Blocks; i++)
        {
            for (int j = 0; j < Blocks; j++)
            {
                grid[i][j] = false;
            }
        }
        repaint();
    }
}
