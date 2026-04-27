package gamelogic;

import ui.CreateCanvas;

public class gameoflifeLogic {
    
public int countNeighbors(int x,int y,int Blocks,Boolean[][] grid)
{   
    int neighbors = 0;
    for(int i = -1; i <= 1;i++)
    {
        for(int j = -1; j<=1;j++)
        {
            if(i == 0 && j == 0)
            {
                continue;
            }
            if(grid[(x+i+Blocks)%Blocks][(y+j+Blocks)%Blocks])
            {
                neighbors++;
            }
        }
    }
    return neighbors;
}

public void updateGrid(CreateCanvas canva)
{
int Blocks = canva.get_Blocks();
Boolean[][] grid = canva.get_grid();
Boolean nextgrid[][]= new Boolean[Blocks][Blocks];
// int randomCount = Math.round((float)(Math.random() * 2))+ 1;
// int randomCount2 = Math.round((float)(Math.random() * 1)) + 1;
// int randomCount3 = Math.round((float)(Math.random() * 2.5))+ 1;

for(int i = 0; i< Blocks;i++)
{
    for(int j = 0;j < Blocks;j++)
    {
        int neighbors = countNeighbors(i, j,Blocks,grid);
        if(grid[i][j])
        {
            nextgrid[i][j] = (neighbors == 2|| neighbors == 3);
        }
        else
        {
            nextgrid[i][j] = (neighbors == 3);
        }
    }
}

//COPY THE nextgrid memory to grid memory
for(int i = 0; i< Blocks;i++)
{
    System.arraycopy(nextgrid[i], 0, grid[i], 0, Blocks);
}
}

}
