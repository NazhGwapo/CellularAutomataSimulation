package main;


import java.awt.*;
import ui.Screen;


public class Main
{
    public static void main(String args[])
    {
        Screen window = new Screen();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.CreateScreen("Game of Life", screenSize.width,screenSize.height);        
    }
}