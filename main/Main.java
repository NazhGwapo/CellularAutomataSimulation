package main;


import java.awt.*;
import ui.mainScreen.Screen;


public class Main
{
    public static void main(String args[])
    {
        Screen window = new Screen();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.CreateScreen("Conway's game of life", screenSize.width,screenSize.height);        
    }
}