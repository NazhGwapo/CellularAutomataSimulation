package ui.ControlPanel.PanelComponents;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ui.mainComponents.mainFrame;



public class AliveGraph 
{

        private graphCanva graphCanvas;
        private Boolean graphRunning;
        private Dialog dialog;
    public void showGraph(mainFrame parent)
    {
       dialog = new Dialog(parent, "Graph", false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int)(screenSize.width * 0.4);
        int height = (int)(screenSize.height * 0.4);

        dialog.setSize(width, height);
        dialog.setLayout(new BorderLayout());

        graphCanvas = new graphCanva();
        
        
        dialog.add(graphCanvas, BorderLayout.CENTER);
        graphRunning = true;
        Button closeBtn = new Button("Close");
        closeBtn.addActionListener(e -> 
            {   

                dialog.dispose();
                dialog = null;
                graphRunning = false;
                
            }
        );

        
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                dialog.dispose();
                dialog = null;
                graphRunning = false;
            }
        });
        dialog.add(closeBtn, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

  
    public graphCanva getgraphCanva()
    {
        return this.graphCanvas;
    }

    public boolean  getRunning()
    {
        return graphRunning;
    }
}
