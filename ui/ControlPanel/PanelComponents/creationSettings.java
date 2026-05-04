package ui.ControlPanel.PanelComponents;

import java.awt.*;

public class creationSettings extends Panel {

    private final Label chancesText;
    private final Label blocksText;
    private final TextField chancesField;
    private final TextField blocksField;


    public creationSettings()
    {
        this.setLayout(new FlowLayout());
         chancesText = new Label("Chances(0-100)");
        chancesText.setFont(new Font("Arial",Font.BOLD,15));
        chancesField = new TextField("50"); 
        blocksText = new Label("Dimension (2-500)");
        blocksText.setFont(new Font("Arial",Font.BOLD,15));
        blocksField = new TextField("100"); 


        this.add(chancesText);
        this.add(chancesField);
        this.add(blocksText);
        this.add(blocksField);
    }


   
 

    public TextField getchancesField()
    {
        return chancesField;
    }
    
    public Label getchancesText()
    {
        return chancesText;
    }

    public TextField getblocksField()
    {
        return blocksField;
    }

    public Label getblocksText()
    {
        return blocksText;
    }

}
