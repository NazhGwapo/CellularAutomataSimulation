package ui;

import java.awt.*;



public class AliveGraph 
{

        private graphCanva graphCanvas;

    public void showGraph(mainFrame parent)
    {
       Dialog dialog = new Dialog(parent, "Graph", false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int)(screenSize.width * 0.4);
        int height = (int)(screenSize.height * 0.4);

        dialog.setSize(width, height);
        dialog.setLayout(new BorderLayout());

        graphCanvas = new graphCanva();
        
        
        dialog.add(graphCanvas, BorderLayout.CENTER);

        Button closeBtn = new Button("Close");
        closeBtn.addActionListener(e -> dialog.dispose());
        
        dialog.add(closeBtn, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

  
    public graphCanva getgraphCanva()
    {
        return this.graphCanvas;
    }
}
