package gamelogic;


import ui.CreateCanvas;

public class clickToggle
{

private boolean Toggle = true;

public Boolean get_Toggle()
{
    return this.Toggle;
}


public void set_Toggle(Boolean toggle)
{
    this.Toggle = toggle ;
}
private int getCellSize(CreateCanvas canva)
{
    return Math.min(canva.getWidth(), canva.getHeight()) / canva.get_Blocks();
}

public void toggleCell(int x, int y,CreateCanvas canva)
{
    int size = getCellSize(canva);
    

    int startX = (canva.getWidth() - size * canva.get_Blocks()) / 2;
    int startY = (canva.getHeight() - size * canva.get_Blocks()) / 2;
    Boolean[][] grid = canva.get_grid();
    int col = (x - startX) / size;
    int row = (y - startY) / size;

    if (row >= 0 && row < canva.get_Blocks() && col >= 0 && col < canva.get_Blocks())
    {
        if(!grid[row][col] && Toggle)
        {
            grid[row][col] = Toggle;
        }
        else if(grid[row][col] && Toggle == false) 
        {
            grid[row][col] = Toggle;
        }
        
        canva.repaint();
    }
}

}