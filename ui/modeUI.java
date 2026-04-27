package ui;

import java.awt.*;

public class modeUI extends Panel {

    private String modeType;
    private Label chancesText;
    private Label blocksText;
    private TextField chancesField;
    private TextField blocksField;


    public modeUI()
    {
        this.setLayout(new FlowLayout());
    }

    public void ConwayLabel(String modeType)
    {
        chancesText = new Label("Chances(0-100)");
        chancesText.setFont(new Font("Arial",Font.BOLD,15));
        chancesField = new TextField("50"); 
        blocksText = new Label("Dimension (2-500)");
        blocksText.setFont(new Font("Arial",Font.BOLD,15));
        blocksField = new TextField("100"); 


        this.modeType = modeType;
        this.add(chancesText);
        this.add(chancesField);
        this.add(blocksText);
        this.add(blocksField);
    }

    public void ElementaryCellularLabel(choosePanel cPanel, String modeType)
    {
        this.modeType = modeType;
    }

    public String getModeType() {
        return modeType;
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
