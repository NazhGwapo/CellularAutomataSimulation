package ui.ControlPanel.PanelComponents;

import java.awt.*;



public class ruleFields extends Panel 
{
    private Label textRule1;
    private Label textRule2;
    private Label textRule3;
    private TextField ruleField1;
    private TextField ruleField2;
    private TextField ruleField3;


    public ruleFields() 
    {
    this.setLayout(new FlowLayout());
    }

    public void conwayField()
    {

        textRule1 = new Label("Rule 1(1-5)");
        textRule1.setFont(new Font("Arial",Font.BOLD,12));
        textRule2 = new Label("Rule 2(1-5)");
        textRule2.setFont(new Font("Arial",Font.BOLD,12));
        textRule3 = new Label("Rule 3(1-5)");
        textRule3.setFont(new Font("Arial",Font.BOLD,12));
        ruleField1 = new TextField("2");
        ruleField2 = new TextField("3");
        ruleField3 = new TextField("3");

        this.add(textRule1);
        this.add(ruleField1);
        this.add(textRule2);
        this.add(ruleField2);
        this.add(textRule3);
        this.add(ruleField3);

    }

    public TextField getrField1()
    {
        return ruleField1;
    }


    public TextField getrField2()
    {
        return ruleField2;
    }
    
    public TextField getrField3()
    {
        return ruleField3;
    }
    
    
    public Label getr1Text()
    {
        return textRule1;
    }

    public Label getr2Text()
    {
        return textRule2;
    }
    public Label getr3Text()
    {
        return textRule2;
    }


}
