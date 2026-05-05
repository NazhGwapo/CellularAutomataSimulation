package ui.ControlPanel.PanelComponents;
 
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
 
public class graphCanva extends Canvas {
 
    private final List<Integer> alivelist = new ArrayList<>();
 
    private Image offscreen;
    private Graphics offg;
 
    public void pushAliveinList(int alive) {
        this.alivelist.add(alive);
    }
 



    @Override
    public void update(Graphics g) {
        paint(g);
    }
 
    @Override
    public void paint(Graphics g) {
        int w = getWidth();
        int h = getHeight();
 
        if (offscreen == null || offscreen.getWidth(this) != w || offscreen.getHeight(this) != h) {
            offscreen = createImage(w, h);
            offg = offscreen.getGraphics();
        }
 
        offg.setColor(Color.BLACK);
        offg.fillRect(0, 0, w, h);
 
        if (alivelist.isEmpty()) {
            g.drawImage(offscreen, 0, 0, this);
            return;
        }
 
        int marginLeft = 50;
        int marginRight = 20;
        int marginTop = 20;
        int marginBottom = 40;
 
        int plotW = w - marginLeft - marginRight;
        int plotH = h - marginTop - marginBottom;
 
        int maxAlive = 1;
        for (int val : alivelist) {
            if (val > maxAlive) 
                maxAlive = val;
        }
 
        int totalGens = alivelist.size();
 
        int ySteps = 5;
        for (int i = 0; i <= ySteps; i++) {
            int yVal = (int) ((double) maxAlive * i / ySteps);
            int yPixel = marginTop + plotH - (int) ((double) plotH * i / ySteps);
            offg.setColor(new Color(60, 60, 60));
            offg.drawLine(marginLeft, yPixel, marginLeft + plotW, yPixel);
 
            offg.setColor(Color.LIGHT_GRAY);
            offg.drawString(String.valueOf(yVal), 2, yPixel + 4);
        }
 
        offg.setColor(Color.WHITE);
        offg.drawLine(marginLeft, marginTop, marginLeft, marginTop + plotH);
        offg.drawLine(marginLeft, marginTop + plotH, marginLeft + plotW, marginTop + plotH);
 
        offg.setColor(Color.LIGHT_GRAY);
        offg.drawString("Alive", 2, marginTop + plotH / 2);
        offg.drawString("Generation", marginLeft + plotW / 2 - 25, h - 5);
 
        int xTicks = Math.min(5, totalGens);
        for (int i = 0; i <= xTicks; i++) {
            int gen = (int) ((double) (totalGens - 1) * i / xTicks);
            int xPixel = marginLeft + (totalGens > 1 ? (int) ((double) plotW * gen / (totalGens - 1)) : 0);
            offg.drawString(String.valueOf(gen), xPixel - 5, marginTop + plotH + 14);
        }
 
        offg.setColor(new Color(0, 220, 120));
        for (int i = 1; i < totalGens; i++) {
            int x1 = marginLeft + (int) ((double) plotW * (i - 1) / Math.max(totalGens - 1, 1));
            int y1 = marginTop + plotH - (int) ((double) plotH * alivelist.get(i - 1) / maxAlive);
            int x2 = marginLeft + (int) ((double) plotW * i / Math.max(totalGens - 1, 1));
            int y2 = marginTop + plotH - (int) ((double) plotH * alivelist.get(i) / maxAlive);
            offg.drawLine(x1, y1, x2, y2);
        }
 
        int lastX = marginLeft + plotW;
        int lastY = marginTop + plotH - (int) ((double) plotH * alivelist.get(totalGens - 1) / maxAlive);
        offg.setColor(Color.WHITE);
        offg.fillOval(lastX - 3, lastY - 3, 6, 6);
 
        g.drawImage(offscreen, 0, 0, this);
    }
}